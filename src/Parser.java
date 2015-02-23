import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;


public class Parser {
	
	Parser(String filePath)
	{
		parsedFile = new ParsedFile();
		try 
		{
			scanner = new Scanner(new File(filePath));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Working Directory = " +
		              System.getProperty("user.dir"));
			
		}	
	}
	
	void ParseFile()
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
				scanner.next();
			}
			else if (scanner.hasNext(Pattern.compile("GENERALIZATION")))
			{
				parsedFile.AddGeneralization(ParseGeneralization());
				scanner.next();
			}
			else if(scanner.hasNext(Pattern.compile("RELATION")))
			{
				parsedFile.AddAssociation(ParseAssociation());
				scanner.next();
			}
			else if (scanner.hasNext(Pattern.compile("AGGREGATION")))
			{
				parsedFile.AddAggregation(ParseAggregation());
				scanner.next();
			}
			
		}
		while(scanner.hasNext(Pattern.compile("CLASS")))
		{
			parsedFile.AddClass(ParseClass());
			scanner.next();
		}
	}
	
	Model ParseModel()
	{
		scanner.next();
		String modelName = scanner.next();
		return new Model(modelName);
	}
	
	UMLClass ParseClass()
	{
		scanner.next();
		String className = scanner.next();
	//	System.out.println(className);
		UMLClass umlClass= new UMLClass(className);
		umlClass.AddAttributes(ParseClassAttributes());
		umlClass.AddOperations(ParseClassOperations());
		
		return umlClass;
	
		
	}

	
	Vector<UMLAttribute> ParseClassAttributes()
	{
		Vector<UMLAttribute> attributes = new Vector<UMLAttribute>();
		scanner.next();
		while(!scanner.hasNext(Pattern.compile("OPERATIONS")))
		{
			String attributeName = scanner.next();
		//	System.out.println(attributeName);
			scanner.next();
			String attributeType = scanner.next();
			if(attributeType.charAt(attributeType.length()-1) == ',')
			{
				attributeType = attributeType.substring(0, attributeType.length()-1);
			//	System.out.println(attributeType);
			}
		//	System.out.println(attributes);
			attributes.addElement(new UMLAttribute(attributeName, attributeType));
		}		
		return attributes;
	}
	
	Vector<UMLOperation> ParseClassOperations()
	{
		Vector<UMLOperation> operations = new Vector<UMLOperation>();
		String test = scanner.next();
		scanner.skip(Pattern.compile("\\s+"));
		while(!scanner.hasNext(Pattern.compile(";")))
		{
			String operationLine = scanner.nextLine();
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
			
			Vector<UMLAttribute> attributes = ParseOperationAttributes(operationLine.substring(attributesStartingIndex +1, attributesEndingIndex));
			
			UMLOperation operation = new UMLOperation(operationName, returnType);
			operation.AddAttributes(attributes);
			
			operations.addElement(operation);			
		}		
		return operations;
	}
	
	Vector<UMLAttribute> ParseOperationAttributes(String attributeLine)
	{
		Vector<UMLAttribute> attributes = new Vector<UMLAttribute>();
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
			attributes.addElement(new UMLAttribute(attributeName, attributeType));			
		}		
		lineScanner.close();
		System.out.println(attributes);
		return attributes;
	}
	
	UMLAssociation ParseAssociation()
	{
		scanner.next();
		String associationName = scanner.next();
		scanner.next();
		scanner.next();
		String firstRole = scanner.next();
		String firstRoleMultiplicity = scanner.next();
		scanner.next();
		String secondRole = scanner.next();
		String secondRoleMultiplicity = scanner.next();
		
		return new UMLAssociation(associationName, new UMLRole(firstRole, firstRoleMultiplicity), new UMLRole(secondRole, secondRoleMultiplicity));
		
	}
	
	UMLAggregation ParseAggregation()
	{
		Vector<UMLRole> parts = new Vector<UMLRole>();
		
		scanner.next();
		scanner.next();
		scanner.next();
		String containerName = scanner.next();
		String containerMultiplicity = scanner.next();
		scanner.next();
		while(scanner.hasNext(Pattern.compile("CLASS")))
		{
			scanner.next();
			String partName = scanner.next();
			
			String partMultiplicity = scanner.next();
			
			if(partMultiplicity.charAt(partMultiplicity.length()-1) == ',')
			{
				partMultiplicity = partMultiplicity.substring(0, partMultiplicity.length()-1);
			}
			parts.addElement(new UMLRole(partName, partMultiplicity));
		}

		return new UMLAggregation(new UMLRole(containerName, containerMultiplicity), parts);		
	}
	
	UMLGeneralization ParseGeneralization()
	{
		Vector<String> subClasses = new Vector<String>();
		scanner.next();
		String generalizationName = scanner.next();
		scanner.next();
		while(!scanner.hasNext(Pattern.compile(";")))
		{
			String subClass = scanner.next();
			
			if(subClass.charAt(subClass.length()-1) == ',')
			{
				subClass = subClass.substring(0, subClass.length()-1);
			}
			subClasses.addElement(subClass);
			
		}
		
		return new UMLGeneralization(generalizationName, subClasses);
		
	}


	 Scanner scanner ;
	 ParsedFile parsedFile;
	 
	int currentIndex =0;
	
	
	
	 
	
	
 	
}
