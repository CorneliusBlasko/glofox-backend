package com.glofox.backend.services;

import com.glofox.backend.models.Booking;
import com.glofox.backend.models.Member;
import com.glofox.backend.models.Studio;
import com.glofox.backend.models.StudioClass;

import java.util.List;

public interface Service {

  void createClass(StudioClass studioClass, String name);

  List<Studio> getAllStudios();

  void bookClass(Booking booking, Member member);
}
