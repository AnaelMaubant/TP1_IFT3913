import java.util.Vector;


public class ParsedFile {
	
	ParsedFile()
	{
		_classes = new Vector<UMLClass>();
		_associations = new Vector<UMLAssociation>();
		_aggregations = new Vector<UMLAggregation>();
		_generalizations = new Vector<UMLGeneralization>();
		
	}
	
	void AddModel(Model model)
	{
		_model = model;		
	}
	
	void AddClass(UMLClass umlClass)
	{
		_classes.addElement(umlClass);
	}
	
	void AddAssociation(UMLAssociation association)
	{
		_associations.addElement(association);
	}
	
	void AddAggregation(UMLAggregation aggregation)
	{
		_aggregations.addElement(aggregation);
	}
	
	void AddGeneralization(UMLGeneralization generalization)
	{
		_generalizations.addElement(generalization);
	}
	
	
	
	
	Model _model;
	Vector<UMLClass> _classes;
	Vector<UMLAssociation> _associations;
	Vector<UMLAggregation> _aggregations;
	Vector<UMLGeneralization> _generalizations;

}

