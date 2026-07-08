package ar.edu.utn.frba.dds.climalert.helpers.impl;

import ar.edu.utn.frba.dds.climalert.helpers.NotificadorAlerta;
import ar.edu.utn.frba.dds.climalert.models.entities.classes.RegistroClimatico;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class NotificadorCorreo implements NotificadorAlerta {

  private final JavaMailSender mailSender;

  private final List<String> destinatarios = List.of(
      "admin@clima.com",
      "emergencias@clima.com",
      "meteorologia@clima.com",
      "brandon0campos24@gmail.com"
  );

  public NotificadorCorreo(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public void enviarAlerta(RegistroClimatico registro) {
    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(destinatarios.toArray(new String[0]));
    mensaje.setSubject("¡ALERTA CLIMÁTICA DETECTADA!");

    String texto = String.format(
        """
            Se han detectado condiciones climáticas críticas en %s, %s:
            - Temperatura: %.1f °C
            - Humedad: %.1f %%
            - Fecha y Hora: %s
            
            Por favor, tomar las medidas correspondientes.""",
        registro.getCiudad(),
        registro.getPais(),
        registro.getTemperatura(),
        registro.getHumedad(),
        registro.getFechaHora().toString()
    );

    mensaje.setText(texto);
    this.mailSender.send(mensaje);
  }
}