package com.Ptron.service.service.impl;

import com.Ptron.entity.Comment;
import com.Ptron.entity.Post;
import com.Ptron.exception.ResourceNotFound;
import com.Ptron.payload.CommentDto;
import com.Ptron.payload.PostDto;
import com.Ptron.payload.PostWithCommentDto;
import com.Ptron.repository.CommentRepository;
import com.Ptron.repository.PostRepository;
import com.Ptron.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);

        return dto;
    }

    public PostWithCommentDto getAllCommentsByPostId(long id) {
        Post post = postRepository.findById(id).get();

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());


        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        PostWithCommentDto postWithCommentDto = new PostWithCommentDto();

        postWithCommentDto.setCommentDto(dtos);
        postWithCommentDto.setPost(dto);

        return postWithCommentDto;
    }

    @Override
    public CommentDto getCommentsById(long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound(("Comment not found with Id :" + id))
        );
        return mapToDto(comment);
    }


    Comment mapToEntity(CommentDto dto) {
        Comment comment = modelMapper.map(dto, Comment.class);
        return comment;

    }

    CommentDto mapToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }
}



