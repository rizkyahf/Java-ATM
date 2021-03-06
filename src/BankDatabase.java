
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class BankDatabase {
   private Account[] accounts; // array of Accounts
   // myadd new
//   private Map<>
   // end myadd
   
   public BankDatabase() {
      accounts = new Account[10]; // just 2 accounts for testing
      // Akun bank
      accounts[0] = new Account(1234, 4321, 1000.0, 1200.0);
      accounts[1] = new Account(8765, 5678, 200.0, 200.0);
      //Akun buat tokopedia
      accounts[2] = new Account(39028765, 1234, 0, 0);
      // Akun buat ETol
      accounts[3] = new Account(11111, 1234, 0.0, 0.0);
      // Akun buat Shopee
      accounts[4] = new Account(40418765, 1234, 0, 0);
      //Akun buat OVO
      accounts[5] = new Account(33400890, 1234, 0, 0);
      // Akun buat Donasi
       accounts[6] = new Account(9988, 2808, 0.0, 0.0);
      // Akun buat Dana
       accounts[7] = new Account(2529123, 1234, 0, 0);
      // Akun buat Bukalapak
       accounts[8] = new Account(1104123, 1234, 0, 0);
       // akun buat gopay
       accounts[9] = new Account(4114045,1234,0,0);
      // end add
   }
   
//   private Account getAccount(int accountNumber) {
   public Account getAccount(int accountNumber) {
       // myadd
       for(Account key : accounts){
           if(key.getAccountNumber() == accountNumber) return key;
       }
       // end add
       
      return null; // if no matching account was found, return null
   } 
   
   public boolean getStatusAccount(int accountNumber){
      for (Account key : accounts){
          if(key.getAccountNumber() == accountNumber) return key.getStatus();
      }
       
       return false;
   }
   
   public LinkedHashSet<AccountLog> getAccountLog(int userAccountNumber){
       Account userAccount = getAccount(userAccountNumber);
       return userAccount.getAccountLog();
   }

   public boolean authenticateUser(int userAccountNumber) {
        // attempt to retrieve the account with the account number
        Account userAccount = getAccount(userAccountNumber);

        // if account exists, return result of Account method validatePIN
        if (userAccount != null) {
        // System.out.println(userAccount.getPin());
          return true;
        }
        else {
           return false; // account number not found, so return false
        }
    } 

   public double getAvailableBalance(int userAccountNumber, int CurrencyUnit) {
      return getAccount(userAccountNumber).getAvailableBalance(CurrencyUnit);
   } 

   public double getTotalBalance(int userAccountNumber, int CurrencyUnit) {
      return getAccount(userAccountNumber).getTotalBalance(CurrencyUnit);
   } 

   public void credit(int userAccountNumber, double amount, int CurrencyUnit) {
      getAccount(userAccountNumber).credit(amount,CurrencyUnit);
   }

   public void debit(int userAccountNumber, double amount, int CurrencyUnit) {
      getAccount(userAccountNumber).debit(amount,CurrencyUnit);
   } 
   boolean userauthentication(int accountNumber) {
        Account userAccount = getAccount(accountNumber);
        return userAccount != null;
    }
   
   boolean cekstatus(int userAccountNumber){
       Account user = getAccount(userAccountNumber);
       boolean cek = false;
       
       cek = getStatusAccount(userAccountNumber);
       
       return cek;
   }
   public void Blokir(int userAccountNumber, boolean Status){
       Account user = getAccount(userAccountNumber);
       
       user.setStatus(Status);
   }
   
   public void ChangePin(int userAccountNumber, int newPin) {
       getAccount(userAccountNumber).setPin(newPin);
    } 
   public boolean authenticateUsertoTransfer (int userAccountNumber) {
       Account userAccount = getAccount (userAccountNumber);
       
       if (userAccount != null) {
           return true;
       }
       else{
           return false;
       }
   }
} 