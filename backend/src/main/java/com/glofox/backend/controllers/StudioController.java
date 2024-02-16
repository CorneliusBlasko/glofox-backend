package com.glofox.backend.controllers;

import com.glofox.backend.models.Studio;
import com.glofox.backend.services.interfaces.IStudioService;
import com.glofox.backend.services.StudioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudioController {

  IStudioService studioService;

  public StudioController(StudioService service) {
    this.studioService = service;
  }

  @GetMapping("/studios")
  public List<Studio> getAllStudios() {
    return this.studioService.getAllStudios();
  }

}
