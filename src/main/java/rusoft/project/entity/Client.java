package rusoft.project.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "clients")
public class Client implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String clientName;
    @NotEmpty
    private Long bitrhYear;
    @OneToMany(mappedBy = "owner")
    @OrderBy("carId ASC")
    private List<Car> cars;
}
