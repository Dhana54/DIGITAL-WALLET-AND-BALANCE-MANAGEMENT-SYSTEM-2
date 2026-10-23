package wallet;
public class WalletService {
    public void addMoney(Wallet wallet, double amount) {
        wallet.setBalance(wallet.getBalance() + amount);
        wallet.getTransactions().add("Added: " + amount);
        System.out.println("Money added successfully!");
    }
    public void sendMoney(Wallet sender, Wallet receiver, double amount) {
        if (sender.getBalance() >= amount) {
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            sender.getTransactions().add("Sent: " + amount + " to " + receiver.getUserName());
            receiver.getTransactions().add("Received: " + amount + " from " + sender.getUserName());
            System.out.println("Transaction successful!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }
    public void checkBalance(Wallet wallet) {
        System.out.println("Balance: " + wallet.getBalance());
    }
     public void showTransactions(Wallet wallet) {
        System.out.println("Transaction History:");
        for (String t : wallet.getTransactions()) {
            System.out.println(t);
        }
    }
}
package wallet;
import java.util.ArrayList;
import java.util.List;
public class Wallet {
    private String userName;
    private double balance;
    private List<String> transactions;
    public Wallet(String userName) {
        this.userName = userName;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }
    public String getUserName() {
        return userName;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public List<String> getTransactions() {
        return transactions;
    }
}
package wallet;
import java.util.Scanner;
public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        WalletService service = new WalletService();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        Wallet user = new Wallet(name);
        Wallet friend = new Wallet("Friend");
        int choice;
        do {
            System.out.println("\n--- Digital Wallet ---");
            System.out.println("1. Add Money");
            System.out.println("2. Send Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double add = sc.nextDouble();
                    service.addMoney(user, add);
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    double send = sc.nextDouble();
                    service.sendMoney(user, friend, send);
                    break;
                case 3:
                    service.checkBalance(user);
                    break;
                case 4:
                    service.showTransactions(user);
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }
}