package com.glofox.backend.repositories;

import com.glofox.backend.models.Owner;
import com.glofox.backend.models.StudioClass;

public interface Repository {

  void createOwner(Owner owner);

  Owner getOwnerByName(String ownerId);

  void createClass(StudioClass studioClass, String ownerId);

}
