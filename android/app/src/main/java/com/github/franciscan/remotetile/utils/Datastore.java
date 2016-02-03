package com.github.franciscan.remotetile.utils;

import com.github.franciscan.remotetile.R;
import com.github.franciscan.remotetile.model.Command;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class Datastore {

    private static Datastore mInstance = new Datastore();
    private List<Command> commands = new ArrayList<>();

    /**
     * Get singletone instance
     */
    public static Datastore getInstance() {
        return mInstance;
    }

    /**
     * Initialize command list from prefeences
     */
    private Datastore() {

        Set<String> keys = Prefs.getAll().keySet();
        Matcher m;

        for (String key : keys)
            if ((m = Constant.regex.matcher(key)).find())
                commands.add(
                        new Command()
                                .withEvent(m.group(1))
                                .withData(m.group(2))
                                .withIcon(Prefs.getInt(key, R.drawable.ic_desktop_windows_white_24dp))
                );

        order();

    }

    /**
     * Reorder List of commands
     */
    private void order() {
        Collections.sort(commands, new Comparator<Command>() {
            @Override
            public int compare(Command t1, Command t2) {
                return t1.getEvent().compareTo(t1.getEvent());
            }
        });
    }

    /**
     * Create and return key string
     */
    public static String key(String event, String key) {
        return event + ":" + key;
    }

    /**
     * Add and save a new command (or update)
     */
    public void add(String event, String data, int icon) {
        Prefs.putInt(key(event, data), icon);
        commands.add(
                new Command()
                        .withEvent(event)
                        .withData(data)
                        .withIcon(icon)
        );
        order();
    }

    /**
     * Remove a command
     */
    public void remove(String event, String data) {

        if (event == null)
            event = "";

        if (data == null)
            data = "";

        String str = key(event, data);

        for (int i = 0; i < commands.size(); i++)
            if (commands.get(i).getAsString().equals(str)) {
                commands.remove(i);
                break;
            }

        Prefs.remove(str);
    }


    /**
     * Get Commands as ArrayList
     */
    public List<Command> getCommands() {
        return commands;
    }
}
