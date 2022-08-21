package com.glofox.backend.services;

import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;

import java.util.List;

public interface Service {

  void create(StudioClass studioClass, String name);

  List<Studio> getAllStudios();

  boolean classExists(StudioClass studioClass, String name);
}
