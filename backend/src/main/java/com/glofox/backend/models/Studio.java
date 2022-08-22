package com.glofox.backend.models;

import lombok.Data;

import java.util.List;

@Data
public class Studio {

  private Owner owner;
  private String name;
  private List<Member> members;
  private List<StudioClass> classes;
  private List<Booking> bookings;

  public Studio(String name, List<Member> members, List<StudioClass> classes, List<Booking> bookings) {
    this.bookings = bookings;
    this.name = name;
    this.members = members;
    this.classes = classes;
  }
}
