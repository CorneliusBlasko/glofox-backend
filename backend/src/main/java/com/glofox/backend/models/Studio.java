package com.glofox.backend.models;

import lombok.Data;

import java.util.List;

@Data
public class Studio {

  private String name;
  private List<Member> members;
  private List<StudioClass> classes;

  //Used only for bootstrapping
  public Studio(String name, List<Member> members, List<StudioClass> classes) {
    this.name = name;
    this.members = members;
    this.classes = classes;
  }
}
