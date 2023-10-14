import javax.swing.*; 
import java.awt.*;
import java.sql.*;
import java.awt.event.*;



public class admin_page implements ActionListener{

    //group(addstaff deletestaff; staffid staffname loginid password jobname; staff_id, staff_name, login_id , pass_word, job_name)
    //group(additem deleteitem; itemname,itemid,itemcategory,itemprice;item_name , item_id, item_category , item_price)


    JButton logout,changepass,trends,staff,menuitem,revenue;
    JPanel menu,todo;
    JFrame admin_page;
    JButton addstaff , deletestaff, confirmaddstaff;
    JButton additem , deleteitem;
    JLabel staffid, staffname , loginid , password, jobname;
    JTextField staff_id, staff_name, login_id , pass_word, job_name;

    JLabel itemname,itemid,itemcategory,itemprice;
    JTextField item_name , item_id, item_category , item_price;
    admin_page(){
  
        
        admin_page = new JFrame("Admin");
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\canteen application\\logo reduced.png");    
        admin_page.setIconImage(icon);
        admin_page.setContentPane(new JLabel(new ImageIcon("D:\\canteen application\\top-view-circular-food-frame.jpg")));
        admin_page.setBounds(0,0,1920,1080);
        admin_page.setLayout(null); 
        admin_page.setLocationByPlatform(true);
        admin_page.setVisible(true);
        
       
        
        
        
        menu  = new JPanel();
        menu.setBounds(0,0,300,1080);
        menu.setBackground(new Color(0,74,173));
        



    

        staff = new JButton("Staff");
        staff.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
        staff.setBounds(0,80,300, 40);
        staff.setBackground(new Color(0,74,173));
        staff.setForeground(Color.WHITE);
        staff.setBorderPainted(false);

        menuitem = new JButton("Menu");
        menuitem.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
        menuitem.setBounds(0,170,300, 40);
        menuitem.setBackground(new Color(0,74,173));
        menuitem.setForeground(Color.WHITE);
        menuitem.setBorderPainted(false);

        trends = new JButton("Trending");
        trends.setBounds(0,260,300, 40);
        trends.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
        trends.setBackground(new Color(0,74,173));
        trends.setForeground(Color.WHITE);
        trends.setBorderPainted(false);

        revenue = new JButton("Revenue");
        revenue.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
        revenue.setBounds(0,350,300, 40);
        revenue.setBackground(new Color(0,74,173));
        revenue.setForeground(Color.WHITE);
        revenue.setBorderPainted(false);
        revenue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new revenue_page();
            }
        });

        changepass = new JButton("Change Password");
        changepass.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
        changepass.setBounds(0,440,300, 40);
        changepass.setBackground(new Color(0,74,173));
        changepass.setForeground(Color.WHITE);
        changepass.setBorderPainted(false);

        logout = new JButton("Logout");
        logout.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
        logout.setBounds(0,530,300, 40);
        logout.setBackground(new Color(0,74,173));
        logout.setForeground(Color.WHITE);
        logout.setBorderPainted(false);

        addstaff = new JButton("Add new Staff");
        addstaff.setFont(new Font("Baskerville Old Face", Font.BOLD, 40));
        addstaff.setBounds(300,0,615,40);  

        addstaff.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                

                staffname = new JLabel("Enter Staff Name:");
                staffname.setBounds(330,100,300,35);
                staffname.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
                admin_page.add(staffname);

                staff_name = new JTextField();
                staff_name.setBounds(670,100,400,35);
                staff_name.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
                admin_page.add(staff_name);

                staffid = new JLabel("Enter Staff ID:");
                staffid.setBounds(330,160,300,35);
                staffid.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
                admin_page.add(staffid);

                staff_id = new JTextField();
                staff_id.setBounds(670,160,200,35);
                admin_page.add(staff_id);

                loginid = new JLabel("Enter login ID:");
                loginid.setBounds(330,220,300,35);
                loginid.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
                admin_page.add(loginid);

                login_id = new JTextField();
                login_id.setBounds(670,220,300,35);
                login_id.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
                admin_page.add(login_id);

                password = new JLabel("Enter new password:");
                password.setBounds(330,280,300,35);
                password.setFont(new Font("Baskerville Old Face", Font.BOLD, 32));
                admin_page.add(password);

                pass_word = new JTextField();
                pass_word.setBounds(670,280,300,35);
                pass_word.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
                admin_page.add(pass_word);


                jobname = new JLabel("Job name: ");
                jobname.setBounds(330,345,300,35);
                jobname.setFont(new Font("Baskerville Old Face", Font.BOLD, 32));
                admin_page.add(jobname);

                job_name = new JTextField();
                job_name.setBounds(670,345,300,35);
                job_name.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
                admin_page.add(job_name);

                confirmaddstaff = new JButton("Confirm addition");
                confirmaddstaff.setBounds(670,410,300,35);
                confirmaddstaff.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String staffname = staff_name.getText();
                        String id = staff_id.getText();
                        int idn = Integer.parseInt(id);
                        String loginid = login_id.getText();
                        String pass = pass_word.getText();
                        String job = job_name.getText();
                        try {
                            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?");
                            if(a== JOptionPane.YES_OPTION){
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen_database","root", "root");
                            PreparedStatement st = (PreparedStatement) connection.prepareStatement("insert into canteen_database.staff values(?,?,?,?,?);");
                            st.setInt(1, idn);
                            st.setString(2, staffname);
                            st.setString(3, loginid);
                            st.setString(4, pass);
                            st.setString(5, job);
                            st.executeUpdate();
                            }
                            
                           
                    
                            }catch (Exception ex) {
                               ex.printStackTrace();;
                            }
                        
                    }
                });
                admin_page.add(confirmaddstaff);




            }
        });



        admin_page.add(addstaff);

    
        deletestaff = new JButton("Delete Staff");
        deletestaff.setFont(new Font("Baskerville Old Face", Font.BOLD, 40));
        deletestaff.setBounds(915,0,615,40);
        admin_page.add(deletestaff);
        


       

        additem = new JButton("Add new Staff");
        additem.setFont(new Font("Baskerville Old Face", Font.BOLD, 40));
        additem.setBounds(0,0,615,40);
        additem.setVisible(false);
            
        admin_page.add(additem);
    
        deleteitem = new JButton("Delete Staff");
        deleteitem.setFont(new Font("Baskerville Old Face", Font.BOLD, 40));
        deleteitem.setBounds(615,0,615,40);
        deleteitem.setVisible(false);
        admin_page.add(deleteitem);

      

        logout.addActionListener(this);
        changepass.addActionListener(this);
        trends.addActionListener(this);
        staff.addActionListener(this);
        menuitem.addActionListener(this);
        revenue.addActionListener(this);
        admin_page.add(staff);
        admin_page.add(menuitem);
        admin_page.add(revenue);
        admin_page.add(trends);
        admin_page.add(changepass);
        admin_page.add(logout);
        admin_page.add(menu);
        
        admin_page.pack();
        admin_page.setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==logout){
            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?");
            if(a== JOptionPane.YES_OPTION){
            admin_page.setVisible(false);
            new login_page();
            }
        }
    
        else if(e.getSource()== changepass){
            new passwordchange_page();
        }
    
        else if(e.getSource()== trends){
            new trends_page();
        }

        else if(e.getSource()== staff){
            addstaff.setVisible(true);
            deletestaff.setVisible(true);
        }

        else if(e.getSource()==menuitem){
            menuset();
        }
    }


    public void staffset(){
        
    }

    public void menuset(){
        additem = new JButton("Add new Staff");
        additem.setFont(new Font("Baskerville Old Face", Font.BOLD, 40));
        additem.setBounds(300,0,615,40);
        additem.setVisible(false);
        admin_page.add(additem);
    
        deleteitem = new JButton("Delete Staff");
        deleteitem.setFont(new Font("Baskerville Old Face", Font.BOLD, 40));
        deleteitem.setBounds(915,0,615,40);
        deleteitem.setVisible(false);
        admin_page.add(deleteitem);        
    }



    public static void main(String args[]){
        new admin_page();
    }
}