package matriculas.models.services;

import java.util.List;

import matriculas.models.entity.Usuario;

public interface IUsuarioService {
	public List<Usuario> findAll();
	
	public Usuario save(Usuario usuario);
	
	public Usuario findById(Long cedula);
	
	public void delete(Long cedula);
}
