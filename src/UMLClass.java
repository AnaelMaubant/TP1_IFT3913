import java.util.HashMap;
import java.util.Vector;


public class UMLClass {
	UMLClass(String className)
	{
		_name = className;
		_attributes = new HashMap<String, UMLAttribute>();
		_operations = new HashMap<String, UMLOperation>();
	}
	
	void AddAttributes(HashMap<String, UMLAttribute> attributes)
	{
		_attributes = attributes;
	}
	
	void AddOperations(HashMap<String, UMLOperation> operations)
	{
		_operations = operations;
	}
	
	public String toString()
	{
        return _name;
	}
	
	
	
	HashMap<String, UMLAttribute> _attributes;
	HashMap<String, UMLOperation> _operations;
	String _name;
	
}
