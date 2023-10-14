import javax.swing.*;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class revenue_page{
        JFrame rev;
        JLabel enter , disp;

        public revenue_page(){
         rev = new JFrame("Revenue");
         rev.setBounds(0,0,500,300);
         rev.setLayout(null);
         rev.setVisible(true);

         enter = new JLabel("Total revenue: ");
         enter.setBounds(30,40,200,28);
         enter.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
         rev.add(enter);


         disp = new JLabel();
         disp.setBounds(260,40,100,28);
         disp.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
         

         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("call total_revenue ;");
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                   String revenue = rs.getString(1);
                   String display = new String("Rs."+revenue);
                    disp.setText(display);
                }
            }catch (Exception e1){
                    e1.printStackTrace();
                }
                rev.add(disp);
                
         }
         

        
        


        public static void main(String args[]){
            new revenue_page();
        }
}
