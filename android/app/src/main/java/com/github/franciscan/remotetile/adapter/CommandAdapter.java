package com.github.franciscan.remotetile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.franciscan.remotetile.MainActivity;
import com.github.franciscan.remotetile.R;
import com.github.franciscan.remotetile.model.Command;
import com.github.franciscan.remotetile.utils.Datastore;

import java.util.List;


/**
 * Created by Francesco Cannizzaro on 31/01/2016.
 */
public class CommandAdapter extends ArrayAdapter<Command> {

    public CommandAdapter(Context context, List<Command> data) {
        super(context, 0, data);
    }


    @Override
    public View getView(final int position, View v, ViewGroup parent) {

        final Command command = getItem(position);

        if (v == null)
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_command, parent, false);

        // find views
        ImageView ivIcon = (ImageView) v.findViewById(R.id.icon);
        TextView tvEvent = (TextView) v.findViewById(R.id.event);
        TextView tvData = (TextView) v.findViewById(R.id.data);

        // setup
        ivIcon.setImageResource(command.getIcon());
        tvEvent.setText(command.getEvent());
        tvData.setText(command.getData().isEmpty() ? " -> no args" : command.getData());

        final MainActivity activity = (MainActivity) getContext();

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.updateCommand(command);
            }
        });


        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Datastore.getInstance().remove(command.getEvent(), command.getData());
                remove(command);
                notifyDataSetChanged();
                activity.updateTile();
                return false;
            }
        });

        return v;
    }

}
