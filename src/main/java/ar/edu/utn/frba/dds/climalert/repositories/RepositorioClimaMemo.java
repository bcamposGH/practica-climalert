package ar.edu.utn.frba.dds.climalert.repositories;

import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;
import ar.edu.utn.frba.dds.climalert.repositories.interfaces.IRepositorioClima;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioClimaMemo implements IRepositorioClima {

  private final List<RegistroClimatico> historial = new ArrayList<>();

  @Override
  public void guardar(RegistroClimatico registro) {
    this.historial.add(registro);
  }

  @Override
  public Optional<RegistroClimatico> obtenerUltimo() {
    if (this.historial.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(this.historial.getLast());
  }
}