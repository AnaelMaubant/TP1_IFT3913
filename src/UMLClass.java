import java.util.Vector;


public class UMLClass {
	UMLClass(String name)
	{
		_name = name;
		_attributes = new Vector<UMLAttribute>();
		_operations = new Vector<UMLOperation>();
	}
	
	void AddAttributes(Vector<UMLAttribute> attributes)
	{
		_attributes = attributes;
	}
	
	void AddOperations(Vector<UMLOperation> operations)
	{
		_operations = operations;
	}
	
	String getName()
	{
		return _name;
	}
	
	Vector<UMLAttribute> _attributes;
	Vector<UMLOperation> _operations;
	String _name;

}
