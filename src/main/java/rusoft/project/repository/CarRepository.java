package rusoft.project.repository;

import rusoft.project.entity.Car;

import java.util.Optional;

public interface CarRepository extends AbstractRepository<Car, Long> {

    boolean existsByBrandAndManufactureYear(String brand, Long manufactureYear);

    Optional<Car> findByBrandAndManufactureYear (String brand, Long manufactureYear);

    boolean existsByBrandAndName(String brand, String name);

    Optional<Car> findByBrandAndName (String brand, String name);
}