package rusoft.project.service;

import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;

public interface ClientService {

    public Client addClient(RentStartDto rentStartData);

    public Car removeClient(RentEndDto rentEndData);
}