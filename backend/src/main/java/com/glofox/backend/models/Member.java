package com.glofox.backend.models;

import lombok.Data;


@Data
public class Member {

  private String name;

  public Member(String name) {
    if(name != null && !name.isBlank()) {
      this.name = name;
    } else {
      throw new RuntimeException("All fields must be filled");
    }
  }
}
