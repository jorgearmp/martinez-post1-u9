package com.clinica.recordatorios.service;

import com.clinica.recordatorios.domain.Cita;
import com.clinica.recordatorios.repository.CitaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ProcesadorRecordatoriosService {
    private final CitaRepository citaRepository;
    private final CanalNotificacion canalNotificacion;

    public ProcesadorRecordatoriosService(CitaRepository citaRepository, CanalNotificacion canalNotificacion) {
        this.citaRepository = citaRepository;
        this.canalNotificacion = canalNotificacion;
    }

    public int enviarRecordatoriosProximos(LocalDateTime ahora) {
        LocalDateTime ventanaInicio = ahora.plusHours(24);
        LocalDateTime ventanaFin = ahora.plusHours(26);

        List<Cita> citasProximas = citaRepository.findByFechaHoraCitaBetweenAndRecordatorioEnviadoFalse(ventanaInicio, ventanaFin);
        int enviados = 0;

        for (Cita cita : citasProximas) {
            String mensaje = String.format("Apreciado(a) %s, le recordamos su cita médica el %s.",
                    cita.getPacienteNombre(), cita.getFechaHoraCita());

            boolean exito = canalNotificacion.enviarMensaje(cita.getPacienteTelefono(), mensaje);
            if (exito) {
                cita.setRecordatorioEnviado(true);
                citaRepository.save(cita);
                enviados++;
            }
        }
        return enviados;
    }
}
