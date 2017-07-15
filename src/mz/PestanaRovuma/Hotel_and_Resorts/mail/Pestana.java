package mz.PestanaRovuma.Hotel_and_Resorts.mail;

import java.io.InputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import mz.PestanaRovuma.Hotel_and_Resorts.model.Reserva;


public class Pestana {

	public void enviar(String destinatario, Reserva reserva) {
		
		for(long x=0; x<1000000;x++){
			for(long y=0; y<100;y++){
				
				//demora
			}
			//demora
		}
		try {

		//usuario e senha do seu gmail
		final String usuario = "pestanarovuma2015@gmail.com";
		final String senha = "pestana2015";

		//config. do gmail
		Properties mailProps = new Properties();
		mailProps.put("mail.transport.protocol", "smtp");
		mailProps.put("mail.smtp.starttls.enable","true");
		mailProps.put("mail.smtp.host", "smtp.gmail.com");
		mailProps.put("mail.smtp.auth", "true");
		mailProps.put("mail.smtp.user", usuario);
		mailProps.put("mail.debug", "true");
		mailProps.put("mail.smtp.port", "465");
		mailProps.put("mail.smtp.socketFactory.port", "465");
		mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProps.put("mail.smtp.socketFactory.fallback", "false");

		//eh necessario autenticar
		Session mailSession = Session.getInstance(mailProps, new Authenticator() {	
		public PasswordAuthentication getPasswordAuthentication(){	
		return new PasswordAuthentication(usuario, senha);
		}
		});
		mailSession.setDebug(false);

		//config. da mensagem
		Message mailMessage = new MimeMessage(mailSession);

		//remetente
		mailMessage.setFrom(new InternetAddress(usuario));

		//destinatario
		mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));	

		//mensagem que vai no corpo do email
		MimeBodyPart mbpMensagem = new MimeBodyPart();
		mbpMensagem.setText("A sua reserva foi efectuada com sucesso e o seu codigo é: "+ reserva.getId_Reserva());

		//partes do email
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbpMensagem);
		//imagem que sera enviada em anexo, ta no mesmo diretorio da classe.
		String caminho = "/home/hassan/Projecto_Eclipse/SISPESTANA/relatorios/Gerado.pdf";
		InputStream is = getClass().getResourceAsStream(caminho);

		//setando o anexo
		MimeBodyPart mbpAnexo = new MimeBodyPart();
		mbpAnexo.setDataHandler(new DataHandler(new ByteArrayDataSource(is, "ap")));	
		mbpAnexo.setFileName("Gerado.pdf");
		mp.addBodyPart(mbpAnexo);

		//assunto do email
		mailMessage.setSubject("Pestana App");
		//seleciona o conteudo
		mailMessage.setContent(mp);

		//envia o email
		Transport.send(mailMessage);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		}

