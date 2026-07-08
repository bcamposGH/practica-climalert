package ar.edu.utn.frba.dds.climalert.models.entities.classes;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RegistroClimatico {

  private final String ciudad;
  private final String pais;
  private final double temperatura;
  private final double humedad;
  private final LocalDateTime fechaHora;
  private boolean notificada;

  public RegistroClimatico(String ciudad, String pais, double temperatura, double humedad) {
    this.ciudad = ciudad;
    this.pais = pais;
    this.temperatura = temperatura;
    this.humedad = humedad;
    this.fechaHora = LocalDateTime.now();
    this.notificada = false;
  }

  public boolean esAlerta() {
    return this.temperatura > 35.0 && this.humedad > 60.0;
  }

  public void marcarComoNotificada(){
    this.notificada = true;
  }
}