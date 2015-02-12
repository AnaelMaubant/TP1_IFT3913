import java.util.Vector;


public class UMLOperation {

	UMLOperation(String operationName, String returnType)
	{
		_operationName = operationName;
		_returnType = returnType;
	}
	
	void AddAttributes(Vector<UMLAttribute> attributes)
	{
		_attributes = attributes;
	}
	
	String _operationName;
	String _returnType;
	Vector<UMLAttribute> _attributes;
}
