package matriculas.models.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import matriculas.models.dao.IProgramaDao;
import matriculas.models.entity.Programa;

@Service
public class ProgramaServiceImpl implements IProgramaService {

	@Autowired
	private IProgramaDao programaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Programa> findAll() {
		return (List<Programa>) programaDao.findAll();
	}

	@Override
	@Transactional
	public Programa save(Programa programa) {
		return programaDao.save(programa);
	}

	@Override
	@Transactional(readOnly = true)
	public Programa findById(Long id) {
		return programaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Programa findByCodigo(String codigo) {
		List<Programa> programas = (List<Programa>) programaDao.findAll();
		for (Iterator iterator = programas.iterator(); iterator.hasNext();) {
			Programa programa = (Programa) iterator.next();
			if(programa.getCodigo().equals(codigo)) {
				return programa;
			}
			
		}
		return null;
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		programaDao.deleteById(id);
	}

}
