import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.*;   
import java.awt.*;
import java.lang.Exception;  


public class kitchen_staff implements ActionListener{
    JFrame f2;
    JPanel menu, ongoing, completed;
    JButton logout,changepass,trends,display,complete;
    JTable table1;
    JScrollPane jScrollPane2;
    DefaultTableModel model;
    JTextArea receipt1;
    String receiptdata;
    int order_idk;
    
    kitchen_staff(){
    JFrame f2 = new JFrame("Kitchen staff");
    Image icon = Toolkit.getDefaultToolkit().getImage("D:\\canteen application\\logo reduced.png");    
    f2.setIconImage(icon);
    f2.setContentPane(new JLabel(new ImageIcon("D:\\canteen application\\top-view-circular-food-frame.jpg")));
    f2.setSize(1920,1080); 
    f2.setLayout(null);  
   
    

    menu  = new JPanel();
    menu.setBounds(1280,0,300,1080);
    menu.setBackground(new Color(0,74,173));
    menu.setOpaque(true);
   
    
    logout = new JButton("Logout");
    logout.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    logout.setBackground(new Color(0,74,173));
    logout.setForeground(Color.WHITE);
    logout.setBorderPainted(false);

    changepass = new JButton("Change Password");
    changepass.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    changepass.setSize(300, 300);
    changepass.setBackground(new Color(0,74,173));
    changepass.setForeground(Color.WHITE);
    changepass.setBorderPainted(false);

    trends = new JButton("Trending");
    trends.setSize(300, 300);
    trends.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    trends.setBackground(new Color(0,74,173));
    trends.setForeground(Color.WHITE);
    trends.setBorderPainted(false);

    logout.addActionListener(this);
    changepass.addActionListener(this);
    trends.addActionListener(this);
    menu.add(trends);
    menu.add(changepass);
    menu.add(logout);
    f2.add(menu);

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Order ID");
    model.addColumn("status");
    
    JTable table1 = new JTable(model);
    JScrollPane jScrollPane2 = new JScrollPane(table1);
    jScrollPane2.setViewportView(table1);

    receipt1 = new JTextArea();
    receipt1.setBounds(400,80,500,500);

   
    try{
       
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
        PreparedStatement st = (PreparedStatement) connection.prepareStatement("select * from canteen_database.order2 where status = 'ongoing';");
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            String id = rs.getString(1);
            String status = rs.getString(2);
            String[] row = {id,status};
            model.addRow(row);

        }
    }catch (Exception e1){
            e1.printStackTrace();
        }

        receipt1.setFont(new Font("Baskerville Old Face", Font.BOLD, 20)); 
        receipt1.setBackground(new Color(235, 237, 239));

    display = new JButton("Display order Details");
    display.setBounds(30,410,200,28);
    display.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            int index = table1.getSelectedRow();
                String id1 = model.getValueAt(index, 0).toString();
                order_idk = Integer.parseInt(id1);
                
            try{
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("select receipt from canteen_database.order2 where order_id2 = ? ;");
                st.setInt(1, order_idk);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    String receiptdata = rs.getString(1);
                    receipt1.setText(receiptdata);
        
                }
            }catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        }
    );

     f2.add(receipt1);
     jScrollPane2.setBounds(30,80,300,300);
     f2.add(display);
     
     f2.add(jScrollPane2);
    
        




    JButton refresh = new JButton("Refresh");
    refresh.setBounds(1000,640,150,28);
    refresh.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    refresh.setForeground(Color.WHITE);
    refresh.setBackground(new Color(0,74,173));
    refresh.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           f2.dispose();
           new kitchen_staff();
       }
    });



    complete = new JButton("Complete order");
    complete.setBounds(645,600,255,28);
    complete.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    complete.setForeground(Color.WHITE);
    complete.setBackground(new Color(0,74,173));
    complete.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("update canteen_database.order set status = 'completed' where order_id = ?;");
            st.setInt(1, order_idk);
            st.executeUpdate();
            PreparedStatement st1 = (PreparedStatement) connection.prepareStatement("update canteen_database.order2 set status = 'completed' where order_id2 = ?;");
            st1.setInt(1, order_idk);
            st1.executeUpdate();
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
    });
    f2.add(complete);

    f2.add(refresh);
    f2.setVisible(true);
    

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==logout){
            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?");
            if(a== JOptionPane.YES_OPTION){
            f2.setVisible(false);
            new login_page();
            }
        }
    
        else if(e.getSource()== changepass){
            new passwordchange_page();
        }
    
        else if(e.getSource()== trends){
            new trends_page();
        }
    }
    
    


    public static void main(String args[]){
        new kitchen_staff();

    }
}