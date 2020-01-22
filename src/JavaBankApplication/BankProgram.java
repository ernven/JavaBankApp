package JavaBankApplication;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BankProgram {
	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Account> accountList = new ArrayList<Account>();
	private static DecimalFormat twoDecimals = new DecimalFormat("0.00");
	private static String accountNumber;
	private static double amount;
	
	
	public static void main(String[] args) {
		int choice = -1;

		while (choice != 0) {

			switch (choice) {
			case 1:
				listAccounts();
				break;
			case 2:
				addAccount();
				break;
			case 3:
				depositMoney();
				break;
			case 4:
				withdrawMoney();
				break;
			case 5:
				deleteAccount();
				break;
			}

			displayMenu();
			choice = Integer.parseInt(input.nextLine());
		}

		System.out.println("\nThe program ends now. Bye!");
		input.close();
	}

	private static void displayMenu() {
		String line = "-----------------------------------------------------"
				+ "---------------------------------------------------------------";
		System.out.println(line);
		System.out.print(" 0 = Quit | 1 = List accounts | 2 = Add an account | " +
						 "3 = Deposit money | 4 = Withdraw money | 5 = Delete an account \n");
		System.out.println(line);
		System.out.print("Enter your choice: ");
	}

	private static void listAccounts() {
		System.out.print("\n*** Account list ***\n");
		for (Account account : accountList) {
			System.out.println("Number: " + account.getAccountNumber() + " | Balance: "
					+ twoDecimals.format(account.getBalance()) + " euros");
		}
		System.out.println();
	}

	private static void addAccount() {
		System.out.print("\n*** Add an account ***\n");
		System.out.print("Enter account number: ");
		accountNumber = input.nextLine();
		if (findAccount(accountNumber) == null) {
			accountList.add(new Account(accountNumber));
			System.out.println("\nAccount created successfully!\n");
		} else {
			System.out.println("Account not created. Account " + accountNumber + " exists already!\n");
		}
	}

	// The following helper method is used to find accounts
	// and will be used for checks
	
	private static Account findAccount(String accountNumber) {
		Account myAccount = null;
		for (Account account : accountList) {
			if (account.getAccountNumber().equals(accountNumber)) {
				myAccount = account;
			}
		}
		return myAccount;
	}

	private static void depositMoney() {
		System.out.print("\n*** Deposit money to an account ***\n");
		System.out.print("Enter account number: ");
		accountNumber = input.nextLine();
		if (findAccount(accountNumber) == null) {
			System.out.println("Account " + accountNumber + " does not exist!\n");
		} else {
			System.out.print("Enter the amount to be deposited: ");
			amount = Double.parseDouble(input.nextLine());
			if (amount < 0) {
				System.out.println("\nCannot deposit a negative amount!\n");
			} else {

				for (Account account : accountList) {
					if (account.getAccountNumber().equals(accountNumber)) {
						account.deposit(amount);
						System.out.println("\nDeposit completed successfully!\n");
					}
				}

			}
		}
	}

	private static void withdrawMoney() {
		System.out.print("\n*** Withdraw money from an account ***\n");
		System.out.print("Enter account number: ");
		accountNumber = input.nextLine();
		if (findAccount(accountNumber) == null) {
			System.out.println("Account " + accountNumber + " does not exist!\n");
		} else {
			System.out.print("Enter the amount to be withdrawn: ");
			amount = Double.parseDouble(input.nextLine());
			if (amount < 0) {
				System.out.println("\nCannot withdraw a negative amount!\n");
			} else {

				if (findAccount(accountNumber).withdraw(amount)) {
					System.out.println("\nWithdrawal completed successfully!\n");
				} else {
					System.out.println("\nWithdrawal not completed. Available balance is too low.\n");
				}
			}
		}
	}

	private static void deleteAccount() {
		System.out.print("\n*** Delete an account ***\n");
		System.out.print("Enter account number: ");
		accountNumber = input.nextLine();
		if (findAccount(accountNumber) == null) {
			System.out.println("Account " + accountNumber + " does not exist!\n");
		} else {
			accountList.remove(findAccount(accountNumber));
			System.out.println("\nAccount deleted successfully!\n");
		}
	}
}