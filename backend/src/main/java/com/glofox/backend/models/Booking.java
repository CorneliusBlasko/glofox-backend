package com.glofox.backend.models;

import lombok.Data;

import java.util.Date;

@Data
public class Booking {

  private final String memberName;
  private Date date;

}
