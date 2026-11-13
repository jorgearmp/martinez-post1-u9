# ACTIVIDAD POST-CONTENIDO — UNIDAD 9
## PROBAR EL DISEÑO: JUNIT 5, MOCKITO, TEST DOUBLES Y PIPELINE CI/CD
**Estudiante:** Jorge Armando Martínez Peinado (Cód. 1152162)  
**Curso:** Patrones de Diseño de Software (1156701-A)  
**Institución:** Universidad Francisco de Paula Santander (UFPS)  

---

## 1. SUITE DE PRUEBAS AUTOMATIZADAS
- **Frameworks:** JUnit 5 (Jupiter) y Mockito 5.
- **Caso Probado:** `ProcesadorRecordatoriosService` con aislamiento total de la infraestructura.
- **Doble de Prueba Aplicado:**
  - `CitaRepository`: Mockeado con `when(...).thenReturn(...)` para simular la consulta sin base de datos.
  - `CanalNotificacion`: Mockeado y verificado mediante `verify(..., times(1))` para certificar la no duplicidad de despachos de mensajes.

---

## 2. INTEGRACIÓN CONTINUA (CI/CD)
El proyecto incluye el archivo `.github/workflows/ci.yml` configurado con GitHub Actions para compilar el proyecto en JDK 17 y correr la suite de pruebas automáticamente en cada push.

---

## 3. INSTRUCCIONES DE EJECUCIÓN
```bash
mvn clean test
mvn exec:java
```
