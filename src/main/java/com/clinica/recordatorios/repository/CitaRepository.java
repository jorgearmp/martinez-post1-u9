package com.clinica.recordatorios.repository;

import com.clinica.recordatorios.domain.Cita;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository {
    List<Cita> findByFechaHoraCitaBetweenAndRecordatorioEnviadoFalse(LocalDateTime desde, LocalDateTime hasta);
    void save(Cita cita);
}
