import java.util.ArrayList;
import java.util.Scanner;

public class POSSystem {
    private ShoppingCart cart;
    private ProductManager product;
    private FileManager file;

    public POSSystem(ProductManager product, ShoppingCart cart, FileManager file){
        this.product = product;
        this.cart = cart;
        this.file = file;
    }


    public void checkout(Scanner scanner){
        if (cart.getCartItems().isEmpty()){
            System.out.println("The Cart is Empty.");
            return;
        }

        //display items in cart
        System.out.print("""
        =================================================================
        ID       Item Name                   Quantity       Price
        =================================================================
        """);
        for (CartItem item : cart.getCartItems()){
            System.out.printf("%-8s %-27s %-14d %-8.2f\n",
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getProduct().getPrice()
            );
        }
        System.out.println("=================================================================");
        System.out.printf("Total: RM%.2f%n", cart.calculateTotal());
        System.out.println("=================================================================");
        
        String confirmation;
        while (true){
            System.out.print("Confirm to check out (y/n): ");
            confirmation = scanner.nextLine().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("n")){
                break;
            } else {
                System.out.println("Please Enter y for YES or n for NO.");
            }
        }
        
        if (confirmation.equals("n")){
            return;
        }

        //create order
        String orderId = generateOrderId();

        Order newOrder = new Order(orderId, cart.getCartItems(), cart.calculateTotal());
        
        
        //save orders.txt
        file.saveOrder(newOrder);


        //delete stock of product
        for (CartItem item : cart.getCartItems()) {

            Product p = product.getProduct(item.getProduct().getId());

            p.setStock(p.getStock() - item.getQuantity());
        }

        //save products.txt
        file.saveProducts(product.getProducts());

        //clear cart
        cart.clearCart();

        System.out.println("Checkout completed successfully.");
    }

    private String generateOrderId() {
        ArrayList<Order> orders = file.readOrders();

        int nextId = orders.size() + 1;

        return String.format("Order%04d", nextId);
    }




    public void salesSummary(Scanner scanner){
        ArrayList<Order> orders = file.readOrders();

        if (orders.isEmpty()){
            System.out.println("No Order Found.");
            return;
        }




        System.out.println("========== SALES SUMMARY ==========\n");

        System.out.println("Total Orders: " + orders.size());
        System.out.printf("Total Revenue: RM %.2f\n", calculateRevenue(orders));
        System.out.printf("Average Order: RM %.2f\n", (calculateRevenue(orders)/orders.size()) );
        
        System.out.println("\n========== ORDER LIST ==========");
        displayOrder(orders);
        System.out.println("================================");
        
    }

    private double calculateRevenue(ArrayList<Order> orders){
        
        if (orders.isEmpty()){
            return 0;
        }
        
        double totalRevenue = 0;
        for (Order order : orders){
            totalRevenue += order.getTotal();
        }
        return totalRevenue;
    }

    private void displayOrder(ArrayList<Order> orders){
        for (Order order : orders){
            System.out.printf("%s  Total: RM %.2f\n", order.getId(), order.getTotal());
        }
    }

    
}
