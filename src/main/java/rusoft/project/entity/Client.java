package rusoft.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "clients")
@EqualsAndHashCode(exclude = {"id", "cars"})
public class Client implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Long birthYear;
    @OneToMany(mappedBy = "owner")
    @OrderBy("id ASC")
    private List<Car> cars;
}