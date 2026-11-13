package com.clinica.recordatorios;

import com.clinica.recordatorios.domain.Cita;
import com.clinica.recordatorios.repository.CitaRepository;
import com.clinica.recordatorios.service.CanalNotificacion;
import com.clinica.recordatorios.service.ProcesadorRecordatoriosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcesadorRecordatoriosServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CanalNotificacion canalNotificacion;

    private ProcesadorRecordatoriosService service;

    @BeforeEach
    void setUp() {
        service = new ProcesadorRecordatoriosService(citaRepository, canalNotificacion);
    }

    @Test
    @DisplayName("Debe despachar recordatorios a citas próximas y persistir el estado enviado")
    void debeEnviarRecordatoriosYActualizarEstado() {
        // Arrange
        LocalDateTime ahora = LocalDateTime.of(2026, 11, 20, 10, 0);
        Cita cita1 = new Cita(1L, "Carlos Gómez", "+573105559988", ahora.plusHours(25));
        
        when(citaRepository.findByFechaHoraCitaBetweenAndRecordatorioEnviadoFalse(any(), any()))
                .thenReturn(List.of(cita1));
        when(canalNotificacion.enviarMensaje(eq("+573105559988"), anyString()))
                .thenReturn(true);

        // Act
        int enviados = service.enviarRecordatoriosProximos(ahora);

        // Assert
        assertEquals(1, enviados);
        assertTrue(cita1.isRecordatorioEnviado());
        verify(canalNotificacion, times(1)).enviarMensaje(eq("+573105559988"), anyString());
        verify(citaRepository, times(1)).save(cita1);
    }
}
