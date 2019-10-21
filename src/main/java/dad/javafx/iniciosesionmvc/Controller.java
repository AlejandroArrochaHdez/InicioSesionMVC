package dad.javafx.iniciosesionmvc;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.codec.digest.DigestUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {
	
	private View view = new View();
	private Model model = new Model();

	public Controller() {
		model.usuarioProperty().bind(view.getUsuarioText().textProperty());
		model.passwordProperty().bind(view.getPasswordText().textProperty());
		
		view.getAccederButton().setOnAction(e -> onAccederAction(e));
		view.getCancelarButton().setOnAction(e -> onCancelarAction(e));
	}
	
	private void onCancelarAction(ActionEvent e) {
		Platform.exit();
	}

	private void onAccederAction(ActionEvent e) {
		boolean acceder = validarUsuario();
		
		if (acceder) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Iniciar Sesion");
			alert.setHeaderText("Acceso Permitido");
			alert.setContentText("Las credenciales de acceso son válidas");
			alert.showAndWait();
			Platform.exit();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Iniciar Sesion");
			alert.setHeaderText("Acceso Denegado");
			alert.setContentText("El usuario y/o la contraseña no son válidos");
			alert.showAndWait();
			view.clearPassword();
		}
	}
	
	public boolean validarUsuario() {
		
		//Variables de metodo
		
		String user = model.getUsuario(); //Nombre usuario
		String pass = DigestUtils.md5Hex(model.getPassword()).toUpperCase();	//Password	
		boolean acceso = false;
		
		
		//BLOQUE DE LECTURA DE FICHERO
		
		String fichero = "user.csv";
		String line = "";
		String separador = ",";
		
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			
			line = br.readLine();
			
			while(line != null) {
				
				String[] datos = line.split(separador);
				
				//Condicional que se encarga de comprobar que coincide nombre y contraseña con los almacenados
				
				if(  (user.equals(datos[0]))  &&  (pass.equals(datos[1]))  ) { 														
					acceso = true; //Validamos el acceso
					break; //Abandonamos el bucle					
				}
				
				
				line = br.readLine();
				
			}

			br.close();
			
		} catch (Exception e1) {
			
			System.out.println("Error en la lectura");
		}
		
		
		return acceso;
		
	}

	public View getRoot() {
		return view;
	}
	
}
