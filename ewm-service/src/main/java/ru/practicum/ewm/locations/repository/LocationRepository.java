package ru.practicum.ewm.locations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.locations.model.Location;
import ru.practicum.ewm.locations.model.dto.LocationOutputDtoWithDistance;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {


    @Query("select l from Location l where l.lat = ?1 and l.lon = ?2 ")
    Optional<Location> findLocation(Double lat, Double lon);

    Optional<Location> getLocationByLatAndLon(Double lat, Double lon);

    @Query(nativeQuery = true,
            value = "select *,distance(:lat, :lon, lat, lon)  from locations " +
                    "where (distance(:lat, :lon, lat, lon) < (:distance+radius))" +
                    "and (:name is null or upper(name) like upper(concat('%', :name, '%')))" +
                    "and (:description is null or upper(description) like upper(concat('%', :description, '%')))" +
                    "order by distance asc ")
    Page<LocationOutputDtoWithDistance> searchLocations(Double lat,
                                                        Double lon,
                                                        Double distance,
                                                        String name,
                                                        String description,
                                                        Pageable page);

    @Query(nativeQuery = true,
            value = "select *,distance(:lat, :lon, lat, lon) from locations " +
                    "where (distance(:lat, :lon, lat, lon) < (:distance+radius))")
    List<Location> getLocations(@Param("lat") Double lat,
                                @Param("lon") Double lon,
                                @Param("distance") Double distance);
}
