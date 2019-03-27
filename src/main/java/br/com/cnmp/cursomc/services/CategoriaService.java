package br.com.cnmp.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cnmp.cursomc.domain.Categoria;
import br.com.cnmp.cursomc.dto.CategoriaDTO;
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
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria  insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria  update (Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir categorias que estejam com produtos cadastrados");
		}
		
	}
	public Categoria fromDTO(CategoriaDTO catDTO) {
		return new Categoria(catDTO.getId(), catDTO.getNome());
	}
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
