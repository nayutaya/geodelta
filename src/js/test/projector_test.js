
function test_namespace() {
  assertNotUndefined(geodelta);
  assertNotUndefined(geodelta.projector);
}

function test_latToMy() {
  assertRoughlyEquals(+1.0, geodelta.projector.latToMy(+85.0511), 1.0E-5);
  assertEquals(0.0, geodelta.projector.latToMy(0.0));
  assertRoughlyEquals(-1.0, geodelta.projector.latToMy(-85.0511), 1.0E-5);
}

function test_lngToMx() {
  assertEquals(+1.0, geodelta.projector.lngToMx(+180.0));
  assertEquals(+0.5, geodelta.projector.lngToMx( +90.0));
  assertEquals( 0.0, geodelta.projector.lngToMx(   0.0));
  assertEquals(-0.5, geodelta.projector.lngToMx( -90.0));
  assertEquals(-1.0, geodelta.projector.lngToMx(-180.0));
}
