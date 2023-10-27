package org.example;

import org.bson.Document;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert (1) or search (2)");
            int command = scanner.nextInt();
            if (command == 1) {
                System.out.println("Insert the word");
                String word = scanner.next();
                Document document = new Document("name", word);

                if (DataBaseConfigurations.insertDocument(document)) {
                    System.out.println("Document inserted successfully.");
                } else {
                    System.out.println("Failed to insert the document.");
                }
            } else if (command == 2) {
                System.out.println("Enter the word to search for");
                String word = scanner.next();
                Document queryDocument = new Document("name", word);

                if (DataBaseConfigurations.searchForDocument(queryDocument)) {
                    System.out.println("Search completed.");
                } else {
                    System.out.println("No matches found.");
                }
            } else {
                System.out.println("Invalid command. Use 1 for insert or 2 for search.");
            }
            scanner.close();
    }
}
