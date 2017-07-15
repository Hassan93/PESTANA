package mz.PestanaRovuma.Hotel_and_Resorts.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_categoria_cat")
public class Categoria {
	@Id
	@Column(name="id_Categoria_cat")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Categoria;
	@Column(name="designacao_cat")
	private String designacao;
	@Column(name="descricao_cat")
	private String descricao;
	@Column(name="preco_cat")
	private double preco;
	
	private Blob imagem;
	
	
	
	public Blob getImagem() {
		return imagem;
	}
	public void setImagem(Blob imagem) {
		this.imagem = imagem;
	}
	public int getId_Categoria() {
		return id_Categoria;
	}
	public void setId_Categoria(int id_Categoria) {
		this.id_Categoria = id_Categoria;
	}
	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
