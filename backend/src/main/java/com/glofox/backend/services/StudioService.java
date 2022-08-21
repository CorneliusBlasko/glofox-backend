package com.glofox.backend.services;

import com.glofox.backend.exceptions.DuplicatedException;
import com.glofox.backend.exceptions.RoleException;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.repositories.Repository;
import com.glofox.backend.repositories.StudioRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StudioService implements Service{

  Repository repository;

  public StudioService(StudioRepository repository) {
    this.repository = repository;
  }

  public void create(StudioClass studioClass, String name) {
    if(this.repository.getOwnerByName(name) == null){
      throw new RoleException();
    }
    if(classExists(studioClass, name)){
      throw new DuplicatedException();
    }
    this.repository.createClass(studioClass, name);

  }

  public List<Studio> getAllStudios(){
    return this.repository.getAllStudios();
  }

  public boolean classExists(StudioClass studioClass, String name) {
    return this.repository.getStudioByOwnerName(name).getClasses().stream()
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
