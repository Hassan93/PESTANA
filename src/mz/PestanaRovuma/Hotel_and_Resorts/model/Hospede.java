package mz.PestanaRovuma.Hotel_and_Resorts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_hospede_hspd")
public class Hospede{

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id_hospede_hspd")

private int id_hospede;

@OneToOne
@JoinColumn(name="id_ocupante")
private Ocupante ocupante;
public Ocupante getOcupante() {
	return ocupante;
}
public void setOcupante(Ocupante ocupante) {
	this.ocupante = ocupante;
}
public int getId_hospede() {
	return id_hospede;
}
public void setId_hospede(int id_hospede) {
	this.id_hospede = id_hospede;
}

	}
