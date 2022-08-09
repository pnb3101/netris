package service;

import configuration.properites.ExchangeProperties;
import dto.DataDto;
import dto.SourceDataDto;
import dto.TokenDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService{

    private final ExchangeProperties properties;
    private final RestTemplate restClient;
    private final HttpHeaders headers;

    @Override
    public DataDto[] getData() {
        log.info("Получение данные");
        ResponseEntity<DataDto[]> response = restClient.exchange(properties.getSource(), HttpMethod.GET,
                new HttpEntity<>(headers), DataDto[].class);

        return new DataDto[0];
    }

    @Override
    public CompletableFuture<SourceDataDto> getSourceData(String sourceUrl) {
        log.info("Получение данных по URL: ", sourceUrl);
        ResponseEntity<SourceDataDto> response = restClient.exchange(sourceUrl, HttpMethod.GET,
                new HttpEntity<>(headers), SourceDataDto.class);

        return CompletableFuture.completedFuture(response.getBody());
    }

    @Override
    public CompletableFuture<TokenDataDto> getTokenData(String tokenUrl) {
        log.info("Получение данных по токену: ", tokenUrl);
        ResponseEntity<TokenDataDto> response = restClient.exchange(tokenUrl, HttpMethod.GET,
                new HttpEntity<>(headers), TokenDataDto.class);

        return CompletableFuture.completedFuture(response.getBody());
    }
}
