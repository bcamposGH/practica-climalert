package ar.edu.utn.frba.dds.climalert.models.entities.classes;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RegistroClimatico {

  private final double temperatura;
  private final double humedad;
  private final LocalDateTime fechaHora;

  public RegistroClimatico(double temperatura, double humedad) {
    this.temperatura = temperatura;
    this.humedad = humedad;
    this.fechaHora = LocalDateTime.now();
  }

  public boolean esAlerta() {
    return this.temperatura > 35.0 && this.humedad > 60.0;
  }
}