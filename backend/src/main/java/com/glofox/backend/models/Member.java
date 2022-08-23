package com.glofox.backend.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Member {

  private String name;
  private List<Booking> bookings;

  public Member(String name) {
    this.bookings = new ArrayList<>();
    if (name != null && !name.isBlank()) {
      this.name = name;
    } else {
      throw new RuntimeException("All fields must be filled");
    }
  }
}
