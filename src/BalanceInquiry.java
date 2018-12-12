public class BalanceInquiry extends Transaction {
   // BalanceInquiry constructor
   private int CurrencyUnit;
   public BalanceInquiry(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, int atmCurrencyUnit) {

      super(userAccountNumber, atmScreen, atmBankDatabase);
      CurrencyUnit = atmCurrencyUnit;
   } 

   // performs the transaction
   @Override
   public void execute() {
      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();

      // get the available balance for the account involved
      double availableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber(),CurrencyUnit);

      // get the total balance for the account involved
      double totalBalance = 
         bankDatabase.getTotalBalance(getAccountNumber(),CurrencyUnit);
      
      // display the balance information on the screen
      screen.displayMessageLine("\nBalance Information:");
      screen.displayMessage(" - Available balance: "); 
      screen.displayDollarAmount(availableBalance,CurrencyUnit);
      screen.displayMessage("\n - Total balance:     ");
      screen.displayDollarAmount(totalBalance,CurrencyUnit);
      screen.displayMessageLine("");
   }
} 