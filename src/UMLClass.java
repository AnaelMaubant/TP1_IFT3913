import java.util.Vector;


public class UMLClass {
	UMLClass(String className)
	{
		_name = className;
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
	
	/*static UMLClass getName(String nom)
	{
		 for (int i=0, max = liste.size(); i<max; i++)
	            if (liste.get(i).equals(nom))
	                return liste.get(i);
	        return null;  
	}*/
	
	
	
	Vector<UMLAttribute> _attributes;
	Vector<UMLOperation> _operations;
	String _name;
	
}
