import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        double balance = 1000.0;
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n-- Bank Menu --");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current balance: " + balance);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    balance += sc.nextDouble();
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter withdraw amount: ");
                    double amt = sc.nextDouble();
                    if (amt <= balance) {
                        balance -= amt;
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the bank.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }
}