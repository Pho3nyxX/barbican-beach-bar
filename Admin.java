import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultListModel;

public class Admin extends User{
    public Admin(){
        this.menu = new Menu();
        try{
            this.menuFile = new File(this.menuFileName);
            this.menuFile.createNewFile();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        this.username = "admin";
        this.password = "admin";

        this.loadMenu();
    }

    public void AddMenuItem(MenuItem item) throws MenuItemAlreadyExistsException{
        try{
            menu.addItem(item);
            this.saveMenu();
        }catch(MenuItemAlreadyExistsException e){
            e.printStackTrace();
            throw e;
        }
    }


    public void DeleteMenuItem(MenuItem item) throws MenuItemNotFoundException{
        try{
            this.menu.removeItem(item);
            this.saveMenu();
        }catch(MenuItemNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }

    private void EditMenuItem(MenuItem item) throws MenuItemNotFoundException{
        try{
            this.menu.removeItem(item);
            this.saveMenu();
        }catch(MenuItemNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }

    public void saveMenu(){
        try{
            FileOutputStream f = new FileOutputStream(this.menuFile);
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(this.menu);

            // close streams
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("Menu File not found");
        } catch (IOException e) {
            System.out.println("2Error initializing stream");
        } //catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        //      e.printStackTrace();
        //  }
    }

}
