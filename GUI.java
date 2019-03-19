import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class GUI{
    public static void main(String args[]){
        LoginPage lp = new LoginPage();
    }
}


class LoginPage extends JFrame implements ActionListener{

    JFrame mainFrame;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JLabel DispMsg;
    JLabel UsernameMsg;
    JLabel PasswordMsg;
    JTextField UsernameField;
    JPasswordField PasswordField;
    JButton LoginButton;

    LoginPage(){
        mainFrame = new JFrame("Login Client Assist");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,400);

        panel1 = new JPanel();
        GridBagConstraints cs = new GridBagConstraints();

        panel2 = new JPanel();

        panel3 = new JPanel();

        cs.fill = GridBagConstraints.HORIZONTAL;

        DispMsg = new JLabel("Welcome to the Login Client");
        DispMsg.setFont(new Font("Arial",Font.BOLD,30));
        panel2.add(DispMsg);

        UsernameMsg = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel1.add(UsernameMsg,cs);

        UsernameField = new JTextField(10);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel1.add(UsernameField,cs);

        PasswordMsg = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel1.add(PasswordMsg,cs);

        PasswordField = new JPasswordField(10);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel1.add(PasswordField,cs);

        LoginButton = new JButton("Login");
        cs.gridx = 0;
        cs.gridy = 200;
        cs.gridwidth = 1;
        panel3.add(LoginButton,cs);
        LoginButton.addActionListener(this);
        setTitle("Login");
        
        mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

        //Flow layout
        mainFrame.getContentPane().add(panel2);
        mainFrame.getContentPane().add(panel1);
        mainFrame.getContentPane().add(panel3);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae){
        String s1 = UsernameField.getText();
        String s2 = new String(PasswordField.getPassword());

        if (s1.equals("admin") && s2.equals("admin")){              // database Required
            System.out.println("3");
        }
        else{
            JOptionPane.showMessageDialog(this,"Incorrect login or password","Error",JOptionPane.ERROR_MESSAGE);
        }
        UsernameField.setText("");
        PasswordField.setText("");
    }
}

class MainPage{
    NextPage np;
    
}