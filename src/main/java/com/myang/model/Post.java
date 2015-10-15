package com.myang.model;

import java.util.List;

/**
 * Created by myang on 10/15/15.
 */

public class Post
{
  private int id;
  private String title;
  private List categories;
  private String content;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List getCategories() {
    return categories;
  }

  public void setCategories(List categories) {
    this.categories = categories;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
