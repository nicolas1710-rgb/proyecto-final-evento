package com.ticketflow.controller;

import com.ticketflow.app.Main;
import com.ticketflow.model.Recinto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestionRecintosController {
    @FXML
    private TableView<Recinto> tvRecintos;
    @FXML
    private TableColumn<Recinto, String> colNombre;
    @FXML
    private TableColumn<Recinto, String> colDireccion;
    @FXML
    private TableColumn<Recinto, String> colCiudad;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtCiudad;
    @FXML
    private Label lblMensaje;

    private Recinto recintoSeleccionado;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getNombre()));
        colDireccion.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getDireccion()));
        colCiudad.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getCiudad()));

        tvRecintos.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null)
                seleccionarRecinto(newV);
        });

        cargarRecintos();
    }

    private void cargarRecintos() {
        tvRecintos.setItems(FXCollections.observableArrayList(Main.recintoRepo.findAll()));
    }

    private void seleccionarRecinto(Recinto r) {
        recintoSeleccionado = r;
        txtNombre.setText(r.getNombre());
        txtDireccion.setText(r.getDireccion());
        txtCiudad.setText(r.getCiudad());
    }

    @FXML
    public void onGuardar() {
        String nombre = txtNombre.getText().trim();
        String dir = txtDireccion.getText().trim();
        String ciu = txtCiudad.getText().trim();

        if (nombre.isEmpty() || ciu.isEmpty()) {
            lblMensaje.setText("Nombre y ciudad son obligatorios.");
            return;
        }

        if (recintoSeleccionado == null) {
            Main.recintoRepo.save(new Recinto(nombre, dir, ciu));
            lblMensaje.setText("Recinto creado.");
        } else {
            recintoSeleccionado.setNombre(nombre);
            recintoSeleccionado.setDireccion(dir);
            recintoSeleccionado.setCiudad(ciu);
            Main.recintoRepo.update(recintoSeleccionado);
            lblMensaje.setText("Recinto actualizado.");
        }
        cargarRecintos();
        onLimpiar();
    }

    @FXML
    public void onLimpiar() {
        recintoSeleccionado = null;
        txtNombre.clear();
        txtDireccion.clear();
        txtCiudad.clear();
        tvRecintos.getSelectionModel().clearSelection();
    }
}
