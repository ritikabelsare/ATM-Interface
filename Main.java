package com.AtmMachine.test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean repeator = false;
		
		System.out.println("\t\t******* Welcome to ATM Machine ******* \n");
		while (repeator == false) {
			try {
				ATM atm = new ATM();
				Scanner sc = new Scanner(System.in);
				int choice = 0;

				while (choice != 5) {
					System.out.println("press 1 : To create an ATM Account: ");
					System.out.println("Press 2 : For Login: ");
					System.out.println("Press 3 : Display All ATM Accounts: ");
					System.out.println("Press 4 : To See overall Transaction Activity");
					System.out.println("Press 5 : Exit ");
					System.out.println("Press 6 : For Go To Bank for Create an Account");
					choice = sc.nextInt();
					
					switch (choice) {
					case 1:
						// Generate ATM UserId and password
						repeator=true;
						atm.creating();
						
						break;

					case 2:
						// Login
						repeator=true;
						atm.login();
						break;

					case 3:
						// ATM Info
						repeator=true;
						atm.display();
						break;
						
					case 4: 
						//to see overall transaction history
						atm.allTransactionHistory();
						break;

					case 5:
						// exit
						repeator=true;
						System.out.println("\t\t Thank you for Visiting....");
						break;

					case 6:
						repeator=true;
						System.out.println("********* Welcome to Bank **********");
						atm.createAccount();
						break;

					default:
						repeator=true;
						System.out.println("Invalid Choice\n Enter correct Choice: ");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input\nPlease Enter again\n\n");
			}
		}

	}
}
