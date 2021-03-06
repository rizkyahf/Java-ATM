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
   private boolean accountAuthenticatide;
   private int CurrencyUnit;
   
   public TopUp(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot, CashDispenser atmCashDispenser,
      int atmCurrencyUnit) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      
      depositSlot = atmDepositSlot;
      keypad = atmKeypad;
      destinationNo = 0;
      bankDatabase = atmBankDatabase;
      sourceNo = userAccountNumber;
      amount = 0;
      cashDispenser = atmCashDispenser;
      CurrencyUnit = atmCurrencyUnit;
   } 

   // perform transaction
   @Override
   public void execute() {
       Screen screen = getScreen();
       double availableBalance;
              
       destinationNo = promptForDestinationAccount();
   }

   private int promptForDestinationAccount() {
      int userChoice = 0; // local variable to store return value
      Screen screen = getScreen(); // get reference to screen
      
      
         screen.displayMessage("\nDestination Menu: \n");
         screen.displayMessageLine("1 - E-Toll");
         screen.displayMessageLine("2 - Dana");
         screen.displayMessageLine("3 - OVO");
         screen.displayMessageLine("4 - Shopee");
         screen.displayMessageLine("5 - Tokopedia");
         screen.displayMessageLine("6 - Bukalapak");
         screen.displayMessageLine("7 - Gopay");
         screen.displayMessageLine("0 - Cancel transaction");
         screen.displayMessage("\nChoose destination: ");

      int input;

    while(!keypad.hasNextInput()){
        keypad.getLine();
        screen.displayMessageLine("\nInput is Invalid");
        screen.displayMessage("Please re-enter destination: ");
    }

      input = keypad.getInput(); // receive input of deposit amount
      switch(input){
          case 1: topupETol(); break;
          case 2: topupDana();break;// akun dana
          case 3: topupOvo(); break;
          case 4: topupShopee(); break;// akun shopee
          case 5: topupTokped(); break;
          case 6: topupBuklap(); break;
          case 7: topupGopay(); break;
          case CANCELED:
        screen.displayMessageLine(
           "\nCanceling transaction...");
//         return CANCELED;
        break;
      }

         return (int) input; 
   }
   
   private double promptForAmount(){
       Screen screen = getScreen(); // get reference to screen

      screen.displayMessage("\nPlease enter amount to transfer (or 0 to cancel): ");
      int input;

    while(!keypad.hasNextInput()){
        keypad.getLine();
        screen.displayMessageLine("\nInput is Invalid");
        screen.displayMessage("Please re-enter amount to transfer (or 0 to cancel): ");
    }

      input = keypad.getInput(); // receive input of deposit amount
      
      if (input == CANCELED) {
        screen.displayMessageLine(
           "\nCanceling transaction...");
         return CANCELED;
      }
      else {
         return (double) input; // return dollar amount
      }
   }
   private void topupTokped(){
     
     Screen screen = getScreen();       
        BankDatabase atmBankDatabase = super.getBankDatabase();
        amount = promptForAmount();
        int agree;
        double Balance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),CurrencyUnit);
        if(amount == CANCELED){
            screen.displayMessageLine("\nCanceling transaction...");
        }
        else if(amount <= Balance){
            screen.displayMessage("\n Input Tokopedia account with code 3902 + acc number: ");

            int Receiver_Account = keypad.getInput();
            accountAuthenticatide = atmBankDatabase.userauthentication(super.getAccountNumber());
            if(accountAuthenticatide = true){
                screen.displayMessage(" Ketik 1 untuk menyetujui transaksi \n Masukan anda : ");
                agree = keypad.getInput();
                if (agree == 1){
                atmBankDatabase.debit(Receiver_Account, amount,CurrencyUnit);
                atmBankDatabase.credit(super.getAccountNumber(), amount,CurrencyUnit);
                screen.displayMessage("\n Transfer success from " + super.getAccountNumber() + " to " + Receiver_Account + "\n");  
                } else { screen.displayMessage ("Transaksi Di batalkan\n");
                }
                          
            } else screen.displayMessage("\nAccount not registered\n");
        } else screen.displayMessageLine("\nNot Enough Saldo\n");  
   }
   
   private double promptAmountETol(){
       Screen screen = getScreen();
       String kode;
       int jumlah;
       String Kode_1 = "123456789";
       String Kode_2 = "234567891";
       String Kode_3 = "345678912";
       
       screen.displayMessage("Masukan kode token (9 Karakter) : ");
       kode = keypad.getLine();
       jumlah = kode.length();
       if (jumlah == 9){
           if (Kode_1.compareTo(kode) == 0){
               return 100.0;
           } else if (Kode_2.compareTo(kode) == 0){
               return 200.0;
           } else if (Kode_3.compareTo(kode) == 0){
               return 300.0;
           } else screen.displayMessage("Kode token salah! Topup dibatalkan!");
       } else screen.displayMessage("Kode token salah! Topup dibatalkan!");
       
       return -1;
   }
   
    private void topupETol(){
        Screen screen = getScreen();
        BankDatabase atmBankDatabase = super.getBankDatabase();
        amount = promptAmountETol();
        int agree, input;
        double Balance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),CurrencyUnit);

        if (amount != -1){
            if(amount <= Balance){
                screen.displayMessage("\nInput ETol user account : ");
                input = keypad.getInput();
                System.out.println(super.getAccountNumber());
                accountAuthenticatide = atmBankDatabase.userauthentication(input);
                if (accountAuthenticatide == true){
                    screen.displayMessage("\nBerikut adalah nominal topup : " +amount);
                    screen.displayMessage("\nKetik 1 untuk menyetujui transaksi. \nMasukan anda : ");
                    agree = keypad.getInput();
                    if (agree == 1){
                         atmBankDatabase.debit(input, amount,CurrencyUnit);
                         atmBankDatabase.credit(super.getAccountNumber(), amount,CurrencyUnit);
                         screen.displayMessage("\nTopUp Sukses!");
                    } else screen.displayMessage("\nTopUp dibatalkan!");
                } else screen.displayMessage("\nAccount tidak ter-registrasi!");
            } else screen.displayMessage("\nSaldo anda tidak cukup!");           
        }
    }
    private void topupShopee(){
        Screen screen = getScreen();       
        BankDatabase atmBankDatabase = super.getBankDatabase();
        amount = promptForAmount();
        int agree;
        double Balance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),CurrencyUnit);
        
        if(amount == CANCELED){
            screen.displayMessageLine("\nCanceling transaction...");
        }
        else if(amount <= Balance){
            screen.displayMessage("\n Input Shopee account with code 4041 + acc number: ");

            int Receiver_Account = keypad.getInput();
            accountAuthenticatide = atmBankDatabase.userauthentication(super.getAccountNumber());
            if(accountAuthenticatide = true){
                screen.displayMessage(" Ketik 1 untuk menyetujui transaksi \n Masukan anda : ");
                agree = keypad.getInput();
                if (agree == 1){
                atmBankDatabase.debit(Receiver_Account, amount,CurrencyUnit);
                atmBankDatabase.credit(super.getAccountNumber(), amount,CurrencyUnit);
                screen.displayMessage("\n Transfer success from " + super.getAccountNumber() + " to " + Receiver_Account + "\n");  
                } else { screen.displayMessage ("Transaksi Di batalkan\n");
                }
                          
            } else screen.displayMessage("\nAccount not registered\n");
        } else screen.displayMessageLine("\nNot Enough Saldo\n");  
    }
    private void topupOvo(){
        Screen screen = getScreen();       
        BankDatabase atmBankDatabase = super.getBankDatabase();
        amount = promptForAmount();
        int agree;
        double Balance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),CurrencyUnit);
        
        if(amount == CANCELED){
            screen.displayMessageLine("\nCanceling transaction...");
        }
        else if(amount <= Balance){
            screen.displayMessage("\n Input OVO account with code 3340 + acc number: ");

            int Receiver_Account = keypad.getInput();
            accountAuthenticatide = atmBankDatabase.userauthentication(super.getAccountNumber());
            if(accountAuthenticatide = true){
                screen.displayMessage(" Ketik 1 untuk menyetujui transaksi \n Masukan anda : ");
                agree = keypad.getInput();
                if (agree == 1){
                atmBankDatabase.debit(Receiver_Account, amount,CurrencyUnit);
                atmBankDatabase.credit(super.getAccountNumber(), amount,CurrencyUnit);
                screen.displayMessage("\n Transfer success from " + super.getAccountNumber() + " to " + Receiver_Account + "\n");  
                } else { screen.displayMessage ("Transaksi Di batalkan\n");
                }
                          
            } else screen.displayMessage("\nAccount not registered\n");
        } else screen.displayMessageLine("\nNot Enough Saldo\n"); 
        
    }
    private void topupDana(){
        Screen screen = getScreen();       
        BankDatabase atmBankDatabase = super.getBankDatabase();
        amount = promptForAmount();
        int agree;
        double Balance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),CurrencyUnit);
        
        if(amount == CANCELED){
            screen.displayMessageLine("\nCanceling transaction...");
        }
        else if(amount <= Balance){
            screen.displayMessage("\n Input Dana account with code 2529 + acc number: ");

            int Receiver_Account = keypad.getInput();
            accountAuthenticatide = atmBankDatabase.userauthentication(super.getAccountNumber());
            if(accountAuthenticatide = true){
                screen.displayMessage(" Ketik 1 untuk menyetujui transaksi \n Masukan anda : ");
                agree = keypad.getInput();
                if (agree == 1){
                atmBankDatabase.debit(Receiver_Account, amount,CurrencyUnit);
                atmBankDatabase.credit(super.getAccountNumber(), amount,CurrencyUnit);
                screen.displayMessage("\n Transfer success from " + super.getAccountNumber() + " to " + Receiver_Account + "\n");  
                } else screen.displayMessage ("Transaksi Di batalkan\n");       
            } else screen.displayMessage("\nAccount not registered\n");
        } else screen.displayMessageLine("\nNot Enough Saldo\n");    
    }
    private void topupBuklap(){
     
     Screen screen = getScreen();       
        BankDatabase atmBankDatabase = super.getBankDatabase();
        amount = promptForAmount();
        int agree;
        double Balance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),CurrencyUnit);
        
        if(amount == CANCELED){
            screen.displayMessageLine("\nCanceling transaction...");
        }
        else if(amount <= Balance){
            screen.displayMessage("\n Input Bukalapak account with code 1104 + acc number: ");

            int Receiver_Account = keypad.getInput();
            accountAuthenticatide = atmBankDatabase.userauthentication(super.getAccountNumber());
            if(accountAuthenticatide = true){
                screen.displayMessage(" Ketik 1 untuk menyetujui transaksi \n Masukan anda : ");
                agree = keypad.getInput();
                if (agree == 1){
                atmBankDatabase.debit(Receiver_Account, amount,CurrencyUnit);
                atmBankDatabase.credit(super.getAccountNumber(), amount,CurrencyUnit);
                screen.displayMessage("\n Transfer success from " + super.getAccountNumber() + " to " + Receiver_Account + "\n");  
                } else { screen.displayMessage ("Transaksi Di batalkan\n");
                }
                          
            } else screen.displayMessage("\nAccount not registered\n");
        } else screen.displayMessageLine("\nNot Enough Saldo\n");  
   }
    
    private void topupGopay(){
        Screen screen = getScreen();
        BankDatabase atmBankDatabase = super.getBankDatabase();
        screen.displayMessage("\n Masukkan kode perusahaan gojek : 41 dan"
                + "\n nomor telepon yang terdaftar (Contoh : 4114045)\n");
        int Receiver_Account = keypad.getInput();
        accountAuthenticatide = atmBankDatabase.userauthentication(super.getAccountNumber());
        if(accountAuthenticatide = true){
            amount = promptForAmount();
            int agree;
            double Balance = atmBankDatabase.getAvailableBalance(super.getAccountNumber(),CurrencyUnit);
            if(amount == CANCELED){
                screen.displayMessageLine("\nCanceling transaction...");
            }
            else if(amount <= Balance){
                screen.displayMessage(" Ketik 1 untuk menyetujui transaksi \n Masukan anda : ");
                agree = keypad.getInput();
                if (agree == 1){
                    atmBankDatabase.debit(Receiver_Account,amount,CurrencyUnit);
                    atmBankDatabase.credit(super.getAccountNumber(),amount,CurrencyUnit);
                    screen.displayMessage("\n Transaksi sukses!");
                } else screen.displayMessage ("Transaksi di batalkan\n");
            }else screen.displayMessageLine("\n saldo kurang!\n"); 
        }else screen.displayMessage("\n akun tidak ditemukan!\n");
    }
}

