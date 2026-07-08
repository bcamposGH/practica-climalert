package ar.edu.utn.frba.dds.climalert.services;

import ar.edu.utn.frba.dds.climalert.helpers.NotificadorAlerta;
import ar.edu.utn.frba.dds.climalert.helpers.ProveedorClima;
import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;
import ar.edu.utn.frba.dds.climalert.repositories.interfaces.IRepositorioClima; // <-- Importar la interfaz
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ClimalertService {

  private final ProveedorClima proveedorClima;
  private final NotificadorAlerta notificador;
  private final IRepositorioClima repositorioClima; // <-- Usar la interfaz

  public ClimalertService(ProveedorClima proveedorClima,
                          NotificadorAlerta notificador,
                          IRepositorioClima repositorioClima) { // <-- Usar la interfaz
    this.proveedorClima = proveedorClima;
    this.notificador = notificador;
    this.repositorioClima = repositorioClima;
  }

  public void registrarClima() {
    RegistroClimatico registro = this.proveedorClima.obtenerClimaActual();
    this.repositorioClima.guardar(registro);
  }

  public void procesarAlerta() {
    Optional<RegistroClimatico> ultimoRegistro = this.repositorioClima.obtenerUltimo();

    ultimoRegistro.ifPresent(registro -> {
      if (registro.esAlerta() && !registro.isNotificada()) {
        this.notificador.enviarAlerta(registro);
        registro.marcarComoNotificada();
        System.out.println("Alerta enviada y registro marcado como notificado.");
      }
    });
  }
}