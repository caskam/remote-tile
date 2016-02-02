package com.github.franciscan.remotetile.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.franciscan.remotetile.R;
import com.github.franciscan.remotetile.adapter.IconsAdapter;
import com.github.franciscan.remotetile.utils.Constant;
import com.github.franciscan.remotetile.utils.Datastore;
import com.github.franciscan.remotetile.utils.Icons;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class Editor extends Fragment implements View.OnClickListener, DialogInterface.OnDismissListener {

    private static HashMap<String, List<Integer>> icons = null;
    private int icon = R.drawable.ic_info_outline_white_24dp;
    private MaterialDialog dialog, dialogChooser;
    private GridView gvIcons;
    private ImageView ivIcon;
    private String event, data;
    private IconsAdapter adapter;
    private EditText evEvent, evData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editor, container, false);

        // find views
        evEvent = (EditText) v.findViewById(R.id.ev_event);
        evData = (EditText) v.findViewById(R.id.ev_data);
        TextView tvEvent = (TextView) v.findViewById(R.id.event);
        TextView tvData = (TextView) v.findViewById(R.id.data);
        ivIcon = (ImageView) v.findViewById(R.id.icon);

        // link with listener
        v.findViewById(R.id.btn_icon).setOnClickListener(this);
        v.findViewById(R.id.fab).setOnClickListener(this);
        link(evData, tvData);
        link(evEvent, tvEvent);


        // find & fill with current values
        if (getArguments() != null) {
            event = getArguments().getString(Constant.EVENT_EXTRA);
            data = getArguments().getString(Constant.DATA_EXTRA);
            icon = getArguments().getInt(Constant.ICON_EXTRA);
            evData.setText(data);
            tvData.setText(data);
            evEvent.setText(event);
            tvEvent.setText(event);
            if (icon != 0) {
                ivIcon.setImageResource(icon);
                ivIcon.setTag(icon);
            }
        }

        // create icons chooser
        dialog = new MaterialDialog.Builder(getActivity())
                .customView(R.layout.content_dialog, false)
                .dismissListener(Editor.this)
                .backgroundColorRes(R.color.dark_background)
                .build();

        assert dialog.getCustomView() != null;
        gvIcons = (GridView) dialog.getCustomView().findViewById(R.id.gv_icons);

        dialogChooser = new MaterialDialog.Builder(getActivity())
                .title("Choose Icon Category")
                .items(Icons.categories)
                .itemsColorRes(android.R.color.white)
                .titleColorRes(android.R.color.white)
                .backgroundColorRes(R.color.dark_background)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog d, View itemView, int which, CharSequence text) {
                        generateIconsIdentifiers();
                        String key = text.toString();
                        adapter = new IconsAdapter(getActivity(), icons.get(key), ivIcon, dialog);
                        gvIcons.setAdapter(adapter);
                        dialog.show();
                    }
                })
                .build();


        new Thread(new Runnable() {
            @Override
            public void run() {
                generateIconsIdentifiers();
            }
        }).start();

        return v;
    }

    /**
     * Link Editext changes to TextView text     *
     */
    private void link(EditText ev, final TextView tv) {
        ev.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv.setText(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /**
     * Create Hashmap (category based) of drawable id from drawable strings
     */
    private void generateIconsIdentifiers() {

        if (icons != null)
            return;

        int id;
        icons = new HashMap<>();

        for (int j = 0; j < Icons.get.length; j++) {
            List<Integer> list = new LinkedList<>();

            for (int i = 0; i < Icons.get[j].length; i++)
                if ((id = getResources().getIdentifier(Icons.get[j][i], "drawable", "com.github.franciscan.remotetile")) != 0)
                    list.add(id);

            icons.put(Icons.categories[j], list);
        }
    }

    /**
     * Get String from EditText
     */
    private String getText(EditText e) {
        return e.getText().toString();
    }

    /**
     * Save command
     */
    private void save() {

        Datastore.getInstance().remove(event, data);

        String event = getText(evEvent),
                data = getText(evData);

        if (event.isEmpty())
            return;

        Datastore.getInstance().add(event, data, icon);
        getActivity().onBackPressed();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab)
            save();
        else if (dialogChooser != null)
            dialogChooser.show();
    }

    /**
     * Get last icon id after chooser dialog is closed
     */
    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (ivIcon.getTag() != null)
            icon = (Integer) ivIcon.getTag();
    }
}
