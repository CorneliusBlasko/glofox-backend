package com.glofox.backend.utilities;

import com.glofox.backend.dtos.BookingDto;
import com.glofox.backend.dtos.Dto;
import com.glofox.backend.dtos.StudioClassDto;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ValidationUtils {

  public static boolean fieldsAreNotNull(Dto dto) {
    Field[] fields = Dto.class.getDeclaredFields();

    if(dto instanceof StudioClassDto){
      fields = StudioClassDto.class.getDeclaredFields();
    } else if (dto instanceof BookingDto) {
      fields = BookingDto.class.getDeclaredFields();
    }

    List<Field> fieldList = Arrays.asList(fields);

    return fieldList.stream().anyMatch(Objects::nonNull);

  }

  public static boolean datesAreValid(Dto dto) {
    if (dto instanceof StudioClassDto) {
      StudioClassDto scdto = (StudioClassDto) dto;
      return !scdto.getStart().before(scdto.getEnd());
    }
    return true;
  }

}
