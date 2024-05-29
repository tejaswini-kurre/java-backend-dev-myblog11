package com.myblog.myblog11.service;

import com.myblog.myblog11.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto getPoswtById(Long id);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsWithPagination(int pageNo, int pageSize);

    List<PostDto> getAllPostsWithPaginationWithSortBy(int pageNo, int pageSize, String sortBy, String sortDir);
}
