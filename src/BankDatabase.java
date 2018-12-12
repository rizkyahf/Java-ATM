
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
      accounts = new Account[8]; // just 2 accounts for testing
      // Akun bank
      accounts[0] = new Account(1234, 4321, 1000.0, 1200.0);
      accounts[1] = new Account(8765, 5678, 200.0, 200.0);
      //Akun buat tokopedia
      accounts[2] = new Account(39028765, 1234, 0, 0);
      accounts[3] = new Account(39021234, 1234, 0, 0);
      // Akun buat ETol
      accounts[4] = new Account(11111, 1234, 0.0, 0.0);
      accounts[5] = new Account(22222, 8765, 0.0, 0.0);
      // Akun buat Shopee
      accounts[6] = new Account(40418765, 1234, 0, 0);
      accounts[7] = new Account(40411234, 1234, 0, 0);
      // myadd new
      
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
   
   public LinkedHashSet<AccountLog> getAccountLog(int userAccountNumber){
       Account userAccount = getAccount(userAccountNumber);
       return userAccount.getAccountLog();
   }

   public boolean authenticateUser(int userAccountNumber, int userPIN) {
      // attempt to retrieve the account with the account number
      Account userAccount = getAccount(userAccountNumber);

      // if account exists, return result of Account method validatePIN
      if (userAccount != null) {
         return userAccount.validatePIN(userPIN);
      }
      else {
         return false; // account number not found, so return false
      }
   } 

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   } 

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   } 

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   } 
   boolean userauthentication(int accountNumber) {
        Account userAccount = getAccount(accountNumber);
        return userAccount != null;
    }
   
   boolean cekstatus(int userAccountNumber){
       Account user = getAccount(userAccountNumber);
       
       return user.getStatus();
   }
   public void Blokir(int userAccountNumber, boolean Status){
       Account user = getAccount(userAccountNumber);
       
       user.setStatus(Status);
   }
   
   public void ChangePin(int userAccountNumber, int newPin) {
       getAccount(userAccountNumber).setPin(newPin);
    } 
} 