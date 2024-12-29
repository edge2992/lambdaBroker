package model;

import java.util.List;

public interface LocationRepository {
  void save(Location location);

  Location findById(Integer id);

  List<Location> findAllByCreatedAtRange(String start, String end);
}
