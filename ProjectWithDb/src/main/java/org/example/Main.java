package org.example;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Use your MongoDB cluster's connection string
        String connectionString = "mongodb+srv://lab2clusterpwd:qKkIn7DSBQUpaSvs@maincluster.g9qacno.mongodb.net/";
        // Connect to the MongoDB cluster using the connection string
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("MainDb");
        MongoCollection<Document> collection = database.getCollection("Names");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert (1) or search (2)");
        Integer command = scanner.nextInt();
        System.out.println("Insert the word");
        String word = scanner.next();
        scanner.close();
        System.out.println("Inserted word " + word);
        Document document = new Document("name", word);
        if(command == 1){
            collection.insertOne(document);
        }else if(command == 2){
            FindIterable<Document> result = collection.find(document);
            try (MongoCursor<Document> cursor = result.iterator()) {
                while (cursor.hasNext()) {
                    Document research= cursor.next();
                    System.out.println("Match found: " + research.toJson());
                }
            }
        }
        mongoClient.close();
    }
}