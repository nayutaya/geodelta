# encoding: utf-8

require "stringio"

class SVG
  def initialize(params = {})
    @params = params
    @styles = []
    @shapes = []
  end

  def style(name, properties)
    props = properties.map { |k, v| %|#{k}: #{v};| }.join(" ")
    @styles << %|#{name} { #{props} }|
    return self
  end

  def text(text, params = {})
    @shapes << %|<text#{attrs(params)}>#{text}</text>|
    return self
  end

  def rect(params = {})
    @shapes << %|<rect#{attrs(params)} />|
    return self
  end

  def polygon(params = {})
    @shapes << %|<polygon#{attrs(params)} />|
    return self
  end

  def write(io)
    io.puts(%|<?xml version="1.0" encoding="utf-8"?>|)
    io.puts(%|<svg#{attrs(@params)} xmlns="http://www.w3.org/2000/svg">|)

    unless @styles.empty?
      io.puts(%| <defs>|)
      io.puts(%|  <style type="text/css">|)
      io.puts(%|   <![CDATA[|)
      @styles.each { |style|
        io.puts(%|    #{style}|)
      }
      io.puts(%|   ]]>|)
      io.puts(%|  </style>|)
      io.puts(%| </defs>|)
    end

    @shapes.each { |shape|
      io.puts(%| #{shape}|)
    }

    io.puts(%|</svg>|)

    return self
  end

  def to_s
    io = StringIO.new
    self.write(io)
    io.string
  end

  def mime_type
    return "image/svg+xml"
  end

  def attrs(params)
    return params.map { |k, v| %| #{k}="#{v}"| }.join
  end
  private :attrs
end
