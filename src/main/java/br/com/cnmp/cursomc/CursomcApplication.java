package br.com.cnmp.cursomc;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cnmp.cursomc.domain.Categoria;
import br.com.cnmp.cursomc.domain.Cidade;
import br.com.cnmp.cursomc.domain.Cliente;
import br.com.cnmp.cursomc.domain.Endereco;
import br.com.cnmp.cursomc.domain.Estado;
import br.com.cnmp.cursomc.domain.Pagamento;
import br.com.cnmp.cursomc.domain.PagamentoBoleto;
import br.com.cnmp.cursomc.domain.PagamentoCartao;
import br.com.cnmp.cursomc.domain.Pedido;
import br.com.cnmp.cursomc.domain.Produto;
import br.com.cnmp.cursomc.domain.enums.EstadoPagamento;
import br.com.cnmp.cursomc.domain.enums.TipoCliente;
import br.com.cnmp.cursomc.repositories.CategoriaRepository;
import br.com.cnmp.cursomc.repositories.CidadeRepository;
import br.com.cnmp.cursomc.repositories.ClienteRepository;
import br.com.cnmp.cursomc.repositories.EnderecoRepository;
import br.com.cnmp.cursomc.repositories.EstadoRepository;
import br.com.cnmp.cursomc.repositories.PagamentoRepository;
import br.com.cnmp.cursomc.repositories.PedidoRepository;
import br.com.cnmp.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private EstadoRepository estRepo;
	
	@Autowired
	private CidadeRepository cidRepo;
	
	@Autowired
	private ClienteRepository cliRepo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private PedidoRepository pedRepo;
	
	@Autowired
	private PagamentoRepository pagRepo;
	
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
	
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		
		estRepo.saveAll(Arrays.asList(est1, est2));
		cidRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36336336366", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("6132222122", "61981112002") );
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 203", "Jardim", "38330834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 600", "Centro", "7000100", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		cliRepo.saveAll(Arrays.asList(cli1));
		endRepo.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("11/02/2019 15:41"), cli1, e1); 
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 15:41"), cli1, e2); 
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 15:41"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
	
	}

}

