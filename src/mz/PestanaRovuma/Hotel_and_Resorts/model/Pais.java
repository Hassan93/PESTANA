package mz.PestanaRovuma.Hotel_and_Resorts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_pais_p")

public class Pais {
	
	@Id
	@Column(name="id_p")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="prefixo_p")
	private int prefixo;
	@Column(name="nome_util")
	private String nome;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPrefixo() {
		return prefixo;
	}
	public void setPrefixo(int prefixo) {
		this.prefixo = prefixo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return nome;
	}
	
  
}
