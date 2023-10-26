import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.example.DataBaseConfigurations;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.IOException;

import static org.junit.Assert.*;

public class dbTests {
    @Test
    public void testDBConnection() throws IOException {
        MongoClient mongoClient = DataBaseConfigurations.returnMongoClient();
        assertNotNull(mongoClient);
    }
    @Test
    public void searchForInsertedWord_DocumentInDB() throws IOException {
        Document document = new Document("name", "IAmInDB");
        assertTrue(DataBaseConfigurations.searchForDocument(document));
    }

    @Test
    public void searchForInsertedWord_DocumentNotInDB() throws IOException {
        Document document = new Document("name", "IAmNotInDb");
        assertFalse(DataBaseConfigurations.searchForDocument(document));
    }

    /*
    @Test
    public void failingTestOnPurpose() throws IOException {
        Document doc = new Document("name", "IAmNotInDb");
        assertTrue(DataBaseConfigurations.searchForDocument(doc));
    }
    * */




}
