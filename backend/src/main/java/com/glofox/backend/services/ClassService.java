package com.glofox.backend.services;

import com.glofox.backend.exceptions.ClassNonExistentException;
import com.glofox.backend.exceptions.DuplicatedException;
import com.glofox.backend.exceptions.InvalidDateException;
import com.glofox.backend.exceptions.RoleException;
import com.glofox.backend.models.Booking;
import com.glofox.backend.models.Member;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.repositories.Repository;
import com.glofox.backend.services.interfaces.IClassService;

import java.util.Date;

public class ClassService implements IClassService {

  Repository repository;

  @Override
  public void createClass(StudioClass studioClass, String name) {
    if (this.repository.getOwnerByName(name) == null) {
      throw new RoleException();
    }
    if (classExists(studioClass, name)) {
      throw new DuplicatedException();
    }
    this.repository.createClass(studioClass, name);
  }

  @Override
  public void bookClass(Booking booking, Member member) {
    //Check if is member of studio
    Studio studio = this.repository.getStudioByMember(member);
    if (studio != null) {
      //check if class exists in studio
      StudioClass studioClass = studio.getClasses().stream()
          .filter(c -> c.getName().equals(booking.getClassName()))
          .findFirst()
          .orElse(null);
      if (studioClass != null) {
        //Check if booking already exists
        if (this.repository.getStudioMember(member, studio).getBookings().contains(booking)) {
          throw new DuplicatedException();
        } else {
          //Check if booking date is inside the class date range
          if (booking.getDate().after(studioClass.getStart())
              && booking.getDate().before(studioClass.getEnd())) {
            member.getBookings().add(booking);
            for (Member m : studio.getMembers()) {
              if (m.getName().equals(member.getName())) {
                m.getBookings().add(booking);
              }
            }
          } else {
            throw new InvalidDateException();
          }
        }
      } else {
        throw new ClassNonExistentException();
      }
    } else {
      throw new RoleException();
    }
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
