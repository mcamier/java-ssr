var global = this;
var window = this;
var process = {env: {}};
var print = function() {
    for( var i = 0; i < arguments.length; i++ ) {
       var value = arguments[i];
       java.lang.System.out.print( value );
    }
    java.lang.System.out.println();
}

var console = {};
console.debug = print;
console.warn = print;
console.log = print;