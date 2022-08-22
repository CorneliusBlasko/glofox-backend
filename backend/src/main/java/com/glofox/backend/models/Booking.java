package com.glofox.backend.models;

import com.glofox.backend.dtos.BookingDto;
import com.glofox.backend.exceptions.InvalidDateFormatException;
import lombok.Data;

import java.util.Date;

@Data
public class Booking {

  private String className;
  private String student;
  private Date date;

  public Booking(BookingDto dto, String student){
    if(fieldsAreNotNull(dto, student)) {
        this.className = dto.getClassName();
        this.student = student;
        try{
        this.date = dto.getDate();
        } catch (Exception e) {
          throw new InvalidDateFormatException("The date format must be dd-MM-yyyy");
        }
    } else {
      throw new RuntimeException("All fields must be filled");
    }
  }

  private boolean fieldsAreNotNull(BookingDto dto, String student) {
    return dto.getClassName() != null
        && !dto.getClassName().isBlank()
        && student != null
        && !student.isBlank()
        && dto.getDate() != null;
  }

}
