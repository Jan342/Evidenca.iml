package evidenca;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class empRegistration {
    private JPanel panel1;
    private JTextField textName;
    private JTextField textSurname;
    private JTextField textEmail;
    private JTextField textMobile;
    private JTextField textAddress;
    private JButton saveButton;
    private JButton menuButton;
    private JTextField textSalary;
    private JComboBox comboBox1;

    private JFrame frame;

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



    public empRegistration() {

         frame = new JFrame("empRegistration");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        connect();
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String name,surname,email;
            int mobile,salary;


            name = textName.getText();
            surname = textSurname.getText();
            email = textEmail.getText();
            mobile = Integer.parseInt(textMobile.getText());
            salary = Integer.parseInt(textSalary.getText());


            try{
                pst = con.prepareStatement("insert into zaposleni (ime, priimek, email, telefon, placa) values(?,?,?,?,?)");
                pst.setString(1,name);
                pst.setString(2,surname);
                pst.setString(3,email);
                pst.setInt(4,mobile);
                pst.setInt(5,salary);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Registered!");

                textName.setText("");
                textSurname.setText("");
                textEmail.setText("");
                textMobile.setText("");
                textSalary.setText("");
                textName.requestFocus();


            }catch (SQLException e1){
                e1.printStackTrace();

            }


        }
    });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
            }
        });
        comboBox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    pst = con.prepareStatement("SELECT ime FROM kraji");
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()){
                        String name = rs.getString("ime");
                        comboBox1.addItem(name);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
