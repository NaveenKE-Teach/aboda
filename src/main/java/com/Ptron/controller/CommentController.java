/*
package com.Ptron.controller;

import com.Ptron.payload.CommentDto;
import com.Ptron.payload.PostDto;
import com.Ptron.payload.PostWithCommentDto;
import com.Ptron.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    // http://localhost:8080/api/comments/1
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long postId) {

        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    private ResponseEntity<PostWithCommentDto> getAllCommentsByPostId(@PathVariable long postId) {
        PostWithCommentDto allCommentsByPostId = commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(allCommentsByPostId, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public  ResponseEntity<CommentDto> getCommentById(@PathVariable("id")long id){
        CommentDto dto = commentService.getCommentsById(id);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }


}


*/

package com.Ptron.controller;

import com.Ptron.payload.CommentDto;
import com.Ptron.payload.PostWithCommentDto;
import com.Ptron.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // http://localhost:8080/api/comments/{postId}
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable long postId) {

        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/comments/post/{postId}
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostWithCommentDto> getAllCommentsByPostId(@PathVariable long postId) {
        PostWithCommentDto allCommentsByPostId = commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(allCommentsByPostId, HttpStatus.OK);
    }

    // http://localhost:8080/api/comments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("id") long id) {
        CommentDto dto = commentService.getCommentsById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

