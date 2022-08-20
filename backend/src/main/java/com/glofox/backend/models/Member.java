package com.glofox.backend.models;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Member {

  private final UUID id;
  private String name;
  private List<Booking> bookings;
}
