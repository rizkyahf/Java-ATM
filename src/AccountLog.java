// myAdd new
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RizkyAHF
 */
public class AccountLog {
    private boolean isDeposit;
    private double amount;
    private double totalBalance;
    
    public boolean isIsDeposit() {
        return isDeposit;
    }

    public double getAmount() {
        return amount;
    }

    public double getTotalBalance() {
        return totalBalance;
    }
    
    
    public AccountLog(boolean isDeposit, double amount, double totalBalance){
        this.isDeposit = isDeposit;
        this.amount = amount;
        this.totalBalance = totalBalance;
    }
    
    
}
//end add