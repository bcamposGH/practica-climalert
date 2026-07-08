package ar.edu.utn.frba.dds.climalert.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;
import ar.edu.utn.frba.dds.climalert.helpers.NotificadorAlerta;
import ar.edu.utn.frba.dds.climalert.helpers.ProveedorClima;
import ar.edu.utn.frba.dds.climalert.repositories.RepositorioClimaMemo;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClimalertServiceTest {

  private NotificadorAlerta notificadorMock;
  private RepositorioClimaMemo repositorioMock;
  private ClimalertService climalertService;

  @BeforeEach
  public void setUp() {
    ProveedorClima proveedorMock = mock(ProveedorClima.class);
    notificadorMock = mock(NotificadorAlerta.class);
    repositorioMock = mock(RepositorioClimaMemo.class);

    climalertService = new ClimalertService(proveedorMock, notificadorMock, repositorioMock);
  }

  // TESTS DEL DOMINIO (RegistroClimatico)

  @Test
  public void testNoEsAlertaCuandoCondicionesSonNormales() {
    RegistroClimatico registro = new RegistroClimatico("London", "United Kingdom",34.0, 50.0);
    assertFalse(registro.esAlerta(), "No debería ser alerta con 34 grados y 50% de humedad");
  }

  @Test
  public void testEsAlertaCuandoSuperaUmbrales() {
    RegistroClimatico registro = new RegistroClimatico("London", "United Kingdom",36.0, 65.0);
    assertTrue(registro.esAlerta(), "Debería ser alerta con 36 grados y 65% de humedad");
  }

  // ==========================================
  // TESTS DEL SERVICIO (ClimalertService)
  // ==========================================

  @Test
  public void testVerificarYProcesarAlertasEnviaCorreoSiEsAlerta() {
    RegistroClimatico registroAlerta = new RegistroClimatico("London", "United Kingdom",36.0, 65.0);
    when(repositorioMock.obtenerUltimo()).thenReturn(Optional.of(registroAlerta));

    climalertService.procesarAlerta();

    verify(notificadorMock).enviarAlerta(registroAlerta);
  }

  @Test
  public void testVerificarYProcesarAlertasNoEnviaCorreoSiNoHayRegistros() {
    when(repositorioMock.obtenerUltimo()).thenReturn(Optional.empty());

    climalertService.procesarAlerta();

    verify(notificadorMock, never()).enviarAlerta(null);
  }
}