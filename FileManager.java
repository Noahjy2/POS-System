import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;


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

    public void saveCartItem(ArrayList<CartItem> cartItems){
        
    }
}