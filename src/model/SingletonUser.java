package model;
import java.util.ArrayList;
public class SingletonUser {
    private static SingletonUser instance;
    private ArrayList<Customer> listCustomer = new ArrayList<>();
    
    public static SingletonUser getInstance() {
        if (instance == null) {
            instance = new SingletonUser();
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
