
import java.util.LinkedHashSet;

public class Account {
   private int accountNumber; // account number
   private int pin; // PIN for authentication
   private double availableBalance; // funds available for withdrawal
   private double totalBalance; // funds available & pending deposits
   // myadd new
   private LinkedHashSet<AccountLog> accountLogs = new LinkedHashSet<>();
   private boolean Status;
   // end add

   // Account constructor initializes attributes
   public Account(int theAccountNumber, int thePIN, 
      double theAvailableBalance, double theTotalBalance) {
      accountNumber = theAccountNumber;
      pin = thePIN;
      
      // myadd
      availableBalance = theAvailableBalance;
      totalBalance = theTotalBalance;
      // end add
      // myadd new
      AccountLog temp = new AccountLog(true, theTotalBalance, theTotalBalance);
      accountLogs.add(temp);
      Status = true;
      // end myadd
   }

   // determines whether a user-specified PIN matches PIN in Account
   public boolean validatePIN(int userPIN) {
      if (userPIN == pin) {
         return true;
      }
      else {
//         return true;
           return false;
      }
   } 

   // returns available balance
   public double getAvailableBalance(int atmCurrencyUnit) {
      if(atmCurrencyUnit == 2) return availableBalance*getConversion(atmCurrencyUnit);
      else return availableBalance;
   } 

   // returns the total balance
   public double getTotalBalance(int atmCurrencyUnit) {
      if(atmCurrencyUnit == 2) return totalBalance*getConversion(atmCurrencyUnit);
      else return totalBalance;
   } 

   public void credit(double amount,int atmCurrencyUnit) {
     // myadd
     int n = 1;
     if (atmCurrencyUnit == 2) n = getConversion(atmCurrencyUnit); 
     this.availableBalance -= amount/n;
     this.totalBalance -= amount/n;
     // end add
     // myadd new
     AccountLog temp = new AccountLog(false, amount, this.totalBalance);
     accountLogs.add(temp);
     // end myadd
   }

   public void debit(double amount,int atmCurrencyUnit) {
     // myadd
     int n = 1;
     if (atmCurrencyUnit == 2) n = getConversion(atmCurrencyUnit);
     this.availableBalance += amount/n;
     this.totalBalance += amount/n;
     // end add
     // myadd new
     AccountLog temp = new AccountLog(true, amount, this.totalBalance);
     accountLogs.add(temp);
     // end add
   }

   public int getAccountNumber() {
      return accountNumber;  
   }
   
   // myadd new
   public LinkedHashSet<AccountLog> getAccountLog(){
       return accountLogs;
   }
   
   public void setStatus(boolean Stat){
       this.Status = Stat;
   }
   
   public boolean getStatus(){
       return Status;
   }
   // end add

    public void setPin(int pin) {
        this.pin = pin;
    }
    
    public int getConversion(int CurrencyUnit){
        if(CurrencyUnit == 2) return 14426;
        else return 1;
    }

} 