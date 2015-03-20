
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
	
	public String toType()
	{
		return _type;
	}
	String _name;
	String _type;
}
