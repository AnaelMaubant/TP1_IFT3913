
public class UMLAggregationListObject {
	

		public UMLAggregationListObject(String key, UMLAggregation aggregation, UMLAssociation association)
		{
			_key = key;
			_aggregation = aggregation;
			_association = association;
		}
		
		public String toString()
		{
			return _key;
		}
		
		String _key;
		UMLAggregation _aggregation;
		UMLAssociation _association;
	


}
