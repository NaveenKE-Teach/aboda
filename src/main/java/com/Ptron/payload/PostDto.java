package com.Ptron.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PostDto {
    private long id;

    @Column(unique = true)
    @Size(min = 3, message = "Title should be at least 3 characters")
    private String title;

    @NotBlank(message = "description should not be blank")
    @Size(min = 4, message = "Description should be at least 4 characters")
    private String description;

    @NotBlank(message = "content should not be blank")
    @Size(min = 5, message = "Content should be at least 5 characters")
    private String content;
}
