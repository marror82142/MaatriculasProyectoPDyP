package matriculas.models.services;

import java.util.List;

import matriculas.models.entity.Programa;

public interface IProgramaService {
	public List<Programa> findAll();
	
	public Programa save(Programa programa);
	
	public Programa findById(Long id);
	
	public Programa findByCodigo(String codigo);
	
	public void delete(Long id);
}
