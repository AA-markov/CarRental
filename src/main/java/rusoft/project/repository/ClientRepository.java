package rusoft.project.repository;

import rusoft.project.entity.Client;

public interface ClientRepository extends AbstractRepository<Client, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndBirthYear(String name, Long birthYear);
}