import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.BufferedWriter;

public class FileManager {

    public void saveProducts(ArrayList<Product> products){

        String filePath = "products.txt";

        try (FileWriter writer = new FileWriter(filePath)){
            
            for (Product product : products){
                writer.write(product.toFileString() + "\n");
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Cannot Find Products File Location.");
        }
        catch (IOException e){
            System.out.println("Cannot Access to File");
        }
        catch (Exception e){
            System.out.println("Something Went Wrong");
        }
    }


    public void readProducts(ArrayList<Product> products){
        
        String filePath = "products.txt";
        File file = new File(filePath);

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            line = reader.readLine();

            if (line == null){
                System.out.println("The Products File Is Empty.");
                return;
            }

            do {
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                int stock = Integer.parseInt(data[3]);

                Product newProduct = new Product(id,name,price,stock);
                products.add(newProduct);

            } while ((line = reader.readLine()) != null);

        } 
        catch (FileNotFoundException e){
            System.out.println("Cannot Find File Location.");
        }
        catch (IOException e){
            System.out.println("Cannot Access to File.");
        }
        catch (Exception e){
            System.out.println("Something Went Wrong.");
        }
    }


    
    public void saveOrder(Order order) {

        String filePath = "orders.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            writer.write(order.getId());
            writer.newLine();

            for (CartItem item : order.getCart()) {

                writer.write(
                        item.getProduct().getName() + ","
                        + item.getQuantity() + ","
                        + item.getProduct().getPrice());

                writer.newLine();
            }

            writer.write("Total," + order.getTotal());

            writer.newLine();
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Cannot save order.");
        }
    }



    public ArrayList<Order> readOrders() {

        ArrayList<Order> orders = new ArrayList<>();

        String filePath = "orders.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            Order currentOrder = null;

            ArrayList<CartItem> cart = null;

            while ((line = reader.readLine()) != null) {

                if (line.isBlank()) {
                    continue;
                }

                if (line.startsWith("Order")) {

                    cart = new ArrayList<>();

                    currentOrder = new Order(line, cart, 0);

                }
                else if (line.startsWith("Total")) {

                    String[] data = line.split(",");

                    double total = Double.parseDouble(data[1]);

                    currentOrder.setTotal(total);

                    orders.add(currentOrder);

                }
                else {

                    String[] data = line.split(",");

                    String name = data[0];

                    int quantity = Integer.parseInt(data[1]);

                    double price = Double.parseDouble(data[2]);

                    Product product = new Product("", name, price, 0);

                    CartItem item = new CartItem(product, quantity);

                    cart.add(item);
                }

            }

        } catch (IOException e) {

            System.out.println("Cannot read orders.");

        }

        return orders;
    }
}