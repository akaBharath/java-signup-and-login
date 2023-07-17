import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Account {
    private ArrayList<AccountWrapper> accounts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void createAccount() {
        System.out.print("\nEnter email (eg. abc@gmail.com): ");
        String email = scanner.next();

        if(isValidEmail(email.toLowerCase())){
            String password = "";
            while (password.length() < 12){
                System.out.print("Create password (must be >12 chars): ");
                password = scanner.next();
            }

            accounts.add(new AccountWrapper(email.toLowerCase(), password));
            System.out.printf("\nAccount (%s) created successfully!\n\n", email.toLowerCase());
            goSleep();
        } else {
            System.out.println("\nInvalid email!\n");
            goSleep();
        }
    }

    public void loginAccount() {
        System.out.print("\nEnter your email (eg. abc@gmail.com): ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        if(isValidLogin(email.toLowerCase(), password)){
            System.out.println("\nLogin successful!");
            System.out.printf("Your email is %s\n\n", email.toLowerCase());
            goSleep();
        } else {
            System.out.println("\nInvalid Email or Password, Try again!");
            goSleep();
        }
    }

    private boolean isValidLogin(String email, String password){
        if(isValidEmail(email.toLowerCase())) {
            for (AccountWrapper a : accounts) {
                if (a.getEmail().equals(email.toLowerCase()) && a.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void viewAccountDetails(){
        System.out.print("\nEnter your email (eg. abc@gmail.com): ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        if(isValidLogin(email.toLowerCase(), password)) {
            System.out.println("\nLogin successful!");
            System.out.printf("Your email is '%s'\n", email.toLowerCase());
            System.out.printf("Your password is '%s'\n\n", password);
            goSleep();
        } else {
            System.out.println("\nInvalid login details, Try again!\n");
            goSleep();
        }
    }

    public void deleteAccount() {
        System.out.print("\nEnter your email to delete: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        if(isValidLogin(email.toLowerCase(), password)){
            System.out.print("\nDo you want to delete this account 'y' or 'n': ");
            String yOrN = scanner.next();
            if(yOrN.equalsIgnoreCase("y") || yOrN.equalsIgnoreCase("yes")){
                AccountWrapper accountToRemove = null;
                for (AccountWrapper account : accounts) {
                    if (account.getEmail().equals(email.toLowerCase()) && account.getPassword().equals(password)) {
                        accountToRemove = account;
                        break;
                    }
                }
                if (accountToRemove != null) {
                    accounts.remove(accountToRemove);
                    System.out.printf("Account (%s) successfully deleted!\n\n", email.toLowerCase());
                    goSleep();
                } else {
                    System.out.println("\nAccount Deletion Failed!\n");
                    goSleep();
                }
            } else {
                System.out.println("\nAccount Deletion Cancelled!\n");
                goSleep();
            }
        } else {
            System.out.println("\nInvalid login details, Try again!\n");
            goSleep();
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public static void menu() {
        System.out.print("""
                [1]. Create Account.
                [2]. Login.
                [3]. View Account Details.
                [4]. Delete Account.
                """);
    }

    public static void goSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class AccountWrapper {
    private String email;
    private String password;

    public String getEmail() {
        return email.toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public AccountWrapper(String email, String password){
        this.email = email;
        this.password = password;
    }
}
