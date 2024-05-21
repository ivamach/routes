package com.ivamach.routes.services;

import com.ivamach.routes.models.GpsPoint;
import com.ivamach.routes.models.GpsTrack;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class GpsTrackServiceImp implements GpsTrackService {

  private final String FILES_PATH = "src/main/resources/";

  @Override
  public GpsTrack load(String id) {
    Path path = Paths.get(FILES_PATH + id + ".csv");
    GpsTrack gpsTrack = new GpsTrack();
    try {
      List<String> input = Files.readAllLines(path);
      gpsTrack.setPoints(getGpsPoints(input));
    } catch (IOException e) {
      throw new RuntimeException("Error reading file " + id + ".csv", e);
    }
    return gpsTrack;
  }

  @Override
  public void save(String id, GpsTrack gpsTrack) {
    Path path = Paths.get(FILES_PATH + id + ".csv");
    try {
      Files.write(path, gpsPointsToList(gpsTrack.getPoints()));
    } catch (IOException e) {
      throw new RuntimeException("Error writing file " + id + ".csv", e);
    }
  }

  private List<GpsPoint> getGpsPoints(List<String> lines) {
    List<GpsPoint> gpsPoints = new ArrayList<>();
    for (int line = 1; line < lines.size(); line++) {
      String[] coords = lines.get(line).split(",");
      gpsPoints.add(
              new GpsPoint(
                      Double.parseDouble(coords[0]),
                      Double.parseDouble(coords[1]),
                      Double.parseDouble(coords[2])));
    }
    return gpsPoints;
  }

  private List<String> gpsPointsToList(List<GpsPoint> gpsPoints) {
    List<String> lines = new ArrayList<>();
    lines.add("x,y,t");
    for (GpsPoint gpsPoint : gpsPoints) {
      lines.add(gpsPoint.toString());
    }
    return lines;
  }
}
