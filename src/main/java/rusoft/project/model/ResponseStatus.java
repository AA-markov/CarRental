package rusoft.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ResponseStatus {
    @JsonProperty("OK")
    OK (HttpStatus.OK),
    @JsonProperty("Client already exists")
    CLIENTFOUND (HttpStatus.BAD_REQUEST),
    @JsonProperty("Incorrect couple of car and client")
    CLIENTCARNOTFOUND (HttpStatus.BAD_REQUEST),
    @JsonProperty("Car with given data hasn't been found")
    CARNOTFOUND (HttpStatus.NOT_FOUND),
    @JsonProperty("Car is in use")
    CARINRENT (HttpStatus.NOT_ACCEPTABLE);

    @Getter
    @Setter
    private HttpStatus status;
}
