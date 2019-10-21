package dad.javafx.iniciosesionmvc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class View extends GridPane {
	
	private TextField usuarioText;
	private PasswordField passwordText;
	private Button accederButton;
	private Button cancelarButton;
	
	public View() {
		super();
		
		usuarioText = new TextField();
		usuarioText.setPromptText("Usuario");
		
		passwordText = new PasswordField();
		passwordText.setPromptText("Contraseña");
		
		accederButton = new Button("Acceder");
		accederButton.setDefaultButton(true);
		
		cancelarButton = new Button("Cancelar");
		
		HBox buttonHbox = new HBox(10, accederButton, cancelarButton);
		buttonHbox.setAlignment(Pos.CENTER);
		
		this.setHgap(30);
		this.setVgap(15);
		this.setPadding(new Insets(5));
		this.setGridLinesVisible(false);
		this.addRow(0, new Label("Usuario:"), usuarioText);
		this.addRow(1, new Label("Contraseña:"), passwordText);
		this.addRow(2, buttonHbox);
		this.setAlignment(Pos.CENTER);
		
		GridPane.setColumnSpan(buttonHbox, 2);
		
	}
	
	public TextField getUsuarioText() {
		return usuarioText;
	}

	public PasswordField getPasswordText() {
		return passwordText;
	}

	public Button getAccederButton() {
		return accederButton;
	}

	public Button getCancelarButton() {
		return cancelarButton;
	}

	public void clearPassword() {
		passwordText.clear();
	}
	
	
}
