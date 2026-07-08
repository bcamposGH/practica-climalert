package ar.edu.utn.frba.dds.climalert.jobs;

import ar.edu.utn.frba.dds.climalert.services.ClimalertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimalertScheduler {

  private static final Logger log = LoggerFactory.getLogger(ClimalertScheduler.class);

  private final ClimalertService climalertService;

  public ClimalertScheduler(ClimalertService climalertService) {
    this.climalertService = climalertService;
  }

  @Scheduled(initialDelay = 0, fixedRate = 300000)
  public void registrarClimaTask() {
    try {
      this.climalertService.registrarClima();
      log.info("Clima consultado y guardado en memoria.");
    } catch (Exception e) {
      log.error("Error al consultar la API de clima. Motivo: {}", e.getMessage());
    }
  }

  @Scheduled(initialDelay = 0, fixedRate = 60000)
  public void procesarAlertasTask() {
    try {
      this.climalertService.procesarAlerta();
      log.info("Verificación de alertas completada.");
    } catch (Exception e) {
      log.error("Error al procesar las alertas. Motivo: {}", e.getMessage());
    }
  }
}