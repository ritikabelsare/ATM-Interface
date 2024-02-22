# ATM-Interface
Problem Statement :
The ATMs in our cities are built on Java, as we have all seen them. It is a console-based application with five different classes. In order to use the system, the user must enter his or her user ID and pin when it starts. Once the details are entered successfully, ATM functionality is unlocked. As a result of the project, the following operations can be performed:
Transactions History
Withdraw
Deposit
Transfer
Quit

Code Exceution :-
This Java program simulates an ATM (Automated Teller Machine) system with several functionalities for managing user accounts and transactions. 
_**Main Class:-**_

1. **Main Class (`Main`)**:
   - This class serves as the entry point of the program.
   - It displays a welcome message and initiates a loop to prompt the user for actions.
   - The loop continues until the user chooses to exit the program.
   - Exception handling is implemented to handle invalid input using a try-catch block.

2. **ATM Class**:
   - The specific implementation of the ATM functionalities is encapsulated within the `ATM` class.
   - It contains methods for various operations such as creating an account, logging in, displaying account information, and viewing transaction history.

3. **Functionality**:
   - **Create an ATM Account (Option 1)**:
     - This option allows a user to create a new ATM account.
     - When we create an account at that time account should be created otherwise,
     - atm card not create so at the time we should go with option 5 to create an account in bank.
     - It prompts the user to provide necessary details such as user ID and PIN.
     - Once entered, the account is created, and the user can proceed with other actions.
     - If Id is already exits then you can not create an account from same User Id.

   - **Login (Option 2)**:
     - Users can log in using their existing account credentials.
     - The system verifies the provided credentials and grants access upon successful authentication.

   - **Display All ATM Accounts (Option 3)**:
     - This option displays information about all existing ATM accounts.
     - Users can view details such as user ID. This option only visible to Administrator no one can access it without password.

   - **View Overall Transaction Activity (Option 4)**:
     - Users can view the overall transaction activity, likely showing a history of transactions across all accounts.
     - Without Admin id and password this history can not be see. only three attempt to enter the password after that they backout.

   - **Exit (Option 5)**:
     - This option allows users to exit the ATM system.
     - Upon selection, the program terminates with a farewell message.

   - **Go To Bank for Creating an Account (Option 6)**:
     - This option simulates visiting a bank branch to create a new account.
     - Users are redirected to a separate method or process for creating an account directly with the bank.

4. **Exception Handling**:
   - The program handles input mismatch exceptions using a try-catch block.
   - If the user enters invalid input, such as a non-integer value, the program prompts them to enter the correct input.

_**ATM Class**_
This Java class `ATM` represents the core functionality of an ATM (Automated Teller Machine) system. It interacts with a database to perform operations such as creating accounts, logging in, displaying account information, conducting transactions (withdrawal, deposit, transfer), and viewing transaction history.

1. **Account Creation (`creating()` method)**:
   - Prompts the user to enter account details like account number, login ID, and password.
   - Verifies the entered account number against the database.
   - If the account number exists, prompts for login credentials.
   - Upon successful verification and login, records the transaction in the transaction history.

2. **Login (`login()` method)**:
   - Prompts the user to enter login credentials (User ID and Password).
   - Verifies the credentials against the database.
   - If the credentials are correct, allows the user to proceed with account-related operations.

3. **Display All ATM Accounts (`display()` method)**:
   - Requires an admin password for security purposes.
   - If the correct password is provided, displays all ATM accounts stored in the database.

4. **Account Information (`accountInfo()` method)**:
   - Retrieves and displays account information such as account number, holder name, balance, and email ID.

5. **Create Account (`createAccount()` method)**:
   - Allows a user to create a new account by providing details like holder name, initial balance, and email ID.
   - Ensures a minimum balance of 1000 during account creation.
   - Generates an account number for the newly created account.

6. **Transaction Handling (`transaction()` method)**:
   - Provides options for various transactions such as withdrawal, deposit, and viewing transaction history.
   - Implements logic for withdrawal and deposit operations, updating the account balance accordingly.
   - Handles exceptions such as insufficient balance during withdrawal.

7. **Transfer Funds (`transfer()` method)**:
   - Facilitates transferring funds from one account to another.
   - Prompts the user to enter the recipient's account number and the amount to transfer.
   - Records the transaction in the transaction history.

8. **View Overall Transaction Activity (`allTransactionHistory()` method)**:
   - Allows an administrator to view the overall transaction history.
   - Requires admin credentials for access.

This class interacts with the `Verify` class, which likely provides methods to verify account credentials, perform database operations, and manage transaction history. Additionally, it utilizes exception handling to manage errors such as invalid input or insufficient balance effectively.
Overall, this ATM simulation program provides users with essential banking functionalities such as account creation, login, account information display, and transaction history viewing, offering a comprehensive experience akin to using a real ATM machine.
