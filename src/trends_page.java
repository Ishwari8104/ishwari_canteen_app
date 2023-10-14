import javax.swing.*;    
import java.awt.*;
import java.sql.*;



public class trends_page{
    JFrame trending;
    JLabel mosttrending , top10 , trendingcategory, titem;


    trends_page(){
        trending = new JFrame("Check Order Trends");
        trending.setBounds(30,70,700,700);

        mosttrending = new JLabel("Most Trending Item:");
        mosttrending.setBounds(30,70,300,28);
        mosttrending.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));

        top10 = new JLabel("Top 10 most trending items: ");
        top10.setBounds(30,150,300,28);
        top10.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));

        titem = new JLabel();
        titem.setBounds(370,70,500,28);
        titem.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
        


        String item, item1 ;
        DefaultListModel<String> l1 = new DefaultListModel<>(); 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
            PreparedStatement st = (PreparedStatement) connection.prepareStatement("call trend;");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                item = rs.getString(2);
                l1.addElement(item);
    
            }

        }catch (Exception e1){
                e1.printStackTrace();
            }

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
                PreparedStatement st2 = (PreparedStatement) connection.prepareStatement("call top;");
                ResultSet rs2 = st2.executeQuery();
                while(rs2.next()){
                    item1 = rs2.getString(2);
                    titem.setText(item1);
                }
            }catch (Exception e1){
                    e1.printStackTrace();
                }
    

            JList<String> list = new JList<>(l1);  
            list.setBounds(370,150, 500,300); 
            list.setFont(new Font("Baskerville Old Face", Font.BOLD, 24)); 
            list.setBackground(new Color(235, 237, 239));
       
        trending.add(list);      
        trending.setLayout(null);
        trending.add(top10);  
        trending.add(mosttrending);
        trending.add(titem);
        trending.setVisible(true);
        
    }



    public static void main(String args[]){
        new trends_page();
    }
}