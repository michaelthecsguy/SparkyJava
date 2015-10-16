package com.myang;

import static spark.Spark.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.myang.dao.PostDAO;
import com.myang.dao.impl.PostDAOImpl;
import com.myang.dto.NewPostPayloadDTO;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Hello world!
 *
 */
public class App 
{
  private static final int HTTP_BAD_REQUEST = 400;

  public static String dataToJson(Object data)
  {
    try
    {
      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      StringWriter sw = new StringWriter();
      mapper.writeValue(sw, data);

      return sw.toString();
    }
    catch (IOException e)
    {
      throw new RuntimeException("IOException from a StringWriter?");
    }
  }

  public static void main(String[] args)
  {
    System.out.println("Hello World!");
    //lambda expression for Java 1.8
    //get("/hello", (req, res) -> "Hello World");

    //for Java 1.7
    get(new Route("/helloworld")
    {
      public Object handle(Request request, Response response) {
        return "Hello World!";
      }
    });

    get(new Route("/helloworld/:username")
    {
      public Object handle(Request request, Response response)
      {
        return "Hello " + request.params("username") + "!";
      }
    });

    //Data persists in Memory.  There is no DB involved for now
    PostDAO postDao = new PostDAOImpl();
    Gson gson = new Gson();

    // insert a post (using HTTP post method)
    post(new Route("/createAPost") {
      public Object handle(Request request, Response response) {
        try {
          ObjectMapper mapper = new ObjectMapper();
          NewPostPayloadDTO newPostData = mapper.readValue(request.body(), NewPostPayloadDTO.class);
          if (!newPostData.isValid()) {
            response.status(HTTP_BAD_REQUEST);
            return "";
          }
          int id = postDao.createPost(newPostData.getTitle(), newPostData.getContent(), newPostData.getCategories());
          response.status(200);
          response.type("application/json");

          return id;
        } catch (IOException ex) {
          response.status(HTTP_BAD_REQUEST);
          return "Request Body Does Not Match with DTO object";
        }
      }

    });

    // get all post (using HTTP get method)
    get(new Route("/listAllPosts") {
      public Object handle(Request request, Response response) {
        response.status(200);
        response.type("application/json");
        return dataToJson(postDao.getAllPostsInList());
      }
    });

    // Added for Using non-existing REST Endpoint
    after(new Filter("/*")
    {
      public void handle(Request requset, Response response)
      {
        System.out.println("Response in Raw format: " + response.raw());

        if (response.raw().getStatus() == 0)
          halt(404, "Invalid Web RESTful Endpoint");
      }
    });

  }
}
