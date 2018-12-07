// myadd new
public class Transfer extends Transaction {
   private double amount; // amount to transfer
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option
   private int destinationNo;
   private int sourceNo;
   private Account destAccount;
   private BankDatabase bankDatabase; // account information database
   private CashDispenser cashDispenser; // reference to cash dispenser

   public Transfer(int userAccountNumber, Screen atmScreen, 
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
      Screen screen = getScreen(); // get reference to screen

      screen.displayMessage("\nPlease enter destination account number (or 0 to cancel): ");
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
// end add
