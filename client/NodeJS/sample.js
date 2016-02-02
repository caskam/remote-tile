var rTile = require("./remote-tile");

rTile
	.port(8080)
	.logs(true)
	.add("standby", function(args) {
		pcUtils.exec("rundll32.exe powrprof.dll,SetSuspendState 0,1,0");
	})
	.add("shutdown", function(args) {
		pcUtils.exec("Shutdown.exe -s -t " + (!args || args == 'now' ? "00" : args));
	})
	.start();
