import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== POS SYSTEM ==========\n");

        System.out.print("""
        1. Product Management 
        2. Shopping Cart
        3. Checkout
        4. Sales Summary
        5. Exit
        """);
        System.out.println("Enter your choice: ");    
        String choice = scanner.nextLine();
    }
}