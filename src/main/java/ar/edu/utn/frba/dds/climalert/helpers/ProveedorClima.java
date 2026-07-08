package ar.edu.utn.frba.dds.climalert.helpers;

import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;

public interface ProveedorClima {
  RegistroClimatico obtenerClimaActual();
}