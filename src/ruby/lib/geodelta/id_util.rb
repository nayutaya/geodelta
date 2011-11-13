# encoding: utf-8

module GeoDelta
  module IdUtil
    def self.get_all_delta_ids(level)
      return (0..7).map { |id| [id] } if level == 1
      return (0..7).to_a.product(*([(0..3).to_a] * (level - 1)))
    end
  end
end
