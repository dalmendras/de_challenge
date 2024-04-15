package cl.dechallenge.apirest.backend.repository;

import cl.dechallenge.apirest.backend.model.Jobs;
import org.springframework.data.repository.CrudRepository;

public interface JobsRepository extends CrudRepository<Jobs, Integer> {

}
