package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocationModelTest {

  @Test
  void testSerialize() throws Exception {
    LocationModel locationModel = LocationModel.builder()
            .id(1)
            .latitude(37.7749)
            .longitude(122.4194)
            .createdAt(LocalDateTime.of(2021, 7, 1, 0, 0))
            .build();

    // jackson
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(locationModel);
    assertEquals("{\"id\":1,\"latitude\":37.7749,\"longitude\":122.4194,\"createdAt\":\"2021-07-01T00:00:00\"}", json);
  }

}