
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
        private Keypad keypad;
        private DepositSlot depositSlot;
        private final static int CANCELED = 0;
        private int destinationNo;
        private int sourceNo;
//        private Account destAccount;
        private BankDatabase bankDatabase;
        private CashDispenser cashDispenser;
//        private boolean accountAuthentication;
        
    public Payment(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad,DepositSlot atmDepositSlot, CashDispenser atmCashDispenser) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        
        keypad = atmKeypad;
//        depositSlot =  atmDepositSlot;
        destinationNo  = 0;
        bankDatabase =  atmBankDatabase;
        sourceNo = userAccountNumber;
        amount = 0;
        cashDispenser = atmCashDispenser;
        
    }

    @Override
    public void execute() {
       double availableBalance;
       
       amount = DisplayMenuPayment();
       if  ( amount != 0){
       if (cashDispenser.isSufficientCashAvailable(amount)){
           BankDatabase atmBankDatabase = super.getBankDatabase();
           availableBalance = atmBankDatabase.getAvailableBalance(super.getAccountNumber());
           if (amount <= availableBalance){
               cashDispenser.dispenseCash(amount);
               atmBankDatabase.getAccount(super.getAccountNumber()).credit(amount);
           }
       }
    }
    }
    
    
//    private int prompForDestinationAccount(){
//        int userChoice = 0;
//        Screen screen = getScreen();
//        
//        int input = keypad.getInput();
//        switch(input){
//            case 1 ://VoucherListrik();break;
//            case 2 : //apa ajaa
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
         screen.displayMessageLine("1 - Voucher Listrik");
         screen.displayMessageLine("2 - Isi aja");
         screen.displayMessageLine("0 - Cancel transaction");
         screen.displayMessage("\nChoose adestination: ");
         int input = keypad.getInput();
         switch(input){
             case 1 : 
                userChoice = DisplayMenuListrik();
             break;
             case 2 : break;
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
         screen.displayMessageLine("0 - Cancel transaction");
         screen.displayMessage("\nChoose a Voucher amount: ");
         
         int input = keypad.getInput();
         
         switch(input){
             case 1 :
             case 2 :
             case 3 :
             case 4 :
             case 5 :
              screen.displayMessageLine("\nYour Voucher Number IS : ");
              stroom();
              userChoice = amounts[input];
              break;
             case CANCELED :
                 screen.displayMessageLine("\nCanceling Transaction");
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
    }

