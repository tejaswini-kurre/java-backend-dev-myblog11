package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToPost(postDto);
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);

        return dto;
    }

    @Override
    public PostDto getPoswtById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not Found with id: " + id)
        );

        PostDto dto = new PostDto();
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        List<PostDto> data = posts.stream().map(post -> mapToDto((post))).collect(Collectors.toList());
        return data;
    }

    @Override
    public List<PostDto> getAllPostsWithPagination(int pageNo, int pageSize) {
        PageRequest pagable = PageRequest.of(pageNo, pageSize);

        Page<Post> pagePost = postRepo.findAll(pagable);
        List<Post> posts = pagePost.getContent();

        List<PostDto> data = posts.stream().map(post -> mapToDto((post))).collect(Collectors.toList());
        return data;
    }

    @Override
    public List<PostDto> getAllPostsWithPaginationWithSortBy(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortBy);

        PageRequest pagable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> pagePost = postRepo.findAll(pagable);
        List<Post> posts = pagePost.getContent();

        List<PostDto> data = posts.stream().map(post -> mapToDto((post))).collect(Collectors.toList());
        return data;
    }

    PostDto mapToDto(Post post) {
        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;
    }

    Post mapToPost(PostDto dto) {
        Post post = modelMapper.map(dto, Post.class);
        return post;
    }
}
