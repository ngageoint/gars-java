# GARS Java

#### Global Area Reference System Lib ####

The GARS Library was developed at the [National Geospatial-Intelligence Agency (NGA)](http://www.nga.mil/) in collaboration with [BIT Systems](https://www.caci.com/bit-systems/). The government has "unlimited rights" and is releasing this software to increase the impact of government investments by providing developers with the opportunity to take things in new directions. The software use, modification, and distribution rights are stipulated within the [MIT license](http://choosealicense.com/licenses/mit/).

### Pull Requests ###
If you'd like to contribute to this project, please make a pull request. We'll review the pull request and discuss the changes. All pull request contributions to this project will be released under the MIT license.

Software source code previously released under an open source license and then modified by NGA staff is considered a "joint work" (see 17 USC § 101); it is partially copyrighted, partially public domain, and as a whole is protected by the copyrights of the non-government authors and must be released according to the terms of the original open source license.

### About ###

[GARS](http://ngageoint.github.io/gars-java/) is a Java library providing Global Area Reference System functionality, a standardized geospatial reference system for areas.

### Usage ###

View the latest [Javadoc](http://ngageoint.github.io/gars-java/docs/api/)

#### Coordinates ####

```java

GARS gars = GARS.parse("006AG39");
Point point = gars.toPoint();
Point pointMeters = point.toMeters();

double latitude = 63.98862388;
double longitude = 29.06755082;
Point point2 = Point.create(longitude, latitude);
GARS gars2 = point2.toGARS();
String garsCoordinate = gars2.toString();
String gars30m = gars2.coordinate(GridType.THIRTY_MINUTE);
String gars15m = gars2.coordinate(GridType.FIFTEEN_MINUTE);
String gars5m = gars2.coordinate(GridType.FIVE_MINUTE);

```

#### Draw Tile Template ####

See [gars-android](https://github.com/ngageoint/gars-android) for a concrete example

```java

// GARSTile tile = ...;

Grids grids = Grids.create();

ZoomGrids zoomGrids = grids.getGrids(tile.getZoom());
if (zoomGrids.hasGrids()) {

  for (Grid grid : zoomGrids) {

    List<Line> lines = grid.getLines(tile);
    if (lines != null) {
      for (Line line : lines) {
        Pixel pixel1 = line.getPoint1().getPixel(tile);
        Pixel pixel2 = line.getPoint2().getPixel(tile);
        // Draw line
      }
    }

    List<Label> labels = grid.getLabels(tile);
    if (labels != null) {
      for (Label label : labels) {
        PixelRange pixelRange = label.getBounds()
            .getPixelRange(tile);
        Pixel centerPixel = label.getCenter().getPixel(tile);
        // Draw label
      }
    }

  }
}

```

#### Properties ####

Default grid properties including zoom ranges, styles, and labelers are defined in [gars.properties](https://github.com/ngageoint/gars-java/blob/master/src/main/resources/gars.properties). The defaults can be changed in code by modifying the [Grids](https://github.com/ngageoint/gars-java/blob/master/src/main/java/mil/nga/gars/grid/Grids.java).

### Installation ###

Pull from the [Maven Central Repository](http://search.maven.org/#artifactdetails|mil.nga|gars|1.0.0|jar) (JAR, POM, Source, Javadoc)

    <dependency>
        <groupId>mil.nga</groupId>
        <artifactId>gars</artifactId>
        <version>1.0.0</version>
    </dependency>

### Build ###

[![Build & Test](https://github.com/ngageoint/gars-java/workflows/Build%20&%20Test/badge.svg)](https://github.com/ngageoint/gars-java/actions/workflows/build-test.yml)

Build this repository using Eclipse and/or Maven:

    mvn clean install
