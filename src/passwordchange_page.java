import javax.swing.*; 
import java.awt.event.*;   
import java.awt.*;
import java.sql.*;

public class passwordchange_page implements ActionListener {
    JFrame changepassFrame;
    JPasswordField p1,p2,p3;
    JTextField uid;
    


    public passwordchange_page(){
        changepassFrame = new JFrame("Change Password");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\canteen application\\logo reduced.png");    
        changepassFrame.setIconImage(icon);
        changepassFrame.setSize(700,300);
        changepassFrame.setLayout(null); 

        JLabel userid = new JLabel("Enter user id:");
        userid.setBounds(50,30,300,25);
        userid.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));

        JLabel oldpass = new JLabel("Enter your old password");
        oldpass.setBounds(50,70,300,25);
        oldpass.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));

        JLabel newpass = new JLabel("Enter new password:");
        newpass.setBounds(50,110,300,25);
        newpass.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));

        JLabel confirmpass = new JLabel("Re-enter new password:");
        confirmpass.setBounds(50,150,300,25);
        confirmpass.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));

        uid = new JTextField();
        uid.setBounds(400, 30, 200, 25);
        uid.setFont(new Font("Serif", Font.PLAIN, 24));

        p1= new JPasswordField();
        p1.setBounds(400, 70, 200, 25);
        p1.setFont(new Font("Serif", Font.PLAIN, 24));

        p2= new JPasswordField();
        p2.setBounds(400, 110, 200, 25);
        p2.setFont(new Font("Serif", Font.PLAIN, 24));

        p3= new JPasswordField();
        p3.setBounds(400, 150, 200, 25);
        p3.setFont(new Font("Serif", Font.PLAIN, 24));
        
        JButton change = new JButton("Confirm change.");
        change.setBounds(300,200,150,25);
        change.addActionListener(this);
        changepassFrame.add(change);
        

        changepassFrame.add(userid);
        changepassFrame.add(uid);
        changepassFrame.add(oldpass);
        changepassFrame.add(newpass);
        changepassFrame.add(confirmpass);
        changepassFrame.add(p1);
        changepassFrame.add(p2);
        changepassFrame.add(p3);
        changepassFrame.setVisible(true);
        

    }


    public void actionPerformed(ActionEvent e){

        String oldpass = new String(p1.getPassword());
        String newpass = new String(p2.getPassword());
        String confirmpass = new String(p3.getPassword());
        
        if(newpass.equals(confirmpass)){
            int a = JOptionPane.showConfirmDialog(null,"Confirm password change?");
            if(a== JOptionPane.YES_OPTION){

            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select password from staff where password=? and job_name = 'Receptionist';");
            st.setString(1, oldpass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                try{
                    
                    PreparedStatement st1 = (PreparedStatement) connection.prepareStatement("UPDATE staff SET password=? WHERE password=? and job_name= 'Receptionist';");
                    st1.setString(1,confirmpass);
                    st1.setString(2,oldpass);
                    st1.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Password changed succesfully.");

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            else
            JOptionPane.showMessageDialog(null, "Old password does not match");
    
            }catch (Exception ex) {
                System.out.println(e);
            }
        }
    }
    else{
    JOptionPane.showMessageDialog(null, "new password & confirm password do not match");
    }
    }

    public static void main(String args[]){
        new passwordchange_page();
    }
}