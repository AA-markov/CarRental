package rusoft.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.entity.Car;
import rusoft.project.entity.Client;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ClientController {

    @PutMapping(path = "/add")
    public ResponseEntity<Client> addClient(@RequestBody @Valid RentStartDto rent) {
        return ResponseEntity.ok(new Client());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Car> deleteClient(@RequestBody @Valid RentEndDto rent) {
        return ResponseEntity.ok(new Car());
    }
}