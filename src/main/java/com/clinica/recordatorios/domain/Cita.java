package com.clinica.recordatorios.domain;

import java.time.LocalDateTime;

public class Cita {
    private final Long id;
    private final String pacienteNombre;
    private final String pacienteTelefono;
    private final LocalDateTime fechaHoraCita;
    private boolean recordatorioEnviado;

    public Cita(Long id, String pacienteNombre, String pacienteTelefono, LocalDateTime fechaHoraCita) {
        this.id = id;
        this.pacienteNombre = pacienteNombre;
        this.pacienteTelefono = pacienteTelefono;
        this.fechaHoraCita = fechaHoraCita;
        this.recordatorioEnviado = false;
    }

    public Long getId() { return id; }
    public String getPacienteNombre() { return pacienteNombre; }
    public String getPacienteTelefono() { return pacienteTelefono; }
    public LocalDateTime getFechaHoraCita() { return fechaHoraCita; }
    public boolean isRecordatorioEnviado() { return recordatorioEnviado; }
    public void setRecordatorioEnviado(boolean recordatorioEnviado) { this.recordatorioEnviado = recordatorioEnviado; }
}
