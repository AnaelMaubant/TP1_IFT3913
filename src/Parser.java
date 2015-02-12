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
		parsedFile.AddClass(ParseClass());
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
			scanner.next();
			String attributeType = scanner.next();
			
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
		return attributes;
	}

	
	 Scanner scanner ;
	 ParsedFile parsedFile;
	 
	int currentIndex =0;


}
