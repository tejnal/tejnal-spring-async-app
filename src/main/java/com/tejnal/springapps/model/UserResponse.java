package com.tejnal.springapps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;


@JsonPropertyOrder({"id", "name", "blog"})
public class UserResponse {

    @NotNull
    @JsonProperty("id")
    private Long userId;
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("blog")
    private String blog;

    public UserResponse() {
      super();
    }

    public UserResponse(Long userId, String name, String blog) {
        super();
        this.userId = userId;
        this.name = name;
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }


}
