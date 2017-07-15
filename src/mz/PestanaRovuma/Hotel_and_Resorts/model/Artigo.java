package mz.PestanaRovuma.Hotel_and_Resorts.model;

import java.awt.Image;
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
@Table(name="pestana_bsn_artigo_art")
public class Artigo {
	@Id
	@Column(name="id_Artigo_art")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Artigo;
	@Column(name="descricao_art")
	private String descricao;
	@Column(name="imagem_art")
	private byte [] imagem;
	
	
	public int getId_Artigo() {
		return id_Artigo;
	}
	public void setId_Artigo(int id_Artigo) {
		this.id_Artigo = id_Artigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	

	
	
}
