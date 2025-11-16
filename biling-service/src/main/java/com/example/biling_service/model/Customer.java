package com.example.biling_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // ignore tout ce que tu ne définis pas
public class Customer {
    private Long id;
    private String name;
    private String email;
    private Links _links;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {
        private Link self;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Link {
            private String href;
        }
    }
}
