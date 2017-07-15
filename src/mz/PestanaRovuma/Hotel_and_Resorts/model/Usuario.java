package mz.PestanaRovuma.Hotel_and_Resorts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario_usr")
public class Usuario {
@Id
@Column(name="id_Usuario_usr")
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_Usuario;
@Column(name="nome_usr")
	private String nome;
@Column(name="username_usr")
	private String username;
@Column(name="password_usr")
	private String password;
@Column(name="perfil_usr")
   private String perfil;
public String getPerfil() {
	return perfil;
}
public void setPerfil(String perfil) {
	this.perfil = perfil;
}
public int getId_Usuario() {
	return id_Usuario;
}
public void setId_Usuario(int id_Usuario) {
	this.id_Usuario = id_Usuario;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
	

}