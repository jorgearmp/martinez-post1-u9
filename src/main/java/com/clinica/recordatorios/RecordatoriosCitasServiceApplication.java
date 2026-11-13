package com.clinica.recordatorios;

import com.clinica.recordatorios.domain.Cita;
import com.clinica.recordatorios.repository.CitaRepository;
import com.clinica.recordatorios.service.CanalNotificacion;
import com.clinica.recordatorios.service.ProcesadorRecordatoriosService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecordatoriosCitasServiceApplication {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println(" SISTEMA DE RECORDATORIOS DE CITAS MÉDICAS - UNIDAD 9 (UFPS)");
        System.out.println(" Estudiante: Jorge Martínez");
        System.out.println("==================================================================");

        var citasEnMemoria = new ArrayList<Cita>();
        LocalDateTime ahora = LocalDateTime.of(2026, 10, 1, 8, 0);

        // Agregando cita dentro de 24 horas
        citasEnMemoria.add(new Cita(1L, "Jorge Martínez", "+573001234567", ahora.plusHours(25)));

        CitaRepository fakeRepo = new CitaRepository() {
            @Override
            public List<Cita> findByFechaHoraCitaBetweenAndRecordatorioEnviadoFalse(LocalDateTime desde, LocalDateTime hasta) {
                return citasEnMemoria;
            }

            @Override
            public void save(Cita cita) {
                System.out.println("  [DB Fake] Estado de cita actualizado: Recordatorio enviado = " + cita.isRecordatorioEnviado());
            }
        };

        CanalNotificacion dummyCanal = (dest, msg) -> {
            System.out.printf("  [SMS Gateway] Despachando a %s: %s%n", dest, msg);
            return true;
        };

        var service = new ProcesadorRecordatoriosService(fakeRepo, dummyCanal);
        int procesadas = service.enviarRecordatoriosProximos(ahora);

        System.out.printf("Proceso finalizado. Total recordatorios emitidos: %d%n", procesadas);
    }
}
