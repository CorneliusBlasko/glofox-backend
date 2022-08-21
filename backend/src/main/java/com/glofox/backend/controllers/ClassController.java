package com.glofox.backend.controllers;

import com.glofox.backend.controllers.dtos.StudioClassDto;
import com.glofox.backend.models.StudioClass;
import com.glofox.backend.services.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassController {

  ClassService classService;

  public ClassController(ClassService service) {
    this.classService = service;
  }

  @PostMapping("/{id}/create")
  public ResponseEntity<String> createClass(@RequestBody StudioClassDto classDto,
                                            @PathVariable String id) {
    try {
      StudioClass studioClass = new StudioClass(classDto);
      this.classService.createClass(studioClass, id);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
