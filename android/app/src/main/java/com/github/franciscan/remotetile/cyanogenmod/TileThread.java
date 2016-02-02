package com.github.franciscan.remotetile.cyanogenmod;

import com.github.franciscan.remotetile.utils.Constant;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class TileThread extends Thread {

    private String event, data;

    /**
     * Send new command
     */
    public void send(String event, String data) {
        this.event = event;
        this.data = data;
        start();
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(Prefs.getString(Constant.IP_ADDRESS, ""), Prefs.getInt(Constant.PORT, 4545));
            if (s.isConnected()) {
                PrintStream writer = new PrintStream(s.getOutputStream(), true);
                writer.print(event + ":" + data);
                s.shutdownOutput();
                s.close();
            }
        } catch (Exception ignored) {
        }
    }
}
