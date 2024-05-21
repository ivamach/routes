package com.ivamach.routes;

import com.ivamach.routes.models.GpsPoint;
import com.ivamach.routes.models.GpsTrack;
import com.ivamach.routes.services.GpsTrackServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class RoutesApplication implements CommandLineRunner {

  private final GpsTrackServiceImp gpsTrackService;

  public static void main(String[] args) {
    SpringApplication.run(RoutesApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    load();
    save();
  }

  private void save() {
    GpsTrack track = new GpsTrack();
    List<GpsPoint> points = new ArrayList<>();
    points.add(new GpsPoint(48.12345, 18.0005, 179.6));
    points.add(new GpsPoint(48.13333, 18.0100, 179.4));
    points.add(new GpsPoint(48.14345, 18.0208, 179.1));
    points.add(new GpsPoint(48.15345, 18.0335, 179.7));
    points.add(new GpsPoint(48.16745, 18.1005, 179.0));
    track.setPoints(points);
    gpsTrackService.save("track001", track);
  }

  private void load() {
    GpsTrack track = gpsTrackService.load("track001");
    List<GpsPoint> points = track.getPoints();
    points.add(new GpsPoint(48.17345, 18.1100, 179.7));
    gpsTrackService.save("track002", track);
  }
}
