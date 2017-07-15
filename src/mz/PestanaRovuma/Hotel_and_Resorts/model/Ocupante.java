package mz.PestanaRovuma.Hotel_and_Resorts.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="pestana_bsn_ocupante_ocup")
public class Ocupante {
@Id
@Column(name="id_ocupante_ocup")
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
@Column(name="nome_ocup")
	private String nome;
@ManyToOne
@JoinColumn(name="id_reserva_quarto")
	private Reserva_Quarto reserva;

@Column(name="data_checkin_ocup")
private Date data_checkin;

@Column(name="proposito_ocup")
private String proposito;
@Column(name="pedido_ocup")
private String pedido;

@ManyToOne
@JoinColumn(name="id_quarto")
private Quarto quarto;
@Column(name="data_chekout")
private Date dataCheckOut;


public Quarto getQuarto() {
	return quarto;
}

public void setQuarto(Quarto quarto) {
	this.quarto = quarto;
}

public Date getDataCheckOut() {
	return dataCheckOut;
}

public void setDataCheckOut(Date dataCheckOut) {
	this.dataCheckOut = dataCheckOut;
}

public Date getData_checkin() {
	return data_checkin;
}
public void setData_checkin(Date data_checkin) {
	this.data_checkin = data_checkin;
}

public String getPedido() {
	return pedido;
}

public void setPedido(String pedido) {
	this.pedido = pedido;
}

public String getProposito() {
	return proposito;
}

public void setProposito(String proposito) {
	this.proposito = proposito;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public Reserva_Quarto getReserva() {
	return reserva;
}
public void setReserva(Reserva_Quarto reserva) {
	this.reserva = reserva;
} 



}
