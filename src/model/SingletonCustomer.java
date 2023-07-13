package model;
import java.util.ArrayList;
public class SingletonCustomer {
    private static SingletonCustomer instance;
    private ArrayList<Customer> listCustomer = new ArrayList<>();
    
    public static SingletonCustomer getInstance() {
        if (instance == null) {
            instance = new SingletonCustomer();
        }
        return instance;
    }
    
    public void reset(){
        this.listCustomer = new ArrayList<>();
    }
    
    public ArrayList<Customer> getListCustomer(){
        return listCustomer;
    }
    
    public Customer getCustomer(int index){
        return listCustomer.get(index);
    }
    
    public void addCustomer(Customer c){
        this.listCustomer.add(c);
    }
}
