import java.util.HashMap;
import java.util.Map.Entry;


public class MetricsCalculator {
	
	/*    
     *     Les Differentes metriques a calculer
     */
    
    //1. ANA(ci) : Nombre moyen d’arguments des méthodes locales pour la classe ci.
    public static float ANA(HashMap<String, UMLOperation> hash)
    {
    	float argumentsTotal = 0;
    	float methodNumber = 0;

        for(Entry<String, UMLOperation> entry : hash.entrySet())
        {
        	argumentsTotal += entry.getValue()._attributes.size();
        	methodNumber += 1;
        }
        
        float mean = argumentsTotal/methodNumber;
             
		return mean;
             
    }
    
    //2. NOM(ci) : Nombre de méthodes locales/héritées de la classe ci.
    public static int NOM(HashMap<String, UMLOperation> hash)
    {
    	return hash.size();
    }
    
    //3. NOA(ci) : Nombre d’attributs locaux/hérités de la classe ci
    public static int NOA(HashMap<String, UMLAttribute> hash)
    {
		return hash.size();
    }
    
    //4. ITC(ci) : Nombre de fois où d’autres classes du diagramme apparaissent comme types des arguments des méthodes de ci.
    public static float ITC(ParsedFile parsedFile, HashMap<String, UMLOperation> hash)
    {
    	int compteur =0;
    	for(Entry<String, UMLOperation> entry : hash.entrySet())
    	{
    		for(Entry<String, UMLAttribute> argumentEntry : entry.getValue()._attributes.entrySet())
    		{
    			if(parsedFile._classes.containsKey(argumentEntry.getValue()._type))
    			{
    				compteur++;
    			}
    		}
    	}
    	return compteur;
    }
    
    //5. ETC(ci) : Nombre de fois où ci apparaît comme type des arguments dans les méthodes des autres classes du diagramme.
    public static float ETC(ParsedFile parsedFile, String className)
    {
    	int compteur =0;
    	for(Entry<String, UMLClass> entryClass : parsedFile._classes.entrySet())
    	{
    		for(Entry<String, UMLOperation> entryMethod : entryClass.getValue()._operations.entrySet())
    		{
    			for(Entry<String, UMLAttribute> argumentEntry : entryMethod.getValue()._attributes.entrySet())
    			{
    				if(argumentEntry.getValue()._type.equals(className))
    				{
    					compteur++;
    				}
    			}
    		}
    	}
    	return compteur;
    }
    
    //6 CAC(ci) : Nombre d’associations (incluant les agrégations) locales/héritées auxquelles participe une classe ci.
    public static float CAC(ParsedFile parsedFile, String className)
    {
    	int compteur =0;
    	for(Entry<String, UMLAggregation> entry : parsedFile._aggregations.entrySet())
    	{
    		if(entry.getValue()._container._className.equals(className))
    		{
    			compteur ++;
    		}
    		else
    		{
    			for(Entry<String, UMLRole> entryRole : entry.getValue()._parts.entrySet())
    			{
    				if(entryRole.getValue()._className.equals(className))
    				{
    					compteur ++;
    				}
    			}
    		}
    	}
    	
    	for(Entry<String, UMLAssociation> entry : parsedFile._associations.entrySet())
    	{
    		if(entry.getValue()._firstRole._className.equals(className) || entry.getValue()._secondRole._className.equals(className))
    		{
    			compteur++;
    		}
    	}    	
    	return compteur;
    }
    
    //7 Taille du chemin le plus long reliant une classe ci à une classe racine dans le graphe d’héritage.
    public static float DIT(ParsedFile parsedFile, String className)
    {
    	int compteur =0;
    	String currentClassName = GetParentClass(parsedFile, className);
    	while(!currentClassName.equals("No parent class"))
    	{
    		compteur ++;    		
    		currentClassName = GetParentClass(parsedFile, currentClassName);
    	}
    	
    	return compteur;
    }
    
    //8  CLD(ci) : Taille du chemin le plus long reliant une classe ci à une classe feuille dans le graphe d’héritage.
    public static float CLD(ParsedFile parsedFile, String className)
    {
    	int compteur =0;
    	compteur = GetChildrenCLD(parsedFile, className);
    	
    	return compteur;    	
    }
    
    //9 NOC(ci) : Nombre de sous-classes directes de ci.
    public static float NOC(ParsedFile parsedFile, String className)
    {
    	int compteur=0;
    	
    	if(parsedFile._generalizations.containsKey(className))
    	{
    		compteur = parsedFile._generalizations.get(className)._subClasses.size();
    	}
    	
    	return compteur;
    }
    
    //10 NOD(ci) : Nombre de sous-classes directes et indirectes de ci.
    public static float NOD(ParsedFile parsedFile, String className)
    {
    	int compteur =0;
    	compteur = GetChildrenNOD(parsedFile, className);
    	
    	return compteur;
    }
    
    public static String GetParentClass(ParsedFile parsedFile, String className)
    {
    	for(Entry<String, UMLGeneralization> entry : parsedFile._generalizations.entrySet())
    	{
    		for(Entry<String, String> entrySubClasses : entry.getValue()._subClasses.entrySet())
    		{
    			if(entrySubClasses.getValue().equals(className))
    			{
    				return entry.getValue()._generalizationName;
    			}
    		}
    	}    	
    	return "No parent class";
    }
    
    public static int GetChildrenCLD(ParsedFile parsedFile, String className)
    {
    	int compteur =0;
    	
    	if(!parsedFile._generalizations.containsKey(className))
    	{
    		return compteur;
    	}
    	else
    	{
    		for(Entry<String, String> entry :  parsedFile._generalizations.get(className)._subClasses.entrySet())
    		{
    			int childrenCLD = GetChildrenCLD(parsedFile, entry.getValue());
    			if(compteur < childrenCLD)
    			{
    				compteur = childrenCLD;
    			}    				
    		}
    	}
  	
    	compteur ++;
    	return compteur;
    }
    
	
	public static int GetChildrenNOD(ParsedFile parsedFile, String className)
	{
    	int compteur =0;
    	
    	if(!parsedFile._generalizations.containsKey(className))
    	{
    		return compteur;
    	}
    	else
    	{
    		compteur = parsedFile._generalizations.get(className)._subClasses.size();
    		
    		for(Entry<String, String> entry :  parsedFile._generalizations.get(className)._subClasses.entrySet())
    		{
    			int childrenCLD = GetChildrenNOD(parsedFile, entry.getValue());
    			compteur += childrenCLD;
    		}
    	}
    	return compteur;
	}
	
	public static String CreateMetricsMatrix(ParsedFile parsedFile)
	{
		String metricsMatrix = "";
		for(Entry<String, UMLClass> entry : parsedFile._classes.entrySet())
		{
			metricsMatrix += CreateMetricsLine(parsedFile, entry.getValue());
		}
		return metricsMatrix;
	}
	
	private static String CreateMetricsLine(ParsedFile parsedFile, UMLClass umlClass)
	{
		String metricsLine = umlClass._name + " ";
		
		metricsLine += MetricsCalculator.ANA(umlClass._operations) + " ,";
		metricsLine += MetricsCalculator.NOM(umlClass._operations) + " ,";
		metricsLine += MetricsCalculator.NOA(umlClass._attributes) + " ,";
		metricsLine += MetricsCalculator.ITC(parsedFile ,umlClass._operations) + " ,";
		metricsLine += MetricsCalculator.ETC(parsedFile ,umlClass._name) + " ,";
		metricsLine += MetricsCalculator.CAC(parsedFile ,umlClass._name) + " ,";
		metricsLine += MetricsCalculator.DIT(parsedFile ,umlClass._name) + " ,";
		metricsLine += MetricsCalculator.CLD(parsedFile ,umlClass._name) + " ,";
		metricsLine += MetricsCalculator.NOC(parsedFile ,umlClass._name) + " ,";
		metricsLine += MetricsCalculator.NOD(parsedFile ,umlClass._name) + "\n";
		return metricsLine;
	}

}
