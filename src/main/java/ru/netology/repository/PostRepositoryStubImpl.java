package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
    private final List<Post> postsList = Collections.synchronizedList(new ArrayList<>());
    private int counter = 1;

    public List<Post> all() {
        return postsList.stream()
                .filter(post -> !post.getRemovedFlag())
                .collect(Collectors.toList());
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

    public boolean removeById(long id) {
        Post searchingPost = findById(id);
        if (searchingPost != null) {
            searchingPost.setRemovedFlag(true);
            postsList.set(postsList.indexOf(searchingPost), searchingPost);
            return true;
        }
        return false;
    }

    public Post findById(long id) {
        return postsList.stream()
                .filter(p -> p.getId() == id)
                .filter(p -> !p.getRemovedFlag())
                .findFirst()
                .orElse(null);
    }
}