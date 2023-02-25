package ru.netology.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostDTO;
import ru.netology.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
  private final PostRepository repository;
  public PostService(PostRepository repository) {
    this.repository = repository;
  }


  public List<PostDTO> all() {
    return repository.all();
  }


  public PostDTO getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public PostDTO save(Post post) {
    return repository.save(post);
  }

  public List<PostDTO> removeById(long id) {
    return repository.removeById(id);
  }
}

