
public class UMLAssociation {
	
	UMLAssociation(String name, UMLRole firstRole, UMLRole secondRole)
	{
		_firstRole = firstRole;
		_secondRole = secondRole;
		_name = name;
	}
	
	UMLRole _firstRole;
	UMLRole _secondRole;
	String _name;
}
