package com.AtmMachine.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.AtmMachine.database.Verify;
import com.AtmMachine.entity.Account;
import com.AtmMachine.entity.Login;
import com.AtmMachine.entity.TransactionHistory;
import com.AtmMachine.entity.Transfer;
import com.AtmMachine.exception.DuplicateEntry;
import com.AtmMachine.exception.LowBalanceException;

public class ATM {

	AtmOperations atmOperation = new AtmOperations();
	Verify v = new Verify();
	Scanner sc = new Scanner(System.in);
	LocalDateTime myDateObj = LocalDateTime.now();
//    System.out.println("Before formatting: " + myDateObj);
	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	String formattedDate = myDateObj.format(myFormatObj);
//    System.out.println("After formatting: " + formattedDate);

	protected void creating() {
		// After create option
		System.out.println(formattedDate);
		boolean flag = false, status = false;
		System.out.println("Welcome to ATM Machine");
		while (flag != true) {
			try {
				System.out.println("Enter Account Number : ");
				int accNo = sc.nextInt();
				ResultSet set = v.accountVerify(accNo);
				if (set != null) {
					System.out.println("Enter the Login Id : ");
					String id = sc.next();

					System.out.println("Enter the Login Password : ");
					String pass = sc.next();

					Login login = new Login(id, pass, accNo);
					flag = true;
					status = true;
					if (atmOperation.v.createAtm(login)) {

						TransactionHistory t = new TransactionHistory(accNo, "Create Card", formattedDate);
						v.addTransactionHistoryForCreate(t);
						afterVerified(login);
					} else {
						System.out.println(
								"\nAccount Number Not Exits... Please Connect with Bank or \nPress 5 to Create an account\n");
					}
				} else {
					System.out.println("Account Number not Exists...\nPlease Enter Correct Account Number : ");
				}
			} catch (DuplicateEntry e) {
				new DuplicateEntry().getMessage("User Id Already Exits Please \n");
			}
		}
	}

	private void afterVerified(Login login) {
		int choice = 0;
		while (choice != 3) {
			System.out.println("Press 1 : Account Information");
			System.out.println("Press 2 : Transaction");
			System.out.println("Press 3 : Go Home Page ");
			System.out.println("For More Details\n Enter the Choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// account information
				System.out.println("Account Information");
				accountInfo(login);
				break;

			case 2:
				// transaction
				System.out.println("Account Transaction");
				transaction(login);
				break;

			case 3:
				// exit
				System.out.println("You Are in Home Page ");
				break;
			default:
				System.out.println("Invalid Choice\nPlease Enter choice again!");
			}
		}
	}

	protected void login() {
		// after choosing login option
		boolean flag = false;
		int c = 0;
		do {
			try {
				System.out.println("Enter the User Id : ");
				String id = sc.next();

				System.out.println("Enter the Password : ");
				String pass = sc.next();

				if (v.verify(id, pass)) {
					System.out.println("Verification Successful....");
					Login login = new Login(id, pass);
					afterVerified(login);

				} else {
					System.out.println("Verification Fails....\nRe-Enter Id & Password");
					System.out.println("Do You To Re-Enter ? Press 1 to continue otherwise press 0");
					c = sc.nextInt();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (flag == false && c == 1);
	}

	protected void display() {
		System.out.println("Enter the Admin Password for security purpose! ");
		String admin = sc.next();
		if (admin.equals("Ritika")) {
			v.display();
		} else {
			System.out.println("Invalid Admin Login");
		}

	}

	private void accountInfo(Login login) {
		// account info
		try {
			int accNo = v.searchAccount(login);
			System.out.println("--------------------------------------------");
			ResultSet set = v.accountVerify(accNo);
			System.out.println("Account Number : " + set.getInt(1));
			System.out.println("Holder Name : " + set.getString(2));
			System.out.println("Balance : " + set.getDouble(3));
			System.out.println("Email Id : " + set.getString(4));
			System.out.println("--------------------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();

	}

	protected void createAccount() {
		boolean flag = false;
		while (flag == false) {
			try {
				System.out.println("Enter Holder Name : ");
				String holderName = sc.nextLine();
				holderName = sc.nextLine();
				System.out.println("Enter Balance : ");
				double balance = sc.nextDouble();
				if (balance < 1000) {
					throw new LowBalanceException();
				}
				System.out.println("Enter Email : ");
				String emailId = sc.next();
				Account acc = new Account(holderName, balance, emailId);
				if (v.createAccount(acc) > 0) {
					System.out.println("Account Creation Successful...");
					System.out.println("Your Account Number is  : " + v.searchAccountNo(emailId));
					flag = true;
				} else {
					System.out.println("Creation Fails...\n Email Already Exits");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please Enter Valid Input");
			} catch (LowBalanceException i) {
				System.out.println(
						"Insufficient Balance to create an account\nMinumum 1000 is the Limit of Creating an account ");
			}
		}
	}

	private void transaction(Login login) {
		int choice = 0;

		while (choice != 5) {
			System.out.println("Press 1 : To Withdraw ");
			System.out.println("Press 2 : Deposit ");
			System.out.println("Press 3 : Transaction History ");
			System.out.println("Press 4 : Transfer");
			System.out.println("Press 5 : Quit ");
			System.out.println("Enter Your Choice : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// withdraw
				withdraw(login);
				break;
			case 2:
				// deposit
				deposit(login);
				break;
			case 3:
				// transaction history
				transactionHistory(login);
				break;
			case 4:
				// transfer
				transfer(login);
				break;
			case 5:
				// exit
				System.out.println("You are in Option Page");
				break;
			default:
				System.out.println("Invalid Input");
			}
		}
	}

	private void withdraw(Login login) {
		try {
			int accNo = v.searchAccount(login);
			System.out.println("Enter the amount you want to Withdraw : ");
			int amount = sc.nextInt();
			if (v.withdraw(accNo, amount)) {
				TransactionHistory t = new TransactionHistory(accNo, "Withdraw", amount, formattedDate);
				v.addTransactionHistory(t);
				System.out.println("Transaction Successful...");
				System.out.println("Do You Want To Show Account Details Press 1 otherwise press 0");
				int choice = sc.nextInt();
				if (choice == 1) {
					accountInfo(login);
				}
			} else {
				System.out.println("Transaction Fail......");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input\nPlease Enter Again");
		}
	}

	protected void deposit(Login login) {
		try {
			int accNo = v.searchAccount(login);
			System.out.println("Enter the amount you want to Deposit : ");
			int amount = sc.nextInt();
			if (v.deposit(accNo, amount)) {
				TransactionHistory t = new TransactionHistory(accNo, "deposit", amount, formattedDate);
				v.addTransactionHistory(t);
				System.out.println("Transaction Successful...");
				System.out.println("Do You Want To Show Account Details Press 1 otherwise press 0");
				int choice = sc.nextInt();
				if (choice == 1) {
					accountInfo(login);
				}
			} else {
				System.out.println("Transaction Fail......");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input\nPlease Enter Again");
		}
	}

	private void transactionHistory(Login login) {
		int accNo = v.searchAccount(login);
		v.searchHistory(accNo);
	}

	private void transfer(Login login) {
//		System.out.println("Enter Sender Account Number : ");

		int sender = v.searchAccount(login);
		System.out.println("Enter Receiver Account Number : ");
		int receiver = sc.nextInt();
		System.out.println("Enter Amount : ");
		int amount = sc.nextInt();
//		Transfer
		Transfer t = new Transfer(sender, receiver, amount);
		if (v.findSenderReceiver(t)) {
			TransactionHistory history = new TransactionHistory(sender, "transfer", amount, receiver, formattedDate);
			v.addTransactionHistoryForTransfer(history);
		}

	}

	public void allTransactionHistory() {

		try {
			ResultSet set = v.allTransactionHistory();
			while (set.next()) {
				System.out.print("Account Number : "+set.getInt(1));
				System.out.print("\tAccount Action  : "+set.getString(2));
				System.out.print("\tAmount : "+set.getInt(3));
				System.out.print("\tReceiver : "+set.getInt(4));
				System.out.print("\tTime : "+set.getString(5));
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("No any Entry Recorded..");
		}

	}

}
