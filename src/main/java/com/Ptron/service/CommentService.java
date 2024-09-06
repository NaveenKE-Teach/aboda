package com.Ptron.service;

import com.Ptron.payload.CommentDto;
import com.Ptron.payload.PostWithCommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    PostWithCommentDto getAllCommentsByPostId(long postId);

    public CommentDto getCommentsById(long id);


}
