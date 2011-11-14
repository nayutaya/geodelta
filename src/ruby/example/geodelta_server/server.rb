# encoding: utf-8

require "sinatra/base"
require "json"
require "stringio"

require_relative "../../lib/geodelta"
require_relative "../../lib/geodelta/region"
require_relative "../../lib/geodelta/id_util"
require_relative "../svg"

class GeoDeltaServer < Sinatra::Base
  set :public_folder, File.dirname(__FILE__) + "/public"

  get "/api/encode" do
    lat   = (params["lat"]   || "0.0").to_f
    lng   = (params["lng"]   || "0.0").to_f
    level = (params["level"] || "1"  ).to_i

    code        = GeoDelta.get_delta_code(lat, lng, level)
    coordinates = GeoDelta.get_coordinates_from_code(code)

    response = {
      "request" => {
        "lat"   => lat,
        "lng"   => lng,
        "level" => level,
      },
      "response" => {
        "code"        => code,
        "coordinates" => coordinates.map { |lat, lng|
          {"lat" => lat, "lng" => lng}
        },
      },
    }

    content_type(:json)
    return JSON.dump(response)
  end

  get "/demo/delta_level2.svg" do
    level = 2

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-16.0 -14.0 32.0 28.0")
    svg.style("polygon", "fill" => "none", "stroke" => "black", "stroke-width" => "0.05")
    svg.style("text", "text-anchor" => "middle", "dominant-baseline" => "central")

    all_ids = GeoDelta::IdUtil.get_all_delta_ids(level)
    all_ids.each { |ids|
      coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon("points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "))
      svg.text(
        ids.join(","),
        "x"         => coordinates[0][0],
        "y"         => coordinates[0][1],
        "font-size" => "2.0")
    }

    io = StringIO.new
    svg.write(io)

    content_type("image/svg+xml")
    return io.string
  end

  get "/demo/random_region_delta_level2.svg" do
    level = 2
    x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
    y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
    dx = x2 - x1
    dy = y1 - y2

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-16.0 -14.0 32.0 28.0")
    svg.style("polygon.a", "fill" => "green", "stroke" => "none")
    svg.style("polygon.b", "fill" => "none", "stroke" => "black", "stroke-width" => "0.05")
    svg.style("text", "text-anchor" => "middle", "dominant-baseline" => "central")

    regional_ids = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, level)
    regional_ids.each { |ids|
      coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "a")
    }

    all_ids = GeoDelta::IdUtil.get_all_delta_ids(level)
    all_ids.each { |ids|
      coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

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

    io = StringIO.new
    svg.write(io)

    content_type("image/svg+xml")
    return io.string
  end

  get "/demo/random_region_delta_level3.svg" do
    level = 3
    x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
    y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
    dx = x2 - x1
    dy = y1 - y2

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-16.0 -14.0 32.0 28.0")
    svg.style("polygon.a", "fill" => "green", "stroke" => "none")
    svg.style("polygon.b", "fill" => "none", "stroke" => "black", "stroke-width" => "0.05")
    svg.style("text", "text-anchor" => "middle", "dominant-baseline" => "central")

    regional_ids = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, level)
    regional_ids.each { |ids|
      coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "a")
    }

    all_ids = GeoDelta::IdUtil.get_all_delta_ids(level)
    all_ids.each { |ids|
      coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "b")

      svg.text(
        ids.join(","),
        "x"         => coordinates[0][0],
        "y"         => coordinates[0][1],
        "font-size" => "0.75")
    }

    svg.rect(
      "x"            => x1,
      "y"            => -y1,
      "width"        => dx,
      "height"       => dy,
      "fill"         => "none",
      "stroke"       => "red",
      "stroke-width" => 0.1)

    io = StringIO.new
    svg.write(io)

    content_type("image/svg+xml")
    return io.string
  end

  get "/demo/random_region_delta_level4.svg" do
    level = 4
    x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
    y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
    dx = x2 - x1
    dy = y1 - y2

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-16.0 -14.0 32.0 28.0")
    svg.style("polygon.a", "fill" => "green", "stroke" => "none")
    svg.style("polygon.b", "fill" => "none", "stroke" => "black", "stroke-width" => "0.05")
    svg.style("text", "text-anchor" => "middle", "dominant-baseline" => "central")

    regional_ids = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, level)
    regional_ids.each { |ids|
      coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "a")
    }

    all_ids = GeoDelta::IdUtil.get_all_delta_ids(level)
    all_ids.each { |ids|
      coordinates = GeoDelta::Geometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "b")

      svg.text(
        ids.join(","),
        "x"         => coordinates[0][0],
        "y"         => coordinates[0][1],
        "font-size" => "0.25")
    }

    svg.rect(
      "x"            => x1,
      "y"            => -y1,
      "width"        => dx,
      "height"       => dy,
      "fill"         => "none",
      "stroke"       => "red",
      "stroke-width" => 0.1)

    io = StringIO.new
    svg.write(io)

    content_type("image/svg+xml")
    return io.string
  end
end
