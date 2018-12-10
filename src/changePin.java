/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author V
 */
public class changePin extends Transaction {
    
    private Keypad keypad; // reference to keypad

    public changePin(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }
 
    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();  
        Screen screen = getScreen();
        
        screen.displayMessage("\nPlease enter your pin : ");
        int oldPin = keypad.getInput();
        
        
        if(bankDatabase.authenticateUser(getAccountNumber(), oldPin)) {
            screen.displayMessage("\nPlease enter your new pin : ");
            int newPin = keypad.getInput(); // input new pin
            bankDatabase.ChangePin(getAccountNumber(), newPin);
        } 
        else screen.displayMessage("\nPin yang anda masukkan salah....");
    }
    
}
