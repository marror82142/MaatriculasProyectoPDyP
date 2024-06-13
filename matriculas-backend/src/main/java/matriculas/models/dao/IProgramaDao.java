package matriculas.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import matriculas.models.entity.Programa;

public interface IProgramaDao extends CrudRepository<Programa, Long>{

}
