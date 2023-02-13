package evidenca;

import javax.swing.*;
import java.awt.*;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private JTextField nameText;
    private JPanel panel1;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JFrame frame;
    public LoginPage(){

        frame = new JFrame("Login Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(250,200));
        frame.setResizable(false);

        //now add the panel
        frame.add(panel1);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user,pass;
                user = nameText.getText();
                pass = passwordText.getText();
                System.out.println(user);
                System.out.print(pass);

                if(user.isEmpty() || pass.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Vnesi vse podatke!");

                }
                if (user.equals("admin") && pass.equals("pass")) {
                        Menu meni = new Menu();
                        frame.dispose();

                }
                else {
                    JOptionPane.showMessageDialog(null, "narobe geslo");
                }


            }

        });
    }
}
