package tech.astrocoder.neows_client.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tech.astrocoder.neows_client.domain.Asteroid;
import tech.astrocoder.neows_client.service.dtos.NeoBrowseResponse;
import tech.astrocoder.neows_client.service.dtos.NeoFeedResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class NeoWsService {

    private final WebClient webClient;
    private static  final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    public NeoWsService(WebClient webClient){
        this.webClient = webClient;
    }

    //Método para NEO - FEED
    public Mono<NeoFeedResponse> getNeoFeed(LocalDate startDate, LocalDate endDate){

        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/feed")
                        .queryParam("start_date", formattedStartDate)
                        .queryParam("end_date", formattedEndDate)
                        .build())
                .retrieve()
                .bodyToMono(NeoFeedResponse.class);

    }

    //Método para NEO - LOOKUP
    public Mono<Asteroid> getNeoLookup(String asteroidId){
        return webClient.get()
                .uri("/neo/" + asteroidId)
                .retrieve()
                .bodyToMono(Asteroid.class);
    }

    //Método para NEO - BROWSE
    public Mono<NeoBrowseResponse> browseNeo(){
        return webClient.get()
                .uri("/neo/browse")
                .retrieve()
                .bodyToMono(NeoBrowseResponse.class);
    }

}
