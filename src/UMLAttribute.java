
public class UMLAttribute {

	UMLAttribute(String name, String type)
	{
		_name = name;
		_type = type;
	}
	
	public String toString()
	{
		return _type + " " + _name;
	}
	String _name;
	String _type;
}
