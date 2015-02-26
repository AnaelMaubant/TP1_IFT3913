import java.util.HashMap;
import java.util.Vector;


public class ParsedFile {
	
	ParsedFile()
	{
		_classes = new HashMap<String, UMLClass>();
		_associations = new HashMap<String, UMLAssociation>();
		_aggregations = new HashMap<String, UMLAggregation>();
		_generalizations = new HashMap<String, UMLGeneralization>();
		
	}
	
	void AddModel(Model model)
	{
		_model = model;		
	}
	
	void AddClass(UMLClass umlClass)
	{
		_classes.put(umlClass.toString(),umlClass);
	}
	
	void AddAssociation(UMLAssociation association)
	{
		_associations.put(association._name, association);
	}
	
	void AddAggregation(UMLAggregation aggregation)
	{
		_aggregations.put(aggregation._container._className, aggregation);
	}
	
	void AddGeneralization(UMLGeneralization generalization)
	{
		_generalizations.put(generalization.toString(), generalization);
	}
	
	
	
	
	Model _model;
	HashMap<String, UMLClass> _classes;
	HashMap<String, UMLAssociation> _associations;
	HashMap<String, UMLAggregation> _aggregations;
	HashMap<String, UMLGeneralization> _generalizations;

}

