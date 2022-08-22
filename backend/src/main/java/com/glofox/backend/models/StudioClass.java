package com.glofox.backend.models;

import com.glofox.backend.dtos.StudioClassDto;
import com.glofox.backend.exceptions.InvalidDateFormatException;
import com.glofox.backend.utilities.ValidationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class StudioClass {

  private String name;
  private Date start;
  private Date end;
  private Integer capacity;

  public StudioClass(StudioClassDto dto) {
    if(!ValidationUtils.fieldsAreNotNull(dto)) {
      if(ValidationUtils.datesAreValid(dto)) {
        throw new RuntimeException("The start date must be before the end date");
      } else {
        try{
          this.start = dto.getStart();
          this.end = dto.getEnd();
        } catch (Exception e) {
          throw new InvalidDateFormatException("The date format must be dd-MM-yyyy");
        }
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

}
