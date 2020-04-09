package rusoft.project.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class RentStartDto implements AbstractRentDto {
    @NotEmpty
    String clientName;
    @NotNull
    Long birthYear;
    @NotEmpty
    String carBrand;
    @NotNull
    Long manufactureYear;
}