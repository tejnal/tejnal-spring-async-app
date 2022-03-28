package com.tejnal.springapps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private int id;

  @Column
  private Long personId;
  @Column
  private String name;
  @Column
  private String blog;

  public Person() {
    super();
  }

  public Person(Long personId, String name, String blog) {
    super();
    this.personId = personId;
    this.name = name;
    this.blog = blog;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
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
    return "Person{" +
            "id=" + id +
            ", personId=" + personId +
            ", name='" + name + '\'' +
            ", blog='" + blog + '\'' +
            '}';
  }
}
