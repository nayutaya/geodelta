# encoding: utf-8

require "sinatra/base"
require "json"

require_relative "../../lib/geodelta"

class GeoDeltaServer < Sinatra::Base
  get "/api/encode" do
    lat   = (params["lat"]   || "0.0").to_f
    lng   = (params["lng"]   || "0.0").to_f
    level = (params["level"] || "1"  ).to_i

    code       = GeoDelta.get_delta_code(lat, lng, level)
    clat, clng = GeoDelta.get_center_latlng_from_delta_code(code)

    response = {
      "request" => {
        "lat"   => lat,
        "lng"   => lng,
        "level" => level,
      },
      "response" => {
        "code"   => code,
        "center" => {"lat" => clat, "lng" => clng},
      },
    }

    content_type(:json)
    return JSON.dump(response)
  end
end
