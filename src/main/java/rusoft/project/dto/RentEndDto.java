package rusoft.project.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
public class RentEndDto implements AbstractRentDto {
    @NotEmpty
    String clientName;
    @NotEmpty
    String carBrand;
}