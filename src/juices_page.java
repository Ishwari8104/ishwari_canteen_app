import javax.swing.*;    
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.lang.Exception; 

public class juices_page{
    juices_page(){
        JFrame f = new JFrame();
 
        // Frame Title
        f.setTitle("List of Juices");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\canteen application\\logo reduced.png");    
        f.setIconImage(icon);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Item ID");
        model.addColumn("Item Name");
        model.addColumn("Item Price");

        String id = "";
        String name = "";
        String price = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select item_id, item_name, item_price from food where item_category = 'juice';");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                id = rs.getString(1);
                name = rs.getString(2);
                price = rs.getString(3);
                String[] row = {id,name,price};
                model.addRow(row);

            }
        }catch (Exception e1){
                System.out.println(e1);
            }

        
        // Data to be displayed in the JTable
    
        JTable j = new JTable(model);
        j.setBounds(30, 40, 500, 500);
 
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 500);
        // Frame Visible = true
        f.setVisible(true);
    }
 
  
    public static void main(String[] args)
    {
        new juices_page();
    }
}
