package main.java.com.ccp.db;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
public class MongoConfig {
  @Value("${ccp.mongo.uri}") private String uri;
  @Value("${ccp.mongo.username}") private String username;
  @Value("${ccp.mongo.password}") private String password;
  @Value("${db.name}") private String dbName;

  @Bean
  public MongoClient mongoClient() {
    var conn = new ConnectionString(uri);
    var cred = MongoCredential.createPlainCredential(username, "$external", password.toCharArray());
    var settings = MongoClientSettings.builder()
        .applyConnectionString(conn)
        .credential(cred)
        .build();
    return MongoClients.create(settings);
  }

  @Bean
  public MongoDatabase db(MongoClient client) {
    MongoDatabase db = client.getDatabase(dbName);
    // ensure basic indexes exist (idempotent)
    db.getCollection("vitals").createIndex(new Document("patientId", 1).append("ts", 1));
    db.getCollection("alerts").createIndex(new Document("patientId", 1).append("createdAt", -1));
    return db;
  }
}
