package com.myblog.myblog11.service.impl;

import com.myblog.myblog11.entity.Comment;
import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.repository.CommentRepository;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;
    private CommentRepository commentRepo;
    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto dto, long postId) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + postId)
        );

        Comment comment = new Comment();
        comment.setEmail(dto.getEmail());
        comment.setText(dto.getText());
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);
        CommentDto commentDto = new CommentDto();
        commentDto.setId(savedComment.getId());
        commentDto.setEmail(savedComment.getEmail());
        commentDto.setText(savedComment.getText());

        return commentDto;
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepo.deleteById(commentId);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto dto) {
        Comment comment = commentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment not founf for id" + id)
        );

        Comment c = mapToEntity(dto);
        c.setId(comment.getId());
        c.setPost(comment.getPost());
        Comment savedComment = commentRepo.save(c);
        CommentDto commentDto = mapToDto(savedComment);

        return commentDto;

    }

    CommentDto mapToDto(Comment comment) {
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    Comment mapToEntity(CommentDto dto) {
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;
    }
}
