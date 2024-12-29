package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbImmutable;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey;

@Value
@Builder
@DynamoDbImmutable(builder = Location.LocationBuilder.class)
public class Location {
  @Getter(onMethod_ = {@DynamoDbPartitionKey})
  private Integer id;
  private Double latitude;
  private Double longitude;

  @Getter(onMethod_ = {@DynamoDbSecondarySortKey(indexNames = "locationCreatedAtIndex")})
  private String createdAt;
}
