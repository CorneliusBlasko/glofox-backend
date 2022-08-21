package com.glofox.backend.models;

import lombok.Data;

@Data
public class Owner {

  private String name;
  private Studio studio;

  //Used only for bootstrapping
  public Owner(String name, Studio studio) {
    this.name = name;
    this.studio = studio;
  }
}
