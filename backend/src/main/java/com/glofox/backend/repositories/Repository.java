package com.glofox.backend.repositories;

import com.glofox.backend.models.Member;
import com.glofox.backend.models.Owner;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;

import java.util.List;

public interface Repository {

  void createStudio(Studio studio);

  Owner getOwnerByName(String name);

  Studio getStudioByOwnerName(String name);

  void createClass(StudioClass studioClass, String name);

  List<Studio> getAllStudios();

  Studio getStudioByMember(Member member);


}
