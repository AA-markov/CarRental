package rusoft.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rusoft.project.dto.RentEndDto;
import rusoft.project.dto.RentStartDto;
import rusoft.project.model.ResponseStatus;
import rusoft.project.service.RentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @PutMapping(path = "/add")
    public ResponseEntity<String> addClient(@RequestBody @Valid RentStartDto rentStart) {
        ResponseStatus response = rentService.addClient(rentStart);
        return new ResponseEntity(response, response.getStatus());
        //    OK,
        //    CLIENTFOUND,
        //    CARNOTFOUND,
        //    CARINRENT
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteClient(@RequestBody @Valid RentEndDto rentEnd) {
        ResponseStatus response = rentService.removeClient(rentEnd);
        return new ResponseEntity(response, response.getStatus());
        //    OK,
        //    CLIENTCARNOTFOUND
    }
}