package rusoft.project.repository;

import rusoft.project.entity.Car;

import java.util.Optional;

public interface CarRepository extends AbstractRepository<Car, Long> {

    boolean existsByBrandAndManufactureYear(String brand, Long manufactureYear);

    Optional<Car> findByBrandAndManufactureYear (String brand, Long manufactureYear);

    boolean existsByBrandAndOwner(String brand, String owner);

    Optional<Car> findByBrandAndOwner(String brand, String owner);
}