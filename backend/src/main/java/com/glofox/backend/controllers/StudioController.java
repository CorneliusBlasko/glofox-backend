package com.glofox.backend.controllers;

import com.glofox.backend.controllers.dtos.StudioClassDto;
import com.glofox.backend.exceptions.DuplicatedException;
import com.glofox.backend.exceptions.RoleException;
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
      this.studioService.create(studioClass, name);
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

}
