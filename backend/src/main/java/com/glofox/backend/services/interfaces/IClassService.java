package com.glofox.backend.services.interfaces;

import com.glofox.backend.models.Booking;
import com.glofox.backend.models.Member;
import com.glofox.backend.models.StudioClass;

public interface IClassService {

  void bookClass(Booking booking, Member member);

  void createClass(StudioClass studioClass, String name);

}
