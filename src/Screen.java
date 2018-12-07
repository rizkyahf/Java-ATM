public class Screen {
   // display a message without a carriage return
   public void displayMessage(String message) {
      System.out.print(message); 
   } 

   // display a message with a carriage return
   public void displayMessageLine(String message) {
      System.out.println(message);   
   } 

   // displays a dollar amount
   public void displayDollarAmount(double amount) {
      System.out.printf("$%,.2f", amount);   
   }
} 