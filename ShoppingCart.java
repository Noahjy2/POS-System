import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart{
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private ProductManager manager;
    private FileManager file = new FileManager();



    public ShoppingCart(ProductManager manager){
        this.manager = new ProductManager();
    }



    public CartItem getCartItem(String id){
        for (CartItem cartItem : cartItems){
            if (cartItem.getProduct().getId().equals(id)){
                return cartItem;
            }
        }

        return null;
    }



    public void addItem(Scanner scanner){
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();

        Product product = manager.getProduct(id);
        if (product == null){
            System.out.println("Product Not Found.");
            return;
        } 

        int quantity;
        while (true) {
            System.out.print("Enter Quantity: ");
            String input = scanner.nextLine();

            try {
                quantity = Integer.parseInt(input);
                break;
            } catch(NumberFormatException e){
                System.out.println("Please Enter Valid Number.");
            }
        }    

        if (getCartItem(id) != null){
            getCartItem(id).setQuantity(getCartItem(id).getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem(product, quantity);
            cartItems.add(cartItem);
        }
       
        System.out.println("Product Added to Cart Successfully.");
    }



    public void removeItem(Scanner scanner){
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();

        CartItem selectedCartItem = getCartItem(id);

        if (selectedCartItem == null){
            System.out.println("Product Not Found in Cart.");
            return;
        }

        cartItems.remove(selectedCartItem);
        System.out.println("Product Removed from Cart Successfully.");
    }



    public void updateQuantity(Scanner scanner){
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();

        CartItem selectedCartItem = getCartItem(id);

        if (selectedCartItem == null){
            System.out.println("Product Not Found in Cart.");
            return;
        }

        int newQuantity;
        while (true){
            System.out.print("Enter new quantity: ");
            String input = scanner.nextLine();

            try {
                newQuantity = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e){
                System.out.println("Please Enter Valid Number.");
            }
        }

        selectedCartItem.setQuantity(newQuantity);
        System.out.println("Quantity Updated Successfully.");        
    }



    public void viewCart(){
        
        if (cartItems.isEmpty()){
            System.out.println("Cart is Empty.");
            return;
        }

        System.out.print("""
        \n=============================================
        ID      Product Name        Quantity
        =============================================
        """);
        for (CartItem cartItem : cartItems){
            System.out.printf("%-8s %-20s %5d",
                cartItem.getProduct().getId(),
                cartItem.getProduct().getName(),
                cartItem.getQuantity()
            );   
        }
    }



    public double calculateTotal(){
        double totalPrice = 0;

        for (CartItem cartItem : cartItems){
            totalPrice += (cartItem.getProduct().getPrice() * cartItem.getQuantity());
        }

        return totalPrice;
    }



    public void clearCart(){
        cartItems.clear();
        System.out.println("Cart cleared successfully.");
    }

}