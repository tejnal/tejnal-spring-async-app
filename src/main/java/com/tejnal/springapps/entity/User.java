package com.tejnal.springapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private int id;

  @Column
  private Long user_id;
  @Column
  private String name;
  @Column
  private String blog;

  public User() {}

  public User(Long user_id, String name, String blog) {
    super();
    this.user_id = user_id;
    this.name = name;
    this.blog = blog;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
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

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", user_id=" + user_id +
            ", name='" + name + '\'' +
            ", blog='" + blog + '\'' +
            '}';
  }
}
