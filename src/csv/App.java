package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * https://github.com/datablist/sample-csv-files?tab=readme-ov-file
 */
public class App {
    public static Map<String, Customer> parseCSV(String filePath) throws IOException {
        Map<String, Customer> customerMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] headers = parseLine(reader.readLine()); // Skip the header line
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = parseLine(line);

                // Create a Customer object using the values from the CSV
                Customer customer = new Customer(
                    values[0],  // Index
                    values[1],  // Customer Id
                    values[2],  // First Name
                    values[3],  // Last Name
                    values[4],  // Company
                    values[5],  // City
                    values[6],  // Country
                    values[7],  // Phone 1
                    values[8],  // Phone 2
                    values[9],  // Email
                    values[10], // Subscription Date
                    values[11]  // Website
                );

                // Add to map using Customer Id as key, avoiding duplicates
                customerMap.putIfAbsent(customer.getCustomerID(), customer);
            }
        }
        return customerMap;
    }

    // This method manually parses a CSV line, handling quoted fields correctly
    public static String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '\"') {
                // Toggle insideQuotes when encountering a quote character
                insideQuotes = !insideQuotes;
            } else if (currentChar == ',' && !insideQuotes) {
                // If we encounter a comma outside of quotes, it's the end of a field
                result.add(currentField.toString().trim());
                currentField.setLength(0); // Reset the field buffer
            } else {
                // Add the character to the current field
                currentField.append(currentChar);
            }
        }

        // Add the last field to the result (since the line might not end with a comma)
        result.add(currentField.toString().trim());

        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String filePath = args[0];  // Replace with your file path
        try {
            System.out.println(">>> " + filePath);
            Map<String, Customer> customerMap = parseCSV(filePath);
            System.out.println(customerMap.size());

            // 10000
            // Customer c = customerMap.get("e99DcfDaDac8a06");
            // System.out.println(c.toString());

            Customer c = customerMap.get("19596540c6aC028");
            System.out.println(c.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}