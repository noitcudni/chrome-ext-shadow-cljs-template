if (typeof goog === "undefined") {
  var goog = {};
}

goog.isBoolean = function(x) {
  return typeof x === "boolean";
};

goog.isFunction = function(x) {
  return typeof x === "function";
};
