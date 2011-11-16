# encoding: utf-8

require_relative "lib/geodelta"
require_relative "lib/geodelta/region"
require_relative "lib/geodelta/id_util"
require_relative "example/svg"

x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse

#x1, y1 = -4.0, +1.0
#x2, y2 = +4.0, -1.0

dx = x2 - x1
dy = y1 - y2

svg = SVG.new(
  "width"   => "190mm",
  "height"  => "190mm",
  "viewBox" => "-16.0 -14.0 32.0 28.0")

svg.style(
  "polygon.a",
  "fill"   => "green",
  "stroke" => "none")

svg.style(
  "polygon.b",
  "fill"         => "none",
  "stroke"       => "black",
  "stroke-width" => "0.05")

svg.style(
  "text",
  "text-anchor"       => "middle",
  "dominant-baseline" => "central")

regional_ids = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, 2)
regional_ids.each { |ids|
  coordinates = GeoDelta::DeltaGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

  svg.polygon(
    "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
    "class"  => "a")
}

all_ids = GeoDelta::IdUtil.get_all_delta_ids(2)
all_ids.each { |ids|
  coordinates = GeoDelta::DeltaGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

  svg.polygon(
    "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
    "class"  => "b")

  svg.text(
    ids.join(","),
    "x"         => coordinates[0][0],
    "y"         => coordinates[0][1],
    "font-size" => "1.5")
}

svg.rect(
  "x"            => x1,
  "y"            => -y1,
  "width"        => dx,
  "height"       => dy,
  "fill"         => "none",
  "stroke"       => "red",
  "stroke-width" => 0.1)

File.open("random_region_level2.svg", "wb") { |file|
  svg.write(file)
}
