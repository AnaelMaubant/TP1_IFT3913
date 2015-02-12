import java.util.Vector;


public class UMLGeneralization {
	
	UMLGeneralization(String name, Vector<String> subclasses)
	{
		_generalizationName = name;
		_subClasses = subclasses;
		
	}
	
	String _generalizationName;
	Vector<String> _subClasses;
	

}
