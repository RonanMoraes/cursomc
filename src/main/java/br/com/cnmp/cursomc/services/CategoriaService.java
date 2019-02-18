package br.com.cnmp.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cnmp.cursomc.domain.Categoria;
import br.com.cnmp.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = repo.findById(id);
		return cat.orElse(null);
	}
}
