package com.ivamach.routes.services;

import com.ivamach.routes.models.GpsTrack;

public interface GpsTrackService {

  GpsTrack load(String id);

  void save(String id, GpsTrack gpsTrack);
}
