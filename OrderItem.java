public class OrderItem
{
    protected int itemId;
    protected int quantity;
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class OrderItem
     */
    public OrderItem(int itemId, int quantity)
    {
        // initialise instance variables
        quantity = 0;
    }

    public int getQuantity(){
        return this.quantity;
    }
    public int getItemId(){
        return this.itemId;
    }

    public int setQuantity(int quantity){
        if(quantity < 1){
            quantity = 1;
        }
        return this.quantity = quantity;
    }
    public int setItemId(){
        return this.itemId = itemId;
    }
}
