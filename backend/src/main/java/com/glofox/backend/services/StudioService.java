package com.glofox.backend.services;

import com.glofox.backend.models.Studio;
import com.glofox.backend.repositories.Repository;
import com.glofox.backend.repositories.StudioRepository;
import com.glofox.backend.services.interfaces.IStudioService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudioService implements IStudioService {

  Repository repository;

  public StudioService(StudioRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Studio> getAllStudios() {
    return this.repository.getAllStudios();
  }
}
