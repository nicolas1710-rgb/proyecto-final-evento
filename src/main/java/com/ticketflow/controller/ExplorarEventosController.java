package com.ticketflow.controller;

import com.ticketflow.app.Main;
import com.ticketflow.model.Evento;
import com.ticketflow.enums.CategoriaEvento;
import com.ticketflow.util.NavigationManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ExplorarEventosController {
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ComboBox<String> cmbCiudad;
    @FXML
    private ComboBox<CategoriaEvento> cmbCategoria;
    @FXML
    private TextField txtPrecioMax;
    @FXML
    private TableView<Evento> tvEventos;
    @FXML
    private TableColumn<Evento, String> colNombre;
    @FXML
    private TableColumn<Evento, String> colFecha;
    @FXML
    private TableColumn<Evento, String> colCiudad;
    @FXML
    private TableColumn<Evento, String> colCategoria;

    @FXML
    public void initialize() {
        // Inicializar combos
        cmbCategoria.setItems(FXCollections.observableArrayList(CategoriaEvento.values()));
        cmbCiudad.setItems(FXCollections.observableArrayList("Bogotá", "Medellín", "Cali"));

        // Columnas
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colFecha.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFechaHora().toString()));
        colCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        colCategoria.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCategoria().getDescripcion()));

        // Doble clic para detalle
        tvEventos.setRowFactory(tv -> {
            TableRow<Evento> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    verDetalleEvento(row.getItem());
                }
            });
            return row;
        });

        cargarEventos();
    }

    @FXML
    public void onFiltrar() {
        cargarEventos();
    }

    @FXML
    public void onLimpiar() {
        dpFecha.setValue(null);
        cmbCiudad.setValue(null);
        cmbCategoria.setValue(null);
        txtPrecioMax.clear();
        cargarEventos();
    }

    private void cargarEventos() {
        LocalDateTime fecha = dpFecha.getValue() != null ? dpFecha.getValue().atStartOfDay() : null;
        String ciudad = cmbCiudad.getValue();
        CategoriaEvento categoria = cmbCategoria.getValue();
        Double precioMax = null;
        try {
            if (!txtPrecioMax.getText().isEmpty())
                precioMax = Double.parseDouble(txtPrecioMax.getText());
        } catch (NumberFormatException ignored) {
        }

        List<Evento> eventos = Main.eventoService.buscarConFiltros(fecha, ciudad, categoria, precioMax);
        tvEventos.setItems(FXCollections.observableArrayList(eventos));
    }

    private void verDetalleEvento(Evento evento) {
        // Guardar el evento en una sesión temporal o pasarlo al controller
        // Aquí pasamos directamente configurando un controller estático por simplicidad
        // en FX
        DetalleEventoController.eventoActual = evento;
        NavigationManager.getInstance().navigateTo("/com/ticketflow/view/DetalleEventoView.fxml",
                "TicketFlow - Detalle Evento");
    }
}
