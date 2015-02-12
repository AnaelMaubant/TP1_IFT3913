import java.util.Vector;


public class ParsedFile {
	
	ParsedFile()
	{
		classes = new Vector<UMLClass>();
	}
	
	void AddModel(Model model)
	{
		_model = model;		
	}
	
	void AddClass(UMLClass umlClass)
	{
		classes.addElement(umlClass);
	}
	
	Model _model;
	Vector<UMLClass> classes;

}

