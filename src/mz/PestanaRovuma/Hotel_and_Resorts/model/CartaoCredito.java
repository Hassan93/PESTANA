package mz.PestanaRovuma.Hotel_and_Resorts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_cartaocredito_card")
public class CartaoCredito {

	@Id
	@Column(name="id_numeroDaConta_card")
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id_numeroDaConta;
	@Column(name="tipo_card")
	private String tipo;
	@Column(name="dataExpiracao_card")
	private Date dataExpiracao;
	@Column(name="senha_card")
	private String senha;
	@ManyToOne
	@JoinColumn(name = "id_Cliente")
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public long getId_numeroDaConta() {
		return id_numeroDaConta;
	}
	public void setId_numeroDaConta(long id_numeroDaConta) {
		this.id_numeroDaConta = id_numeroDaConta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
