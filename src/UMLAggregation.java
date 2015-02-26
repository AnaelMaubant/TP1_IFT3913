import java.util.HashMap;
import java.util.Vector;


public class UMLAggregation {

	UMLAggregation(UMLRole container, HashMap<String, UMLRole> parts)
	{
		_container = container;
		_parts = parts;
	}
	
	UMLRole _container;
	HashMap<String, UMLRole> _parts;
}
