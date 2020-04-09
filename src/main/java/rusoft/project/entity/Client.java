package rusoft.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "clients")
public class Client implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long birthYear;
    @OneToMany(mappedBy = "owner")
    @EqualsAndHashCode.Exclude
    private List<Car> carList;
}