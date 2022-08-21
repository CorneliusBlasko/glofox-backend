package com.glofox.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Owner {

  private String name;

  //Used only for bootstrapping
  public Owner(String name) {
    this.name = name;
  }
}
