package model;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationRepositoryImpl implements LocationRepository {
  private final DynamoDbTable<Location> locationTable;

  public LocationRepositoryImpl(DynamoDbClient dynamoDbClient) {
    DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build();

    this.locationTable = enhancedClient.table("Locations", TableSchema.fromBean(Location.class));
  }

  @Override
  public void save(Location location) {
    locationTable.putItem(location);
  }

  @Override
  public Location findById(Integer id) {
    return locationTable.getItem(r -> r.key(k -> k.partitionValue(id)));
  }

  @Override
  public List<Location> findAllByCreatedAtRange(String start, String end) {
    Expression filterExpression = Expression.builder()
            .expression("#createdAt BETWEEN :start AND :end")
            .expressionValues(Map.of(
                    ":start", AttributeValue.builder().s(start).build(),
                    ":end", AttributeValue.builder().s(end).build()))
            .build();

    List<Location> locations = new ArrayList<>();
    locationTable.scan(s -> s.filterExpression(filterExpression)).items().forEach(locations::add);
    return locations;
  }
}
