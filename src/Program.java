import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListCellRenderer;





public class Program {

    public static void main(String s[])
    {
        mainFrame=new JFrame("Programme Parseur");
        mainFrame.getContentPane().setLayout(new BorderLayout(10,10));
        mainFrame.setSize(640,480);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
        
        jPanelTop=new JPanel();
        
		jPanelCenter=new JPanel();
		mainFrame.getContentPane().add(jPanelCenter,BorderLayout.CENTER);
		jPanelCenter.setLayout(new GridLayout(2,2,5,5));
		
		final JFileChooser fileChooser = new JFileChooser("src/"); 
		
		CreateTopPanel(mainFrame, jPanelTop, fileChooser);
		CreateClassPanel(mainFrame);
		CreateAttributesPanel(mainFrame, jPanelCenter);
		CreateMethodsPanel(mainFrame, jPanelCenter);
		CreateSubClassesPanel(mainFrame, jPanelCenter);
		CreateAggregationsPanel(mainFrame, jPanelCenter);
		CreateMetriquesPanel(mainFrame);
		CreateDetailsPanel(mainFrame);
		CreateListeners();
		
		 
		
        mainFrame.setVisible(true);

    }
    
    public static void CreateClassPanel(final JFrame mainFrame)
    {
    	JPanel jPanelClasses=new JPanel();
        jPanelClasses.setLayout(new BorderLayout());
        
        final JLabel labelClasse = new JLabel("Classes");
  
        final DefaultListModel<UMLClass> classesModel = new DefaultListModel<UMLClass>();
        
        classesList = new JList<UMLClass>(classesModel);

        jPanelClasses.add(labelClasse, BorderLayout.NORTH);
        jPanelClasses.add(classesList, BorderLayout.CENTER);        

        mainFrame.getContentPane().add(jPanelClasses,BorderLayout.WEST);
    }
    
    public static void CreateMethodsPanel(JFrame mainFrame, JPanel mainPanel)
    {
        JPanel jPanelMethodes=new JPanel();
        jPanelMethodes.setLayout(new BorderLayout());
        JLabel labelMeth = new JLabel("Méthodes");
        
        final DefaultListModel<UMLOperation> operationsModel = new DefaultListModel<UMLOperation>();
        operationsList = new JList<UMLOperation>(operationsModel);
        
        jPanelMethodes.add(labelMeth, BorderLayout.NORTH);
        jPanelMethodes.add(operationsList, BorderLayout.CENTER);        
        
        mainPanel.add(jPanelMethodes);
    }
    
    public static void CreateSubClassesPanel(JFrame mainFrame, JPanel mainPanel)
    {
        JPanel jPanelSousClasses=new JPanel();
        jPanelSousClasses.setLayout(new BorderLayout());
        JLabel labelSClas = new JLabel("Sous-Classes");
        
        final DefaultListModel<String> subClassesModel = new DefaultListModel<String>();
        subClassesList = new JList<String>(subClassesModel);
        
        jPanelSousClasses.add(labelSClas, BorderLayout.NORTH);
        jPanelSousClasses.add(subClassesList, BorderLayout.CENTER); 
        
        mainPanel.add(jPanelSousClasses);
    }
    
    public static void CreateAttributesPanel(JFrame mainFrame, JPanel mainPanel)
    {
        JPanel jPanelAttributs=new JPanel();
        jPanelAttributs.setLayout(new BorderLayout());
        JLabel labelAttr = new JLabel("Attributs");
        
        final DefaultListModel<UMLAttribute> attributesModel = new DefaultListModel<UMLAttribute>();
        attributesList = new JList<UMLAttribute>(attributesModel);
        
        jPanelAttributs.add(labelAttr, BorderLayout.NORTH);
        jPanelAttributs.add(attributesList, BorderLayout.CENTER); 
        
        mainPanel.add(jPanelAttributs);
    }
    
    public static void CreateAggregationsPanel(JFrame mainFrame, JPanel mainPanel)
    {
        JPanel jPanelAssoc=new JPanel();
        jPanelAssoc.setLayout(new BorderLayout());
        JLabel labelAssoc = new JLabel("Associations/Aggrégations");
        
        final DefaultListModel<UMLAggregationListObject> aggregationsModel = new DefaultListModel<UMLAggregationListObject>();
        aggregationsList = new JList<UMLAggregationListObject>(aggregationsModel);
        
        jPanelAssoc.add(labelAssoc, BorderLayout.NORTH);
        jPanelAssoc.add(aggregationsList, BorderLayout.CENTER); 
        
        mainPanel.add(jPanelAssoc);
    }
    
    public static void CreateMetriquesPanel(JFrame mainFrame)
    {
    	JPanel jPanelMetriques=new JPanel();
        jPanelMetriques.setLayout(new BoxLayout(jPanelMetriques, BoxLayout.Y_AXIS));
        jPanelMetriques.setPreferredSize(new Dimension(100,10));
        JLabel labelMetriques = new JLabel("Metriques");
        textMetriques=new JTextArea();
       // textMetriques.append("ANA =" + "\n" + "NOM =" + "\n" + "NOA =" + x+ "\n" + "ITC =" + "\n"+ "ETC =" + "\n" +"CAC =" + "\n" + "DIT =" + "\n" +"CLD =" + "\n" + "NOC =" + "\n" +"NOD =" + "\n");
        
        jPanelMetriques.add(labelMetriques);
        jPanelMetriques.add(textMetriques);
        System.out.println("x"+x);
        mainFrame.getContentPane().add(jPanelMetriques,BorderLayout.EAST);
    }
    
    
    
    public static void CreateDetailsPanel(JFrame mainFrame)
    {
		JPanel jPanelDetails=new JPanel();
        jPanelDetails.setLayout(new BoxLayout(jPanelDetails, BoxLayout.Y_AXIS));
        jPanelDetails.setPreferredSize(new Dimension(10,100));
        JLabel labelDetails = new JLabel("Détails");
        textDetails=new JTextArea();
        jPanelDetails.add(labelDetails);
        jPanelDetails.add(textDetails);
        mainFrame.getContentPane().add(jPanelDetails,BorderLayout.SOUTH);
    }

    public static void CreateTopPanel(JFrame mainFrame,JPanel topPanel, final JFileChooser fileChooser)
    {
    	
    	mainFrame.getContentPane().add(topPanel,BorderLayout.NORTH);
    	topPanel.setLayout(new FlowLayout());
    	
        final JTextField textfield=new JTextField(30);
        final JButton button = new JButton();
        final JButton button1=new JButton();
        
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	 File file;
                if(fileChooser.showOpenDialog(null) ==JFileChooser.APPROVE_OPTION)
                {
                    file = fileChooser.getSelectedFile();
                    textfield.setText(file.getName());
                	parser = new Parser(file.getName());
                   	parser.ParseFile();
                   	ClearLists();
                   	FillClassList(parser.parsedFile._classes);
                }    
            } 
        });
        
        button1.addActionListener(new ActionListener()
        {
        	 public void actionPerformed(ActionEvent e)
             {
        		 
             }
        });
        
        button.setText("Charger Fichier");
        button1.setText("Calculer métriques");
        topPanel.add(button);
        topPanel.add(textfield);
        topPanel.add(button1);
    }
    
    public static void CreateListeners()
    {
    	classesList.addListSelectionListener(new ListSelectionListener(){
    		@Override
    		public void valueChanged(ListSelectionEvent arg0)
    		{
    			if(classesList.getSelectedValue() != null && classesList.getValueIsAdjusting())
    			{
        			FillMethodList(classesList.getSelectedValue()._operations);
        			FillAttributesList(classesList.getSelectedValue()._attributes);
        			 x=NOA(classesList.getSelectedValue()._attributes);
        			 System.out.println("X1="+x);
        			FillSubClassesList(parser.parsedFile._generalizations);
        			FillAggregationsList(parser.parsedFile._aggregations, parser.parsedFile._associations);
        			CreateMetriquesDetails(classesList.getSelectedValue());
    			}
    		}
    	});
    	
    	
    	attributesList.addListSelectionListener(new ListSelectionListener(){
    		@Override
    		public void valueChanged(ListSelectionEvent arg0)
    		{
    			
    		}
    	});
    	
    	
    	aggregationsList.addListSelectionListener(new ListSelectionListener(){
    		@Override
    		public void valueChanged(ListSelectionEvent arg0)
    		{
    			if(aggregationsList.getSelectedValue() != null && aggregationsList.getValueIsAdjusting())
    			{
    				CreateAggregationDetails(aggregationsList.getSelectedValue());
    			}    			
    		}
    	});
    	
    	operationsList.addListSelectionListener(new ListSelectionListener(){
    		@Override
    		public void valueChanged(ListSelectionEvent arg0)
    		{
    			
    		}
    	});
    	
    }
    
    public static void FillClassList(HashMap<String, UMLClass> hash)
    {
        final DefaultListModel<UMLClass> classesModel = new DefaultListModel<UMLClass>();
        for(Entry<String, UMLClass> entry : hash.entrySet())
        {
        	classesModel.addElement(entry.getValue());	
        }
             
        classesList.setModel(classesModel);
    	
    }
    
    public static void FillMethodList(HashMap<String, UMLOperation> hash)
    {
    	float y=0;
    	final DefaultListModel<UMLOperation> operationsModel = new DefaultListModel<UMLOperation>();
        for(Entry<String, UMLOperation> entry : hash.entrySet())
        {
        	operationsModel.addElement(entry.getValue());	
        }
             
        operationsList.setModel(operationsModel);  
       // System.out.println("Modele methodes ="+operationsModel.getSize());
        //System.out.println("Arguments  ="+operationsModel.toString());
      //  System.out.println("Arguments de el ="+operationsModel.elementAt(2)._attributes.size());
        System.out.println("Arguments de listemethode  ="+operationsModel.getSize());
        //for(int i=0; i<operationsModel.getSize();i++){
     //   y=((float)operationsModel.elementAt(2)._attributes.size() / operationsModel.getSize());
     //   System.out.println( "y"+y );
        
    }
    
    public static float ANA(HashMap<String, UMLOperation> hash)
    {
    	float elements=0;
    	float compteur =0;
    	final DefaultListModel<UMLOperation> operationsModel = new DefaultListModel<UMLOperation>();
        for(Entry<String, UMLOperation> entry : hash.entrySet())
        {
        	operationsModel.addElement(entry.getValue());	
        }
             
        operationsList.setModel(operationsModel);  
        
         for(int i=0; i<operationsModel.getSize();i++)
         {
        	 if(operationsModel.getSize()!=0 ){
        	 
        	         	 elements+=operationsModel.elementAt(i)._attributes.size();
        	         	compteur=(float)(elements/operationsModel.getSize());
        	 }
        	 else {
        		compteur=operationsModel.getSize();
        	 }
        	
         }
		return compteur;
         
        
        
        
    }
    
    public static int NOM(HashMap<String, UMLOperation> hash)
    {
    	int compteur=0;
    	final DefaultListModel<UMLOperation> operationsModel1 = new DefaultListModel<UMLOperation>();
        for(Entry<String, UMLOperation> entry : hash.entrySet())
        {
        	operationsModel1.addElement(entry.getValue());	
        }
             
        operationsList.setModel(operationsModel1); 
        compteur+=operationsModel1.getSize();
        return compteur;
        
    }
    
    public static void FillSubClassesList(HashMap<String, UMLGeneralization> hash)
    {
    	final DefaultListModel<String> subClassesModel = new DefaultListModel<String>();
    	
    	if(parser.parsedFile._generalizations.containsKey(classesList.getSelectedValue().toString()))
		{
            for(Entry<String, String> entry : parser.parsedFile._generalizations.get(classesList.getSelectedValue().toString())._subClasses.entrySet())
            {
            	subClassesModel.addElement(entry.getValue());	
            }
		}
             
        subClassesList.setModel(subClassesModel);    	
    }
    
    public static void FillAttributesList(HashMap<String, UMLAttribute> hash)
    {
    	int compteur =0;
    	final DefaultListModel<UMLAttribute> attributesModel = new DefaultListModel<UMLAttribute>();
        for(Entry<String, UMLAttribute> entry : hash.entrySet())
        {
        	attributesModel.addElement(entry.getValue());
        	
        }
             
        attributesList.setModel(attributesModel);   
        System.out.println("Modele attribut ="+attributesModel.getSize());
       
    }
    
    public static int NOA(HashMap<String, UMLAttribute> hash)
    {
    	//FillAttributesList(classesList.getSelectedValue()._attributes);
    	int compteur =0;
    	final DefaultListModel<UMLAttribute> attributesModel1 = new DefaultListModel<UMLAttribute>();
        for(Entry<String, UMLAttribute> entry : classesList.getSelectedValue()._attributes.entrySet())
        {
        	attributesModel1.addElement(entry.getValue());
        	
        }
        attributesList.setModel(attributesModel1);
      //  System.out.println("Modele compteur1 =" +classesList.getSelectedValue()._attributes.size());
      //  attributesList.setModel(attributesModel1);   
        System.out.println("Modele compteur ="+attributesModel1.getSize());
        //System.out.println(parser.parsedFile._classes.size());
        compteur+=attributesModel1.getSize();
        System.out.println("Compteur = "+compteur);
		return compteur;
    
    
    }
    
    public static void FillAggregationsList(HashMap<String, UMLAggregation> aggregationsHash, HashMap<String, UMLAssociation> associationsHash)
    {
    	final DefaultListModel<UMLAggregationListObject> aggregationsModel = new DefaultListModel<UMLAggregationListObject>();
        for(Entry<String, UMLAggregation> entry : aggregationsHash.entrySet())
        {
        	if(entry.getValue()._parts.containsKey(classesList.getSelectedValue().toString()))
        	{
        		aggregationsModel.addElement(new UMLAggregationListObject("(A) C_" + entry.getValue()._container._className, entry.getValue(), null));            		
        	}
        	
        	if(entry.getValue()._container._className.equals(classesList.getSelectedValue().toString()))
        	{
        		for(Entry<String, UMLRole> UMLRoleEntry : entry.getValue()._parts.entrySet())
        		{
        			aggregationsModel.addElement(new UMLAggregationListObject("(A) P_" + UMLRoleEntry.getValue()._className, entry.getValue(), null));
        		}        		
        	}
        }
        
        for(Entry<String, UMLAssociation> entry : associationsHash.entrySet())
        {
        	if(entry.getValue()._firstRole._className.equals(classesList.getSelectedValue().toString()) ||
        			entry.getValue()._secondRole._className.equals(classesList.getSelectedValue().toString()))
        	{
        		aggregationsModel.addElement(new UMLAggregationListObject("(R) " + entry.getValue()._name, null, entry.getValue()));
        	}        	
        }
             
        aggregationsList.setModel(aggregationsModel);    	
    }
    
    public static void CreateAggregationDetails(UMLAggregationListObject aggregation)
    {
    	String detailsString = "";
    	if(aggregation._aggregation != null)
    	{
    		detailsString += "AGGREGATION\nCONTAINER\n\tCLASS ";
    		detailsString += aggregation._aggregation._container._className;
    		detailsString += aggregation._aggregation._container._multiplicity + "\n";
    		
    		detailsString += "PARTS\n";
    		
    		for(Entry<String, UMLRole> entry : aggregation._aggregation._parts.entrySet())
    		{
    			detailsString += "\tCLASS" + entry.getValue()._className + " " + entry.getValue()._multiplicity + "\n";
    		}
    	}
    	else
    	{
    		detailsString += "RELATION " + aggregation._association._name + "\n";
    		detailsString += "\tROLES\n\t";
    		detailsString += "CLASS " + aggregation._association._firstRole._className + " " + aggregation._association._firstRole._multiplicity + "\n\t";
    		detailsString += "CLASS " + aggregation._association._secondRole._className + " " + aggregation._association._secondRole._multiplicity;
    	}
    	textDetails.setText(detailsString);
    }
    
    public static void CreateMetriquesDetails(UMLClass umlClass)
    {
    	String detailsString = "";
    	if(UMLClass.class != null)
    	{
    		detailsString += "ANA = "+ANA(classesList.getSelectedValue()._operations)+"\n";
    		detailsString += "NOM = "+NOM(classesList.getSelectedValue()._operations)+"\n";
    		detailsString += "NOA = "+NOA(classesList.getSelectedValue()._attributes)+"\n";
    		
    	}
    	textMetriques.setText(detailsString);
    }
    
    public static void ClearLists()
    {
    	DefaultListModel<UMLClass> classesModel = (DefaultListModel<UMLClass>) classesList.getModel();
    	classesModel.removeAllElements();
    	
    	DefaultListModel<UMLOperation> operationsModel = (DefaultListModel<UMLOperation>) operationsList.getModel();
    	operationsModel.removeAllElements();
        
    	DefaultListModel<UMLAttribute> attributesModel = (DefaultListModel<UMLAttribute>) attributesList.getModel();
    	attributesModel.removeAllElements();
        
    	DefaultListModel<String> subClassesModel = (DefaultListModel<String>) subClassesList.getModel();
    	subClassesModel.removeAllElements();
        
    	DefaultListModel<UMLAggregationListObject> aggregationsModel = (DefaultListModel<UMLAggregationListObject>) aggregationsList.getModel();
    	aggregationsModel.removeAllElements();    
    	
    	textDetails.setText("");
    }
   
    
    public static Parser parser;
    public static JPanel jPanelCenter;
    public static JList<UMLClass> classesList;
    public static JList<UMLOperation> operationsList;
    public static JList<UMLAttribute> attributesList;
    public static JList<String> subClassesList;
    public static JList<UMLAggregationListObject> aggregationsList;
    public static JList metriquesList;
    public static JTextArea textDetails;
    public static JTextArea textMetriques;
    public static JPanel jPanelTop;
    public static JFrame mainFrame;
    public static int x;
   
 
    }

