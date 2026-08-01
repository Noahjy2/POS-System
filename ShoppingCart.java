import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart{

    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private ProductManager product;


    public ShoppingCart(ProductManager product){
        this.product = product;
    }

    public ArrayList<CartItem> getCartItems(){
        return cartItems;
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

        if (product.getProducts().isEmpty()){
            System.out.println("There Is No Product Yet.");
            return;
        }

        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();

        Product addProduct = product.getProduct(id);
        if (addProduct == null){
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

        if (quantity > addProduct.getStock()){
            System.out.println("Not enough Item.");
            return;
        }


        if (getCartItem(id) != null){
            getCartItem(id).setQuantity(getCartItem(id).getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem(addProduct, quantity);
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

        if (newQuantity > selectedCartItem.getProduct().getStock()){
            //or = negative
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
        ID       Product Name        Quantity
        =============================================
        """);
        for (CartItem cartItem : cartItems){
            System.out.printf("%-8s %-20s %5d\n",
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
    }
}