package com.glofox.backend.controllers;

import com.glofox.backend.models.Owner;
import com.glofox.backend.repositories.OwnerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OwnerController {

  OwnerRepository repository;

  public OwnerController(OwnerRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/owners")
  public List<Owner> getOwners(){
    return this.repository.getAllOwners();
  }

  @GetMapping("/owners/{id}")
  public Owner getOwner(@PathVariable String id){
    return this.repository.getOwnerByName(id);
  }

}
