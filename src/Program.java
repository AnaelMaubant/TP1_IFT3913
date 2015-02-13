import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Program {

 

    public static void main(String s[]) {
    	
    	Parser parser = new Parser("test.txt");
    	
    	parser.ParseFile();
     
        JFrame jFrame=new JFrame("Programme Parseur");
        JPanel jPanelNorth=new JPanel();
		JPanel jPanelClasses=new JPanel();
		JPanel jPanelCenter=new JPanel();
		JPanel jPanelDetails=new JPanel();
 
	
        jFrame.getContentPane().setLayout(new BorderLayout(10,10));
		jFrame.getContentPane().add(jPanelNorth,BorderLayout.NORTH);
		jFrame.getContentPane().add(jPanelClasses,BorderLayout.WEST);
		jFrame.getContentPane().add(jPanelCenter,BorderLayout.CENTER);
		jFrame.getContentPane().add(jPanelDetails,BorderLayout.SOUTH);



        jPanelNorth.setLayout(new FlowLayout());
        JTextField textfield=new JTextField(35);
        JButton button = new JButton();
        button.setText("Charger Fichier");
        jPanelNorth.add(button);
        jPanelNorth.add(textfield);
 


        jPanelClasses.setLayout(new BoxLayout(jPanelClasses, BoxLayout.Y_AXIS));
        jPanelClasses.setPreferredSize(new Dimension(200,200));
        JLabel labelClasse = new JLabel("Classes");
        JTextArea textClasse=new JTextArea();
        jPanelClasses.add(labelClasse);
        jPanelClasses.add(textClasse);
        
        
        
        jPanelCenter.setLayout(new GridLayout(2,2,5,5));
        
        JPanel jPanelAttributs=new JPanel();
        jPanelAttributs.setLayout(new BoxLayout(jPanelAttributs, BoxLayout.Y_AXIS));   
        JLabel labelAttr = new JLabel("Attributs");
        JTextArea textAttr=new JTextArea();
        jPanelAttributs.add(labelAttr);
        jPanelAttributs.add(textAttr);
        
        JPanel jPanelMethodes=new JPanel();
        jPanelMethodes.setLayout(new BoxLayout(jPanelMethodes, BoxLayout.Y_AXIS));
        JLabel labelMeth = new JLabel("Méthodes");
        JTextArea textMeth=new JTextArea();
        jPanelMethodes.add(labelMeth);
        jPanelMethodes.add(textMeth);
        
        JPanel jPanelSousClasses=new JPanel();
        jPanelSousClasses.setLayout(new BoxLayout(jPanelSousClasses, BoxLayout.Y_AXIS));
        JLabel labelSClas = new JLabel("Sous-Classes");
        JTextArea textSClas=new JTextArea();
        jPanelSousClasses.add(labelSClas);
        jPanelSousClasses.add(textSClas);
        
        JPanel jPanelAssoc=new JPanel();
        jPanelAssoc.setLayout(new BoxLayout(jPanelAssoc, BoxLayout.Y_AXIS));
        JLabel labelAssoc = new JLabel("Associations/Aggrégations");
        JTextArea textAssoc=new JTextArea();
        jPanelAssoc.add(labelAssoc);
        jPanelAssoc.add(textAssoc);
        
         
            
        
      
        
        jPanelCenter.add(jPanelAttributs);
        jPanelCenter.add(jPanelMethodes);
        jPanelCenter.add(jPanelSousClasses);
        jPanelCenter.add(jPanelAssoc);
        
        jPanelDetails.setLayout(new BoxLayout(jPanelDetails, BoxLayout.Y_AXIS));
        jPanelDetails.setPreferredSize(new Dimension(10,100));
        JLabel labelDetails = new JLabel("Détails");
        JTextArea textDetails=new JTextArea();
        jPanelDetails.add(labelDetails);
        jPanelDetails.add(textDetails);
       
        
        jFrame.setSize(640,480);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 

    }

 

}
