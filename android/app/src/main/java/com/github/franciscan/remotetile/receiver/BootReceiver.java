package com.github.franciscan.remotetile.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.github.franciscan.remotetile.utils.Constant;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.sendBroadcast(new Intent(Constant.UPDATE_TILE));
    }
}
