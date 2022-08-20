package com.glofox.backend.controllers;

import com.glofox.backend.controllers.dtos.StudioClassDto;
import com.glofox.backend.exceptions.ClassCreationException;
import com.glofox.backend.models.StudioClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ClassController {



  @PostMapping("/create")
  public ResponseEntity<String> createClass(@RequestBody StudioClassDto classDto) {
    try {
      StudioClass studioClass = new StudioClass(classDto);


      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
