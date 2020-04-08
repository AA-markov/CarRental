package rusoft.project.service;

import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.model.ResponseStatus;

public interface RentService {

    public ResponseStatus addClient(RentStartDto rentStartData);

    public ResponseStatus removeClient(RentEndDto rentEndData);
}