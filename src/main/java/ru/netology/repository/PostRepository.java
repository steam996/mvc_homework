package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostDTO;
import ru.netology.service.PostDTOService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepository {
  private ConcurrentHashMap <Long, Post> postMap = new ConcurrentHashMap();
  private AtomicLong id = new AtomicLong();
  public List<PostDTO> all() {
    if (postMap.isEmpty()){
      return Collections.emptyList();
    }
    List <PostDTO> postList = postMap.values().stream()
            .filter(x -> !x.isRemoved())
            .map(PostDTOService::postToPostDTO)
            .collect(Collectors.toList());
    return postList;
  }

  public Optional<PostDTO> getById(long id) {
    return Optional.ofNullable(PostDTOService.postToPostDTO(postMap.get(id)));
  }

  public PostDTO save(Post post) {
    if (!postMap.containsKey(post.getId()) && post.getId() != 0){
      throw new NotFoundException();
    }
    if (post.getId() == 0){
      post.setId(id.incrementAndGet());
    }
    postMap.put(post.getId(), post);
    return PostDTOService.postToPostDTO(post);
  }

  public List<PostDTO> removeById(long id) {
    if (!postMap.containsKey(id) || postMap.get(id).isRemoved())
      throw new NotFoundException();
    postMap.get(id).setRemoved();
    return all();
  }
}
