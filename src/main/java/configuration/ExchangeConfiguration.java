package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

@Configuration
public class ExchangeConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean(name = "headers")
    HttpHeaders httpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "\"Mozilla/5.0\"");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
