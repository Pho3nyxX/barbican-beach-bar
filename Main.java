import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.border.Border;
import javax.swing.*;

public class Main extends JFrame
{
    protected User user;
    protected MenuItem selectedMenuItem;
    protected boolean loggedIn = false;
    protected char userType = 'x';

    private JMenuBar menuBar;
    private JList list_Menu;
    //login dialoge
    private JLabel Label_ErrorMessage;
    private JLabel Label_LoginFailed;
    private JButton button_Login;
    private JButton button_Edit;
    private JButton button_Order;
    private JLabel label_Username;
    private JLabel label_Password;
    private JLabel label_Login;
    private JPasswordField passwordfield_Password;
    private JTextField textfield_Username;
    private JPanel contentPane;
    private JLabel label_item_description;
    private JLabel label_item_description_value;
    private JLabel label_itemname;
    private JLabel label_itemnamevalue;
    private JLabel label_preptime;
    private JLabel label_preptimevalue;
    private JPanel panel_detailsview;
    private JPanel panel_search;
    private JTextField textfield_search;
    private JButton button_search;
    private ListSelectionListener listSelectionListener;

    //Constructor
    public Main(){

        this.setTitle("Menu manager");
        this.setSize(500,400);
        ShowLoginDialogue();
    }

    //method for generate menu
    public void generateMenu(){
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");

        JMenuItem exit = new JMenuItem("Exit   ");
        JMenuItem preferences = new JMenuItem("Preferences   ");
        JMenuItem about = new JMenuItem("About   ");

        file.addSeparator();
        file.add(exit);
        tools.add(preferences);
        help.add(about);
        //add, edit delete menu item buttons
        if(this.userType == 'a'){
            JMenuItem addMenuItemDialog = new JMenuItem("Add menu item   ");
            JMenuItem deleteMenuItemDialog = new JMenuItem("Delete menu item   ");
            file.add(addMenuItemDialog);
            file.add(deleteMenuItemDialog);
            addMenuItemDialog.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        showAddMenuItemDialog(false);
                    }
                });
            deleteMenuItemDialog.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        confirmDeleteMenuItemDialog(evt);
                    }
                });
        }
        menuBar.add(file);
        menuBar.add(tools);
        menuBar.add(help);
    }

    public void showMainWindow(){
        this.setSize(500,400);
        this.contentPane.removeAll();
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        this.contentPane = new JPanel(null);
        this.contentPane.setPreferredSize(new Dimension(500,400));
        this.contentPane.setBackground(new Color(192,192,192));

        this.user.loadMenu();
        DefaultListModel mod = this.user.getMenuListModel();
        //System.out.println("test");

        //adding components to contentPane panel

        this.label_item_description = new JLabel();
        this.label_item_description.setBounds(5,44,80,35);
        this.label_item_description.setBackground(new Color(214,217,223));
        this.label_item_description.setForeground(new Color(0,0,0));
        this.label_item_description.setEnabled(true);
        this.label_item_description.setFont(new Font("sansserif",0,12));
        this.label_item_description.setText("Description");
        this.label_item_description.setVisible(true);

        this.label_item_description_value = new JLabel();
        this.label_item_description_value.setBounds(92,44,90,35);
        this.label_item_description_value.setBackground(new Color(214,217,223));
        this.label_item_description_value.setForeground(new Color(0,0,0));
        this.label_item_description_value.setEnabled(true);
        this.label_item_description_value.setFont(new Font("sansserif",Font.BOLD,12));
        this.label_item_description_value.setText("");
        this.label_item_description_value.setVisible(true);

        this.label_preptime = new JLabel();
        this.label_preptime.setBounds(5,88,80,35);
        this.label_preptime.setBackground(new Color(214,217,223));
        this.label_preptime.setForeground(new Color(0,0,0));
        this.label_preptime.setEnabled(true);
        this.label_preptime.setFont(new Font("sansserif",0,12));
        this.label_preptime.setText("Prep time");
        this.label_preptime.setVisible(true);

        this.label_preptimevalue = new JLabel();
        this.label_preptimevalue.setBounds(92,88,90,35);
        this.label_preptimevalue.setBackground(new Color(214,217,223));
        this.label_preptimevalue.setForeground(new Color(0,0,0));
        this.label_preptimevalue.setEnabled(true);
        this.label_preptimevalue.setFont(new Font("sansserif",Font.BOLD,12));
        this.label_preptimevalue.setText("");
        this.label_preptimevalue.setVisible(true);

        this.label_itemname = new JLabel();
        this.label_itemname.setBounds(6,5,80,35);
        this.label_itemname.setBackground(new Color(214,217,223));
        this.label_itemname.setForeground(new Color(0,0,0));
        this.label_itemname.setEnabled(true);
        this.label_itemname.setFont(new Font("sansserif",0,12));
        this.label_itemname.setText("Menu Item");
        this.label_itemname.setVisible(true);

        this.label_itemnamevalue = new JLabel();
        this.label_itemnamevalue.setBounds(92,5,90,35);
        this.label_itemnamevalue.setBackground(new Color(214,217,223));
        this.label_itemnamevalue.setForeground(new Color(0,0,0));
        this.label_itemnamevalue.setEnabled(true);
        this.label_itemnamevalue.setFont(new Font("sansserif",Font.BOLD,12));
        this.label_itemnamevalue.setText("");
        this.label_itemnamevalue.setVisible(true);

        this.button_Edit = new JButton();
        this.button_Edit.setBounds(212,132,90,35);
        this.button_Edit.setBackground(new Color(250,250,250));
        this.button_Edit.setForeground(new Color(0,0,0));
        this.button_Edit.setEnabled(true);
        this.button_Edit.setFont(new Font("sansserif",0,12));
        this.button_Edit.setText("Edit item");
        this.button_Edit.setVisible(true);
        this.button_Edit.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    showAddMenuItemDialog(true);
                }
            });

        this.button_Order = new JButton();
        this.button_Order.setBounds(212,132,90,35);
        this.button_Order.setBackground(new Color(250,250,250));
        this.button_Order.setForeground(new Color(0,0,0));
        this.button_Order.setEnabled(true);
        this.button_Order.setFont(new Font("sansserif",0,12));
        this.button_Order.setText("Order item");
        this.button_Order.setVisible(true);
        this.button_Order.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    showOrderMenuItemDialog();
                }
            });


        this.list_Menu = new JList(mod);
        this.list_Menu.setBounds(5,57,150,390);
        this.list_Menu.setBackground(new Color(255,255,255));
        this.list_Menu.setForeground(new Color(0,0,0));
        this.list_Menu.setEnabled(true);
        this.list_Menu.setFont(new Font("sansserif",0,12));
        this.list_Menu.setVisible(true);
        this.list_Menu.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent listSelectionEvent) {
                    menuListValueChanged(listSelectionEvent);
                }
            });

        //adding components to contentPane panel
        this.panel_detailsview = new JPanel(null);
        this.panel_detailsview.setBorder(BorderFactory.createEtchedBorder(1));
        this.panel_detailsview.setBounds(163,57,317,175);
        this.panel_detailsview.setBackground(new Color(214,217,223));
        this.panel_detailsview.setForeground(new Color(0,0,0));
        this.panel_detailsview.setEnabled(true);
        this.panel_detailsview.setFont(new Font("sansserif",0,12));
        this.panel_detailsview.setVisible(false);

        //add search components
        panel_search = new JPanel(null);
        panel_search.setBorder(BorderFactory.createEtchedBorder(1));
        panel_search.setBounds(5,5,475,46);
        panel_search.setBackground(new Color(214,217,223));
        panel_search.setForeground(new Color(0,0,0));
        panel_search.setEnabled(true);
        panel_search.setFont(new Font("sansserif",0,12));
        panel_search.setVisible(true);

        textfield_search = new JTextField();
        textfield_search.setBounds(5,5,350,35);
        textfield_search.setBackground(new Color(255,255,255));
        textfield_search.setForeground(new Color(0,0,0));
        textfield_search.setEnabled(true);
        textfield_search.setFont(new Font("sansserif",0,12));
        textfield_search.setText("");
        textfield_search.setVisible(true);
        //Set action for key events
        //Call defined method
        textfield_search.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt){
                    searchMenu(textfield_search.getText());
                }
            });

        button_search = new JButton();
        button_search.setBounds(360,5,105,35);
        button_search.setBackground(new Color(214,217,223));
        button_search.setForeground(new Color(0,0,0));
        button_search.setEnabled(true);
        button_search.setFont(new Font("sansserif",0,12));
        button_search.setText("Search menu");
        button_search.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button_search.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    searchMenu(textfield_search.getText());
                }
            });

        //adding components to contentPane panel
        this.panel_detailsview.add(label_item_description);
        this.panel_detailsview.add(label_item_description_value);
        this.panel_detailsview.add(label_itemname);
        this.panel_detailsview.add(label_itemnamevalue);
        this.panel_detailsview.add(label_preptime);
        this.panel_detailsview.add(label_preptimevalue);

        if(this.userType == 'a'){
            this.panel_detailsview.add(this.button_Edit);
        }else{
            this.panel_detailsview.add(this.button_Order);
        }
        panel_search.add(button_search);
        panel_search.add(textfield_search);

        contentPane.add(panel_search);
        contentPane.add(list_Menu);
        contentPane.add(panel_detailsview);
        /*
        String s = (String)JOptionPane.showInputDialog(
        this,
        "Complete the sentence:\n"
        + "\"Green eggs and...\"");*/

        //adding panel to JFrame and seting of window position and close operation
        this.add(this.contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.pack();
        //SwingUtilities.updateComponentTreeUI(this);

        this.invalidate();
        this.validate();
        this.repaint();
        this.setVisible(false);

        this.setVisible(true);

    }
    //
    public void ShowLoginDialogue(){

        this.setTitle("Log in");
        this.setSize(500,400);
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        this.contentPane = new JPanel(null);
        this.contentPane.setPreferredSize(new Dimension(500,400));
        this.contentPane.setBackground(new Color(192,192,192));

        this.Label_ErrorMessage = new JLabel();
        this.Label_ErrorMessage.setBounds(146,233,250,35);
        this.Label_ErrorMessage.setBackground(new Color(214,217,223));
        this.Label_ErrorMessage.setForeground(new Color(0,0,0));
        this.Label_ErrorMessage.setEnabled(true);
        this.Label_ErrorMessage.setFont(new Font("sansserif",0,12));
        this.Label_ErrorMessage.setText("Username and/or password incorrect.");
        this.Label_ErrorMessage.setVisible(false);

        this.Label_LoginFailed = new JLabel();
        this.Label_LoginFailed.setBounds(207,219,90,25);
        this.Label_LoginFailed.setBackground(new Color(214,217,223));
        this.Label_LoginFailed.setForeground(new Color(0,0,0));
        this.Label_LoginFailed.setEnabled(true);
        this.Label_LoginFailed.setFont(new Font("sansserif",0,12));
        this.Label_LoginFailed.setText("Login Failed.");
        this.Label_LoginFailed.setVisible(false);

        this.button_Login = new JButton();
        this.button_Login.setBounds(220,265,130,35);
        this.button_Login.setBackground(new Color(214,217,223));
        this.button_Login.setForeground(new Color(0,0,0));
        this.button_Login.setEnabled(true);
        this.button_Login.setFont(new Font("sansserif",0,12));
        this.button_Login.setText("Login");
        this.button_Login.setVisible(true);
        //Set methods for mouse events
        //Call defined methods
        button_Login.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    AttemptLogin(evt);
                }
            });

        this.label_Username = new JLabel();
        this.label_Username.setBounds(148,121,90,35);
        this.label_Username.setBackground(new Color(214,217,223));
        this.label_Username.setForeground(new Color(0,0,0));
        this.label_Username.setEnabled(true);
        this.label_Username.setFont(new Font("sansserif",0,12));
        this.label_Username.setText("Username");
        this.label_Username.setVisible(true);

        this.label_Password = new JLabel();
        this.label_Password.setBounds(148,173,90,35);
        this.label_Password.setBackground(new Color(214,217,223));
        this.label_Password.setForeground(new Color(0,0,0));
        this.label_Password.setEnabled(true);
        this.label_Password.setFont(new Font("sansserif",0,12));
        this.label_Password.setText("Password");
        this.label_Password.setVisible(true);

        this.label_Login = new JLabel();
        this.label_Login.setBounds(216,69,90,35);
        this.label_Login.setBackground(new Color(214,217,223));
        this.label_Login.setForeground(new Color(0,0,0));
        this.label_Login.setEnabled(true);
        this.label_Login.setFont(new Font("SansSerif",0,20));
        this.label_Login.setText("Log in");
        this.label_Login.setVisible(true);

        this.passwordfield_Password = new JPasswordField();
        this.passwordfield_Password.setBounds(220,172,130,35);
        this.passwordfield_Password.setBackground(new Color(214,217,223));
        this.passwordfield_Password.setForeground(new Color(0,0,0));
        this.passwordfield_Password.setEnabled(true);
        this.passwordfield_Password.setFont(new Font("sansserif",0,12));
        this.passwordfield_Password.setVisible(true);

        this.textfield_Username = new JTextField();
        this.textfield_Username.setBounds(220,121,130,35);
        this.textfield_Username.setBackground(new Color(255,255,255));
        this.textfield_Username.setForeground(new Color(0,0,0));
        this.textfield_Username.setEnabled(true);
        this.textfield_Username.setFont(new Font("sansserif",0,12));
        this.textfield_Username.setText("");
        this.textfield_Username.setVisible(true);

        //adding components to contentPane panel
        this.contentPane.add(Label_ErrorMessage);
        this.contentPane.add(Label_LoginFailed);
        this.contentPane.add(button_Login);
        this.contentPane.add(label_Username);
        this.contentPane.add(label_Password);
        this.contentPane.add(label_Login);
        this.contentPane.add(passwordfield_Password);
        this.contentPane.add(textfield_Username);

        //adding panel to JFrame and seting of window position and close operation
        this.add(this.contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    //Method mouseClicked for button_Login
    private void AttemptLogin (MouseEvent evt) {
        //TODO
        this.user = new User();

        if(textfield_Username.getText().equals("admin")){
            this.user = new Admin();
            this.loggedIn = ((Admin)this.user).validateUser(textfield_Username.getText(), passwordfield_Password.getText());
            this.userType = 'a';
            try{
                MenuItem m = new MenuItem("Chicken", 500, "Serverd with rice and peas and steamed vegetables", 20);
                ((Admin)this.user).AddMenuItem(m);
                MenuItem n = new MenuItem("Fish", 300, "Serverd with rice and peas and steamed vegetables", 15);
                ((Admin)this.user).AddMenuItem(n);
                MenuItem o = new MenuItem("Pork", 700, "Serverd with rice and peas and steamed vegetables", 30);
                ((Admin)this.user).AddMenuItem(o);
            }catch(MenuItemAlreadyExistsException e){
                System.out.println("Exists");
            }

        }else if(textfield_Username.getText().equals("cust1")){
            this.user = new Customer();
            this.loggedIn = ((Customer)this.user).validateUser(textfield_Username.getText(), passwordfield_Password.getText());
            this.userType = 'c';
        }
        if(this.loggedIn){
            this.remove(contentPane);
            //this.invalidate();
            this.showMainWindow();
            //return user;
        }else{
            Label_LoginFailed.setVisible(true);
            Label_ErrorMessage.setVisible(true);
            //return null;
        }
    }

    /**
     * Method menuListValueChanged
     *
     * @param listSelectionEvent A parameter
     */
    private void menuListValueChanged(ListSelectionEvent listSelectionEvent){
        boolean adjust = listSelectionEvent.getValueIsAdjusting();
        if (!adjust) {
            JList list = (JList) listSelectionEvent.getSource();
            int selections[] = list.getSelectedIndices();
            Object selectionValues[] = list.getSelectedValues();
            for (int i = 0, n = selections.length; i < n; i++) {
                //if (i == 0) {
                //System.out.print("  Selections: ");
                //}
                //System.out.print(selections[i] + "/" + selectionValues[i] + " ");
                this.selectedMenuItem = this.user.searchMenuItem(selectionValues[i].toString());
                label_itemnamevalue.setText(this.selectedMenuItem.getName());
                label_item_description_value.setText(this.selectedMenuItem.getDescription());;
                label_preptimevalue.setText(String.valueOf(this.selectedMenuItem.getPrepTime())+" Minutes");;
                this.panel_detailsview.setVisible(true);
                //System.out.println(this.selectedMenuItem.toString());
            }

        }
        this.list_Menu.addListSelectionListener(this.listSelectionListener);

    }

    /**
     * Method showAddMenuItemDialog
     *
     * @param editItemIfExists A parameter
     */
    void showAddMenuItemDialog(boolean editItemIfExists){
        JTextField name = new JTextField();
        JTextField price = new JTextField();
        JTextField prepTime = new JTextField();
        JTextArea description = new JTextArea();
        description.setBounds(5,5,150,100);
        String dialog_Title = "Add menu item";
        if(this.selectedMenuItem != null && editItemIfExists){
            name.setText(this.selectedMenuItem.getName());
            price.setText(String.valueOf(this.selectedMenuItem.getPrice()));
            prepTime.setText(String.valueOf(this.selectedMenuItem.getPrepTime()));
            description.setText(this.selectedMenuItem.getDescription());
            dialog_Title = "Edit menu item";
        }
        price.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {
                    String value = price.getText();
                    int l = value.length();
                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                        price.setEditable(true);
                    } else {
                        price.setEditable(false);
                        System.out.println(price.getText().substring(0, (price.getText().length()-1)));
                        price.setText(price.getText().substring(0, (price.getText().length()-1)));
                        price.setEditable(true);
                    }
                }
            });
        prepTime.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {
                    String value = prepTime.getText();
                    int l = value.length();
                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                        prepTime.setEditable(true);
                    } else {
                        prepTime.setEditable(false);
                        System.out.println(prepTime.getText().substring(0, (prepTime.getText().length()-1)));
                        prepTime.setText(prepTime.getText().substring(0, (prepTime.getText().length()-1)));
                        prepTime.setEditable(true);
                    }
                }
            });
        Object[] message = {
                "Name:", name,
                "Price:", price,
                "Prep time:", prepTime,
                "Description:", description
            };

        int option = JOptionPane.showConfirmDialog(this, message, dialog_Title, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try{
                MenuItem m = new MenuItem(name.getText(), Float.parseFloat(price.getText()), description.getText(), Integer.parseInt(prepTime.getText()));
                if(!editItemIfExists){
                    ((Admin)this.user).AddMenuItem(m);
                    this.user.loadMenu();
                    this.list_Menu.setModel(this.user.getMenuListModel());
                }else{
                    this.selectedMenuItem.setName(name.getText());
                    try{
                        this.selectedMenuItem.setPrice(Float.parseFloat(price.getText()));
                    }catch(InvalidPriceException e){
                        System.out.println("Price is invalid.");
                    }
                    this.selectedMenuItem.setPrepTime(Integer.parseInt(prepTime.getText()));
                    this.selectedMenuItem.setDescription(description.getText());
                    ((Admin)this.user).saveMenu();
                }
            }catch(MenuItemAlreadyExistsException e){
                System.out.println("Menu item exists");
            }
        }
    }

    /**
     * Method searchMenu
     *
     * @param term A parameter
     */
    private void searchMenu(String term){
        this.list_Menu.setModel(this.user.searchMenuItems(term));
    }

    /**
     * Method confirmDeleteMenuItemDialog
     *
     * @param evt A parameter
     */
    void confirmDeleteMenuItemDialog(ActionEvent evt){
        if(this.selectedMenuItem != null){
            String message = "Are you sure you want to delete this item";
            Object[] options = {
                    "Yes:",
                    "Cancel"
                };

            int option = JOptionPane.showOptionDialog(this, message, "Delete menu item", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
            if (option == JOptionPane.OK_OPTION) {
                try{
                    System.out.println(this.selectedMenuItem.getName());
                    ((Admin)this.user).DeleteMenuItem(this.selectedMenuItem);
                    this.user.loadMenu();
                    this.list_Menu.setModel(this.user.getMenuListModel());
                }catch(MenuItemNotFoundException e){
                    System.out.println("Menu Item doesn't Exist");
                }
            } else {
                System.out.println("Login canceled");
            }
        }
    }


    /**
     * Method showOrderMenuItemDialog
     *
     */
    void showOrderMenuItemDialog(){
        JTextField quantity = new JTextField();
        String dialog_Title = "Order menu item";
        if(this.selectedMenuItem != null){

        }
        quantity.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent ke) {
                    String value = quantity.getText();
                    int l = value.length();
                    if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                        quantity.setEditable(true);
                    } else {
                        quantity.setEditable(false);
                        System.out.println(quantity.getText().substring(0, (quantity.getText().length()-1)));
                        quantity.setText(quantity.getText().substring(0, (quantity.getText().length()-1)));
                        quantity.setEditable(true);
                    }
                }
            });
        Object[] message = {
                "How many do you want to order:", quantity,
            };

        int option = JOptionPane.showConfirmDialog(this, message, dialog_Title, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            /*try{
                /OrderItem m = new OrderItem(name.getText(), Float.parseFloat(price.getText()), description.getText(), Integer.parseInt(prepTime.getText()));
                ((Admin)this.user).AddMenuItem(m);
                this.user.loadMenu();
                    this.list_Menu.setModel(this.user.getMenuListModel());
            }catch(OrderItemAlreadyExistsException e){
                System.out.println("Menu item exists");
            }*/
        }
    }


    /**
     * Method main
     *
     * @param args A parameter
     */
    public static void main(String[] args) throws IOException{
        User user;
        char userType;
        String username;
        String password;
        boolean loggedIn = false;
        boolean exit = false;
        String options = "press x to exit";

        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        new LoginDialogue();
        }
        });*/

        //System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Main();
                }
            });

        /*

        System.out.println("Welcome to menu management app\n");
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //loop to Select user type
        while(true){
        System.out.println("Enter C to continue as a customer or A if your are an admin\nPress X to exit");
        reader = new BufferedReader(new InputStreamReader(System.in));
        userType = (char)reader.read();
        if(userType == 'C' || userType == 'c'){
        System.out.println("Customer");
        System.out.println("Log in as customer");
        user = new Customer();
        options = "1. Add new items to menu\n 2. Edit menu items\n 3. Delete menu items\n 4. Search for menu items 5. Exit";
        break;
        }else if(userType == 'A' || userType == 'a'){
        user = new Admin();
        System.out.println("Log in as administrator");
        break;
        }else if(userType == 'X' || userType == 'x'){
        return;
        }else{
        System.out.println("Please enter a valid choice");
        continue;
        }
        }

        // loop to handle logins
        while(loggedIn == false){
        //Log in user
        System.out.println("Enter username:");
        reader = new BufferedReader(new InputStreamReader(System.in));
        username = reader.readLine();
        System.out.println("Enter password:");
        password = reader.readLine();
        System.out.println(username);
        System.out.println(password);

        if(user.validateUser(username, password)){
        loggedIn = true;
        System.out.println("Log in successful:");
        break;
        }else{
        System.out.println("Log in failed. \nPlease enter a valid username and password or contact the system adminstrator.");
        }
        }

        //loop to manage application flow
        while(exit == false){
        System.out.println(options);
        reader = new BufferedReader(new InputStreamReader(System.in));
        try{
        MenuItem m = new MenuItem("Chicken", 500);
        ((Admin)user).AddMenuItem(m);
        exit = true;
        }catch(MenuItemAlreadyExistsException e){

        }
        }
        Menu menu;
        menu = new Menu();
        try{
        MenuItem test = null;
        menu.addItem(new MenuItem("Chicken", 500));
        test = menu.searchItems("Chik");
        if(test != null){
        System.out.println("Item found");
        }else{
        System.out.println("Item not found");
        }
        }catch(MenuItemAlreadyExistsException e){
        System.out.println("Item Exists");
        }
        System.out.println("Hello World");
         */
    }
}
