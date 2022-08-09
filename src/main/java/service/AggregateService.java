package service;

import domain.AggregateData;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface AggregateService {

    Collection<AggregateData> aggregate() throws InterruptedException, ExecutionException;
}
