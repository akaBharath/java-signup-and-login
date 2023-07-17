/*
Author: Bharath Bairy (instagram: @akabharathbairy)
Date modified: 17-07-2023
Time modified: 11:48 AM GMT+5:30
*/

import java.util.Scanner;

public class Main{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Account a = new Account();
        Account.menu();
        try {
            while (true) {
                System.out.print("\nEnter action to take: ");
                String input = scanner.next();
                if (input.equals("1")) {
                    a.createAccount();
                    Account.menu();
                } else if (input.equals("2")) {
                    a.loginAccount();
                    Account.menu();
                } else if (input.equals("3")) {
                    a.viewAccountDetails();
                    Account.menu();
                } else if (input.equals("4")) {
                    a.deleteAccount();
                    Account.menu();
                } else if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("ex") || input.equalsIgnoreCase("q")) {
                    break;
                } else {
                    main(args);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
