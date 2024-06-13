package matriculas.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import matriculas.models.entity.Matricula;

public interface IMatriculaDao extends CrudRepository<Matricula, Long>{

}
