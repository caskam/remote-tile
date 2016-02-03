package com.github.franciscan.remotetile.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.franciscan.remotetile.R;
import com.github.franciscan.remotetile.utils.Constant;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class DialogActivity extends AppCompatActivity {

    private String event, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);

        if (getIntent() == null || (event = getIntent().getStringExtra(Constant.EVENT_EXTRA)) == null)
            finish();

        data = getIntent().getStringExtra(Constant.DATA_EXTRA);

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        finish();
                    }
                });

        if (data.contains(","))
            builder.title("Choose")
                    .items(data.split(","))
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            Intent intent = new Intent();
                            intent.setAction(Constant.UPDATE_TILE);
                            intent.putExtra(Constant.EVENT_EXTRA, event);
                            intent.putExtra(Constant.DATA_EXTRA, text.toString());
                            sendBroadcast(intent);
                        }
                    });
        else
            builder.title("Data?")
                    .positiveText("RUN")
                    .input(null, null, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            Intent intent = new Intent();
                            intent.setAction(Constant.UPDATE_TILE);
                            intent.putExtra(Constant.EVENT_EXTRA, event);
                            intent.putExtra(Constant.DATA_EXTRA, input.toString());
                            sendBroadcast(intent);
                        }
                    });


        builder.show();
    }
}
