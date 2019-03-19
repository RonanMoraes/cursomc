package br.com.cnmp.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cnmp.cursomc.domain.Pedido;
import br.com.cnmp.cursomc.repositories.PedidoRepository;
import br.com.cnmp.cursomc.services.exeception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> cat = repo.findById(id);
		return cat.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado. Id:"+id+" Tipo:"+Pedido.class.getName()));
	}
}
