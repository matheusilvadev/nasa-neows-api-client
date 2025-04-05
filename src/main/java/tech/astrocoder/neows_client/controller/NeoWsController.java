package tech.astrocoder.neows_client.controller;


import io.netty.handler.codec.socks.SocksRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tech.astrocoder.neows_client.domain.Asteroid;
import tech.astrocoder.neows_client.service.NeoWsService;
import tech.astrocoder.neows_client.service.dtos.NeoBrowseResponse;
import tech.astrocoder.neows_client.service.dtos.NeoFeedResponse;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/neo")
public class NeoWsController {

    private final NeoWsService neoWsService;

    @Autowired
    public NeoWsController(NeoWsService neoWsService) {
        this.neoWsService = neoWsService;
    }

    @GetMapping("/feed")
    public Mono<NeoFeedResponse> getNeoFeed(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){

        return neoWsService.getNeoFeed(startDate, endDate);
    }

    @GetMapping("/lookup/{asteroidId}")
    public Mono<Asteroid> getNeoLookup(@PathVariable String asteroidId){
        return neoWsService.getNeoLookup(asteroidId);
    }

    @GetMapping("/browse")
    public Mono<NeoBrowseResponse> browseNeo(){
        return neoWsService.browseNeo();
    }

}
