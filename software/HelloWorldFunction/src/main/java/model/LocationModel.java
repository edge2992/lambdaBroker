package model;

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
  private LocalDateTime createdAt;
}
