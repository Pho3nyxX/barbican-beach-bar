import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;
import javax.swing.DefaultListModel;

public class Menu implements Serializable{
    private ArrayList<MenuItem> items;
    private DefaultListModel listModel;
    
    public Menu(){
        this.items = new ArrayList<MenuItem>();
    }
    
    public ArrayList<MenuItem> getItems() {
        final ArrayList<MenuItem> itemsCopy = new ArrayList<MenuItem>();
        Collections.copy((ArrayList<MenuItem>)itemsCopy, (ArrayList<MenuItem>)this.items);
        return itemsCopy;
    }
    
    public void setItems(final ArrayList<MenuItem> items) {
        Collections.copy((ArrayList<MenuItem>)(this.items = new ArrayList<MenuItem>()), (ArrayList<MenuItem>)items);
    }
    
    public void addItem(final MenuItem item) throws MenuItemAlreadyExistsException {
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i).getName().equals(item.getName())) {
                throw new MenuItemAlreadyExistsException(item.getName());
            }
        }
        this.items.add(item);
    }
    
    public void editItem(MenuItem item) throws MenuItemNotFoundException {
        boolean found = false;
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i).getName().equals(item.getName())) {
                this.items.get(i).setName(item.getName());
                try{
                    this.items.get(i).setPrice(item.getPrice());
                }catch(InvalidPriceException e){
                    System.out.println("Price is invalid.");
                }
                this.items.get(i).setPrepTime(item.getPrepTime());
                this.items.get(i).setDescription(item.getDescription());
                found = true;
                //System.out.println("Found");
                break;
            }
        }
        if(!found){
            //System.out.println("not found");
            throw new MenuItemNotFoundException(item.getName());
        }
    }
    
    /**
     * Method removeItem
     *
     * @param item A parameter
     */
    public void removeItem(MenuItem item) throws MenuItemNotFoundException {
        boolean found = false;
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i).getName().equals(item.getName())) {
                this.items.remove(i);
                found = true;
                //System.out.println("Found");
                break;
            }
        }
        if(!found){
            //System.out.println("not found");
            throw new MenuItemNotFoundException(item.getName());
        }
    }    
    public MenuItem searchItem(String term){
        for(int i = 0; i < this.items.size(); i++){
            if(this.items.get(i).getName().contains(term)){
                return this.items.get(i);
            }
        }
        return null;
    }
    
    public DefaultListModel searchItems(String term){
        DefaultListModel itemsFound = new DefaultListModel();
        for(int i = 0; i < this.items.size(); i++){
            if(this.items.get(i).getName().contains(term)){
                itemsFound.addElement(this.items.get(i));
            }
        }
        return itemsFound;
    }


    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public DefaultListModel generateListModel()
    {
        this.listModel = new DefaultListModel();
        for(int i = 0; i < this.items.size(); i++){
            this.listModel.addElement(this.items.get(i));
            System.out.println("added");
        }
        return this.listModel;
    }

}
class MenuItemAlreadyExistsException extends Exception{
    int a;
    public MenuItemAlreadyExistsException(String name) {
        super("Menu Item "+ name +"Already Exists");
    }

    public String toString(){
        return getMessage() ;
    }
}

class MenuItemNotFoundException extends Exception{
    int a;
    public MenuItemNotFoundException(String name) {
        super("Menu Item "+ name +"Does not exist in the menu");
    }

    public String toString(){
        return getMessage() ;
    }
}