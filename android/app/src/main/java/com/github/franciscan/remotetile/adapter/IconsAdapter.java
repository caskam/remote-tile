package com.github.franciscan.remotetile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.franciscan.remotetile.R;

import java.util.List;


/**
 * Created by Francesco Cannizzaro on 31/01/2016.
 */
public class IconsAdapter extends ArrayAdapter<Integer>{

    private ImageView icon;
    private MaterialDialog dialog;

    public IconsAdapter(Context context, List<Integer> data, ImageView icon,MaterialDialog dialog) {
        super(context, 0, data);
        this.icon = icon;
        this.dialog = dialog;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final int id = getItem(position);

        if (v == null)
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_icon, parent, false);

        // find views
        ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_icon);
        ivIcon.setImageResource(id);

        // listener
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icon.setImageResource(id);
                icon.setTag(id);
                dialog.dismiss();
            }
        });

        return v;
    }


}
