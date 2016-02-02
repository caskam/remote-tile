var rTile = require("./remote-tile"),
	exec = require("child_process").exec;

rTile
	.port(8080)
	.logs(true)
	.add("standby", function(args) {
		exec("rundll32.exe powrprof.dll,SetSuspendState 0,1,0");
	})
	.add("shutdown", function(args) {
		exec("Shutdown.exe -s -t " + (!args || args == 'now' ? "00" : args));
	})
	.start();
