package com.ticketflow.controller;

import com.ticketflow.app.Main;
import com.ticketflow.model.Asiento;
import com.ticketflow.model.Recinto;
import com.ticketflow.model.Zona;
import com.ticketflow.enums.TipoZona;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestionZonasController {
    @FXML
    private ComboBox<Recinto> cmbRecintoFiltro;
    @FXML
    private TableView<Zona> tvZonas;
    @FXML
    private TableColumn<Zona, String> colNombre;
    @FXML
    private TableColumn<Zona, String> colCapacidad;
    @FXML
    private TableColumn<Zona, String> colPrecio;
    @FXML
    private TableColumn<Zona, String> colTipo;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCapacidad;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ComboBox<TipoZona> cmbTipo;
    @FXML
    private TextField txtFilaAsiento;
    @FXML
    private TextField txtNumAsiento;
    @FXML
    private Label lblMensaje;

    private Zona zonaSeleccionada;

    @FXML
    public void initialize() {
        cmbTipo.setItems(FXCollections.observableArrayList(TipoZona.values()));
        cmbRecintoFiltro.setItems(FXCollections.observableArrayList(Main.recintoRepo.findAll()));

        colNombre.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getNombre()));
        colCapacidad.setCellValueFactory(cf -> new SimpleStringProperty(String.valueOf(cf.getValue().getCapacidad())));
        colPrecio.setCellValueFactory(cf -> new SimpleStringProperty(String.valueOf(cf.getValue().getPrecioBase())));
        colTipo.setCellValueFactory(cf -> new SimpleStringProperty(cf.getValue().getTipoZona().name()));

        tvZonas.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null)
                seleccionarZona(newV);
        });
    }

    @FXML
    public void onRecintoSeleccionado() {
        Recinto r = cmbRecintoFiltro.getValue();
        if (r != null) {
            tvZonas.setItems(FXCollections.observableArrayList(r.getZonas()));
        }
    }

    private void seleccionarZona(Zona z) {
        zonaSeleccionada = z;
        txtNombre.setText(z.getNombre());
        txtCapacidad.setText(String.valueOf(z.getCapacidad()));
        txtPrecio.setText(String.valueOf(z.getPrecioBase()));
        cmbTipo.setValue(z.getTipoZona());
    }

    @FXML
    public void onGuardar() {
        Recinto r = cmbRecintoFiltro.getValue();
        if (r == null) {
            lblMensaje.setText("Seleccione un recinto primero.");
            return;
        }

        try {
            String nombre = txtNombre.getText().trim();
            int cap = Integer.parseInt(txtCapacidad.getText().trim());
            double preci = Double.parseDouble(txtPrecio.getText().trim());
            TipoZona tipo = cmbTipo.getValue();

            if (zonaSeleccionada == null) {
                Zona z = new Zona(nombre, cap, preci, tipo);
                Main.zonaRepo.save(z);
                r.getZonas().add(z);
                Main.recintoRepo.update(r);
                lblMensaje.setText("Zona creada.");
            } else {
                zonaSeleccionada.setNombre(nombre);
                zonaSeleccionada.setCapacidad(cap);
                zonaSeleccionada.setPrecioBase(preci);
                zonaSeleccionada.setTipoZona(tipo);
                Main.zonaRepo.update(zonaSeleccionada);
                lblMensaje.setText("Zona actualizada.");
            }
            onRecintoSeleccionado();
            onLimpiar();
        } catch (NumberFormatException e) {
            lblMensaje.setText("Capacidad y precio deben ser numéricos.");
        }
    }

    @FXML
    public void onAgregarAsiento() {
        if (zonaSeleccionada == null) {
            lblMensaje.setText("Seleccione una zona para agregar asientos.");
            return;
        }

        String fila = txtFilaAsiento.getText().trim();
        try {
            int num = Integer.parseInt(txtNumAsiento.getText().trim());
            Asiento a = new Asiento(fila, num, zonaSeleccionada);
            Main.asientoRepo.save(a);
            zonaSeleccionada.getAsientos().add(a);
            Main.zonaRepo.update(zonaSeleccionada);
            lblMensaje.setText("Asiento " + fila + "-" + num + " agregado.");
            txtFilaAsiento.clear();
            txtNumAsiento.clear();
        } catch (NumberFormatException e) {
            lblMensaje.setText("El número de asiento debe ser numérico.");
        }
    }

    @FXML
    public void onLimpiar() {
        zonaSeleccionada = null;
        txtNombre.clear();
        txtCapacidad.clear();
        txtPrecio.clear();
        cmbTipo.setValue(null);
        tvZonas.getSelectionModel().clearSelection();
    }
}
