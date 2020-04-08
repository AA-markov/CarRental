package rusoft.project.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class RentStartDto {
    @NotEmpty
    String clientName;
    @NotEmpty
    Long birthYear;
    @NotEmpty
    String carBrand;
    @NotEmpty
    Long manufactureYear;
}
