import mz.PestanaRovuma.Hotel_and_Resorts.Dao.UsuarioDao;
import mz.PestanaRovuma.Hotel_and_Resorts.model.Usuario;

public class Texte {

	public static void main(String[] args) {
		Usuario user = new Usuario();
		UsuarioDao uss = new UsuarioDao();
		  user.setNome("Recepcionista");
		  user.setPerfil("recep");
		  user.setPassword("12345");
		  user.setUsername("drem");
		  uss.create(user);

	}

}
