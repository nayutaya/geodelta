# encoding: utf-8

require "sinatra"

get "/api/encode" do
  lat   = (params["lat"] || "0.0").to_f
  lng   = (params["lng"] || "0.0").to_f
  level = (params["level"] || "1").to_i
  "encode lat=#{lat} lng=#{lng} level=#{level}"
end
