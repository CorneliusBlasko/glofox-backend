package com.glofox.backend.repositories;

import com.glofox.backend.models.StudioClass;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ClassRepository implements Repository {

  List<StudioClass> classes;

  public ClassRepository() {
    this.classes = new ArrayList<>();
  }

  @Override
  public boolean exists(StudioClass studioClass) {
    return classes.stream()
        .anyMatch(c -> c.getName().equals(studioClass.getName())
            && areTheSameDate(c.getStart(), studioClass.getStart())
            && areTheSameDate(c.getEnd(), studioClass.getEnd())
            && c.getCapacity().intValue() == studioClass.getCapacity().intValue()
        );
  }

  @Override
  public void create(StudioClass studioClass) {

  }

  private boolean areTheSameDate(Date date1, Date date2) {
    int result = date1.compareTo(date2);
    return result == 0;
  }

}
