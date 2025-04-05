package tech.astrocoder.neows_client.configuration;


import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Value("${api.key}")
    private String apikey;

    @Value("${nasa.api.base-url}")
    private String baseUrl;

    @Bean
    public WebClient webClient(){

        //Configurando timeouts
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofSeconds(5))
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast( new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));


        //Filtro para adicionar a api_key como query param automaticamente
        ExchangeFilterFunction apiKeyFilter = ExchangeFilterFunction.ofRequestProcessor(request -> {
            URI updatedUri = UriComponentsBuilder.fromUri(request.url())
                    .queryParam("api_key", apikey)
                    .build(true)
                    .toUri();

            ClientRequest updatedRequest = ClientRequest.from(request)
                    .url(updatedUri)
                    .build();

            return reactor.core.publisher.Mono.just(updatedRequest);
        });

        //Construindo o WebClient
        return WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader("Content-Type","application/json")
                .defaultHeader("Accept", "application/json")
                .filter(apiKeyFilter)
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(512 * 1024))
                .build();

    }

}
