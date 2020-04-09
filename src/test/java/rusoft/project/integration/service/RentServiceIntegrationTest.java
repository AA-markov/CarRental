package rusoft.project.integration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rusoft.project.Application;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;
import rusoft.project.model.ResponseStatus;
import rusoft.project.repository.CarRepository;
import rusoft.project.repository.ClientRepository;
import rusoft.project.service.RentService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class RentServiceIntegrationTest {

    @Autowired
    CarRepository carRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    RentService rentService;
    private RentStartDto rentStartDto;
    private RentEndDto rentEndDto;

    private Client basicClient;
    private Car car;

    @BeforeEach
    void setUp() {
        basicClient = new Client()
                .setName("Main Storage")
                .setBirthYear(0L);
        car = new Car()
                .setBrand("BMW")
                .setManufactureYear(2019L)
                .setOwner(basicClient);
        clientRepository.save(basicClient);
        carRepository.save(car);
        rentStartDto = new RentStartDto()
                .setClientName("Ivan")
                .setBirthYear(1990L)
                .setCarBrand(car.getBrand())
                .setManufactureYear(car.getManufactureYear());
        rentEndDto = new RentEndDto()
                .setClientName("Ivan")
                .setCarBrand(car.getBrand());
    }

    @Test
    void rentCarAndThenGiveBack() {
        ResponseStatus response = rentService.addClient(rentStartDto);

        assertEquals(HttpStatus.OK, response.getStatus());
        Car rentedCar = carRepository.findByBrandAndManufactureYear(car.getBrand(), car.getManufactureYear()).orElse(new Car());
        Client client = clientRepository.findAll().stream().filter(e -> e.getBirthYear() > 0)
                .findFirst().orElse(basicClient);
        assertEquals("Ivan", client.getName());
        assertEquals(rentedCar.getOwner(), client);

        response = rentService.removeClient(rentEndDto);

        assertEquals(HttpStatus.OK, response.getStatus());
        Car returnedCar = carRepository.findByBrandAndManufactureYear(car.getBrand(), car.getManufactureYear()).orElse(new Car());
        assertFalse(clientRepository.findAll().stream().anyMatch(e -> e.getBirthYear() > 0));
        assertEquals(returnedCar.getOwner(), basicClient);
    }
}