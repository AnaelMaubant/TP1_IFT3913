import java.util.Vector;


public class UMLAggregation {

	UMLAggregation(UMLRole container, Vector<UMLRole> parts)
	{
		_container = container;
		_parts = parts;
	}
	
	UMLRole _container;
	Vector<UMLRole> _parts;
}
