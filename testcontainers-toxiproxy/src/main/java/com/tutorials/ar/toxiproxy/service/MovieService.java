package com.tutorials.ar.toxiproxy.service;

import com.tutorials.ar.toxiproxy.model.Movie;
import com.tutorials.ar.toxiproxy.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService{

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}