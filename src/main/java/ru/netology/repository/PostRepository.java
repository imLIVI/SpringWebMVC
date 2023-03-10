package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Stub
public class PostRepository {

    private final List<Post> postsList = Collections.synchronizedList(new ArrayList<>());
    private int counter = 1;

    public List<Post> all() {
        return postsList;
    }

    public Optional<Post> getById(long id) {
        Post post = findById(id);
        return (post == null) ? Optional.empty() : Optional.of(post);
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(counter);
            counter++;
            postsList.add(post);
            return post;
        } else {
            Post searchingPost = findById(post.getId());
            if (searchingPost != null) {
                postsList.set(postsList.indexOf(searchingPost), post);
            }
        }
        return post;
    }

    public void removeById(long id) {
        Post searchingPost = findById(id);
        if (searchingPost != null) {
            postsList.remove(searchingPost);
        }
    }

    public Post findById(long id) {
        return postsList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
