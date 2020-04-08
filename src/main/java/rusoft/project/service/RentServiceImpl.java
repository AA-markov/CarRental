package rusoft.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;
import rusoft.project.model.ResponseStatus;
import rusoft.project.repository.CarRepository;
import rusoft.project.repository.ClientRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RentServiceImpl implements RentService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final Client basicClient;

    @Override
    @Transactional
    public ResponseStatus addClient(RentStartDto rentStartData) {
        if (clientRepository.existsByNameAndBirthYear(rentStartData.getClientName(),
                rentStartData.getBirthYear())) {
            return ResponseStatus.CLIENTFOUND;
        }

        if (!carRepository.existsByBrandAndManufactureYear(rentStartData.getCarBrand(),
                    rentStartData.getManufactureYear())) {
            return ResponseStatus.CARNOTFOUND;
        }
        if (!findByBrandYear(rentStartData.getCarBrand(), rentStartData.getManufactureYear())
                .getOwner().equals(basicClient)) {
            return ResponseStatus.CARINRENT;
        }
        else {
            createRent(rentStartData);
            return ResponseStatus.OK;
        }
    }

    @Override
    public ResponseStatus removeClient(RentEndDto rentEndData) {
        if (rentEndData.getClientName().equals(basicClient.getName())) {
            return ResponseStatus.CLIENTCARNOTFOUND;
        }
        if (carRepository.existsByBrandAndName(rentEndData.getCarBrand(), rentEndData.getClientName())) {
            endRent(rentEndData);
            return ResponseStatus.OK;
        }
        else return ResponseStatus.CLIENTCARNOTFOUND;
    }

    private void createRent(RentStartDto rentStartData) {
        Car car = findByBrandYear(rentStartData.getCarBrand(), rentStartData.getManufactureYear());
        Client client = new Client().setName(rentStartData.getClientName())
                .setBirthYear(rentStartData.getBirthYear())
                .setCars(Collections.singletonList(car));
        clientRepository.save(client);
        carRepository.save(car.setOwner(client));
    }

    private void endRent(RentEndDto rentEndData) {
        Car car = findByBrandName(rentEndData.getCarBrand(), rentEndData.getClientName());
        Client client = car.getOwner();
        clientRepository.delete(client);
        carRepository.save(car.setOwner(basicClient));
    }

    private Car findByBrandYear(String brand, long year) {
        return carRepository.findByBrandAndManufactureYear(brand, year).orElseThrow(() ->
                new EntityNotFoundException(String.format("%s, %s does not exist in database", brand, year)));
    }

    private Car findByBrandName(String brand, String name) {
        return carRepository.findByBrandAndName(brand, name).orElseThrow(() ->
                new EntityNotFoundException(String.format("%s with owner %s does not exist in database", brand, name)));
    }
}