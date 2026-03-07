package com.ticketflow.controller;

import com.ticketflow.app.Main;
import com.ticketflow.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestionUsuariosController {
    @FXML
    private TableView<Usuario> tvUsuarios;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colCorreo;
    @FXML
    private TableColumn<Usuario, String> colTelefono;
    @FXML
    private Label lblMetodos;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getNombreCompleto()));
        colCorreo.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getCorreoElectronico()));
        colTelefono.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getTelefono()));

        tvUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                lblMetodos.setText("Métodos de Pago Agregados: " + newV.getMetodosDePago().size());
            }
        });

        cargarUsuarios();
    }

    private void cargarUsuarios() {
        tvUsuarios.setItems(FXCollections.observableArrayList(Main.usuarioRepo.findAll()));
    }
}
