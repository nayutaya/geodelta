
var geodelta = geodelta || {};

// デルタID列とGeoDeltaコードを相互変換するモジュール
geodelta.encoder = geodelta.encoder || {};

(function() {
  var WORLD_DELTA_TABLE = ["Z", "Y", "X", "W", "V", "T", "S", "R"];
  var SUB_DELTA_TABLE1  = ["K", "M", "N", "P"];
  var SUB_DELTA_TABLE2  = [["2", "3", "4", "5"], ["6", "7", "8", "A"], ["B", "C", "D", "E"], ["F", "G", "H", "J"]];

  // ワールドデルタIDをエンコードする
  geodelta.encoder.encodeWorldDelta = function(id) {
    if ( id < 0 || id > 7 )
    {
      throw "invalid argument (id)";
    }
    return WORLD_DELTA_TABLE[id];
  };

  // ワールドデルタコードをデコードする
  geodelta.encoder.decodeWorldDelta = function(code) {
    // FIXME: indexOf
    for ( var i = 0, len = WORLD_DELTA_TABLE.length; i < len; i++ )
    {
      if ( WORLD_DELTA_TABLE[i] == code )
      {
        return i;
      }
    }
    // TODO: throw new IllegalArgumentException();
    return null;
  };

  // サブデルタID列をエンコードする
  var _encodeSubDelta = function(ids, start) {
    var result = "";
    for ( var i = start, len = ids.length; i < len; i += 2 )
    {
      var rest = len - i;
      if ( rest == 1 )
      {
        result += SUB_DELTA_TABLE1[ids[i]];
      }
      else
      {
        result += SUB_DELTA_TABLE2[ids[i]][ids[i + 1]]
      }
    }
    // TODO: throw new IllegalArgumentException();
    return result;
  };

  // サブデルタID列をエンコードする
  geodelta.encoder.encodeSubDelta = function(ids) {
    if ( ids == null || ids.length == 0 )
    {
      // TODO: throw new IllegalArgumentException();
      return null;
    }
    return _encodeSubDelta(ids, 0);
  };

  // サブデルタコードをデコードする
  geodelta.encoder.decodeSubDelta = function(code) {
    if ( code == null || code == "" )
    {
      // TODO: throw new IllegalArgumentException();
      return null;
    }

    var ids = [];
    for ( var i = 0, len = code.length; i < len; i++ )
    {
      var ch = code.charAt(i);

      switch ( ch )
      {
        case "2": ids.push(0); ids.push(0); break;
        case "3": ids.push(0); ids.push(1); break;
        case "4": ids.push(0); ids.push(2); break;
        case "5": ids.push(0); ids.push(3); break;
        case "6": ids.push(1); ids.push(0); break;
        case "7": ids.push(1); ids.push(1); break;
        case "8": ids.push(1); ids.push(2); break;
        case "A": ids.push(1); ids.push(3); break;
        case "B": ids.push(2); ids.push(0); break;
        case "C": ids.push(2); ids.push(1); break;
        case "D": ids.push(2); ids.push(2); break;
        case "E": ids.push(2); ids.push(3); break;
        case "F": ids.push(3); ids.push(0); break;
        case "G": ids.push(3); ids.push(1); break;
        case "H": ids.push(3); ids.push(2); break;
        case "J": ids.push(3); ids.push(3); break;
        case "K": ids.push(0); break;
        case "M": ids.push(1); break;
        case "N": ids.push(2); break;
        case "P": ids.push(3); break;
        default: return null; // TODO: throw new IllegalArgumentException();
      }
    }
    return ids;
  };

  // デルタID列をエンコードする
  geodelta.encoder.encode = function(ids)
  {
    if ( ids == null || ids.length == 0 )
    {
      // TODO: throw new IllegalArgumentException();
      return null;
    }

    var code = "";
    code += geodelta.encoder.encodeWorldDelta(ids[0]);
    code += _encodeSubDelta(ids, 1);
    return code;
  };

  // GeoDeltaコードをデコードする
  geodelta.encoder.decode = function(code) {
    if ( code == null || code.length == 0 )
    {
      // TODO: throw new IllegalArgumentException();
      return null;
    }
    else if ( code.length == 1 )
    {
      return [geodelta.encoder.decodeWorldDelta(code.charAt(0))];
    }
    else
    {
      var worldId = geodelta.encoder.decodeWorldDelta(code.charAt(0));
      var subIds  = geodelta.encoder.decodeSubDelta(code.substring(1));
      return [worldId].concat(subIds);
    }
  };
})();
