package com.tejnal.springapps.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;
import java.util.Objects;


@JsonPropertyOrder({"id", "name", "blog"})
public class PersonResponse {

    @NotNull
    @JsonProperty("id")
    private Long personId;
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("blog")
    private String blog;

    public PersonResponse() {
      super();
    }

    public PersonResponse(Long personId, String name, String blog) {
        super();
        this.personId = personId;
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonResponse)) return false;
        PersonResponse that = (PersonResponse) o;
        return Objects.equals(getPersonId(), that.getPersonId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getBlog(), that.getBlog());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getName(), getBlog());
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
