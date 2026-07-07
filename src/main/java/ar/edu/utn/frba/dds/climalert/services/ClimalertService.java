package ar.edu.utn.frba.dds.climalert.services;

import ar.edu.utn.frba.dds.climalert.models.entities.interfaces.NotificadorAlerta;
import ar.edu.utn.frba.dds.climalert.models.entities.interfaces.ProveedorClima;
import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;
import ar.edu.utn.frba.dds.climalert.repositories.RepositorioClimaMemo;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClimalertService {

  private final ProveedorClima proveedorClima;
  private final NotificadorAlerta notificador;
  private final RepositorioClimaMemo repositorioClimaMemo;

  public ClimalertService(ProveedorClima proveedorClima,
                          NotificadorAlerta notificador,
                          RepositorioClimaMemo repositorioClimaMemo) {
    this.proveedorClima = proveedorClima;
    this.notificador = notificador;
    this.repositorioClimaMemo = repositorioClimaMemo;
  }

  public void RegistrarClima() {
    RegistroClimatico registro = this.proveedorClima.obtenerClimaActual();
    this.repositorioClimaMemo.guardar(registro);
  }

  public void procesarAlerta() {
    Optional<RegistroClimatico> ultimoRegistro = this.repositorioClimaMemo.obtenerUltimo();

    ultimoRegistro.ifPresent(registro -> {
      if (registro.esAlerta()) {
        this.notificador.enviarAlerta(registro);
      }
    });
  }
}