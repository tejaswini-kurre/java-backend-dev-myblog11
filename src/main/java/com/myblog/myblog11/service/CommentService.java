package com.myblog.myblog11.service;

import com.myblog.myblog11.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    void deleteComment(long commentId);

    CommentDto updateComment(long id, CommentDto dto);
}
