package com.myang.dao.impl;

import com.myang.dao.PostDAO;
import com.myang.model.Post;

import java.util.*;

/**
 * Created by myang on 10/15/15.
 */
public class PostDAOImpl implements PostDAO
{
  private int nextId = 1;
  private Map<Integer, Post> posts = new TreeMap<>();

  public int createPost(String title, String content, List categories){
    int id = nextId++;
    Post post = new Post();
    post.setId(id);
    post.setTitle(title);
    post.setContent(content);
    post.setCategories(categories);
    posts.put(id, post);
    return id;
  }

  public Map<Integer, Post> getAllPostsInMap()
  {
    //return posts.keySet().stream().sorted().map((id) -> posts.get(id)).collect(Collectors.toList());
    return posts;
  }

  public List getAllPostsInList()
  {
    List allPostsList = new LinkedList<Post>();

    for (Map.Entry<Integer, Post> aPost : posts.entrySet())
    {
      allPostsList.add(aPost.getValue());
    }

    return allPostsList;
  }
}
