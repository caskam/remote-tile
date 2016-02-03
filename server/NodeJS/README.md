# Remote-Tile
Cyanogenmod Custom Tile (NodeJS Server)

![nodep](https://img.shields.io/badge/no-dependencies-brightgreen.svg)

- install ```npm install remote-tile```
- create your custom events
- run your node script

## Usage
```javascript
var remoteTile = require("remote-tile");

remoteTile
	.add("event", function(args){
		console.log(args);
	})
	.start();
```

## Methods

#### port(Number port)
setup the listening port for the socket

#### logs(Boolean enabled)
enable/disable verbose logs like
```sh
$ 09:50:22 [event=test] [data=hello]
$ 09:50:29 [event=vlc] [data=play/pause]
```
#### add(String event , Function fn)
Pass a function to run when receive a custom event

*throws Error if fn is not a function*

*throws Error if event is already defined*

#### start()
start the socket

*throws Error if port is already used*
