import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Parser {
	
	Parser(String filePath)
	{
		parsedFile = new ParsedFile();
		try 
		{
			byte[] encoded = Files.readAllBytes(Paths.get(filePath));
			String fileContent = new String(encoded, Charset.defaultCharset());
			fileContent = PreprocessFile(fileContent);
			scanner = new Scanner(fileContent);
			isCorrupted = false;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Working Directory = " +
		              System.getProperty("user.dir"));
			isCorrupted = true;
			
		}
		catch(IOException ex)
		{
			isCorrupted = true;
		}
	}

	String PreprocessFile(String fileContent)
	{
		fileContent = fileContent.replace(";", "\n;\n");
		fileContent = fileContent.replace(",", ",\n");
		fileContent = fileContent.replace(":", " : ");
		return fileContent;
	}
	
	void ParseFile() throws BadGrammarException
	{
		try
		{
			parsedFile.AddModel(ParseModel());
			while(scanner.hasNext())
			{
				if(scanner.hasNext(Pattern.compile("MODEL")))
				{
					parsedFile.AddModel(ParseModel());
				}
				else if (scanner.hasNext(Pattern.compile("CLASS")))
				{
					parsedFile.AddClass(ParseClass());
					BadGrammarException.Validate(scanner.next(), ";", "; keyword missing");
				}
				else if (scanner.hasNext(Pattern.compile("GENERALIZATION")))
				{
					parsedFile.AddGeneralization(ParseGeneralization());
					BadGrammarException.Validate(scanner.next(), ";", "; keyword missing");
				}
				else if(scanner.hasNext(Pattern.compile("RELATION")))
				{
					parsedFile.AddAssociation(ParseAssociation());
					BadGrammarException.Validate(scanner.next(), ";", "; keyword missing");
				}
				else if (scanner.hasNext(Pattern.compile("AGGREGATION")))
				{
					parsedFile.AddAggregation(ParseAggregation());
					BadGrammarException.Validate(scanner.next(), ";", "; keyword missing");
				}
				else
				{
					String invalidString = scanner.next();
					BadGrammarException.Validate(invalidString, "", "Invalid keyword : " + invalidString);
				}			
			}
		}
		catch(Exception e)
		{
			throw new BadGrammarException(e.getMessage());
		}
	}
	
	Model ParseModel()
	{
		scanner.next();
		String modelName = scanner.next();
		return new Model(modelName);
	}
	
	UMLClass ParseClass() throws BadGrammarException
	{
		scanner.next();
		String className = scanner.next();
		UMLClass umlClass= new UMLClass(className);
		try
		{
			umlClass.AddAttributes(ParseClassAttributes());
			umlClass.AddOperations(ParseClassOperations());
		}
		catch(Exception e)
		{
			throw new BadGrammarException("Not able to parse Class : "+ className+ " : " + e.getMessage());
		}
		
		return umlClass;
	
		
	}

	
	HashMap<String, UMLAttribute> ParseClassAttributes() throws BadGrammarException
	{

		HashMap<String, UMLAttribute> attributes = new HashMap<String, UMLAttribute>();
		try
		{
			BadGrammarException.Validate(scanner.next(), "ATTRIBUTES", "ATTRIBUTES keyword missing");

			while(!scanner.hasNext(Pattern.compile("OPERATIONS")))
			{
				String attributeName = scanner.next();

				BadGrammarException.Validate(scanner.next(), ":", ": keyword missing near " + attributeName);

				String attributeType = scanner.next();
				if(attributeType.charAt(attributeType.length()-1) == ',')
				{
					attributeType = attributeType.substring(0, attributeType.length()-1);
				}
				attributes.put(attributeName, new UMLAttribute(attributeName, attributeType));
			}		
		}
		catch(Exception e)
		{
			throw new BadGrammarException("Not able to parse Attributes : " + e.getMessage());
		}
		return attributes;
	}
	
	HashMap<String, UMLOperation> ParseClassOperations() throws BadGrammarException
	{
		HashMap<String, UMLOperation> operations = new HashMap<String, UMLOperation>();
		
		try
		{
			BadGrammarException.Validate(scanner.next(), "OPERATIONS", "OPERATIONS keyword missing");

			scanner.skip(Pattern.compile("\\s+"));
			while(!scanner.hasNext(Pattern.compile(";")))
			{
				String operationLine = scanner.nextLine();
				while(operationLine.equals("\n") || operationLine.equals(""))
				{
					operationLine = scanner.nextLine();
				}

				if(!operationLine.contains(")"))
				{
					operationLine += scanner.nextLine();
				}
				int attributesStartingIndex = operationLine.indexOf("(");
				int attributesEndingIndex = operationLine.indexOf(")");
				int returnTypeStartingIndex = operationLine.lastIndexOf(":");

				String operationName = operationLine.substring(0, attributesStartingIndex);
				operationName = operationName.replaceAll("\\s+","");

				String returnType;
				if(operationLine.charAt(operationLine.length()-1) == ',')
				{
					returnType = operationLine.substring(returnTypeStartingIndex +1, operationLine.length()-1);
				}
				else 
				{
					returnType = operationLine.substring(returnTypeStartingIndex +1, operationLine.length());
				}
				returnType = returnType.replaceAll("\\s+","");

				HashMap<String, UMLAttribute> attributes = ParseOperationAttributes(operationLine.substring(attributesStartingIndex +1, attributesEndingIndex));

				UMLOperation operation = new UMLOperation(operationName, returnType);
				operation.AddAttributes(attributes);

				operations.put(operationName, operation);			
			}
		}
		catch(Exception e)
		{
			throw new BadGrammarException("Not able to parse Operation : " + e.getMessage());
		}
		return operations;
	}
	
	HashMap<String, UMLAttribute> ParseOperationAttributes(String attributeLine) throws BadGrammarException
	{
		HashMap<String, UMLAttribute> attributes = new HashMap<String, UMLAttribute>();
		try
		{
			Scanner lineScanner = new Scanner(attributeLine);
			while(lineScanner.hasNext())
			{
				String attributeName = lineScanner.next();
				lineScanner.next();
				String attributeType = lineScanner.next();
				if(attributeType.charAt(attributeType.length()-1) == ',')
				{
					attributeType = attributeType.substring(0, attributeType.length()-1);
				}
				attributes.put(attributeName, new UMLAttribute(attributeName, attributeType));			
			}		
			lineScanner.close();
		}
		catch(Exception e)
		{
			throw new BadGrammarException("Not able to parse Operation attributes : " + e.getMessage());
		}
		return attributes;
	}
	
	UMLAssociation ParseAssociation() throws BadGrammarException
	{
		BadGrammarException.Validate(scanner.next(), "RELATION", "RELATION keyword missing");
		
		String associationName = scanner.next();
		
		BadGrammarException.Validate(scanner.next(), "ROLES", "ROLES keyword missing");
		
		BadGrammarException.Validate(scanner.next(), "CLASS", "CLASS keyword missing");
		
		String firstRole = scanner.next();
		String firstRoleMultiplicity = scanner.next();
		
		BadGrammarException.Validate(scanner.next(), "CLASS", "CLASS keyword missing");
		
		String secondRole = scanner.next();
		String secondRoleMultiplicity = scanner.next();
		
		return new UMLAssociation(associationName, new UMLRole(firstRole, firstRoleMultiplicity), new UMLRole(secondRole, secondRoleMultiplicity));
		
	}
	
	UMLAggregation ParseAggregation() throws BadGrammarException
	{
		HashMap<String, UMLRole> parts = new HashMap<String, UMLRole>();
		
		BadGrammarException.Validate(scanner.next(), "AGGREGATION", "AGGREGATION keyword missing");
		
		BadGrammarException.Validate(scanner.next(), "CONTAINER", "CONTAINER keyword missing");
		
		BadGrammarException.Validate(scanner.next(), "CLASS", "CLASS keyword missing");
		
		String containerName = scanner.next();
		String containerMultiplicity = scanner.next();
		
		BadGrammarException.Validate(scanner.next(), "PARTS", "PARTS keyword missing");
		
		while(scanner.hasNext(Pattern.compile("CLASS")))
		{
			BadGrammarException.Validate(scanner.next(), "CLASS", "CLASS keyword missing");
			
			String partName = scanner.next();
			
			String partMultiplicity = scanner.next();
			
			if(partMultiplicity.charAt(partMultiplicity.length()-1) == ',')
			{
				partMultiplicity = partMultiplicity.substring(0, partMultiplicity.length()-1);
			}
			parts.put(partName, new UMLRole(partName, partMultiplicity));
		}

		return new UMLAggregation(new UMLRole(containerName, containerMultiplicity), parts);		
	}
	
	UMLGeneralization ParseGeneralization() throws BadGrammarException
	{
		HashMap<String, String> subClasses = new HashMap<String, String>();
		
		BadGrammarException.Validate(scanner.next(), "GENERALIZATION", "GENERALIZATION keyword missing");
		
		String generalizationName = scanner.next();
		
		BadGrammarException.Validate(scanner.next(), "SUBCLASSES", "SUBCLASSES keyword missing");
		
		while(!scanner.hasNext(Pattern.compile(";")))
		{
			String subClass = scanner.next();
			
			if(subClass.charAt(subClass.length()-1) == ',')
			{
				subClass = subClass.substring(0, subClass.length()-1);
			}
			subClasses.put(subClass, subClass);
			
		}
		
		return new UMLGeneralization(generalizationName, subClasses);
		
	}


	 Scanner scanner ;
	 ParsedFile parsedFile;
	 Boolean isCorrupted;
	 
	int currentIndex =0;
	
	
	
	 
	
	
 	
}
