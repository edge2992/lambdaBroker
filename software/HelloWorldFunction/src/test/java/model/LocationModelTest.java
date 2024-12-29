package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
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
    ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
    String json = mapper.writeValueAsString(locationModel);
    assertEquals("{\"id\":1,\"latitude\":37.7749,\"longitude\":122.4194,\"createdAt\":\"2021-07-01T00:00:00\"}", json);
  }

  @Test
  void testDeserialize() throws Exception {
    String json = "{\"id\":1,\"latitude\":37.7749,\"longitude\":122.4194,\"createdAt\":\"2021-07-01T00:00:00\"}";

    // jackson
    ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();
    LocationModel locationModel = mapper.readValue(json, LocationModel.class);
    assertEquals(1, locationModel.getId());
    assertEquals(37.7749, locationModel.getLatitude());
    assertEquals(122.4194, locationModel.getLongitude());
    assertEquals(LocalDateTime.of(2021, 7, 1, 0, 0), locationModel.getCreatedAt());
  }
}