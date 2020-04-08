package rusoft.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import rusoft.project.entity.AbstractEntity;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity, I> extends CrudRepository<E, I> {

}