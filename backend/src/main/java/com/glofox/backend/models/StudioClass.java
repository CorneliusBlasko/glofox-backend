package com.glofox.backend.models;

import com.glofox.backend.controllers.dtos.StudioClassDto;
import com.glofox.backend.exceptions.ClassCreationException;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class StudioClass {

  private final UUID uuid;
  private String name;
  private Date start;
  private Date end;
  private Integer capacity;

  public StudioClass(StudioClassDto dto) {
    if(isValidClass(dto)) {
      if(!isValidDate(dto)) {
        throw new ClassCreationException("The start date must be before the end date");
      } else {
        this.uuid = UUID.randomUUID();
        this.name = dto.getName();
        this.start = dto.getStart();
        this.end = dto.getEnd();
        this.capacity = dto.getCapacity();
      }
    } else {
      throw new ClassCreationException("All fields must be filled");
    }
  }

  public StudioClass(String name, Date start, Date end, Integer capacity) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.start = start;
    this.end = end;
    this.capacity = capacity;
  }

  private boolean isValidClass(StudioClassDto dto) {
    return dto.getName() != null
        && !dto.getName().isBlank()
        && dto.getStart() != null
        && dto.getEnd() != null
        && dto.getCapacity() != null;
  }

  private boolean isValidDate(StudioClassDto dto) {
    return dto.getStart().before(dto.getEnd());
  }

}
