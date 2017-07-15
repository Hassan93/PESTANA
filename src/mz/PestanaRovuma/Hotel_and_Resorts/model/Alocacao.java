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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_alocacao_aloca")

public class Alocacao {
	
	@Id
	@Column(name="id_Alocacao_aloca")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Alocacao;
	@OneToOne
	@JoinColumn(name="id_hospede")
	private Hospede hospede;
	@ManyToOne
	@JoinColumn(name="id_reserva")
	private Reserva reserva;
	@Column(name="alocado_aloca")
	private boolean alocado;	
	
	public boolean isAlocado() {
		return alocado;
	}
	public void setAlocado(boolean alocado) {
		this.alocado = alocado;
	}
	public int getId_Alocacao() {
		return id_Alocacao;
	}
	public void setId_Alocacao(int id_Alocacao) {
		this.id_Alocacao = id_Alocacao;
	}
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
	
	
	
}
