package tech.astrocoder.neows_client.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.astrocoder.neows_client.domain.Asteroid;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public record NeoFeedResponse(
        @JsonProperty("near_earth_objects")Map<LocalDate, List<Asteroid>> nearEarthObjects,
        @JsonProperty("links") Map<String, String> links,
        @JsonProperty("element_count") int elementCount) {
}
