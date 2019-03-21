package br.com.cnmp.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.cnmp.cursomc.domain.Categoria;
import br.com.cnmp.cursomc.repositories.CategoriaRepository;
import br.com.cnmp.cursomc.services.exeception.DataIntegrityException;
import br.com.cnmp.cursomc.services.exeception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado. Id:"+id+" Tipo:"+Categoria.class.getName()));
	}
	
	public Categoria  insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public Categoria  update (Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir categorias que estejam com produtos cadastrados");
		}
		
	}
}
