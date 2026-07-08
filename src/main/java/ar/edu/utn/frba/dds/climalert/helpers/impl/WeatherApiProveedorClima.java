package ar.edu.utn.frba.dds.climalert.helpers.impl;

import ar.edu.utn.frba.dds.climalert.dto.WeatherApiResponse;
import ar.edu.utn.frba.dds.climalert.helpers.ProveedorClima;
import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiProveedorClima implements ProveedorClima {

  private final RestTemplate restTemplate;

  @Value("${weatherapi.url}")
  private String apiUrl;

  public WeatherApiProveedorClima(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public RegistroClimatico obtenerClimaActual() {
    WeatherApiResponse response = restTemplate.getForObject(apiUrl, WeatherApiResponse.class);

    if (response != null && response.current() != null) {
      return new RegistroClimatico(
          response.location().name(),
          response.location().country(),
          response.current().tempC(),
          response.current().humidity()
      );
    }
    throw new RuntimeException("No se pudo obtener el clima de WeatherAPI");
  }
}