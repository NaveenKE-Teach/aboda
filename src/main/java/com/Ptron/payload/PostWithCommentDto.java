package com.Ptron.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class PostWithCommentDto {

    private PostDto post;
    private List<CommentDto> commentDto = new ArrayList<>();
}
