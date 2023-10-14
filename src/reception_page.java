import javax.swing.*; 
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.lang.Exception; 
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class reception_page implements ActionListener  {
    public static JTextArea receipt;
    JScrollPane sp;
    JTextField jtf;
    JPanel menu;
    JLabel searchLbl;
    JScrollPane jScrollPane2 = new JScrollPane();
    JButton add,clear;
    DefaultTableModel model2;
    JButton create_receipt;
    JTable jTable2 = new JTable();
    int total = 0;
    int amount;
    JMenuBar rmenu;
    JButton logout,changepass,trends;
    JFrame f1;
    String id = "";
    String name = "";
    String category= "";
    String price = "";
    
    
    int qty = 1;
            int order_id = 1000;

   public reception_page(){
    JLabel L1,L2,L3,L4,L5,L6;
    JLabel order_pg;
    
    
    
    
    JButton create_order,checkout,snacks,south_indian,sandwiches,chinese,lunch,juices;
    
    f1 = new JFrame("Reception");
    Image icon = Toolkit.getDefaultToolkit().getImage("D:\\canteen application\\logo reduced.png");    
    f1.setIconImage(icon);
    f1.setSize(1920,1080);
    f1.setLayout(null);  
   
    f1.setContentPane(new JLabel(new ImageIcon("D:\\canteen application\\top-view-circular-food-frame.jpg")));
    
    f1.setVisible(true);
    menu  = new JPanel();
    menu.setBounds(1280,0,300,1080);
    menu.setBackground(new Color(0,74,173));
    menu.setOpaque(true);
    rmenu = new JMenuBar();
    rmenu.setLayout(new BoxLayout(rmenu, BoxLayout.PAGE_AXIS));
    rmenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    rmenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
    
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
    
    f1.add(menu);
    




    order_pg= new JLabel();
    order_pg.setBounds(800, 0, 800, 1080);
    order_pg.setBackground(new Color(222,226,231));
    f1.add(order_pg);
   

    create_order = new JButton("Create New Order");
    create_order.setOpaque(true);
    create_order.setBounds(30,20,300,40);
    create_order.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    create_order.setForeground(Color.WHITE);
    create_order.setBackground(new Color(0,74,173));
    f1.add(create_order);
    create_order.addActionListener(new ActionListener()
    {  
        public void actionPerformed(ActionEvent e){  
            sp.setVisible(true);
            jtf.setVisible(true);
            searchLbl.setVisible(true);
            add.setVisible(true);
            jScrollPane2.setVisible(true);
            create_receipt.setVisible(true);
            clear.setVisible(true);
            
        } 
    });


    JPanel order = new JPanel();
    order.setBounds(30, 70, 800, 500);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Item ID");
    model.addColumn("Item Name");
    model.addColumn("Category");
    model.addColumn("Item Price");

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
        PreparedStatement st = (PreparedStatement) connection.prepareStatement("select item_id, item_name, item_category, item_price from food;");
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            id = rs.getString(1);
            name = rs.getString(2);
            category = rs.getString(3);
            price = rs.getString(4);
            String[] row = {id,name,category,price};
            model.addRow(row);

        }
    }catch (Exception e1){
            e1.printStackTrace();
        }

    
    TableRowSorter sorter = new TableRowSorter<>(model);
     jtf = new JTextField(15);
    jtf.setBounds(120,70,150,28);
    searchLbl = new JLabel("Search:");
    searchLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
    searchLbl.setBackground(Color.white);
    searchLbl.setOpaque(true);
    searchLbl.setBounds(30,70,80,28);

    JTable j = new JTable(model);
    j.setBounds(30, 40, 800, 500);
    j.getColumnModel().getColumn(0).setPreferredWidth(5);
    j.getColumnModel().getColumn(1).setPreferredWidth(150);


    j.setRowSorter(sorter);
     sp = new JScrollPane(j);
    jtf.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
           search(jtf.getText());
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
           search(jtf.getText());
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
           search(jtf.getText());
        }
        public void search(String str) {
           if (str.trim().length() == 0) {
              sorter.setRowFilter(null);
           } else {
              sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
           }
        }
     });
     sp.setBounds(30, 100, 500, 300);
    sp.setVisible(false);
    jtf.setVisible(false);
    searchLbl.setVisible(false);
    f1.add(sp);
    f1.add(jtf);
    f1.add(searchLbl);
    
    order.setVisible(true);


    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "ID No.", "Item Name", "Category", "Price"
        }
    ));
    jScrollPane2.setViewportView(jTable2);

   



    add = new JButton("Add Items to Order");
     add.addActionListener(new ActionListener()
     {
        public void actionPerformed(ActionEvent e){
           TableModel model1 = j.getModel();

    int[] indexs = j.getSelectedRows();

    Object[] row = new Object[4];

    model2 = (DefaultTableModel) jTable2.getModel();

    

    for(int i = 0; i < indexs.length; i++)
    {
        row[0] = model1.getValueAt(indexs[i], 0);

        row[1] = model1.getValueAt(indexs[i], 1);

        row[2] = model1.getValueAt(indexs[i], 2);

        row[3] = model1.getValueAt(indexs[i], 3);

        model2.addRow(row);
    }
    
    
   
           } 
     });

    
    
    

   
     add.setBounds(30,410,200,28);
     jScrollPane2.setBounds(550,100,300,300);
     f1.add(add);
     add.setVisible(false);
     f1.add(jScrollPane2);
     jScrollPane2.setVisible(false);

    receipt = new JTextArea();
    receipt.setBounds(900,70,350,500);
    receipt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 14));
    create_order.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f1.add(receipt);
    



     clear = new JButton("clear");
     clear.setBounds(600, 410, 100, 28);
     clear.setVisible(false);
     clear.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                receipt.selectAll();
                receipt.replaceSelection("");
                int rowCount = model2.getRowCount();
//Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                model2.removeRow(i); 
        } 
    }
    });
     f1.add(clear);


     JButton refresh = new JButton("Refresh");
     refresh.setBounds(1000,640,150,28);
     refresh.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
     refresh.setForeground(Color.WHITE);
     refresh.setBackground(new Color(0,74,173));
     refresh.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            f1.dispose();
            new reception_page();
        }
     });

     f1.add(refresh);



    checkout = new JButton("Checkout");
    checkout.setBounds(1000,580,150,40);
    checkout.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    checkout.setForeground(Color.WHITE);
    checkout.setBackground(new Color(0,74,173));
    checkout.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
                 try {
            
                    
               
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
                
                
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("insert into canteen_database.order(order_id, item_id, item_price, category) values (?, ?, ?, ?);");
                for(int i=0;i<1;i++){
                    for(int j=0;j<model2.getRowCount();j++){
                        String id = model2.getValueAt(j, 0).toString();
                        int idn = Integer.parseInt(id); 
                        String category =model2.getValueAt(j, 2).toString();
                        String price = model2.getValueAt(j, 3).toString();
                        int pp = Integer.parseInt(price);
                        st.setInt(1,order_id);
                        st.setInt(2, idn);
                        st.setInt(3, pp);
                        st.setString(4,category);
                        st.executeUpdate();
                    }
                    order_id = order_id +1;

                }
                    PreparedStatement st1 = (PreparedStatement) connection.prepareStatement("insert into canteen_database.order2(receipt) values (?);");
                    
                    String rcpt = receipt.getText();
                    
                    st1.setString(1,rcpt);
                    st1.executeUpdate();

                 
                    
        
            
            receipt.print();
            receipt.selectAll();
            receipt.replaceSelection("");
            int rowCount = model2.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
            model2.removeRow(i);     
            }
        }catch (Exception e1){
                    e1.printStackTrace();;
                }
            
             
        }
    });
    f1.add(checkout);

    
    create_receipt = new JButton("Create Receipt");
    create_receipt.setBounds(700, 410, 150, 28);
    create_receipt.setVisible(false);
    create_receipt.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            make_receipt();
        }
    });
    f1.add(create_receipt);


    


   
   



    L1 = new JLabel("Snacks");
    L1.setBounds(110,510,150,28);
    L1.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f1.add(L1);

    L2 = new JLabel("South Indian");
    L2.setBounds(110,610,200,28);
    L2.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f1.add(L2);

    L3 = new JLabel("Sandwiches");
    L3.setBounds(110,710,150,24);
    L3.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f1.add(L3);

    L4 = new JLabel("Chinese");
    L4.setBounds(410,510,150,28);
    L4.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f1.add(L4);

    L5 = new JLabel("Lunch");
    L5.setBounds(410,610,150,28);
    L5.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f1.add(L5);

    L6 = new JLabel("Juices");
    L6.setBounds(410,710,150,28);
    L6.setFont(new Font("Baskerville Old Face", Font.BOLD, 28));
    f1.add(L6);

    snacks = new JButton(new ImageIcon("D:\\app\\snacks.png"));
    snacks.setBounds(50,500,50,50);
    f1.add(snacks);
    snacks.addActionListener(new ActionListener()
    {  
        public void actionPerformed(ActionEvent e){  
        new snacks_page();
        }});
    


    south_indian = new JButton(new ImageIcon("D:\\app\\south indian.png"));
    south_indian.setBounds(50,600,50,50);
    f1.add(south_indian);
    south_indian.addActionListener(new ActionListener()
    {  
        public void actionPerformed(ActionEvent e){  
               new south_indian_page();
        } 
    });


    sandwiches = new JButton(new ImageIcon("D:\\app\\sandwiches.png"));
    sandwiches.setBounds(50, 700, 50, 50);
    f1.add(sandwiches);
    sandwiches.addActionListener(new ActionListener()
    {  
        public void actionPerformed(ActionEvent e){  
               new sandwiches_page();
        } 
    });


    chinese = new JButton(new ImageIcon("D:\\app\\chinese.png"));
    chinese.setBounds(350,500,50,50);
    f1.add(chinese);
    chinese.addActionListener(new ActionListener()
    {  
        public void actionPerformed(ActionEvent e){  
               new chinese_page();
        } 
    });

    
    lunch = new JButton(new ImageIcon("D:\\app\\lunch.png"));
    lunch.setBounds(350,600,50,50);
    f1.add(lunch);
    lunch.addActionListener(new ActionListener()
    {  
        public void actionPerformed(ActionEvent e){  
               new lunch_page();
        } 
    });

    juices = new JButton(new ImageIcon("D:\\app\\juice.png"));
    juices.setBounds(350, 700, 50, 50);
    f1.add(juices);
    juices.addActionListener(new ActionListener()
    {  
        public void actionPerformed(ActionEvent e){  
               new juices_page();
        } 
    });
    }
    
   
   public void make_receipt(){
    for (int i = 0; i < jTable2.getRowCount(); i++){
        amount = Integer.parseInt((String) jTable2.getValueAt(i, 3));
       total += amount;
    }
        receipt.setText(receipt.getText()+"=======================================\n");
        receipt.setText(receipt.getText()+"\t*VES Canteen*\n");
        receipt.setText(receipt.getText()+"=======================================\n");
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        receipt.setText(receipt.getText()+"ID"+"\tItem Name"+"\tCategory"+"\tQuantity"+"\tPrice\n");
        for(int i=0;i< jTable2.getRowCount();i++){
            String id = jTable2.getValueAt(i, 0).toString();
            String name = jTable2.getValueAt(i, 1).toString();
            String category = jTable2.getValueAt(i, 2).toString();
            String price = jTable2.getValueAt(i, 3).toString();
            int quantity = 1;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
                PreparedStatement st = (PreparedStatement) connection.prepareStatement("update food set item_qty = (item_qty-1) where item_name =? ");
                st.setString(1, name);
                st.executeUpdate();
               
         } catch(Exception e){
                e.printStackTrace();
            }
            receipt.setText(receipt.getText()+id+"\t"+name+"\t"+category+"\t"+quantity+"\t"+price+"\n");
        }
        receipt.setText(receipt.getText()+"=======================================\n");
        receipt.setText(receipt.getText()+"\t\ttotal Price: Rs "+total);
        receipt.setText(receipt.getText()+"\n\nCustomization: ");
}


public void actionPerformed(ActionEvent e){
    if(e.getSource()==logout){
        int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?");
        if(a== JOptionPane.YES_OPTION){
        f1.setVisible(false);
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
    new reception_page();
    }

    
}