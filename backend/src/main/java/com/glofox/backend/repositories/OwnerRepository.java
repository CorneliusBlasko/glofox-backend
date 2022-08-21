package com.glofox.backend.repositories;

import com.glofox.backend.models.Owner;
import com.glofox.backend.models.StudioClass;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OwnerRepository implements IOwnerRepository {

  List<Owner> owners;

  public OwnerRepository() {
    this.owners = new ArrayList<>();
  }

  public void createOwner(Owner owner) {
    this.owners.add(owner);
  }

  public Owner getOwnerByName(String ownerId){
    for(Owner o : this.owners){
      if(ownerId.equals(o.getName())){
        return o;
      }
    }
    return null;
  }

  public void createClass(StudioClass studioClass, String ownerId) {
    Owner owner = this.getOwnerByName(ownerId);
    owner.getStudio().getClasses().add(studioClass);
  }

  public List<Owner> getAllOwners() {
    return this.owners;
  }
}
