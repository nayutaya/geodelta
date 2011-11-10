# encoding: utf-8

require_relative "lib/geodelta"
require_relative "lib/geodelta/region"

x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
dx = x2 - x1
dy = y1 - y2

all_ids = []
(0..7).each { |id1|
  (0..3).each { |id2|
    (0..3).each { |id3|
      all_ids << [id1, id2, id3]
    }
  }
}

regional_ids = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, 3)

File.open("random_region_level3.svg", "wb") { |file|
  file.puts(%|<?xml version="1.0" encoding="utf-8"?>|)
  file.puts(%|<svg width="190mm" height="190mm" viewBox="-16.0 -14.0 32.0 28.0" xmlns="http://www.w3.org/2000/svg">|)

  regional_ids.each { |ids|
    coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

    polygon_points = coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" ")
    file.puts(%| <polygon points="#{polygon_points}" fill="green" stroke="none" />|)
  }

  all_ids.each { |ids|
    coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

    polygon_points = coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" ")
    file.puts(%| <polygon points="#{polygon_points}" fill="none" stroke="black" stroke-width="0.05" />|)

    label_x    = coordinates[0][0]
    label_y    = coordinates[0][1]
    label_text = ids.join(",")
    file.puts(%| <text x="#{label_x}" y="#{label_y}" font-size="0.75" text-anchor="middle" dominant-baseline="central">#{label_text}</text>|)
  }

  file.puts(%| <rect x="#{x1}" y="#{-y1}" width="#{dx}" height="#{dy}" fill="none" stroke="red" stroke-width="0.1" />|)
  file.puts(%|</svg>|)
}
