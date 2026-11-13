package com.clinica.recordatorios.service;

public interface CanalNotificacion {
    boolean enviarMensaje(String destinatario, String mensaje);
}
