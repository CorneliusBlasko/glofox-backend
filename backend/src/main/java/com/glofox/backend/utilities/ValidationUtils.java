package com.glofox.backend.utilities;

import com.glofox.backend.dtos.BookingDto;
import com.glofox.backend.dtos.Dto;
import com.glofox.backend.dtos.StudioClassDto;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ValidationUtils {

  public static boolean noNullFields(Dto dto) {
    Field[] fields = Dto.class.getDeclaredFields();

    if(dto instanceof StudioClassDto){
      fields = StudioClassDto.class.getDeclaredFields();
    } else if (dto instanceof BookingDto) {
      fields = BookingDto.class.getDeclaredFields();
    }

    List<Field> fieldList = Arrays.asList(fields);

    return fieldList.stream().noneMatch(Objects::isNull);

  }

  public static boolean classDatesAreValid(StudioClassDto dto) {
      return !dto.getStart().before(dto.getEnd());
  }

}
