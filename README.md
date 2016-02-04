# Remote-Tile
Cyanogenmod Custom Tile (Server + [Android](#custom-tile))

[![release](https://img.shields.io/badge/Latest%20Release-1.0.1-brightgreen.svg)](https://github.com/FrancisCan/Remote-Tile/releases/tag/1.0.1)

![logo](https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/android/app/src/main/res/drawable-xhdpi/ic_launcher.png)

## Description
Execute custom script sending "*event*" and "*args*" to your **pc** / **raspberry** / **server** only tapping a **tile**!

Don't ***waste*** your time :)

## Server
- [x] [**NodeJS**](https://github.com/FrancisCan/Remote-Tile/tree/master/server/NodeJS)
- [ ] Python

# Custom Tile Cyanogenmod 12.1+

## Setup
- install the apk (*or build*) [Release 1.0.1](https://github.com/FrancisCan/Remote-Tile/releases/tag/1.0.1)
- open settings and fill **IP ADDRESS** and **PORT**, *default = 4545*
- add your custom command

## Features
- Remote Tile is created at boot time
- Autoupdate for each change in the app (*delete,update,add command*)
- Almost [1000 icons](https://design.google.com/icons/) (category based)
- Dinamic input/list

## Command Writing
 - event (*everything you want*)
 - data
    - basic argument
    - **ask** input will be asked on tap
    - **arg1,arg2,arg3** item will be asked on tap (checking regex is ```[^,]+,([^,],?)+[^,]$```)

## Screenshot
<img src="https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/preview/1.png" width=650 />
<img src="https://raw.githubusercontent.com/FrancisCan/Remote-Tile/master/preview/2.png" width=650 />

## License
MIT Francesco Cannizzaro
