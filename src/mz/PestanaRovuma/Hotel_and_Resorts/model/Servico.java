package mz.PestanaRovuma.Hotel_and_Resorts.model;

import javax.persistence.*;


@Entity
@Table(name="pestana_bsn_servico_serv")
public class Servico {
	@Id
	@Column()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	@Column(name="designacao_serv")
	private String designacao;
	
	@Column(name="descricao_serv")
	private String descricao;
	
	@Column(name="preco_serv")
	private float preco;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}

}
