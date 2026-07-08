package ar.edu.utn.frba.dds.climalert.repositories.interfaces;

import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;
import java.util.Optional;

public interface IRepositorioClima {

  void guardar(RegistroClimatico registro);

  Optional<RegistroClimatico> obtenerUltimo();
}
