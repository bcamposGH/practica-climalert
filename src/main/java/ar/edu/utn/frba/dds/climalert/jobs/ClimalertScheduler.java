package ar.edu.utn.frba.dds.climalert.jobs;

import ar.edu.utn.frba.dds.climalert.services.ClimalertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimalertScheduler {

  private final ClimalertService climalertService;

  public ClimalertScheduler(ClimalertService climalertService) {
    this.climalertService = climalertService;
  }

  // Se ejecuta cada 5 minutos (300.000 milisegundos)
  @Scheduled(fixedRate = 300000)
  public void registrarClimaTask() {
    this.climalertService.registrarClima();
    System.out.println("Clima consultado y guardado en memoria.");
  }

  // Se ejecuta cada 1 minuto (60.000 milisegundos)
  @Scheduled(fixedRate = 60000)
  public void procesarAlertasTask() {
    this.climalertService.procesarAlerta();
    System.out.println("Verificación de alertas completada.");
  }
}