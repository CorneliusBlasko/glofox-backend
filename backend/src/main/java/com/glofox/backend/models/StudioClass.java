package com.glofox.backend.models;

import com.glofox.backend.controllers.dtos.StudioClassDto;
import com.glofox.backend.exceptions.ClassCreationException;
import lombok.Data;

import java.util.Date;

@Data
public class StudioClass {

  private String name;
  private Date start;
  private Date end;
  private Integer capacity;

  public StudioClass(StudioClassDto dto) {
    if(fieldsAreNotNull(dto)) {
      if(!datesAreValid(dto)) {
        throw new ClassCreationException("The start date must be before the end date");
      } else {
        this.name = dto.getName();
        this.start = dto.getStart();
        this.end = dto.getEnd();
        this.capacity = dto.getCapacity();
      }
    } else {
      throw new ClassCreationException("All fields must be filled");
    }
  }

  //Used only for bootstrapping
  public StudioClass(String name, Date start, Date end, Integer capacity) {
    this.name = name;
    this.start = start;
    this.end = end;
    this.capacity = capacity;
  }

  private boolean fieldsAreNotNull(StudioClassDto dto) {
    return dto.getName() != null
        && !dto.getName().isBlank()
        && dto.getStart() != null
        && dto.getEnd() != null
        && dto.getCapacity() != null;
  }

  private boolean datesAreValid(StudioClassDto dto) {
    return dto.getStart().before(dto.getEnd());
  }

}
