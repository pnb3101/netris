package service;

import dto.DataDto;
import dto.SourceDataDto;
import dto.TokenDataDto;

import java.util.concurrent.CompletableFuture;

public interface ExchangeService {

    DataDto[] getData();
    CompletableFuture<SourceDataDto> getSourceData(String sourceUrl);
    CompletableFuture<TokenDataDto> getTokenData(String tokenUrl);
}
