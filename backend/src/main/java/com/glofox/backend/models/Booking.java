package com.glofox.backend.models;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Booking {

  private final UUID id;
  private Date date;
  private String className;

}
