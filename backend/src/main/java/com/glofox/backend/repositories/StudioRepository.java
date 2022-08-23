package com.glofox.backend.repositories;

import com.glofox.backend.models.Member;
import com.glofox.backend.models.Owner;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudioRepository implements Repository {

  List<Studio> studios;

  public StudioRepository() {
    this.studios = new ArrayList<>();
  }

  @Override
  public void createStudio(Studio studio) {
    this.studios.add(studio);
  }

  @Override
  public Owner getOwnerByName(String name) {
    for (Studio studio : this.studios) {
      if (name.equals(studio.getOwner().getName())) {
        return studio.getOwner();
      }
    }
    return null;
  }

  @Override
  public Studio getStudioByOwnerName(String name) {
    for (Studio studio : this.studios) {
      if (name.equals(studio.getOwner().getName())) {
        return studio;
      }
    }
    return null;
  }

  @Override
  public void createClass(StudioClass studioClass, String name) {
    for (Studio studio : this.studios) {
      if (name.equals(studio.getOwner().getName())) {
        studio.getClasses().add(studioClass);
      }
    }
  }

  @Override
  public List<Studio> getAllStudios() {
    return this.studios;
  }

  @Override
  public Studio getStudioByMember(Member member) {
    for (Studio studio : this.studios) {
      for (Member mem : studio.getMembers()) {
        if (member.getName().equals(mem.getName())) {
          return studio;
        }
      }
    }
    return null;
  }

  @Override
  public Member getStudioMember(Member member, Studio studio) {
    return studio.getMembers().stream()
        .filter(m -> m.getName().equals(member.getName()))
        .findFirst()
        .orElse(null);
  }

}
