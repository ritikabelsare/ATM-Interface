package com.AtmMachine.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.AtmMachine.database.Verify;
import com.AtmMachine.entity.Account;
import com.AtmMachine.entity.Login;

public class AtmOperations {

	Verify v = new Verify();
	Scanner sc = new Scanner(System.in);

	// Verify User id and Password
	protected boolean verify() {
		boolean flag = false;
		int c=0;
		do {
			try {
				System.out.println("Enter the User Id : ");
				String id = sc.next();

				System.out.println("Enter the Password : ");
				String pass = sc.next();

				if (v.verify(id, pass)) {
//					System.out.println("Verification Successful....");
					flag = true;
				} else {
					System.out.println("Verification Fails....\nRe-Enter Id & Password");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Do You To Re-Enter ? Press 1 to continue otherwise press 0");
			c=sc.nextInt();
		}while (flag == false && c==1);
		return flag;
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

	protected void accountInfo(Login login) {
		// account info

		try {

			int accNo = login.getAccNo();
			System.out.println("--------------------------------------------");
			ResultSet set = v.accountVerify(accNo);
			System.out.println("Account Number : " + set.getInt(1));
			System.out.println("Holder Name : "+set.getString(2));
			System.out.println("Balance : "+set.getDouble(3));
			System.out.println("Email Id : "+set.getString(4));
			System.out.println("--------------------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();

	}

	protected void createAccount() {
		System.out.println("Enter Holder Name : ");
		String holderName = sc.next();
		System.out.println("Enter Balance : ");
		double balance = sc.nextDouble();
		System.out.println("Enter Email : ");
		String emailId = sc.next();
		Account acc = new Account(holderName, balance, emailId);
		if (v.createAccount(acc) > 0) {
			System.out.println("Account Creation Successful...");
		} else {
			System.out.println("Creation Fails...");
		}
	}

}
