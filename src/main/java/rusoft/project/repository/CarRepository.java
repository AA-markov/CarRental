package rusoft.project.repository;

import rusoft.project.entity.Car;

import java.util.Optional;

public interface CarRepository extends AbstractRepository<Car, Long> {

    boolean existsByBrandAndManufactureYear(String brand, Long manufactureYear);

    Optional<Car> findByBrandAndManufactureYear(String brand, Long manufactureYear);

    Optional<Car> findByBrandAndOwnerName(String brand, String name);
}