import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;


public class UMLOperation {

	UMLOperation(String operationName, String returnType)
	{
		_operationName = operationName;
		_returnType = returnType;
	}
	
	void AddAttributes(HashMap<String, UMLAttribute> attributes)
	{
		_attributes = attributes;
	}
	
	public String toString()
	{
		String operationString =_returnType + " " + _operationName + "(";
		int counter =1;
		for(Entry<String, UMLAttribute> entry : _attributes.entrySet())
		{
			operationString += entry.getValue()._type;
			if(counter != _attributes.size())
			{
				operationString += ",";
			}
			counter ++;
		}
		
		operationString +=")";
		
		return operationString;
	}
	
	String _operationName;
	String _returnType;
	HashMap<String, UMLAttribute> _attributes;
}
