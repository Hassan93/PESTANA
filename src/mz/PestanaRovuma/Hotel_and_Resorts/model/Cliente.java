package mz.PestanaRovuma.Hotel_and_Resorts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_cliente_clt")

public class Cliente {
	@Id
	@Column(name="id_Cliente_clt")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Cliente; 
	@Column(name="nome_clt")
	private String nome;
	@Column(name="apelido_clt")
	private String apelido;
	@Column(name="email_clt")
	private String email;
	@ManyToOne
	@JoinColumn(name="id_p")
	private Pais pais;
	@Column(name="contacto_clt")
	private long contacto;
	@OneToOne
	@JoinColumn(name="id_numeroDaConta")
	private CartaoCredito cartao;
	
	public int getId_Cliente() {
		return id_Cliente;
	}
	public void setId_Cliente(int id_Cliente) {
		this.id_Cliente = id_Cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public long getContacto() {
		return contacto;
	}
	public void setContacto(long contacto) {
		this.contacto = contacto;
	}
	public CartaoCredito getCartao() {
		return cartao;
	}
	public void setCartao(CartaoCredito cartao) {
		this.cartao = cartao;
	}
	
	
}
