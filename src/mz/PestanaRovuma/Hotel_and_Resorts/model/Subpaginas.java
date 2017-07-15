package mz.PestanaRovuma.Hotel_and_Resorts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pestana_bsn_subpaginas_subp")
public class Subpaginas {

	@Id
	@Column(name="id_subpaginas_subp")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_subpaginas;
	@Column(name="areaGestor_subp")
	private String areaGestor;
	@Column(name="areaRecepcionista_subp")
	private String areaRecepcionista;
	@Column(name="areaHospede_subp")
	private String areaHospede;
	
	public int getId_subpaginas() {
		return id_subpaginas;
	}
	public void setId_subpaginas(int id_subpaginas) {
		this.id_subpaginas = id_subpaginas;
	}
	public String getAreaGestor() {
		return areaGestor;
	}
	public void setAreaGestor(String areaGestor) {
		this.areaGestor = areaGestor;
	}
	public String getAreaRecepcionista() {
		return areaRecepcionista;
	}
	public void setAreaRecepcionista(String areaRecepcionista) {
		this.areaRecepcionista = areaRecepcionista;
	}
	public String getAreaHospede() {
		return areaHospede;
	}
	public void setAreaHospede(String areaHospede) {
		this.areaHospede = areaHospede;
	}
	
	
	
	
}
