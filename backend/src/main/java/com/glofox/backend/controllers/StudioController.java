package com.glofox.backend.controllers;

import com.glofox.backend.dtos.BookingDto;
import com.glofox.backend.dtos.StudioClassDto;
import com.glofox.backend.exceptions.ClassNonExistent;
import com.glofox.backend.exceptions.DuplicatedException;
import com.glofox.backend.exceptions.RoleException;
import com.glofox.backend.models.Booking;
import com.glofox.backend.models.Member;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.services.Service;
import com.glofox.backend.services.StudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudioController {

  Service studioService;

  public StudioController(StudioService service) {
    this.studioService = service;
  }

  @PostMapping("/{name}/classes")
  public ResponseEntity<String> createClass(@RequestBody StudioClassDto classDto,
                                            @PathVariable String name) {
    try {
      StudioClass studioClass = new StudioClass(classDto);
      this.studioService.createClass(studioClass, name);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (DuplicatedException e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (RoleException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/studios")
  public List<Studio> getAllStudios() {
    return this.studioService.getAllStudios();
  }

  @PostMapping("/{name}/bookings")
  public ResponseEntity<String> bookClass(@RequestBody BookingDto bookingDto,
                                            @PathVariable String name) {
    try {
      Booking booking = new Booking(bookingDto, name);
      Member member = new Member(name);
      this.studioService.bookClass(booking, member);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (RoleException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } catch (DuplicatedException | ClassNonExistent e) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

  }

}
