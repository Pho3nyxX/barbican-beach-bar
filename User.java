import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultListModel;

public class User{
    protected String username;
    protected String password;
    protected int userId;
    protected Menu menu;
    protected String menuFileName = "menu";
    protected File menuFile;

    public String getUsername(){
        return this.username;
    }

    public boolean validateUser(String usr, String pwd){
        boolean valid = false;
        //System.out.println(pwd);
        if(this.username.equals(usr) && this.password.equals(pwd)){
            valid = true;
            //System.out.println("user:" + usr);
        }
        return valid;
    }

    public MenuItem searchMenuItem(String term){
        this.loadMenu();
        MenuItem item = this.menu.searchItem(term);
        if(item != null){
            return item;
        }else{
            return null;
        }
    }

    public DefaultListModel searchMenuItems(String term){
        this.loadMenu();
        return this.menu.searchItems(term);
    }

    public void loadMenu(){
        try{
            FileInputStream fi = new FileInputStream(new File(this.menuFileName));
            ObjectInputStream oi = new ObjectInputStream(fi);
            // Read objects
            this.menu = (Menu)oi.readObject();

            //oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            //boolean exists = temp.exists();
            System.out.println("Menu File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public DefaultListModel getMenuListModel(){
        return this.menu.generateListModel();
    }
}
