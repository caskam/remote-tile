package com.github.franciscan.remotetile.cyanogenmod;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.github.franciscan.remotetile.activities.DialogActivity;
import com.github.franciscan.remotetile.MainActivity;
import com.github.franciscan.remotetile.R;
import com.github.franciscan.remotetile.model.Command;
import com.github.franciscan.remotetile.utils.Constant;
import com.github.franciscan.remotetile.utils.Datastore;

import java.util.ArrayList;

import cyanogenmod.app.CMStatusBarManager;
import cyanogenmod.app.CustomTile;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class TilesReceiver extends BroadcastReceiver {

    private TileThread thread = new TileThread();

    @Override
    public void onReceive(Context context, Intent intent) {

        String event, data;

        if (intent.getExtras() != null && (event = intent.getStringExtra(Constant.EVENT_EXTRA)) != null) {
            data = intent.getStringExtra(Constant.DATA_EXTRA);
            thread.send(event, data);
            return;
        }

        ArrayList<CustomTile.ExpandedListItem> list = new ArrayList<>();

        int i = 0;

        for (Command command : Datastore.getInstance().getCommands()) {
            CustomTile.ExpandedListItem item = new CustomTile.ExpandedListItem();
            item.setExpandedListItemTitle(command.getEvent());
            item.setExpandedListItemSummary(command.getData());
            item.setExpandedListItemDrawable(command.getIcon());
            item.setExpandedListItemOnClickIntent(createIntent(context, i++, command));
            list.add(item);
        }

        CustomTile.ListExpandedStyle style = new CustomTile.ListExpandedStyle();
        style.setListItems(list);

        // build tile
        CustomTile customTile = new CustomTile.Builder(context)
                .setExpandedStyle(style)
                .shouldCollapsePanel(true)
                .setOnSettingsClickIntent(new Intent(context, MainActivity.class))
                .setLabel("Remote Tile")
                .setIcon(R.drawable.ic_desktop_windows_white_24dp)
                .build();

        CMStatusBarManager.getInstance(context).publishTile(0, customTile);


    }

    /**
     * Create a Pending Intent from a command string
     */

    private PendingIntent createIntent(Context ctx, int i, Command command) {
        boolean ask = command.getData().equals("ask");
        Intent intent = ask ? new Intent(ctx, DialogActivity.class) : new Intent();
        intent.setAction(Constant.UPDATE_TILE);
        intent.putExtra(Constant.EVENT_EXTRA, command.getEvent());
        intent.putExtra(Constant.DATA_EXTRA, command.getData());
        return ask ? PendingIntent.getActivity(ctx, i, intent, PendingIntent.FLAG_UPDATE_CURRENT) : PendingIntent.getBroadcast(ctx, i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
