package tech.astrocoder.neows_client.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.astrocoder.neows_client.domain.Asteroid;

import java.util.List;

public record NeoBrowseResponse(
        Links links,
        @JsonProperty("near_earth_objects")List<Asteroid> nearEarthObjects,
        Page page) {

    public record Links(String next,
                        String prev,
                        String self){}

    public record Page(int size,
                       @JsonProperty("total_elements") long totalElements,
                       @JsonProperty("total_pages") int totalPages,
                       int number){}

}
