package com.tutorials.ar.toxiproxy.repository;

import com.tutorials.ar.toxiproxy.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {

    List<Movie> findAll();
}
