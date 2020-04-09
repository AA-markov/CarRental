package rusoft.project.integration.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;
import rusoft.project.repository.CarRepository;
import rusoft.project.repository.ClientRepository;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RepositoryIntegrationTest {
    @Autowired
    CarRepository carRepository;
    @Autowired
    ClientRepository clientRepository;
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
        clientRepository.save(basicClient);
        clientRepository.save(client);

        firstCar = new Car()
                .setBrand("BMW")
                .setManufactureYear(2019L)
                .setOwner(basicClient);
        secondCar = new Car()
                .setBrand("Audi")
                .setManufactureYear(2015L)
                .setOwner(client);
        carRepository.save(firstCar);
        carRepository.save(secondCar);
    }

    @AfterEach
    void tearDown() {
        carRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    void existsByBrandAndManufactureYear() {
        assertTrue(carRepository.existsByBrandAndManufactureYear("BMW", 2019L));
        assertTrue(carRepository.existsByBrandAndManufactureYear("Audi", 2015L));
        assertFalse(carRepository.existsByBrandAndManufactureYear("BMW", 2015L));
        assertFalse(carRepository.existsByBrandAndManufactureYear("Audi", 2019L));
    }

    @Test
    void findByBrandAndManufactureYear() {
        Car car = carRepository.findByBrandAndManufactureYear("BMW", 2019L).orElse(secondCar);
        assertEquals(firstCar, car);
        car = carRepository.findByBrandAndManufactureYear("Audi", 2015L).orElse(firstCar);
        assertEquals(secondCar, car);
    }

    @Test
    void findByBrandAndOwnerName() {
        Car car = carRepository.findByBrandAndOwnerName("BMW", "Parking").orElse(secondCar);
        assertEquals(firstCar, car);
        car = carRepository.findByBrandAndOwnerName("Audi", "Ivan").orElse(firstCar);
        assertEquals(secondCar, car);
    }

    @Test
    void existsByName() {
        assertTrue(clientRepository.existsByName("Parking"));
        assertTrue(clientRepository.existsByName("Ivan"));
        assertFalse(clientRepository.existsByName("Some_Name"));
    }

    @Test
    void existsByNameAndBirthYear() {
        assertTrue(clientRepository.existsByNameAndBirthYear("Parking", 1900L));
        assertTrue(clientRepository.existsByNameAndBirthYear("Ivan", 1990L));
        assertFalse(clientRepository.existsByNameAndBirthYear("Parking", 1990L));
        assertFalse(clientRepository.existsByNameAndBirthYear("Ivan", 1900L));
    }
}