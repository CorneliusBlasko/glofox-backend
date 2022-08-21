package com.glofox.backend.services;

import com.glofox.backend.exceptions.ClassCreationException;
import com.glofox.backend.models.Entity;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.repositories.OwnerRepository;
import com.glofox.backend.repositories.Repository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClassService {

  Repository ownerRepository;

  public ClassService(OwnerRepository repository) {
    this.ownerRepository = repository;
  }

  public void create(StudioClass studioClass, String ownerId) {
    if(this.ownerRepository.getOwnerByName(ownerId) == null){
      throw new ClassCreationException("The owner does not exist");
    }
    if(exists(studioClass, ownerId)){
      throw new ClassCreationException("The class already exists");
    }
    this.ownerRepository.createClass(studioClass, ownerId);

  }

  public boolean exists(StudioClass studioClass, String ownerId) {
    return this.ownerRepository.getOwnerByName(ownerId).getStudio().getClasses().stream()
        .anyMatch(c -> c.getName().equals(studioClass.getName())
            && areTheSameDate(c.getStart(), studioClass.getStart())
            && areTheSameDate(c.getEnd(), studioClass.getEnd())
            && c.getCapacity().intValue() == studioClass.getCapacity().intValue()
        );
  }

  private boolean areTheSameDate(Date date1, Date date2) {
    return date1.compareTo(date2) == 0;
  }
}
