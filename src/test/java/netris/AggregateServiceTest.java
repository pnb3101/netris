package netris;

import domain.AggregateData;
import dto.DataDto;
import dto.SourceDataDto;
import dto.TokenDataDto;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import service.AggregateService;
import service.ExchangeService;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class AggregateServiceTest {

    @Autowired
    private AggregateService aggregateService;

    @MockBean
    private ExchangeService exchangeService;

    @Test
    public void testAggregate() throws InterruptedException, ExecutionException {
        final DataDto dataDto = new DataDto();
        dataDto.setId(2L);
        dataDto.setSourceDataUrl("http://testUrl.com");
        dataDto.setTokenDataUrl("hhtp://testUrl.com");

        DataDto[] dataArray = new DataDto[1];
        dataArray[0] = dataDto;

        final SourceDataDto sourceDataDto = new SourceDataDto();
        sourceDataDto.setUrlType("LIVE");
        sourceDataDto.setVideoUrl("rtsp://127.0.0.1/28");

        final TokenDataDto tokenDataDto = new TokenDataDto();
        tokenDataDto.setValue("fa4b5f64-249b-11e9-ab14-d663bd873d9");
        tokenDataDto.setTtl(120);

        when(exchangeService.getData()).thenReturn(dataArray);
        when(exchangeService.getSourceData(dataArray[0].getSourceDataUrl())).thenReturn(CompletableFuture.completedFuture(sourceDataDto));
        when(exchangeService.getTokenData(dataArray[0].getTokenDataUrl())).thenReturn(CompletableFuture.completedFuture(tokenDataDto));

        Collection<AggregateData> result = aggregateService.aggregate();

        assertThat(result, not(IsEmptyCollection.empty()));
        assertThat(result, hasSize(1));
        assertThat(result, contains(hasProperty("id", is(dataDto.getId()))));
        assertThat(result, contains(hasProperty("urlType", is(sourceDataDto.getUrlType()))));
        assertThat(result, contains(hasProperty("value", is(tokenDataDto.getValue()))));
        assertThat(result, contains(hasProperty("ttl", is(tokenDataDto.getTtl()))));
        assertThat(result, contains(hasProperty("videoUrl", is(sourceDataDto.getVideoUrl()))));
    }
}
