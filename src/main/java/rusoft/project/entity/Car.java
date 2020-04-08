package rusoft.project.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "cars")
public class Car implements AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String brand;
    @NotEmpty
    private Long manufactureYear;
    @ManyToOne
    @JoinColumn(name = "name")
    private Client owner;
}