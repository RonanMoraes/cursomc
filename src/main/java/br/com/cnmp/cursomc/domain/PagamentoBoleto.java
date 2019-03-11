package br.com.cnmp.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import br.com.cnmp.cursomc.domain.enums.EstadoPagamento;


@Entity
public class PagamentoBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoBoleto() {
		super();
		
	}
	public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento; 
		this.dataVencimento = dataVencimento;
	}
	public Date getNumeroParcelas() {
		return dataVencimento;
	}
	public void setNumeroParcelas(Date numeroParcelas) {
		this.dataVencimento = numeroParcelas;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
	

}
