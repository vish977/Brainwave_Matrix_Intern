import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM Interface!");

        // Main Menu
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            int option = getValidatedInput(1, 3);

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    if (authenticateUser()) {
                        atmMenu();
                    }
                    break;
                case 3:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }

    private static int getValidatedInput(int min, int max) {
        int input = -1;
        while (input < min || input > max) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();  // Clear invalid input
            }
            input = scanner.nextInt();
            if (input < min || input > max) {
                System.out.println("Invalid option. Please try again.");
            }
        }
        return input;
    }

    private static void registerUser() {
        System.out.print("Enter a new account number: ");
        String accountNumber = scanner.next();

        while (users.containsKey(accountNumber)) {
            System.out.println("Account number already exists. Please enter a different account number.");
            accountNumber = scanner.next();
        }

        System.out.print("Create a new PIN: ");
        String pin = scanner.next();

        users.put(accountNumber, new User(accountNumber, pin, 0.0));
        System.out.println("Registration successful! You can now log in using your account number and PIN.");
    }

    private static boolean authenticateUser() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.next();

        System.out.print("Enter your PIN: ");
        String pin = scanner.next();

        if (users.containsKey(accountNumber) && users.get(accountNumber).getPin().equals(pin)) {
            currentUser = users.get(accountNumber);
            return true;
        } else {
            System.out.println("Authentication failed. Please try again.");
            return false;
        }
    }

    private static void atmMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Logout");
            System.out.print("Please select an option: ");
            int option = getValidatedInput(1, 5);

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    exit = true;
                    System.out.println("You have been logged out.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: $" + currentUser.getBalance());
    }

    private static void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.next();  // Clear invalid input
        }
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
    }

    private static void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.next();  // Clear invalid input
        }
        double amount = scanner.nextDouble();
        currentUser.withdraw(amount);
    }

    private static void changePin() {
        System.out.print("Enter your current PIN: ");
        String currentPin = scanner.next();

        if (currentPin.equals(currentUser.getPin())) {
            System.out.print("Enter your new PIN: ");
            String newPin = scanner.next();
            currentUser.setPin(newPin);
            System.out.println("Your PIN has been successfully changed.");
        } else {
            System.out.println("Incorrect PIN. Please try again.");
        }
    }
}
