import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;





public class Program {

 

    public static void main(String s[]) {
    	
    //	Fenetre iface = new Fenetre();
    	 
        
    	Parser parser = new Parser("test.txt");
       	parser.ParseFile();
   //  System.out.println(  	parser.parsedFile._classes.elementAt(2)._name);
    // System.out.println(parser.ParseClass()._name);
       	
    //   	ParsedFile parsed = new ParsedFile();
      // 	System.out.println(parsed._classes.elements());
       	
     
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


		JFileChooser fileChooser = new JFileChooser("src/");
        jPanelNorth.setLayout(new FlowLayout());
        JTextField textfield=new JTextField(35);
        JButton button = new JButton();
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	 File file;
                if(fileChooser.showOpenDialog(null) ==JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                    
                    textfield.setText(file.getName());
               //     Parser parser = new Parser(file.getName());
                //   	parser.ParseFile();
               
                }} 
        });
        button.setText("Charger Fichier");
        jPanelNorth.add(button);
        jPanelNorth.add(textfield);
 


        jPanelClasses.setLayout(new BoxLayout(jPanelClasses, BoxLayout.Y_AXIS));
        jPanelClasses.setPreferredSize(new Dimension(200,200));
        JLabel labelClasse = new JLabel("Classes");
        JTextArea textClasse=new JTextArea();
        jPanelClasses.add(labelClasse);
        jPanelClasses.add(textClasse);
       
        for(int i=0; i<parser.parsedFile._classes.size(); i++){
        //	textClasse.setText(parser.parsedFile._classes.elementAt(i)._name);
        //	textClasse.insert(parser.parsedFile._classes.elementAt(i)._name,i);
        	textClasse.append(parser.parsedFile._classes.elementAt(i)._name + "\n");
        	
        	 textClasse.addMouseListener(new MouseListener(){
        		 
                

				@Override
				public void mouseClicked(MouseEvent e) {
				
			            
			            if(e.getClickCount() == 2){
			            System.out.print("Do you want to know about:\n\t" + textClasse.getSelectedText());
			            	
			            	}
			    
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
			//		 if (textClasse.getSelectedText() != null){ //see if they selected something 
				//	        String s = textClasse.getSelectedText();
					//        s.substring(1,2);
					        //do work with String s
				//	        }
				} 
             });
        }
      
       
        
        jPanelCenter.setLayout(new GridLayout(2,2,5,5));
        
        JPanel jPanelAttributs=new JPanel();
        jPanelAttributs.setLayout(new BoxLayout(jPanelAttributs, BoxLayout.Y_AXIS));   
        JLabel labelAttr = new JLabel("Attributs");
        JTextArea textAttr=new JTextArea();
        jPanelAttributs.add(labelAttr);
        jPanelAttributs.add(textAttr);
      //  for(int i=0; i <parser.ParseClassAttributes().size();i++){
        //  textAttr.append(parser.ParseClassAttributes().elementAt(3)._name+"\n");
      //  System.out.println(parser.ParseClassAttributes().elementAt(2)._name);
       //   textAttr.append(parser.ParseClassAttributes().elementAt(0)._name);
    //    }
      
        
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
        for(int i=0; i <parser.parsedFile._associations.size();i++){
               textAssoc.append(parser.parsedFile._associations.elementAt(i)._firstRole + parser.parsedFile._associations.elementAt(i)._name  +"\n");
             }
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

