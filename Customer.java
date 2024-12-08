
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Customer extends User
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Customer
     */
    public Customer()
    {
        this.menuFile = new File(this.menuFileName);
        this.username = "cust1";
        this.password = "qwerty";
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
