# Remote-Tile
Cyanogenmod Custom Tile (Server + [Android](#custom-tile))

[![release](https://img.shields.io/badge/Latest%20Release-1.0-brightgreen.svg)](https://github.com/FrancisCan/Remote-Tile/releases/tag/1.0.0)

![logo](https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/android/app/src/main/res/drawable-xhdpi/ic_launcher.png)

## Description
Execute custom script sending "*event*" and "*args*" to your **pc** / **raspberry** / **server** only tapping a **tile**!

Don't ***waste*** your time :)

## Server
- [x] [**NodeJS**](https://github.com/FrancisCan/Remote-Tile/tree/master/server/NodeJS)
- [ ] Python

## Custom Tile

### Setup
- install the apk (*or build*) [Release 1.0.0](https://github.com/FrancisCan/Remote-Tile/releases/tag/1.0.0)
- open settings and fill **IP ADDRESS** and **PORT**, *default = 4545*
- add your custom command

### Features
- Remote Tile is created at boot
- Autoupdate for each change in the app (*delete,update,add command*)
- Almost [1000 icons](https://design.google.com/icons/) (category based)
- Dinamic input asking using **ask** as "data/args" (*see last screenshot*)

### Screenshot
<img src="https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/android/screenshot/main.png" width=300 />
<img src="https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/android/screenshot/editor.png" width=300 />
<img src="https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/android/screenshot/tiles.png" width=300 />
<img src="https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/android/screenshot/tile_expanded.png" width=300 />
<img id="ask" src="https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/android/screenshot/ask.png" width=300 />

## License
MIT Francesco Cannizzaro
