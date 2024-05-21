package com.ivamach.routes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GpsPoint {
  private double x;
  private double y;
  private double t;

  @Override
  public String toString() {
    return x + "," + y + "," + t;
  }

}
