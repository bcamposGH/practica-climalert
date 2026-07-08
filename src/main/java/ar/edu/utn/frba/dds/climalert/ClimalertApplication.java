package ar.edu.utn.frba.dds.climalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class ClimalertApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClimalertApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

    factory.setConnectTimeout(2000);
    factory.setReadTimeout(2000);

    return new RestTemplate(factory);
  }
}