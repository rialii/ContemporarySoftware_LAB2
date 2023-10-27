package org.example;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class DataBaseConfigurations {
    private static final String COLLECTION_NAME = "Names";
    private static final String DATABASE_NAME = "MainDb";
    private static MongoClient mongoClient = null;

    public static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/org/example/config.properties"));
        return properties;
    }

    public static String loadConnectionString() throws IOException {
        Properties properties = loadProperties();
        return properties.getProperty("db.connectionString");
    }

    public static MongoClient returnMongoClient() throws IOException {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(loadConnectionString());
        }
        return mongoClient;
    }

    public static boolean insertDocument(Document document) {
        try {
            MongoCollection<Document> collection = returnMongoClient().getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
            collection.insertOne(document);
            return true;
        } catch (MongoException | IOException mongoException) {
            System.err.println("Failed to insert the document: " + mongoException.getMessage());
            return false;
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }

    public static boolean searchForDocument(Document queryDocument) throws IOException {
        boolean anyDocumentFound = false;
        try {
            MongoCollection<Document> collection = returnMongoClient().getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
            FindIterable<Document> results = collection.find(queryDocument);
            try (MongoCursor<Document> cursor = results.iterator()) {
                while (cursor.hasNext()) {
                    Document resultDocument = cursor.next();
                    System.out.println("Match found: " + resultDocument.toJson());
                    anyDocumentFound = true;
                }
                return anyDocumentFound;
            } catch (MongoException mongoException) {
                System.err.println("Failed to search for documents: " + mongoException.getMessage());
                return false;
            }
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}
