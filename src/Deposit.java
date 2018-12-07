public class Deposit extends Transaction {
   private double amount; // amount to deposit
   private Keypad keypad; // reference to keypad
   private DepositSlot depositSlot; // reference to deposit slot
   private final static int CANCELED = 0; // constant for cancel option

   // Deposit constructor
   public Deposit(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      
      // myadd
      depositSlot = atmDepositSlot;
      keypad = atmKeypad;
      amount = 0;
      // end add
   } 

   // perform transaction
   @Override
   public void execute() {
       // myadd
       Screen screen = getScreen();
       amount = promptForDepositAmount();
       if(amount != 0){
           System.out.printf("\nPlease insert a deposit envelope containing $%.02f.\n ", amount);

           BankDatabase atmBankDatabase = super.getBankDatabase();
           atmBankDatabase.getAccount(super.getAccountNumber()).debit(amount);
           
           screen.displayMessage("\nYour envelope has been received."+
                   "\nNOTE: The money just deposited will not be available "+
                   "until we verify the amount of any enclosed cash and your checks clear.\n");
       }
       // end add
   }

   // prompt user to enter a deposit amount in cents 
   private double promptForDepositAmount() {
      Screen screen = getScreen(); // get reference to screen

      // display the prompt
      screen.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput(); // receive input of deposit amount
      
      // check whether the user canceled or entered a valid amount
      if (input == CANCELED) {
        // myadd
        screen.displayMessageLine(
           "\nCanceling transaction...");
        // end add
         return CANCELED;
      }
      else {
         return (double) input / 100; // return dollar amount
      }
   }
} 
