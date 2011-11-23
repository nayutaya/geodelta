# encoding: utf-8

require "sinatra/base"
require "json"
require "stringio"
require "set"

require_relative "../../lib/geodelta"
require_relative "../../lib/geodelta/hex_geometry"
require_relative "../../lib/geodelta/region"
require_relative "../../lib/geodelta/hex_region"
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

  get "/api/get_all_deltas" do
    north = (params["north"] || "0.0").to_f
    south = (params["south"] || "0.0").to_f
    west  = (params["west"]  || "0.0").to_f
    east  = (params["east"]  || "0.0").to_f
    level = (params["level"] || "1"  ).to_i

    x1, y1 = GeoDelta::Projector.latlng_to_nxy(north, west)
    x2, y2 = GeoDelta::Projector.latlng_to_nxy(south, east)

    ids_list = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, level)

    response = {
      "request" => {
        "north" => north,
        "south" => south,
        "west"  => west,
        "east"  => east,
        "level" => level,
      },
      "response" => {
        "deltas" => ids_list.map { |ids|
          coordinates = GeoDelta.get_coordinates_from_ids(ids)
          {
            "code"        => GeoDelta::Encoder.encode(ids),
            "coordinates" => coordinates.map { |lat, lng|
              {"lat" => lat, "lng" => lng}
            },
          }
        }
      }
    }

    content_type(:json)
    return JSON.dump(response)
  end

  get "/api/get_all_hexes" do
    north = (params["north"] || "0.0").to_f
    south = (params["south"] || "0.0").to_f
    west  = (params["west"]  || "0.0").to_f
    east  = (params["east"]  || "0.0").to_f
    level = (params["level"] || "1"  ).to_i

    x1, y1 = GeoDelta::Projector.latlng_to_nxy(north, west)
    x2, y2 = GeoDelta::Projector.latlng_to_nxy(south, east)

    ids_list = GeoDelta::HexRegion.get_base_delta_ids_in_region(x1, y1, x2, y2, level)

    response = {
      "request" => {
        "north" => north,
        "south" => south,
        "west"  => west,
        "east"  => east,
        "level" => level,
      },
      "response" => {
        "hexes" => ids_list.map { |ids|
          coordinates = GeoDelta::HexGeometry.get_coordinates(ids)
          {
            "code"        => GeoDelta::Encoder.encode(ids),
            "coordinates" => coordinates.map { |x, y|
              lat, lng = GeoDelta::Projector.nxy_to_latlng(x, y)
              {"lat" => lat, "lng" => lng}
            },
          }
        }
      }
    }

    content_type(:json)
    return JSON.dump(response)
  end

  get "/demo/delta.svg" do
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
      coordinates = GeoDelta::DeltaGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

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

  get "/demo/hex.svg" do
    level = (params["level"] || "2").to_i
    font_size =
      case level
      when 2 then 2.0
      when 3 then 0.75
      when 4 then 0.25
      else raise("invalid level")
      end

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-14.0 -14.0 32.0 28.0")
    svg.style("polygon.delta", "fill" => "none", "stroke" => "black", "stroke-width" => "0.05")
    svg.style("polygon.hex", "fill" => "none", "stroke" => "black", "stroke-width" => "0.2")
    svg.style("text", "text-anchor" => "middle", "dominant-baseline" => "central")

    all_delta_ids = GeoDelta::IdUtil.get_all_delta_ids(level)
    all_hex_ids   = all_delta_ids.map { |ids| GeoDelta::HexGeometry.get_base_delta_ids(ids) }.compact.sort.uniq

    all_delta_ids.each { |ids|
      coordinates = GeoDelta::DeltaGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "delta")
      svg.text(
        ids.join(","),
        "x"         => coordinates[0][0],
        "y"         => coordinates[0][1],
        "font-size" => font_size)
    }

    all_hex_ids.each { |ids|
      coordinates = GeoDelta::HexGeometry.get_coordinates(ids) || next
      coordinates.map! { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates.map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "hex")
    }

    content_type(svg.mime_type)
    return svg.to_s
  end

  get "/demo/random_region_delta.svg" do
    level = (params["level"] || "1").to_i
    font_size =
      case level
      when 1 then 4.0
      when 2 then 2.0
      when 3 then 0.75
      when 4 then 0.25
      else raise("invalid level")
      end

    x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
    y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
    dx = x2 - x1
    dy = y1 - y2

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-14.0 -14.0 32.0 28.0")
    svg.style("polygon.a", "fill" => "green", "stroke" => "none")
    svg.style("polygon.b", "fill" => "none", "stroke" => "black", "stroke-width" => "0.05")
    svg.style("text", "text-anchor" => "middle", "dominant-baseline" => "central")

    regional_ids = GeoDelta::Region.get_delta_ids_in_region(x1, y1, x2, y2, level)
    regional_ids.each { |ids|
      coordinates = GeoDelta::DeltaGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates[1, 3].map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "a")
    }

    all_ids = GeoDelta::IdUtil.get_all_delta_ids(level)
    all_ids.each { |ids|
      coordinates = GeoDelta::DeltaGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

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

  get "/demo/random_region_hex.svg" do
    level = (params["level"] || "1").to_i
    font_size =
      case level
      when 1 then 4.0
      when 2 then 2.0
      when 3 then 0.75
      when 4 then 0.25
      else raise("invalid level")
      end

    x1, x2 = [(rand * 24) - 12, (rand * 24) - 12].sort
    y1, y2 = [(rand * 24) - 12, (rand * 24) - 12].sort.reverse
    dx = x2 - x1
    dy = y1 - y2

    svg = SVG.new("width" => "190mm", "height" => "190mm", "viewBox" => "-14.0 -14.0 32.0 28.0")
    svg.style("polygon.a", "fill" => "green", "stroke" => "black", "stroke-width" => "0.2")
    svg.style("polygon.b", "fill" => "none", "stroke" => "black", "stroke-width" => "0.05")
    svg.style("text", "text-anchor" => "middle", "dominant-baseline" => "central")

    regional_hexes = GeoDelta::HexRegion.get_base_delta_ids_in_region(x1, y1, x2, y2, level)
    regional_hexes.each { |ids|
      coordinates = GeoDelta::HexGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

      svg.polygon(
        "points" => coordinates.map { |x, y| "#{x},#{y}" }.join(" "),
        "class"  => "a")
    }

    all_ids = GeoDelta::IdUtil.get_all_delta_ids(level)
    all_ids.each { |ids|
      coordinates = GeoDelta::DeltaGeometry.get_coordinates(ids).map { |x, y| [x, -y] }

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
