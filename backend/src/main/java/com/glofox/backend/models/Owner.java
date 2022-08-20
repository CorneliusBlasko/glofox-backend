package com.glofox.backend.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Owner {

  private final UUID id;
  private String name;
  private Studio studio;

}
