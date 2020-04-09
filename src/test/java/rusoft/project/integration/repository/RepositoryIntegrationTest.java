package rusoft.project.integration.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;
import rusoft.project.repository.CarRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RepositoryIntegrationTest {
    @Autowired
    CarRepository carRepository;
    private Client basicClient;
    private Client client;
    private Car firstCar;
    private Car secondCar;

    @BeforeEach
    void setUp() {
        basicClient = new Client()
                .setName("Parking")
                .setBirthYear(1900L);
        client = new Client()
                .setName("Ivan")
                .setBirthYear(1990L);
        firstCar = new Car()
                .setBrand("BMW")
                .setManufactureYear(2019L);
        secondCar = new Car()
                .setBrand("Audi")
                .setManufactureYear(2015L);
    }

    @Test
    void existsByBrandAndManufactureYear() {
        carRepository.save(firstCar);
        carRepository.save(secondCar);
        List<Car> cars = carRepository.findAll();
        assertEquals(1, cars.size());
        cars = carRepository.findAll();
        assertEquals(2, cars.size());
        carRepository.delete(firstCar);
        cars = carRepository.findAll();
        assertEquals(1, cars.size());
    }

    @Test
    @Disabled
    void findByBrandAndManufactureYear() {
    }

    @Test
    @Disabled
    void findByBrandAndName() {
    }

    @Test
    void existsByName() {
    }

    @Test
    void existsByNameAndBirthYear() {
    }
}