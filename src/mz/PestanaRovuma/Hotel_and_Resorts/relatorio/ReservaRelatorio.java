package mz.PestanaRovuma.Hotel_and_Resorts.relatorio;

import java.util.Date;

public class ReservaRelatorio {
	private long id_reserva;
	private String nome;
	private String proposito;
	private long id_quarto;
	private Date data_checkin;
	private Date data_checkout;
	public long getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(long id_reserva) {
		this.id_reserva = id_reserva;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProposito() {
		return proposito;
	}
	public void setProposito(String proposito) {
		this.proposito = proposito;
	}
	public long getId_quarto() {
		return id_quarto;
	}
	public void setId_quarto(long id_quarto) {
		this.id_quarto = id_quarto;
	}
	public Date getData_checkin() {
		return data_checkin;
	}
	public void setData_checkin(Date data_checkin) {
		this.data_checkin = data_checkin;
	}
	public Date getData_checkout() {
		return data_checkout;
	}
	public void setData_checkout(Date data_checkout) {
		this.data_checkout = data_checkout;
	}
	
	}