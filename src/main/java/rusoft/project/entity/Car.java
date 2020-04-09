package rusoft.project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "cars")
public class Car implements AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull
    private String brand;
    @NotNull
    private Long manufactureYear;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    @EqualsAndHashCode.Exclude
    private Client owner;
}