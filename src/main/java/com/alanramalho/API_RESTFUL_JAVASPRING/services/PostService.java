package com.alanramalho.API_RESTFUL_JAVASPRING.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanramalho.API_RESTFUL_JAVASPRING.domain.Post;
import com.alanramalho.API_RESTFUL_JAVASPRING.repository.PostRepository;
import com.alanramalho.API_RESTFUL_JAVASPRING.services.ServicesExceptions.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRep;

    public Post findById(String id) {
        Optional<Post> obj = postRep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return postRep.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRep.fullSearch(text, minDate, maxDate);
    }
}
