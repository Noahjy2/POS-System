import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    ArrayList<Product> products = new ArrayList<>();
    FileManager file = new FileManager();

    
    public Product getProduct(String id){
        for (Product product : products){
            if (product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }


    public void addProduct(Scanner scanner){
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        if (getProduct(id) != null){
            System.out.println("ID Already Exist.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        for (Product product : products){
            if (product.getName().equals(name)){
                System.out.println("Name Already Exist.");
                return;
            }
        }

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.println("Enter Quantity: ");
        int stock = scanner.nextInt();

        Product newProduct = new Product(id, name, price, stock);
        products.add(newProduct);

        file.saveProducts(products);
    }



    public void deleteProduct(Scanner scanner){
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        if (getProduct(id) != null){
            products.remove(getProduct(id));
            System.out.println("Product Removed Successfully.");

            file.saveProducts(products);
        } else {
            System.out.println("Product Not Found.");
        }
    }


    public void searchProduct(Scanner scanner){
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        if (getProduct(id) != null){
            System.out.println(getProduct(id).toString());
        } else {
            System.out.println("Product Not Found.");
        }
    }


    public void updateProduct(Scanner scanner){
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        if (getProduct(id) == null){
            System.out.println("Product Not Found.");
            return;
        }

        System.out.print("""
        1. ID
        2. Name
        3. Price
        4. Stock    
        """);
        System.out.print("Enter your choice (1-4): ");
        String choice = scanner.nextLine();

        switch (choice){
            case "1" -> {
                System.out.print("Enter New ID: ");
                String newId = scanner.nextLine();
                getProduct(id).setId(newId);
            }
            case "2" -> {
                System.out.print("Enter New Name: ");
                String newName = scanner.nextLine();
                getProduct(id).setName(newName);
            }
            case "3" -> {
                double newPrice;
                while (true) {
                    System.out.print("Enter New Price: ");
                    String input = scanner.nextLine();

                    try {
                        newPrice = Double.parseDouble(input);
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Please enter a valid number.");
                    }
                }

                getProduct(id).setPrice(newPrice);
            }
            case "4" -> {
                int newStock;

                while (true){
                    System.out.print("Enter New Stock: ");
                    String input = scanner.nextLine();

                    try {
                        newStock = Integer.parseInt(input);
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Please enter a valid number.");
                    }
                }
                getProduct(id).setStock(newStock);
            }
            
            default -> System.out.println("Invalid choice.");
        }
        file.saveProducts(products);
    }



    public void viewAllProduct(){
        if (products.isEmpty()){
            System.out.println("No Product Found.");
            return;
        }
        
        System.out.print("""
        =======================================================
        ID       Name                 Price          Stock
        =======================================================
        """);
        for (Product product : products){
            System.out.printf("%-8s %-20s RM %-10.2f  %-5d\n",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
            );
        }
    }

}