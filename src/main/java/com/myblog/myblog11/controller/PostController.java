package com.myblog.myblog11.controller;

import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto) {
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostDto> getPostById(@RequestParam Long id) {
        PostDto dto = postService.getPoswtById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<PostDto> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return posts;
    }

    @GetMapping("/get/all")
    public List<PostDto> getAllPostsWithPagination(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "0") int pageSize

    ) {
        List<PostDto> posts = postService.getAllPostsWithPagination(pageNo, pageSize);
        return posts;
    }

    @GetMapping("/get/all/sorted")
    public List<PostDto> getAllPostsWithPaginationWithSortBy(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "0") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir

    ) {
        List<PostDto> posts = postService.getAllPostsWithPaginationWithSortBy(pageNo, pageSize, sortBy, sortDir);
        return posts;
    }

}
