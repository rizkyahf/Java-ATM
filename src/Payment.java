
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Payment extends Transaction{
        private int amount;
        private double jumlah;
        private Keypad keypad;
        private DepositSlot depositSlot;
        private final static int CANCELED = 6;
        private int destinationNo;
        private int sourceNo;
//        private Account destAccount;
        private BankDatabase bankDatabase;
        private CashDispenser cashDispenser;
        private int currentAccountNumber;
        private int CurrencyUnit;
        
    private boolean userAuthenticated;
//        private boolean accountAuthentication;
        
    public Payment(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad,DepositSlot atmDepositSlot, CashDispenser atmCashDispenser
        ,int atmCurrencyUnit) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        
        keypad = atmKeypad;
//        depositSlot =  atmDepositSlot;
        destinationNo  = 0;
        bankDatabase =  atmBankDatabase;
        sourceNo = userAccountNumber;
        amount = 0;
        cashDispenser = atmCashDispenser;
        CurrencyUnit = atmCurrencyUnit;
        
    }

    @Override
    public void execute() {
       double availableBalance;
//       int userChoice = 0;
        Screen screen = getScreen();

//       DonasiKitabisa();
       amount = DisplayMenuPayment();
       if  ( amount != 6){
       if (cashDispenser.isSufficientCashAvailable(amount,CurrencyUnit)){
           BankDatabase atmBankDatabase = super.getBankDatabase();
           availableBalance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),amount);
           if (amount <= availableBalance){
               cashDispenser.dispenseCash(amount,CurrencyUnit);
               atmBankDatabase.getAccount(super.getAccountNumber()).credit(amount,CurrencyUnit);
            }else screen.displayMessage("\nYou don't have enough balance!");
       }
    }
    }
    
    
//    private int prompForDestinationAccount(){
//        int userChoice = 0;
//        Screen screen = getScreen();
//        
//        int input = keypad.getInput();
//        switch(input){
//            case 1 ://VoucherListrik();
//                break;
//            case 2 : displayMenuDonasi(); break;
//        }
//        if (input == CANCELED){
//            screen.displayMessageLine("\nCanceling transaction...");
//            return CANCELED;
//        }else {
//            return (int) input;
//        }
//      
//        }
   private int DisplayMenuPayment(){
        Screen screen = getScreen();
        int userChoice = 0;
         while (userChoice == 0){
             screen.displayMessage("\nDestination Menu: \n");
         screen.displayMessageLine("1 - Electricity Voucher");
         screen.displayMessageLine("2 - Donation");
         screen.displayMessageLine("3 - Buy Cellphone Credit");
         screen.displayMessageLine("4 - TBD");
         screen.displayMessageLine("5 - TBD");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose adestination: ");
         int input = keypad.getInput();
         switch(input){
             case 1 : 
                userChoice = DisplayMenuListrik();
             break;
             case 2 :
               DonasiKitabisa();
                      break;
             case 3 :beliPulsa(); 
                   break;
             case 4 :screen.displayMessageLine("\nStill Planning.."); break;
             case 5 :screen.displayMessageLine("\nStill Planning.."); break;
                          
             case CANCELED:
                 screen.displayMessageLine("\nCanceling Transaction...");
                 userChoice = CANCELED;break;
             default : screen.displayMessageLine("\nInvalid selection..");
         }
         }
     return userChoice;
   }
    
    private int DisplayMenuListrik(){
        int userChoice = 0;
        
        Screen screen = getScreen();
        
        int[] amounts = {0, 20, 40, 60,100,200};
        while (userChoice == 0){
         screen.displayMessageLine("\nVoucher Amount Menu:");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $40");
         screen.displayMessageLine("3 - $60");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose a Voucher amount: ");
         
         int input = keypad.getInput();
         
         switch(input){
             case 1 :
             case 2 :
             case 3 :
             case 4 :
             case 5 :
                 
//              screen.displayMessageLine("\nYour Voucher Number IS : ");
//              stroom();
//              userChoice = amounts[input];
                 double availableBalance;
                 BankDatabase atmBankDatabase = super.getBankDatabase();
                availableBalance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),amount);
              if (input < availableBalance ){
                  userChoice = amounts[input];
                  screen.displayMessageLine("\nYour Voucher Number IS : ");
                  stroom();
                }
               break;
             case CANCELED :
                 userChoice = CANCELED;
                 break;
             default : screen.displayMessageLine("\nInvalid Selection...");
         }
        }
        return userChoice;
    }
    
     private void stroom(){
         Random rand = new Random();
         int n ;
         for (int i =1;i<=5;i++){
             n = 1000 + rand.nextInt(9000);
             System.out.print(n + " ");
         }
         System.out.print("");
     }
//     private int displayMenuDonasi(){
//      Screen screen = getScreen();
//      screen.displayMessageLine("\nPilih Donasi:");
//      screen.displayMessageLine("1 - kitabisa.com");
//      screen.displayMessageLine("6 - Exit\n");
//      screen.displayMessage("Enter a choice: ");
//      return keypad.getInput(); // return user's selection
//     }
//      private int promptForTujuanDonasi() {
//      int userChoice = 0; // local variable to store return value
//      Screen screen = getScreen(); // get reference to screen
//
//      int input = keypad.getInput(); // receive input of deposit amount
//      switch(input){
//          case 1: DonasiKitabisa(); break;
////          case 2: ;break
//      }
//      if (input == CANCELED) {
//        screen.displayMessageLine(
//           "\nCanceling transaction...");
//         return CANCELED;
//      }
//      else {
//         return (int) input; 
//      }
//   }
     private double promptForTransferAmount(){
        Screen screen = getScreen();
        
        // input account number
        screen.displayMessage("\nMasukkan jumlah yang akan didonasikan" + 
         " (dalam CENTS) : ");
        int input = keypad.getInput();
        return (double) input / 100;
       // receive input of deposit amount
//        return (double) input / 100; // return dollar amount
    } 
     private void DonasiKitabisa()
     {
      Screen screen = getScreen();
        BankDatabase bankDatabase = getBankDatabase();
        int accountNumber = 9988;
    
        
        userAuthenticated = 
        bankDatabase.authenticateUsertoTransfer(accountNumber);
        if (userAuthenticated)
        {
            currentAccountNumber = accountNumber;
            jumlah = promptForTransferAmount();
            bankDatabase.debit(currentAccountNumber,jumlah,CurrencyUnit);
            bankDatabase.credit(getAccountNumber(), jumlah,CurrencyUnit);
            screen.displayMessage("\nTransfer Successful, Thank You");
        }
     else {
           screen.displayMessageLine("Transfer Failed");   
     }
     }
     private void beliPulsa() {
         double availableBalance;    // amount available for withdrawal  
        // get references to bank database and screen  
        BankDatabase bankDatabase = getBankDatabase();  
        Screen screen = getScreen();  
        // loop until cash is dispensed or the user cancels   
          screen.displayMessageLine("\nEnter your phone number : ");
          String noHp = keypad.getLine();
          // obtain a chosen withdrawal amount from the user  
          amount = displayMenuOfAmountsPulsa();  
          // check whether user choose a withdrawal amount or canceled  
          if(amount != 6){  
            // get available balance of account involved  
            availableBalance = bankDatabase.getAvailableBalance(getAccountNumber(), CurrencyUnit);  
            // check whether the user has enough money in the account  
            if(amount <= availableBalance){    
                // update the account involved to reflect the withdrawal  
                bankDatabase.credit(getAccountNumber(), amount, CurrencyUnit);  
                // instructs user to take cash  
                screen.displayMessageLine("\nBerhasil mengisi pulsa sejumlah $."+amount+" ke no "+noHp+"");  
            }  // end if  
            else{  
              // not enough money available in user's account  
              screen.displayMessageLine("\nInsufficient funds in your account.");  
              screen.displayMessageLine("\nPlease choose a smaller amount.");  
            }  // end if  
          }  // end if  
          else{  
            // user choose cancel menu option  
            screen.displayMessageLine("\nCancelling transactions...");  
            return;   // return to main menu because user canceled  
          }  // end if  
     }
     
     private int displayMenuOfAmountsPulsa() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 10, 20, 50, 100, 200};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
         screen.displayMessageLine("\nAmount Menu:");
         screen.displayMessageLine("1 - $10");
         screen.displayMessageLine("2 - $20");
         screen.displayMessageLine("3 - $50");
         screen.displayMessageLine("4 - $100");
         screen.displayMessageLine("5 - $200");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nChoose amount: ");

         int input = keypad.getInput(); // get user input through keypad

         // determine how to proceed based on the input value
         switch (input) {
            case 1: // if the user chose a withdrawal amount 
            case 2: // (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: // corresponding amount from amounts array
            case 4:
            case 5:
               userChoice = amounts[input]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 
      return userChoice; // return withdrawal amount or CANCELED
   }
}

