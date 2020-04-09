package rusoft.project.repository;

import org.springframework.data.jpa.repository.Query;
import rusoft.project.entity.Car;

import java.util.Optional;

public interface CarRepository extends AbstractRepository<Car, Long> {

    boolean existsByBrandAndManufactureYear(String brand, Long manufactureYear);

    Optional<Car> findByBrandAndManufactureYear (String brand, Long manufactureYear);

    @Query(value = "select c.id, c.brand, c.manufacture_year, c.owner from rent.cars c join rent.clients cl on" +
            " c.owner = cl.id where c.brand = ?1 and cl.name = ?2", nativeQuery = true)
    Optional<Car> findByBrandAndName(String brand, String name);
}