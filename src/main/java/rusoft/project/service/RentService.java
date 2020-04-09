package rusoft.project.service;

import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.model.ResponseStatus;

public interface RentService {

    ResponseStatus addClient(RentStartDto rentStartData);

    ResponseStatus removeClient(RentEndDto rentEndData);
}