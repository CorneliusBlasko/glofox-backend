package com.glofox.backend.models;

import lombok.Data;

import java.util.List;

@Data
public class Studio {

  private String name;
  private Owner owner;
  private List<Member> members;
  private List<StudioClass> classes;

  public Studio(String name, List<Member> members, List<StudioClass> classes, List<Booking> bookings) {

    this.name = name;
    this.members = members;
    this.classes = classes;
  }
}
