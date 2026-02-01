package GenericUtilities;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
	
	public class MongoDBUtil {

	    private MongoClient mongoClient;
	    private MongoDatabase database;

	    public MongoDBUtil(String connectionString, String databaseName, String username, String password, String authDatabase) {
	        // Build MongoDB connection string with credentials
	        ConnectionString connString = new ConnectionString(connectionString);
	        MongoClientSettings settings = MongoClientSettings.builder()
	                .applyConnectionString(connString)
	                .credential(MongoCredential.createCredential(username, authDatabase, password.toCharArray()))
	                .build();

	        // Initialize MongoDB connection
	        mongoClient = MongoClients.create(settings);
	        database = mongoClient.getDatabase(databaseName);
	    }

	    // Close MongoDB connection
	    public void close() {
	        if (mongoClient != null) {
	            mongoClient.close();
	        }
	    }

	    // Insert a document into a collection
	    public void insertDocument(String collectionName, Document document) {
	        MongoCollection<Document> collection = database.getCollection(collectionName);
	        collection.insertOne(document);
	    }

	    // Update a document in a collection
	    public void updateDocument(String collectionName, Document filter, Document updateDoc) {
	        MongoCollection<Document> collection = database.getCollection(collectionName);
	        collection.updateOne(filter, new Document("$set", updateDoc));
	    }

	    // Delete a document from a collection
	    public void deleteDocument(String collectionName, Document filter) {
	        MongoCollection<Document> collection = database.getCollection(collectionName);
	        collection.deleteOne(filter);
	    }

	    // Find a document in a collection
	    public Document findDocument(String collectionName, Document filter) {
	        MongoCollection<Document> collection = database.getCollection(collectionName);
	        return collection.find(filter).first();
	    }
	}
