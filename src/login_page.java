import javax.swing.*; 
import java.awt.event.*;   
import java.awt.*;
import java.sql.*; 
import java.lang.Exception;  



public class login_page implements ActionListener{



    JFrame f = new JFrame("Canteen App");
    final JLabel L1,L2,L3,login,pass,img;
    final JTextField T1;
    final JPasswordField P1;
    final JButton B1;
    final JPanel login_panel;
    final JPanel panel = new JPanel();
	final Container cp = f.getContentPane();
    JRadioButton rb1, rb2, rb3;



    
login_page(){

    Image icon = Toolkit.getDefaultToolkit().getImage("D:\\canteen application\\logo reduced.png");    
    f.setIconImage(icon);
    f.setSize(1920,1080);
    f.setLayout(null);  
    f.setVisible(true);

    L1 = new JLabel(new ImageIcon("D:\\project\\logo reduced.png"));
    L1.setBounds(25,15, 100,145);  
    f.add(L1);

    img= new JLabel(new ImageIcon("D:\\project\\test_pic.jpg"));
    img.setBounds(800, 0, 960, 1080);
    f.add(img);

    L2 = new JLabel("Welcome to VES Canteen");
    L2.setBounds(130,72, 500, 36);
    L2.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 36));
    f.add(L2);

   

    L3 = new JLabel("Login as:");
    L3.setBounds(50, 220, 150, 28);
    L3.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
    f.add(L3);

    rb1=new JRadioButton("Admin");  
    rb1.setFont(new Font("Baskerville Old Face", Font.BOLD, 24)); 
    rb1.setBackground(new Color(235,216,202)); 
    rb1.setBounds(100,260,100,30); 

    rb2=new JRadioButton("Receptionist");
    rb2.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
    rb2.setBackground(new Color(235,216,202));
    rb2.setBounds(250,260,200,30); 

    rb3=new JRadioButton("Kitchen staff");   
    rb3.setFont(new Font("Baskerville Old Face", Font.BOLD, 24)); 
    rb3.setBackground(new Color(235,216,202));
    rb3.setBounds(450,260,200,30);    
    ButtonGroup bg=new ButtonGroup(); 
     
    bg.add(rb1);
    bg.add(rb2);  
    bg.add(rb3);  

    f.add(rb1);
    f.add(rb2);
    f.add(rb3);


    login = new JLabel("Login ID:");
    login.setBounds(290, 400, 150, 28);
    login.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f.add(login);

    T1= new JTextField();
    T1.setBounds(180,450,350,30);
    T1.setFont(new Font("Corbel", Font.PLAIN, 24));
    f.add(T1);

    pass = new JLabel("Password:");
    pass.setBounds(290, 500, 150, 28);
    pass.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f.add(pass);

    P1= new JPasswordField();
    P1.setBounds(180, 550, 350, 28);
    P1.setFont(new Font("Serif", Font.PLAIN, 24));
    f.add(P1);

    B1 = new JButton("Login");
    B1.setBounds(290,600,150,28);
    B1.addActionListener(this);  
    f.add(B1);

    login_panel = new JPanel();
    login_panel.setBounds(30,200,700,500);
    login_panel.setBackground(new Color(235,216,202));
    
   
    f.add(login_panel);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }  




 public void actionPerformed(ActionEvent e)  {
    String username = T1.getText();
    String password = new String(P1.getPassword());

        if(rb1.isSelected()){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("select login_id, password from staff where login_id=? and password=? and job_name = 'Admin';");
                st.setString(1, username);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    new admin_page();
                }
                else
                JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
        
                }catch (Exception ex) {
                    System.out.println(e);
                }
            }
        
    else if (rb2.isSelected()){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select login_id, password from staff where login_id=? and password=? and job_name = 'Receptionist';");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                f.setVisible(false);
                new reception_page();
            }
            else
            JOptionPane.showMessageDialog(null, "Username or Password mismatch ");

            }catch (Exception ex) {
                System.out.println(e);
            }
        }
    else if(rb3.isSelected()){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select login_id, password from staff where login_id=? and password=? and job_name = 'Kitchen staff';");
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                new kitchen_staff();
            }
            else
            JOptionPane.showMessageDialog(null, "Username or Password mismatch ");

            }catch (Exception ex) {
                System.out.println(e);
            }

    }

}

    
 



public static void main(String args[]){
    new login_page();
}

}