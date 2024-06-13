package matriculas.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import matriculas.models.dao.IContactoDao;
import matriculas.models.dao.IUsuarioDao;
import matriculas.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IContactoDao contactoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		//contactoDao.save(usuario.getInfoContacto());
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long cedula) {
		return usuarioDao.findById(cedula).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long cedula) {
		usuarioDao.deleteById(cedula);
	}
	
	@Transactional
	public Usuario findByNombre(String nombre) {
		List<Usuario> listaUsuarios = (List<Usuario>) usuarioDao.findAll();
		for(int i = 0; i < listaUsuarios.size(); i++) {
			if(listaUsuarios.get(i).getNombre().equals(nombre)){
				return listaUsuarios.get(i);
			}
		}
		return null;
	}

}
