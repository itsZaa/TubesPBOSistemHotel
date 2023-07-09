package controller;

//import model.Customer;
import model.Laundry;
import model.LaundryTransaction;
import model.OrderStatus;
import model.PaymentMethod;
import model.User;
import model.RoomTransaction;
//import model.Transaction;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public class LaundryController {
    private DatabaseController databaseController;
    private User user;

    public LaundryController(){
        this.databaseController = new DatabaseController();
    }

    public LaundryController(User user) {
        this.databaseController = new DatabaseController();
        this.user = user;
    }

    private RoomTransaction findTransaction() {
        RoomTransaction selected = databaseController.getRoomTransaction(user.getUsername());

        return selected;
    }

    public int getLamaInap() {
        int lamaInap = 0;

        RoomTransaction selected = findTransaction();
        Date getDateCheckIn = selected.getDateCheckIn(); // Get the check-in date
        int duration = selected.getDuration(); // Get the duration in days
        Date currentDate = new Date(); // Get the current date

        long diffInMillis = currentDate.getTime() - getDateCheckIn.getTime();
        long durationInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis) + duration;

        lamaInap = (int) durationInDays;

        return lamaInap;
    }

    public Laundry getLaundryType(String laundryName){
        Laundry result = databaseController.getLaundry(laundryName);
        return result;
    }

    public double countTotalPrice(Laundry laundry, double beratLaundry){
        double totalPrice = Math.ceil(beratLaundry)*laundry.getPrice(); 
        return totalPrice;
    }

    private static String generateTransactionId() {
        // Get the current LocalDateTime
        LocalDateTime now = LocalDateTime.now();

        // Format the LocalDateTime using a specific pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDateTime = now.format(formatter);

        // Generate 5 random numbers
        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000; // Generates a random number between 10000 and 99999

        // Concatenate the formatted DateTime and random numbers to create the transactionId
        String transactionId = "LAUNDRY_" + formattedDateTime + "_" + randomNumber;

        return transactionId;
    }

    public void createLaundryTransaction(User user, double beratLaundry, int roomNumber, Laundry laundry, PaymentMethod paymentMethod){

        String laundryTransactionId = generateTransactionId();

        LaundryTransaction transaction = new LaundryTransaction(laundryTransactionId, user, roomNumber, countTotalPrice(laundry, beratLaundry), OrderStatus.WAITING, LocalDate.now(), null, laundry, paymentMethod);

        databaseController.insertLaundryTransaction(transaction);
    }
}
