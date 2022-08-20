package com.glofox.backend.repositories;

import com.glofox.backend.models.StudioClass;

public interface Repository {

  boolean exists(StudioClass studioClass);

  void create(StudioClass studioClass);

}
