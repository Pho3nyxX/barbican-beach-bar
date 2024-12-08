/**
 *Text genereted by Simple GUI Extension for BlueJ
 */
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;

public class LoginDialogue extends JFrame {
    private JMenuBar menuBar;
    private JLabel Label_ErrorMessage;
    private JLabel Label_LoginFailed;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordfield_Password;
    private JTextField textfield_Username;

    //Constructor
    public LoginDialogue(){

        this.setTitle("LoginDialogue");
        this.setSize(500,400);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,400));
        contentPane.setBackground(new Color(192,192,192));

        Label_ErrorMessage = new JLabel();
        Label_ErrorMessage.setBounds(146,233,250,35);
        Label_ErrorMessage.setBackground(new Color(214,217,223));
        Label_ErrorMessage.setForeground(new Color(0,0,0));
        Label_ErrorMessage.setEnabled(true);
        Label_ErrorMessage.setFont(new Font("sansserif",0,12));
        Label_ErrorMessage.setText("Username and/or password incorrect.");
        Label_ErrorMessage.setVisible(false);

        Label_LoginFailed = new JLabel();
        Label_LoginFailed.setBounds(207,219,90,25);
        Label_LoginFailed.setBackground(new Color(214,217,223));
        Label_LoginFailed.setForeground(new Color(0,0,0));
        Label_LoginFailed.setEnabled(true);
        Label_LoginFailed.setFont(new Font("sansserif",0,12));
        Label_LoginFailed.setText("Login Failed.");
        Label_LoginFailed.setVisible(false);

        button1 = new JButton();
        button1.setBounds(220,265,130,35);
        button1.setBackground(new Color(214,217,223));
        button1.setForeground(new Color(0,0,0));
        button1.setEnabled(true);
        button1.setFont(new Font("sansserif",0,12));
        button1.setText("Login");
        button1.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button1.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    AttemptLogin(evt);
                }
            });

        label1 = new JLabel();
        label1.setBounds(148,121,90,35);
        label1.setBackground(new Color(214,217,223));
        label1.setForeground(new Color(0,0,0));
        label1.setEnabled(true);
        label1.setFont(new Font("sansserif",0,12));
        label1.setText("Username");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(148,173,90,35);
        label2.setBackground(new Color(214,217,223));
        label2.setForeground(new Color(0,0,0));
        label2.setEnabled(true);
        label2.setFont(new Font("sansserif",0,12));
        label2.setText("Password");
        label2.setVisible(true);

        label3 = new JLabel();
        label3.setBounds(216,69,90,35);
        label3.setBackground(new Color(214,217,223));
        label3.setForeground(new Color(0,0,0));
        label3.setEnabled(true);
        label3.setFont(new Font("SansSerif",0,20));
        label3.setText("Log in");
        label3.setVisible(true);

        passwordfield_Password = new JPasswordField();
        passwordfield_Password.setBounds(220,172,130,35);
        passwordfield_Password.setBackground(new Color(214,217,223));
        passwordfield_Password.setForeground(new Color(0,0,0));
        passwordfield_Password.setEnabled(true);
        passwordfield_Password.setFont(new Font("sansserif",0,12));
        passwordfield_Password.setVisible(true);

        textfield_Username = new JTextField();
        textfield_Username.setBounds(220,121,130,35);
        textfield_Username.setBackground(new Color(255,255,255));
        textfield_Username.setForeground(new Color(0,0,0));
        textfield_Username.setEnabled(true);
        textfield_Username.setFont(new Font("sansserif",0,12));
        textfield_Username.setText("");
        textfield_Username.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(Label_ErrorMessage);
        contentPane.add(Label_LoginFailed);
        contentPane.add(button1);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label3);
        contentPane.add(passwordfield_Password);
        contentPane.add(textfield_Username);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseClicked for button1
    private User AttemptLogin (MouseEvent evt) {
        //TODO
        User user = new User();
        boolean loggedIn = false;

        if(textfield_Username.getText().equals("admin")){
            user = new Admin();
            loggedIn = ((Admin)user).validateUser(textfield_Username.getText(), passwordfield_Password.getText());
        }else if(textfield_Username.getText().equals("cust1")){
            user = new Customer();
            loggedIn = ((Customer)user).validateUser(textfield_Username.getText(), passwordfield_Password.getText());
        }
        if(loggedIn){
            this.removeAll();
            this.pack();
            this.setVisible(true);
            return user;
        }else{
            Label_LoginFailed.setVisible(true);
            Label_ErrorMessage.setVisible(true);
            return null;
        }
    }

    //method for generate menu
    public void generateMenu(){
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");

        JMenuItem open = new JMenuItem("Open   ");
        JMenuItem save = new JMenuItem("Save   ");
        JMenuItem exit = new JMenuItem("Exit   ");
        JMenuItem preferences = new JMenuItem("Preferences   ");
        JMenuItem about = new JMenuItem("About   ");

        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        tools.add(preferences);
        help.add(about);

        menuBar.add(file);
        menuBar.add(tools);
        menuBar.add(help);
    }
}
