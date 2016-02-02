package com.github.franciscan.remotetile.model;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class Command {

    private int icon;
    private String event;
    private String data;

    /**
     * Set icon as integer resource
     */
    public Command withIcon(int icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Set event of command
     */
    public Command withEvent(String event) {
        this.event = event;
        return this;
    }

    /**
     * Set data (args) of command
     */
    public Command withData(String data) {
        this.data = data;
        return this;
    }

    /**
     * Combine event and data
     */
    public String getAsString() {
        return event + ":" + data;
    }

    /**
     * Get icon as integer resource
     */
    public int getIcon() {
        return icon;
    }

    /**
     * Get Event as String
     */
    public String getEvent() {
        return event;
    }

    /**
     * Get Data as String
     */
    public String getData() {
        return data;
    }

}
