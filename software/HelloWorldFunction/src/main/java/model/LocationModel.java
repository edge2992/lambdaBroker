package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Generated
@Builder
public class LocationModel {
  @JsonProperty("id")
  private Integer id;
  @JsonProperty("latitude")
  private Double latitude;
  @JsonProperty("longitude")
  private Double longitude;
  @JsonProperty("createdAt")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdAt;

  @JsonCreator
  public static LocationModel create(
      @JsonProperty("id") Integer id,
      @JsonProperty("latitude") Double latitude,
      @JsonProperty("longitude") Double longitude,
      @JsonProperty("createdAt") LocalDateTime createdAt) {
    return LocationModel.builder()
        .id(id)
        .latitude(latitude)
        .longitude(longitude)
        .createdAt(createdAt)
        .build();
  }
}
