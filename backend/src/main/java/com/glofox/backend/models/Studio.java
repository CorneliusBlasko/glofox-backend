package com.glofox.backend.models;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Studio {

  private final UUID id;
  private List<Member> members;
  private List<StudioClass> classes;

}
