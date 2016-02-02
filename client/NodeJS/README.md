# Remote-Tile
Cyanogenmod Custom Tile (NodeJS Client) [see all project](https://github.com/FrancisCan/Remote-Tile/)

- install ```npm install remote-tile```
- create your custom events
- run yout node script

#### Methods

##### port(Number port)
setup the listening port for the socket

##### logs(Boolean enabled)
enable/disable verbose logs like
```sh
$ 09:50:22 [event=test] [data=hello]
$ 09:50:29 [event=vlc] [data=play/pause]
```
##### add(String event , Function fn)
Pass a function to run when receive an "event"
*throws Error if fn is not a function*
*throws Error if event is already defined*

##### start()
start the socket
*throws Error if port is already used*
