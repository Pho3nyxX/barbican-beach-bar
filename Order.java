
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;


public class Order implements Serializable{

    // instance variables - replace the example below with your own
    protected ArrayList<OrderItem> items;
    protected int customerId;
    protected int prepTime;
    protected float totalCost;

    /**
     * Constructor for objects of class Order
     */
    public Order()
    {
        // initialise instance variables
        prepTime = 0;
        totalCost = 0;
        customerId = 0;
        items = new ArrayList<OrderItem>();
    }

    public ArrayList<OrderItem> getItems() {
        ArrayList<OrderItem> itemsCopy = new ArrayList<OrderItem>();
        //MenuItem[] itemsCopy = new MenuItem[this.items.length];
        //System.arraycopy(this.items, 0, copy, 0, copy.length);
        Collections.copy(itemsCopy, this.items);
        return itemsCopy;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = new ArrayList<OrderItem>();
        //System.arraycopy(items, 0, this.items, 0, scr.length);
        Collections.copy(this.items, items);
    }

    public void addItem(OrderItem item)throws OrderItemAlreadyExistsException{
        for(int i = 0; i < this.items.size(); i++){
            throw new OrderItemAlreadyExistsException(item.getMenuItem().getName());
        }
        this.items.add(item);
    }

    public void removeItem(OrderItem item)throws OrderItemNotFoundException{
        for(int i = 0; i < this.items.size(); i++){
            if(this.items.get(i).getMenuItem().getName().equals(item.getMenuItem().getName())){
                this.items.remove(i);
            }else{
                throw new OrderItemNotFoundException(item.getMenuItem().getName());
            }
        }
    }

    public void calculateTotal(){
        for(int i = 0; i < this.items.size(); i++){
            this.totalCost = this.items.get(i).getMenuItem().getPrice() * this.items.get(i).getQuantity();

        }
    }
}

class OrderItemAlreadyExistsException extends Exception{
   int a;
   public OrderItemAlreadyExistsException(String name) {
     super("Order Item "+ name +"Already Exists");
   }

   public String toString(){
     return getMessage() ;
  }
}

class OrderItemNotFoundException extends Exception{
   int a;
   public OrderItemNotFoundException(String name) {
     super("Order Item "+ name +"Does not exist in the menu");
   }
   public String toString(){
     return getMessage() ;
  }
}
