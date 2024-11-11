package product;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        String dirPath = "db";
        String fileName = "filteredProducts.db";

        // If args given, don't use default dir & filename
        if(args.length > 1)
        {
            dirPath = args[0];
            fileName = args[1];
        }

        File newDir = new File(dirPath);
        if(!newDir.exists()) 
            newDir.mkdir();
        File dbFile = new File(dirPath + File.separator + fileName);


        // Task 3 - Create products 
        List<Product> productList = new ArrayList<>();

        productList.add(new Product(1L, "Mouse", "For click UI on screen", "Computer", 99.0f, new Date()));
        productList.add(new Product(2L, "Keyboard", "device that allows alpha numerics inputs", "Computer", 235.5f, new Date()));
        productList.add(new Product(3L, "15.6 inch monitor", "Extended display panel", "Computer", 157.5f, new Date()));
        productList.add(new Product(4L, "Huawei Pura 70 Ultra", "Huawei phone", "Mobile", 900.0f, new Date()));
        productList.add(new Product(5L, "Huawei Mate 50 Pr", "Huawei phone", "Mobile", 1200.0f, new Date()));
        productList.add(new Product(6L, "iPhone 16 Pro", "Iphone", "Mobile", 2000.0f, new Date()));
        productList.add(new Product(7L, "iPhone 14 Pro", "Iphone", "Mobile", 1800.0f, new Date()));

        // Task 3 check
        // for (Product product : productList) {
        //     System.out.println(product);
        // }

        List<Product> expensiveMobiles = productList.stream()
                                                    .filter(prod -> prod.getProdCat().equals("Mobile"))
                                                    .filter(prod -> (prod.getPrice() > 1500.0f))
                                                    .collect(Collectors.toList());

        // Task 4 check
        expensiveMobiles.forEach(expMobile -> System.out.println(expMobile));

        // Task 5 write filtered output to file
        try(FileWriter fw = new FileWriter(dbFile);
            BufferedWriter bw = new BufferedWriter(fw)) {
                expensiveMobiles.forEach(expMobile -> {
                    try {
                        bw.write(expMobile.toString());
                        bw.newLine();
                        bw.flush();
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                });  
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}