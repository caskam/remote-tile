# Remote-Tile
Cyanogenmod Custom Tile (Client + [Android](#custom-tile))

![logo](https://github.com/FrancisCan/Remote-Tile/blob/master/android/app/src/main/res/drawable-hdpi/ic_launcher.png)

## Client
- [x] [NodeJS](#nodejs)
- [ ] Python

### NodeJS

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

## Custom Tile

### Setup
- install the apk (or build) [Release 1.0.0]()
- open settings and fill **IP ADDRESS** and **PORT**, *default = 4545*
- add your custom command

### Features
- Remote Tile is created at boot
- Autoupdate for each change in the app (*delete,update,add* command)
- Almost [1000 icons](https://design.google.com/icons/) (category based)
- Dinamic input asking using **ask** as "data/args" [see](#ask)

### Screenshoot
![main](https://github.com/FrancisCan/Remote-Tile/blob/master/android/screenshoot/main.png|400)
![editor](https://github.com/FrancisCan/Remote-Tile/blob/master/android/screenshoot/editor.png|400)
![tiles](https://github.com/FrancisCan/Remote-Tile/blob/master/android/screenshoot/tiles.png|400)
![tile expanded](https://github.com/FrancisCan/Remote-Tile/blob/master/android/screenshoot/tile_expanded.png|400)
![ask](https://github.com/FrancisCan/Remote-Tile/blob/master/android/screenshoot/ask.png|400)

## License
MIT Francesco Cannizzaro
