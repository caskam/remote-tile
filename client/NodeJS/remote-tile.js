var net = require('net');

/**
 *  The MIT License (MIT)
 *  Copyright (c) 2015 Francesco Cannizzaro
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 *  to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 *  and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

var mod = {};
mod.values = {};
mod.functions = {};

/**
 * set the port of socket
 * [default] 4545
 */
mod.port = function(port) {
	mod.values.port = port;
	return mod;
}

/**
 * add new function by key
 * throws Error if second argument is not a function
 */
mod.add = function(key, fn) {
	if (typeof fn === 'function') {

		if (mod.functions[key])
			throw new Error("Event already defined [" + key + "]");

		mod.functions[key] = fn;
		return mod;
	}
	else
		throw new Error("Second Argument is not a function [" + key + "]");
}

/**
 * Enable/Disable verbose  logs
 * [default] false
 */
mod.logs = function(enabled) {
	mod.values.verbose = enabled == true;
	return mod;
}

/**
 * Add leading zeros
 */
var pad = function(num) {
	var s = num + "";
	while (s.length < 2) s = "0" + s;
	return s;
}

/**
 * Get TimeStamp
 */
var getTimeStamp = function() {
	var currentdate = new Date();
	return pad(currentdate.getHours()) + ":" + pad(currentdate.getMinutes()) + ":" + pad(currentdate.getSeconds());
}

/**
 * Start socket
 */
mod.start = function() {

	var socket = net.createServer(function(socket) {

		socket.on('data', function(key) {

			var match = /(.*):(.*)/.exec(key.toString());

			if (!match && mod.values.verbose)
				console.log("Command string malformed.");

			else {

				if (mod.values.verbose)
					console.log("$ " + getTimeStamp() + " [event=" + match[1] + (match[2] ? "] [data=" + match[2] + "]" : "]"));

				var fn = mod.functions[match[1]];
				if (fn) fn(match[2]);

			}
		});
	});

	socket.listen(mod.values.port || 4545);

	return mod;
}

module.exports = mod;
