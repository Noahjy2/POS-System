import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("""
            ========== POS SYSTEM ==========

            1. Product Management 
            2. Shopping Cart
            3. Checkout
            4. Sales Summary
            5. Exit
            """);
            System.out.print("Enter your choice (1-5): ");    
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> productManagement(scanner);
                case "5" -> System.out.println("Bye");
                default -> System.out.println("Invalid choice.");
            }

            if (choice.equals("5")){
                break;
            }
        }
        
    }


    public static void productManagement(Scanner scanner){
        ProductManager manager = new ProductManager();
        manager.initialize(); //for testing

        while (true){
             System.out.print("""
            \n========== PRODUCT MANGEMENT ==========
            
            1. Add Product
            2. View All Product
            3. Search Product
            4. Update Product
            5. Delete Product
            6. Return Main Menu
            """);
            System.out.print("Enter your choice (1-6): ");
            String option = scanner.nextLine();

            switch (option){
                case "1" -> manager.addProduct(scanner);
                case "2" -> manager.viewAllProduct();
                case "3" -> manager.searchProduct(scanner);
                case "4" -> manager.updateProduct(scanner);
                case "5" -> manager.deleteProduct(scanner);
                case "6" -> {System.out.println("Returning To Main Menu");return;}
                default -> System.out.println("Invalid choice.");
            }
        }
        
       
    }
}