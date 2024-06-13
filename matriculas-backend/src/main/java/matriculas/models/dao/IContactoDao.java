package matriculas.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import matriculas.models.entity.Contacto;
import matriculas.models.entity.Usuario;

public interface IContactoDao extends CrudRepository<Contacto, Long>{

}
