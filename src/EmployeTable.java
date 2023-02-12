import evidenca.empRegistration;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;

public class EmployeTable {
    private JPanel panel1;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField textField1;
    private JButton menuButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmployeTable");
        frame.setContentPane(new EmployeTable().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    Connection con;
    PreparedStatement pst;
    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://snuffleupagus.db.elephantsql.com:5432/hbfssypp", "hbfssypp", "Oq5dieXemgwvy-Qhf8MB3dFl2K5Q0-CR");
            System.out.println("works");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    void table_load(){
        try{
            pst = con.prepareStatement("select * from zaposleni");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public EmployeTable(){
        connect();
        table_load();
    }
}

