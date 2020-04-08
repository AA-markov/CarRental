package rusoft.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;
import rusoft.project.service.ClientService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PutMapping(path = "/add")
    public ResponseEntity<Client> addClient(@RequestBody @Valid RentStartDto rentStart) {
        Client response = clientService.addClient(rentStart);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Car> deleteClient(@RequestBody @Valid RentEndDto rentEnd) {
        Car response = clientService.removeClient(rentEnd);
        return ResponseEntity.ok(response);
    }
}