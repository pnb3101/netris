package service;

import domain.AggregateData;
import dto.SourceDataDto;
import dto.TokenDataDto;
import exception.WrongTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mapper.AggregateDataMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@Service
@RequiredArgsConstructor
public class AggregateServiceImpl implements AggregateService {

    private final ExchangeService exchangeService;
    private final AggregateDataMapper mapper;

    @Override
    public Collection<AggregateData> aggregate() throws InterruptedException, ExecutionException {
        Collection<AggregateData> collection = Collections.synchronizedCollection(new HashSet<AggregateData>());
        Arrays.asList(exchangeService.getData()).parallelStream().forEach(dataDto -> {
            log.info("получение данных по камере id: ", dataDto.getId());
            CompletableFuture<SourceDataDto> sourceData = exchangeService.getSourceData(dataDto.getSourceDataUrl());
            CompletableFuture<TokenDataDto> tokenData = exchangeService.getTokenData(dataDto.getTokenDataUrl());
            CompletableFuture.allOf(sourceData, tokenData).join();
            try {
                collection.add(mapper.toAggregateData(dataDto, sourceData.get(), tokenData.get()));
            } catch (InterruptedException | ExecutionException | WrongTypeException e) {
                log.error("Ошибка при получении данных по камере id: ", dataDto.getId());
            }
        });

        return collection;
    }

}
