package evidenca;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

            String name,surname,email,mobile,address,salary;


            name = textName.getText();
            surname = textSurname.getText();
            email = textEmail.getText();
            mobile = textMobile.getText();
            address = textAddress.getText();
            salary = textSalary.getText();


            try{
                pst = con.prepareStatement("insert into zaposleni (ime, priimek, email, telefon, naslov, placa) values(?,?,?,?,?,?)");
                pst.setString(1,name);
                pst.setString(2,surname);
                pst.setString(3,email);
                pst.setString(4,mobile);
                pst.setString(5,address);
                pst.setString(6,salary);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Registered!");

                textName.setText("");
                textSurname.setText("");
                textEmail.setText("");
                textMobile.setText("");
                textAddress.setText("");
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
    }
}
