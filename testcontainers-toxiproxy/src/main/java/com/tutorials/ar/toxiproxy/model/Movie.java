package com.tutorials.ar.toxiproxy.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "movie-index")
@Data
public class Movie {

    @Id
    private String id;

    private String title;

    private String director;

    private BigDecimal rating;
}