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

 

      //  JFrame frame = new JFrame("JFrame Example");
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
		JPanel jPanel2=new JPanel();
		JPanel jPanel3=new JPanel();
		JPanel jPanel4=new JPanel();
 
		//jPanel.setBackground(Color.GREEN);
		//jPanel2.setBackground(Color.YELLOW);
		//jPanel3.setBackground(Color.RED);
		//jPanel4.setBackground(Color.BLUE);
 
	//	jFrame.getContentPane().setLayout(new GridLayout(1,2));
        jFrame.getContentPane().setLayout(new BorderLayout(10,10));
		jFrame.getContentPane().add(jPanel,BorderLayout.NORTH);
		jFrame.getContentPane().add(jPanel2,BorderLayout.WEST);
		jFrame.getContentPane().add(jPanel3,BorderLayout.CENTER);
		jFrame.getContentPane().add(jPanel4,BorderLayout.SOUTH);


        JPanel panel = new JPanel();

        jPanel.setLayout(new FlowLayout());
      //  JLabel label = new JLabel("This is a label!");
        JTextField textfield=new JTextField(12);
         JButton button = new JButton();
        button.setText("Charger Fchier");

 

       // panel.add(label);

        jPanel.add(button);
        jPanel.add(textfield);
 

      //  JPanel panel1 = new JPanel();

        jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.Y_AXIS));
        jPanel2.setPreferredSize(new Dimension(200,200));
        JLabel label = new JLabel("Classes");
        JTextArea textarea=new JTextArea();
         

 

       // panel.add(label);

        jPanel2.add(label);
        jPanel2.add(textarea);
        
        jPanel3.setLayout(new GridLayout(2,2,5,5));
        
        JPanel jPanel5=new JPanel();
        jPanel5.setLayout(new BoxLayout(jPanel5, BoxLayout.Y_AXIS));   
        JLabel label1 = new JLabel("Attributs");
        JTextArea textarea1=new JTextArea();
       // JTextField textarea1=new JTextField();
        jPanel5.add(label1);
        jPanel5.add(textarea1);
        
        JPanel jPanel6=new JPanel();
        jPanel6.setLayout(new BoxLayout(jPanel6, BoxLayout.Y_AXIS));
        JLabel label2 = new JLabel("Methodes");
        JTextArea textarea2=new JTextArea();
        jPanel6.add(label2);
        jPanel6.add(textarea2);
        
        JPanel jPanel7=new JPanel();
        jPanel7.setLayout(new BoxLayout(jPanel7, BoxLayout.Y_AXIS));
        JLabel label3 = new JLabel("Sous-Classes");
        JTextArea textarea3=new JTextArea();
        jPanel7.add(label3);
        jPanel7.add(textarea3);
        
        JPanel jPanel8=new JPanel();
        jPanel8.setLayout(new BoxLayout(jPanel8, BoxLayout.Y_AXIS));
        JLabel label4 = new JLabel("Associations/Aggregations");
        JTextArea textarea4=new JTextArea();
        jPanel8.add(label4);
        jPanel8.add(textarea4);
        
        //label1.setHorizontalAlignment(SwingConstants.CENTER);
       
        //label2.setHorizontalAlignment(SwingConstants.CENTER);
        
      //  label3.setHorizontalAlignment(SwingConstants.CENTER);
        
      //  label4.setHorizontalAlignment(SwingConstants.CENTER);
        
       
       
       
        
      //  jPanel3.add(label1);
       // jPanel3.add(label2);
      //  jPanel3.add(textarea1);
        //jPanel3.add(textarea2);
        //jPanel3.add(label3);
        //jPanel3.add(label4);
        //jPanel3.add(textarea3);
        //jPanel3.add(textarea4);
        
        jPanel3.add(jPanel5);
        jPanel3.add(jPanel6);
        jPanel3.add(jPanel7);
        jPanel3.add(jPanel8);
        
        jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.Y_AXIS));
        jPanel4.setPreferredSize(new Dimension(10,100));
        JLabel label5 = new JLabel("Details");
        JTextArea textarea5=new JTextArea();
        
        jPanel4.add(label5);
        jPanel4.add(textarea5);
       /* 
        frame.add(panel);
        frame.add(panel1);

        frame.setSize(300, 300);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       frame.setVisible(true);*/
        
        jFrame.setSize(640,480);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 

    }

 

}
