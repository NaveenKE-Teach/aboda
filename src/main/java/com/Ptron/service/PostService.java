package com.Ptron.service;

import com.Ptron.payload.ListPostDto;
import com.Ptron.payload.PostDto;

public interface PostService {

    public PostDto createPost(PostDto postDto);


    void deletePost(long id);

    ListPostDto fetchAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    public PostDto getPostById(long id);
}
