package ar.edu.utn.frba.dds.climalert.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiResponse(Location location, Current current) {

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Location(
      String name,
      String country
  ) {}

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Current(
      @JsonProperty("temp_c") double tempC,
      double humidity
  ) {}
}