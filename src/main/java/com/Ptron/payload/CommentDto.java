package com.Ptron.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CommentDto {
    private long id;
    private String name;
    private String message;
}
