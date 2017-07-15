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

import mz.PestanaRovuma.Hotel_and_Resorts.controller.Operacao;

@Entity
@Table(name="pestana_bsn_reserva_reserv")

public class Reserva {
	@Id
	@Column(name="id_reserva_reserv")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Reserva;
	@OneToOne
	@JoinColumn(name="id_Cliente")
	private Cliente cliente;
    @Column(name="alocado")
    private boolean alocado;
    @Column(name="dataReserva_reserv")
    private String dataReserva = new Operacao().dataActual();
    
	public boolean isAlocado() {
		return alocado;
	}
	public void setAlocado(boolean alocado) {
		this.alocado = alocado;
	}
	public int getId_Reserva() {
		return id_Reserva;
	}
	public void setId_Reserva(int id_Reserva) {
		this.id_Reserva = id_Reserva;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDataReserva() {
		return dataReserva;
	}
	
	
	
}
