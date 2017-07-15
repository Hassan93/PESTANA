package mz.PestanaRovuma.Hotel_and_Resorts.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name="pestana_bsn_comentario_commt")
public class Comentario {
 @Id
 @Column(name="id_Comentario_commt")
 @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Comentario;
 @Column(name="Area_de_sugestao_commt")
	private String area_sugestao;
@Column(name="classificacao_commt")
	private String classificacao;
 @Column(name="comentario_commt")
	private String comentario;
 @OneToOne
 @JoinColumn(name="id_hospede")
	private Hospede hospede;
 @Column(name="data_da_submissao_commt")
	private Date data_da_submissao;
 
public String getArea_sugestao() {
	return area_sugestao;
}
public void setArea_sugestao(String area_sugestao) {
	this.area_sugestao = area_sugestao;
}

public int getId_Comentario() {
	return id_Comentario;
}
public void setId_Comentario(int id_Comentario) {
	this.id_Comentario = id_Comentario;
}
public String getComentario() {
	return comentario;
}
public void setComentario(String comentario) {
	this.comentario = comentario;
}
public String getClassificacao() {
	return classificacao;
}
public void setClassificacao(String classificacao) {
	this.classificacao = classificacao;
}
public Hospede getHospede() {
	return hospede;
}
public void setHospede(Hospede hospede) {
	this.hospede = hospede;
}
public Date getData_da_submissao() {
	return data_da_submissao;
}
	
		
}
