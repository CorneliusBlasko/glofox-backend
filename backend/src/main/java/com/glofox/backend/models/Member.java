package com.glofox.backend.models;

import lombok.Data;

import java.util.List;

@Data
public class Member {

  private final String id;
  private String name;
  private List<Booking> bookings;
}
