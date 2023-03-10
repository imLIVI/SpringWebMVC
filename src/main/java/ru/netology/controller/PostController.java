package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    private final Gson gson = new Gson();

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) {
        deserializeSerialize(response, service.all());
    }

    public void getById(long id, HttpServletResponse response) {
        // TODO: deserialize request & serialize response
        deserializeSerialize(response, service.getById(id));
    }

    public void save(Reader body, HttpServletResponse response) {
        final var post = gson.fromJson(body, Post.class);
        deserializeSerialize(response, service.save(post));
    }

    public void removeById(long id, HttpServletResponse response) {
        // TODO: deserialize request & serialize response
        service.removeById(id);
        deserializeSerialize(response, "Post " + id + " has been deleted");
    }

    public <T> void deserializeSerialize(HttpServletResponse response, T data) {
        response.setContentType(APPLICATION_JSON);
        try {
            response.getWriter().print(gson.toJson(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
