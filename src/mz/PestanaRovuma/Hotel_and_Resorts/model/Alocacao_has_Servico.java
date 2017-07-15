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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mz.PestanaRovuma.Hotel_and_Resorts.controller.Operacao;

@Entity
@Table(name="pestana_bsn_alocacao_has_servico_alse")
public class Alocacao_has_Servico {
	
	
	@Id
	@Column(name="id_alocacao_has_servico_alse")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Alocacao_has_Servico;	
	@OneToOne
	@JoinColumn(name="id_Alocacao")
	private Alocacao alocacao;
	@OneToOne
	@JoinColumn(name="id_Servico")
	private Servico servico;
	@Column(name="data_de_uso_alse")
	private Date data_de_uso;
	@Column(name="hora_de_uso_alse")
	private String hora_de_uso;
	@Column(name="confirmaPagamento_alse")
	private boolean confirmaPagamento;
    @Column(name="data_de_solicitacao_alse")
	private String data_de_solicitacao = new Operacao().dataActual();
    
    public String getData_de_solicitacao() {
		return data_de_solicitacao;
	}

	public String getHora_de_uso() {
		return hora_de_uso;
	}
	
	public void setHora_de_uso(String hora_de_uso) {
		this.hora_de_uso = hora_de_uso;
	}
	
	public int getId_Alocacao_has_Servico() {
		return id_Alocacao_has_Servico;
	}
	public void setId_Alocacao_has_Servico(int id_Alocacao_has_Servico) {
		this.id_Alocacao_has_Servico = id_Alocacao_has_Servico;
	}
	public Alocacao getAlocacao() {
		return alocacao;
	}
	public void setAlocacao(Alocacao alocacao) {
		this.alocacao = alocacao;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Date getData_de_uso() {
		return data_de_uso;
	}
	public void setData_de_uso(Date data_de_uso) {
		this.data_de_uso = data_de_uso;
	}
	public boolean isConfirmaPagamento() {
		return confirmaPagamento;
	}
	public void setConfirmaPagamento(boolean confirmaPagamento) {
		this.confirmaPagamento = confirmaPagamento;
	}
	
	
}
