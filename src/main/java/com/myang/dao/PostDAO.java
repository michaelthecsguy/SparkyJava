package com.myang.dao;

import com.myang.model.Post;
import java.util.List;
import java.util.Map;

/**
 * Created by myang on 10/15/15.
 */
public interface PostDAO
{
  public int createPost(String title, String content, List categories);
  public Map<Integer, Post> getAllPostsInMap();
  public List getAllPostsInList();
}
