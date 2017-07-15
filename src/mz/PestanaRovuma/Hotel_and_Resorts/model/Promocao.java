package mz.PestanaRovuma.Hotel_and_Resorts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_promocao_prm")
public class Promocao {
@Id
@Column(name="id_Promocao_prm")
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Promocao;
@Column(name="descricao_prm")
	private String descricao;
@ManyToOne
@JoinColumn(name="id_categoria")
    private Categoria categoria;
@Column(name="quantidadeMinimaQuartos_prm")
	private int quantidadeMinimaQuartos;
@Column(name="reducaoPercentual_prm")
	private int reducaoPercentual;
@Column(name="data_inicial_prm")
	private Date data_inicial;
@Column(name="data_final_prm")
	private Date data_final;
@Column(name="publicada")
    private String situacao;

public String getSituacao() {
	return situacao;
}
public void setSituacao(String situacao) {
	this.situacao = situacao;
}
public Categoria getCategoria() {
	return categoria;
}
public void setCategoria(Categoria categoria) {
	this.categoria = categoria;
}
public int getId_Promocao() {
	return id_Promocao;
}
public void setId_Promocao(int id_Promocao) {
	this.id_Promocao = id_Promocao;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public int getQuantidadeMinimaQuartos() {
	return quantidadeMinimaQuartos;
}
public void setQuantidadeMinimaQuartos(int quantidadeMinimaQuartos) {
	this.quantidadeMinimaQuartos = quantidadeMinimaQuartos;
}
public int getReducaoPercentual() {
	return reducaoPercentual;
}
public void setReducaoPercentual(int reducaoPercentual) {
	this.reducaoPercentual = reducaoPercentual;
}
public Date getData_inicial() {
	return data_inicial;
}
public void setData_inicial(Date data_inicial) {
	this.data_inicial = data_inicial;
}
public Date getData_final() {
	return data_final;
}
public void setData_final(Date data_final) {
	this.data_final = data_final;
}
	
	
}
