package com.ticketflow.service;

import com.ticketflow.model.*;
import com.ticketflow.enums.*;
import com.ticketflow.repository.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MetricasServiceImpl implements IMetricasService {
    private final CompraRepository compraRepository;
    private final EntradaRepository entradaRepository;
    private final EventoRepository eventoRepository;

    public MetricasServiceImpl(CompraRepository compraRepository,
            EntradaRepository entradaRepository,
            EventoRepository eventoRepository) {
        this.compraRepository = compraRepository;
        this.entradaRepository = entradaRepository;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Map<LocalDate, Double> ventasPorPeriodo(LocalDate desde, LocalDate hasta) {
        Map<LocalDate, Double> resultado = new TreeMap<>();
        compraRepository.findAll().stream()
                .filter(c -> c.getEstadoCompra() == EstadoCompra.PAGADA
                        || c.getEstadoCompra() == EstadoCompra.CONFIRMADA)
                .filter(c -> {
                    LocalDate fecha = c.getFechaCreacion().toLocalDate();
                    return (desde == null || !fecha.isBefore(desde)) && (hasta == null || !fecha.isAfter(hasta));
                })
                .forEach(c -> resultado.merge(c.getFechaCreacion().toLocalDate(), c.getTotal(), Double::sum));
        return resultado;
    }

    @Override
    public Map<Zona, Double> ocupacionPorZona(UUID idEvento) {
        Map<Zona, Double> resultado = new LinkedHashMap<>();
        Evento evento = eventoRepository.findById(idEvento).orElse(null);
        if (evento == null || evento.getRecinto() == null)
            return resultado;

        List<Entrada> entradas = entradaRepository.findByEvento(idEvento);
        for (Zona zona : evento.getRecinto().getZonas()) {
            long vendidos = entradas.stream()
                    .filter(e -> e.getZona() != null && e.getZona().getIdZona().equals(zona.getIdZona()))
                    .filter(e -> e.getEstadoEntrada() == EstadoEntrada.ACTIVA
                            || e.getEstadoEntrada() == EstadoEntrada.USADA)
                    .count();
            double porcentaje = zona.getCapacidad() > 0 ? (vendidos * 100.0 / zona.getCapacidad()) : 0;
            resultado.put(zona, porcentaje);
        }
        return resultado;
    }

    @Override
    public Map<TipoServicio, Double> ingresosPorServiciosAdicionales() {
        Map<TipoServicio, Double> resultado = new EnumMap<>(TipoServicio.class);
        compraRepository.findAll().stream()
                .filter(c -> c.getEstadoCompra() == EstadoCompra.PAGADA
                        || c.getEstadoCompra() == EstadoCompra.CONFIRMADA)
                .flatMap(c -> c.getServiciosAdicionales().stream())
                .forEach(s -> resultado.merge(s.getTipo(), s.getPrecio(), Double::sum));
        return resultado;
    }

    @Override
    public double tasaCancelacion() {
        List<Compra> todas = compraRepository.findAll();
        if (todas.isEmpty())
            return 0.0;
        long canceladas = todas.stream()
                .filter(c -> c.getEstadoCompra() == EstadoCompra.CANCELADA
                        || c.getEstadoCompra() == EstadoCompra.REEMBOLSADA)
                .count();
        return (canceladas * 100.0) / todas.size();
    }

    @Override
    public List<Evento> topEventos(int n) {
        Map<UUID, Double> ingresosPorEvento = new HashMap<>();
        compraRepository.findAll().stream()
                .filter(c -> c.getEstadoCompra() == EstadoCompra.PAGADA
                        || c.getEstadoCompra() == EstadoCompra.CONFIRMADA)
                .filter(c -> c.getEvento() != null)
                .forEach(c -> ingresosPorEvento.merge(c.getEvento().getIdEvento(), c.getTotal(), Double::sum));

        return ingresosPorEvento.entrySet().stream()
                .sorted(Map.Entry.<UUID, Double>comparingByValue().reversed())
                .limit(n)
                .map(e -> eventoRepository.findById(e.getKey()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
