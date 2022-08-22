package com.glofox.backend.models;

import com.glofox.backend.dtos.BookingDto;
import com.glofox.backend.exceptions.InvalidDateFormatException;
import com.glofox.backend.utilities.ValidationUtils;
import lombok.Data;

import java.util.Date;

@Data
public class Booking {

  private String student;
  private Date date;

  public Booking(BookingDto dto){
    if(!ValidationUtils.fieldsAreNotNull(dto)) {
        this.student = dto.getStudent();
        try{
        this.date = dto.getDate();
        } catch (Exception e) {
          throw new InvalidDateFormatException("The date format must be dd-MM-yyyy");
        }
    } else {
      throw new RuntimeException("All fields must be filled");
    }
  }

}
