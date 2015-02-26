import java.util.HashMap;
import java.util.Vector;


public class UMLGeneralization {
	
	UMLGeneralization(String name, HashMap<String, String> subclasses)
	{
		_generalizationName = name;
		_subClasses = subclasses;
		
	}
	
	public String toString()
	{
		return _generalizationName;
	}
	String _generalizationName;
	HashMap<String, String> _subClasses;
	

}
