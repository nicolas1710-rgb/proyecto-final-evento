package com.ticketflow.controller;

import com.ticketflow.app.Main;
import com.ticketflow.model.Usuario;
import com.ticketflow.util.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistroController {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label lblMensaje;

    @FXML
    public void onRegistrar() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String tel = txtTelefono.getText().trim();
        String pass = txtContrasena.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
            lblMensaje.setText("Nombre, correo y contraseña son obligatorios.");
            return;
        }

        try {
            Usuario u = new Usuario(nombre, correo, tel, pass);
            Main.usuarioService.registrar(u);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registro exitoso. Inicie sesión.");
            alert.showAndWait();
            volver();
        } catch (IllegalArgumentException e) {
            lblMensaje.setText(e.getMessage());
        }
    }

    @FXML
    public void volver() {
        NavigationManager.getInstance().goBack();
    }
}
