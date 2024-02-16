package com.glofox.backend.controllers;

import com.glofox.backend.dtos.BookingDto;
import com.glofox.backend.dtos.StudioClassDto;
import com.glofox.backend.exceptions.ClassNonExistentException;
import com.glofox.backend.exceptions.DuplicatedException;
import com.glofox.backend.exceptions.InvalidDateException;
import com.glofox.backend.exceptions.RoleException;
import com.glofox.backend.models.Booking;
import com.glofox.backend.models.Member;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.services.interfaces.IClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ClassController {

  IClassService classService;

  public ClassController(IClassService classService) {
    this.classService = classService;
  }

  @PostMapping("/{name}/classes")
  public ResponseEntity<String> createClass(@RequestBody StudioClassDto classDto,
                                            @PathVariable String name) {
    try {
      StudioClass studioClass = new StudioClass(classDto);
      this.classService.createClass(studioClass, name);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (DuplicatedException e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (RoleException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("/{name}/bookings")
  public ResponseEntity<String> bookClass(@RequestBody BookingDto bookingDto,
                                          @PathVariable String name) {
    try {
      Booking booking = new Booking(bookingDto, name);
      Member member = new Member(name);
      this.classService.bookClass(booking, member);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RoleException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } catch (DuplicatedException | ClassNonExistentException | InvalidDateException e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

  }

}
