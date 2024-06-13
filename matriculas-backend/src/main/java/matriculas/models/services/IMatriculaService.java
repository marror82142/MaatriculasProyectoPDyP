package matriculas.models.services;

import java.util.List;

import matriculas.models.entity.Matricula;

public interface IMatriculaService {
	public List<Matricula> findAll();
	
	public Matricula save(Matricula matricula);
	
	public Matricula findById(Long id);
	
	public void delete(Long id);
}
