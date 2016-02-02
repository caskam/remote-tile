package com.github.franciscan.remotetile.utils;

import java.util.regex.Pattern;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class Constant {

    // intent extra keys
    public final static String EVENT_EXTRA = "event";
    public final static String DATA_EXTRA = "data";
    public final static String ICON_EXTRA = "icon";

    // properties
    public final static String IP_ADDRESS = "ip-addrees";
    public final static String PORT = "port";

    // intent filter / broadcast
    public final static String UPDATE_TILE = "com.github.franciscan.UPDATE_TILE";

    // regex
    public final static String pattern = "([^:]*):(.*)";
    public final static Pattern regex = Pattern.compile(pattern);

}
