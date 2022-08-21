package com.glofox.backend.models;

import com.glofox.backend.controllers.dtos.StudioClassDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class StudioClass {

  private String name;
  private Date start;
  private Date end;
  private Integer capacity;

  public StudioClass(StudioClassDto dto) {
    if(fieldsAreNotNull(dto)) {
      if(!datesAreValid(dto)) {
        throw new RuntimeException("The start date must be before the end date");
      } else {
        this.start = dto.getStart();
        this.end = dto.getEnd();
        this.name = dto.getName();
        this.capacity = dto.getCapacity();
      }
    } else {
      throw new RuntimeException("All fields must be filled");
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
