package rusoft.project.unit.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;
import rusoft.project.model.ResponseStatus;
import rusoft.project.repository.CarRepository;
import rusoft.project.repository.ClientRepository;
import rusoft.project.service.RentService;
import rusoft.project.service.RentServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RentServiceUnitTest {

    @MockBean
    private CarRepository carRepository;
    @MockBean
    private ClientRepository clientRepository;
    private RentService rentService;
    private RentStartDto rentStartDto;
    private RentEndDto rentEndDto;
    private Client basicClient;
    private Client client;
    private Car car;

    @BeforeEach
    void setUp() {
        basicClient = new Client()
                .setName("Parking")
                .setBirthYear(1900L);
        client = new Client()
                .setName("Ivan")
                .setBirthYear(1990L);
        car = new Car()
                .setBrand("BMW")
                .setManufactureYear(2019L);
        rentStartDto = new RentStartDto()
                .setClientName(client.getName())
                .setBirthYear(client.getBirthYear())
                .setCarBrand(car.getBrand())
                .setManufactureYear(car.getManufactureYear());
        rentEndDto = new RentEndDto()
                .setClientName(client.getName())
                .setCarBrand(car.getBrand());
        rentService = new RentServiceImpl(carRepository, clientRepository, basicClient);
    }

    @Test
    void addClientOnExistClientReturnBadRequest() {
        when(clientRepository.existsByNameAndBirthYear(Mockito.anyString(), Mockito.anyLong())).thenReturn(true);
        ResponseStatus response = rentService.addClient(rentStartDto);
        assertEquals(ResponseStatus.CLIENTFOUND, response);
    }

    @Test
    void addClientOnCarNotExistReturnNotFound() {
        when(clientRepository.existsByNameAndBirthYear(Mockito.anyString(), Mockito.anyLong())).thenReturn(false);
        when(carRepository.existsByBrandAndManufactureYear(Mockito.anyString(), Mockito.anyLong())).thenReturn(false);
        ResponseStatus response = rentService.addClient(rentStartDto);
        assertEquals(ResponseStatus.CARNOTFOUND, response);
    }

    @Test
    void addClientOnCarNotAvailableReturnNotAcceptable() {
        when(clientRepository.existsByNameAndBirthYear(Mockito.anyString(), Mockito.anyLong())).thenReturn(false);
        when(carRepository.existsByBrandAndManufactureYear(Mockito.anyString(), Mockito.anyLong())).thenReturn(true);
        when(carRepository.findByBrandAndManufactureYear(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(car.setOwner(client)));
        ResponseStatus response = rentService.addClient(rentStartDto);
        assertEquals(ResponseStatus.CARINRENT, response);
    }

    @Test
    void addClientOnRequestReturnOk() {
        when(clientRepository.existsByNameAndBirthYear(Mockito.anyString(), Mockito.anyLong())).thenReturn(false);
        when(carRepository.existsByBrandAndManufactureYear(Mockito.anyString(), Mockito.anyLong())).thenReturn(true);
        when(carRepository.findByBrandAndManufactureYear(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(Optional.of(car.setOwner(basicClient)));
        ResponseStatus response = rentService.addClient(rentStartDto);
        assertEquals(ResponseStatus.OK, response);
        verify(carRepository, times(1)).save(car);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void removeClientOnRecursiveClientReturnBadRequest() {
        rentEndDto.setClientName(basicClient.getName());
        ResponseStatus response = rentService.removeClient(rentEndDto);
        assertEquals(ResponseStatus.CLIENTCARNOTFOUND, response);
    }

    @Test
    void removeClientOnIncorrectClientCarCoupleReturnBadRequest() {
        when(carRepository.findByBrandAndName(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());
        rentEndDto.setClientName(basicClient.getName());
        ResponseStatus response = rentService.removeClient(rentEndDto);
        assertEquals(ResponseStatus.CLIENTCARNOTFOUND, response);
    }

    @Test
    void removeClientOnRequestReturnOk() {
        when(carRepository.findByBrandAndName(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(car.setOwner(client)));
        ResponseStatus response = rentService.removeClient(rentEndDto);
        assertEquals(ResponseStatus.OK, response);
        verify(clientRepository, times(1)).delete(client);
        verify(carRepository, times(1)).save(car.setOwner(basicClient));
    }
}