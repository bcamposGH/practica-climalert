package ar.edu.utn.frba.dds.climalert.models.entities.interfaces;

import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;

public interface ProveedorClima {
  RegistroClimatico obtenerClimaActual();
}