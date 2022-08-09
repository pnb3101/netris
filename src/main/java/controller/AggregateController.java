package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AggregateService;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@RestController
public class AggregateController {

    private AggregateService aggregateService;

    @GetMapping(path = "/aggregate")
    public ResponseEntity exchange(){
        try{
            return ResponseEntity.ok(aggregateService.aggregate());
        } catch (InterruptedException| ExecutionException e){
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
