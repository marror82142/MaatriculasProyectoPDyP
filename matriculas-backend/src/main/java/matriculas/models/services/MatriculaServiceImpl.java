package matriculas.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import matriculas.models.dao.IMatriculaDao;
import matriculas.models.entity.Matricula;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

	@Autowired
	private IMatriculaDao matriculaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Matricula> findAll() {
		return (List<Matricula>) matriculaDao.findAll();
	}

	@Override
	@Transactional
	public Matricula save(Matricula matricula) {
		return matriculaDao.save(matricula);
	}

	@Override
	@Transactional(readOnly = true)
	public Matricula findById(Long id) {
		return matriculaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		matriculaDao.deleteById(id);
	}

}
