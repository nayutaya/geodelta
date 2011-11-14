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

  get "/demo/delta_level.svg" do
    level = (params["level"] || "1").to_i
    font_size =
      case level
      when 1 then 4.0
      when 2 then 2.0
      when 3 then 0.75
      when 4 then 0.25
      else raise("invalid level")
      end

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-14.0 -14.0 32.0 28.0")
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
        "font-size" => font_size)
    }

    content_type(svg.mime_type)
    return svg.to_s
  end

  get "/demo/random_region_delta_level.svg" do
    level = (params["level"] || "1").to_i
    font_size =
      case level
      when 2 then 2.0
      when 3 then 0.75
      when 4 then 0.25
      else raise("invalid level")
      end

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
        "font-size" => font_size)
    }

    svg.rect(
      "x"            => x1,
      "y"            => -y1,
      "width"        => dx,
      "height"       => dy,
      "fill"         => "none",
      "stroke"       => "red",
      "stroke-width" => 0.1)

    content_type(svg.mime_type)
    return svg.to_s
  end
end
