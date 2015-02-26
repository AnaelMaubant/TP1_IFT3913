
public class UMLAssociation {
	
	UMLAssociation(String name, UMLRole firstRole, UMLRole secondRole)
	{
		_firstRole = firstRole;
		_secondRole = secondRole;
		_name = name;
	}
	
	public String toString()
	{
		return _name;
	}
	UMLRole _firstRole;
	UMLRole _secondRole;
	String _name;
}
