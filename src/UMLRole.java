
public class UMLRole {
	
	UMLRole (String className, String multiplicity)
	{
		_className = className;
		_multiplicity = multiplicity;
	}
	
	public String toString()
	{
		return _className;
	}
	
	String _className;
	String _multiplicity;

}
