import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args){
        FileManager file = new FileManager();
        ProductManager product = new ProductManager(file);
        ShoppingCart cart = new ShoppingCart(product);
        POSSystem pos = new POSSystem(product, cart, file);
        Scanner scanner = new Scanner(System.in);

        file.readProducts(product.getProducts());

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
                case "1" -> productManagement(scanner, product);
                case "2" -> shoppingCart(scanner, cart);
                case "3" -> pos.checkout(scanner);
                case "4" -> pos.salesSummary(scanner);
                case "5" -> System.out.println("Bye");
                default -> System.out.println("Invalid choice.");
            }

            if (choice.equals("5")){
                break;
            }
        }
        
    }


    public static void productManagement(Scanner scanner, ProductManager manager){

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
                case "6" -> {System.out.println("Returning to Main Menu.");return;}
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void shoppingCart(Scanner scanner, ShoppingCart cart){
        

        while (true){
            System.out.print("""
             \n========== SHOPPING CART ==========      
             
             1. Add Product to Cart
             2. View Cart
             3. Update Quantity
             4. Remove Product
             5. Clear Cart
             6. Return Main Menu
            """);
            System.out.print("Enter your choice (1-6): ");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> cart.addItem(scanner);
                case "2" -> cart.viewCart();
                case "3" -> cart.updateQuantity(scanner);
                case "4" -> cart.removeItem(scanner);
                case "5" -> cart.clearCart();
                case "6" -> {System.out.println("Returning to Main Menu.");return;}
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}