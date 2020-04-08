package rusoft.project.service;

import org.springframework.stereotype.Service;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public Client addClient(RentStartDto rentStartData) {
        //TODO: add
        return null;
    }

    @Override
    public Car removeClient(RentEndDto rentEndData) {
        //TODO: add
        return null;
    }
}