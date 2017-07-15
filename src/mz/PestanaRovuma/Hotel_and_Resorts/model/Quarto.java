package mz.PestanaRovuma.Hotel_and_Resorts.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="pestana_bsn_quarto_qrt")
public class Quarto {

@Id
@Column(name = "id_Quarto_qrt")
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_Quarto;
@Column(name = "designacao_qrt")
    private String designacao;
@ManyToOne
@JoinColumn(name = "id_Categoria")
    private Categoria categoria;
@Column(name = "ocupantes_qrt")
    private int ocupantes;
@Column(name = "localizacao_qrt")
    private String localizacao;

		public int getId_Quarto() {
			return id_Quarto;
		}
		public void setId_Quarto(int id_Quarto) {
			this.id_Quarto = id_Quarto;
		}
		public String getDesignacao() {
			return designacao;
		}
		public void setDesignacao(String designacao) {
			this.designacao = designacao;
		}
		public Categoria getCategoria() {
			return categoria;
		}
		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		public int getOcupantes() {
			return ocupantes;
		}
		public void setOcupantes(int ocupantes) {
			this.ocupantes = ocupantes;
		}
		public String getLocalizacao() {
			return localizacao;
		}
		public void setLocalizacao(String localizacao) {
			this.localizacao = localizacao;
		}
			
    
}
