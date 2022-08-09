package mapper;

import domain.AggregateData;
import dto.DataDto;
import dto.SourceDataDto;
import dto.TokenDataDto;
import exception.WrongTypeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log4j2
@Component
public class AggregateDataMapper {

    public AggregateData toAggregateData(DataDto data, SourceDataDto sourceDataDto, TokenDataDto tokenDataDto) throws WrongTypeException{
        if(!Arrays.asList("LIVE", "ARCHIVE").contains(sourceDataDto.getUrlType())){
            log.error("Неверный тип url = {} для камеры id = {}", sourceDataDto.getUrlType(), data.getId());
            throw new WrongTypeException();
        }

        AggregateData aggregateData = new AggregateData();
        aggregateData.setId(data.getId());
        aggregateData.setUrlType(sourceDataDto.getUrlType());
        aggregateData.setVideoUrl(sourceDataDto.getVideoUrl());
        aggregateData.setValue(tokenDataDto.getValue());
        aggregateData.setTtl(tokenDataDto.getTtl());

        return aggregateData;
    }
}
