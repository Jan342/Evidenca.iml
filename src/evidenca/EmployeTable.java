package evidenca;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.*;

public class EmployeTable {
    private JPanel panel1;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField textName;
    private JButton backButton;


    private JFrame frame;

    Connection con;


    public EmployeTable(){
        frame = new JFrame("EmployeTable");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,500);
        frame.setVisible(true);

        connect();
        table_load();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    PreparedStatement pst = con.prepareStatement("SELECT * FROM select_zaposleni_byname(?)");
                    pst.setString(1, textName.getText());
                    ResultSet rs = pst.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("lmao");
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = table1.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "No row selected.");
                    return;
                }

                int selectedId = (int) table1.getModel().getValueAt(table1.getSelectedRow(), 0);
                String newValue = JOptionPane.showInputDialog("Enter the new value:");

                try {
                    PreparedStatement pst = con.prepareStatement("UPDATE zaposleni SET priimek = ? WHERE id = ?");
                    pst.setString(1, newValue);
                    pst.setInt(2, selectedId);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Row updated successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }





            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedid = (int) table1.getModel().getValueAt(table1.getSelectedRow(), 0) ;
                try {

                    PreparedStatement pst = con.prepareStatement("SELECT select_zaposleni_id(?)");
                    pst.setInt(1, selectedid);
                    ResultSet rs = pst.executeQuery();
                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                table_load();
            }
        });


    }

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
            PreparedStatement pst;
            pst = con.prepareStatement("SELECT * FROM select_zaposleni()");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}

