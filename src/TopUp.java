/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Velia Sagita - 171511065
 */
public class TopUp extends Transaction {
       private double amount; // amount to transfer
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option
   private int destinationNo;
   private int sourceNo;
   private Account destAccount;
   private BankDatabase bankDatabase; // account information database
   private CashDispenser cashDispenser; // reference to cash dispenser

   public TopUp(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot, CashDispenser atmCashDispenser) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      
      depositSlot = atmDepositSlot;
      keypad = atmKeypad;
      destinationNo = 0;
      bankDatabase = atmBankDatabase;
      sourceNo = userAccountNumber;
      amount = 0;
      cashDispenser = atmCashDispenser;
   } 

   // perform transaction
   @Override
   public void execute() {
       Screen screen = getScreen();
       double availableBalance;
       
       destinationNo = promptForDestinationAccount();
       if(destinationNo != CANCELED){
           destAccount = bankDatabase.getAccount(destinationNo);
           if(destAccount != null && destinationNo != sourceNo){
               amount = promptForAmount();
               if(amount != CANCELED){
                   availableBalance = bankDatabase.getAvailableBalance(sourceNo);
                   if(amount <= availableBalance){
//                       cashDispenser.dispenseCash((int)amount);
                       bankDatabase.getAccount(sourceNo).credit(amount);
                       bankDatabase.getAccount(destinationNo).debit(amount);
                   }
                   else {
                       screen.displayMessageLine("\nInsufficent Funds...");
                   }
               }
           }
           else{
               screen.displayMessageLine(
                  "\nAccount destination invalid...");
           }
       }
   }

   private int promptForDestinationAccount() {
      int userChoice = 0; // local variable to store return value
      Screen screen = getScreen(); // get reference to screen

      
      while (userChoice ==0){
         screen.displayMessage("\nDestination Menu: ");
         screen.displayMessageLine("1 - E-Toll");
         screen.displayMessageLine("2 - Dana");
         screen.displayMessageLine("3 - OVO");
         screen.displayMessageLine("4 - Shopee");
         screen.displayMessageLine("5 - Tokopedia");
         screen.displayMessageLine("6 - Bukalapak");
         screen.displayMessageLine("0 - Cancel transaction");
         screen.displayMessage("\nChoose adestination: ");
      }
      int input = keypad.getInput(); // receive input of deposit amount
      
      if (input == CANCELED) {
        screen.displayMessageLine(
           "\nCanceling transaction...");
         return CANCELED;
      }
      else {
         return (int) input; 
      }
   }
   
   private double promptForAmount(){
       Screen screen = getScreen(); // get reference to screen

      screen.displayMessage("\nPlease enter amount to transfer in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput(); // receive input of deposit amount
      
      if (input == CANCELED) {
        screen.displayMessageLine(
           "\nCanceling transaction...");
         return CANCELED;
      }
      else {
         return (double) input / 100; // return dollar amount
      }
   }
}
