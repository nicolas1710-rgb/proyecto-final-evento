package com.ticketflow.controller;

import com.ticketflow.app.Main;
import com.ticketflow.model.Evento;
import com.ticketflow.report.ReporteCSV;
import com.ticketflow.report.ReportePDF;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ReportesController {
    @FXML
    private ComboBox<String> cmbTipoReporte;
    @FXML
    private ComboBox<String> cmbFormato;
    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        cmbTipoReporte.getItems().addAll(
                "Ventas por Período (Últimos 30 días)",
                "Top Eventos");
        cmbFormato.getItems().addAll("PDF", "CSV");
    }

    @FXML
    public void onGenerarReporte() {
        String tipo = cmbTipoReporte.getValue();
        String formato = cmbFormato.getValue();

        if (tipo == null || formato == null) {
            lblMensaje.setText("Seleccione tipo de reporte y formato.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter(formato + " Files", "*." + formato.toLowerCase()));

        Window window = cmbTipoReporte.getScene().getWindow();
        File file = fileChooser.showSaveDialog(window);

        if (file != null) {
            try {
                if (tipo.contains("Ventas por Período")) {
                    LocalDate hoy = LocalDate.now();
                    Map<LocalDate, Double> datos = Main.metricasService.ventasPorPeriodo(hoy.minusDays(30), hoy);

                    if (formato.equals("PDF")) {
                        ReportePDF.generarVentasPorPeriodo(datos, file.getAbsolutePath());
                    } else {
                        ReporteCSV.ventasPorPeriodo(datos, file.getAbsolutePath());
                    }
                } else if (tipo.contains("Top Eventos")) {
                    List<Evento> datos = Main.metricasService.topEventos(10);

                    if (formato.equals("PDF")) {
                        ReportePDF.generarTopEventos(datos, file.getAbsolutePath());
                    } else {
                        ReporteCSV.topEventos(datos, file.getAbsolutePath());
                    }
                }
                lblMensaje.setStyle("-fx-text-fill: #4CAF50;");
                lblMensaje.setText("Reporte generado exitosamente en:\n" + file.getAbsolutePath());
            } catch (Exception e) {
                lblMensaje.setStyle("-fx-text-fill: #ff5555;");
                lblMensaje.setText("Error al generar reporte: " + e.getMessage());
            }
        }
    }
}
