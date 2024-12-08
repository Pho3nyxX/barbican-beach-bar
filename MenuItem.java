import java.io.Serializable;

public class MenuItem implements Serializable{
    private int itemId;
    private String name;
    private String description;
    private float price;
    private int prepTime;
    private int quantity;

    public MenuItem(){

    }

    public MenuItem(String name, float price){
        this.setName(name);
        try{
            this.setPrice(price);
        }catch(InvalidPriceException e){
            this.price = 0;
        }
    }
    
    public MenuItem(String name, float price, String description, int preTime ){
        this.setName(name);
        this.setDescription(description);
        this.setPrepTime(prepTime);
        try{
            this.setPrice(price);
        }catch(InvalidPriceException e){
            this.price = 0;
        }
    }
    
    public int getItemId(){
        return this.itemId;
    }
    
    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public float getPrice(){
        return this.price;
    }

    public int getPrepTime(){
        return this.prepTime;
    }

    /**
     * Method getQuantity
     *
     * @return The return value
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Method setName
     *
     * @param name A parameter
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method setDescription
     *
     * @param description A parameter
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Method setPrice
     *
     * @param price A parameter
     */
    public void setPrice(float price) throws InvalidPriceException{
        if(price >= 0 ){
            this.price = price;
        }else{
            throw new InvalidPriceException();
        }
    }

    /**
     * Method setPrepTime
     *
     * @param preTime A parameter
     */
    public void setPrepTime(int preTime){
        this.prepTime = preTime;
    }

    /**
     * Method getQuantity
     *
     * @param quantity - Integer -the new quantity
     */
    public void getQuantity(int quantity){
        this.quantity = quantity;
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String toString()
    {
        // put your code here
        return this.name;
    }
}

class InvalidPriceException extends Exception{
    int a;
    public InvalidPriceException() {
        super("Invalid price entered");
    }

    public String toString(){
        return getMessage() ;
    }
}
