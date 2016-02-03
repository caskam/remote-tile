package com.github.franciscan.remotetile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.github.franciscan.remotetile.activities.SettingsActivity;
import com.github.franciscan.remotetile.adapter.CommandAdapter;
import com.github.franciscan.remotetile.cyanogenmod.TileReceiver;
import com.github.franciscan.remotetile.fragment.Editor;
import com.github.franciscan.remotetile.model.Command;
import com.github.franciscan.remotetile.utils.Constant;
import com.github.franciscan.remotetile.utils.Datastore;

/**
 * Created by Francesco Cannizzaro on 01/02/2016.
 */
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ListView list;
    private CommandAdapter adapter;
    private Datastore datastore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get datastore instance
        datastore = Datastore.getInstance();

        // find views
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        list = (ListView) findViewById(R.id.list);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transition(new Editor());
            }
        });

        setSupportActionBar(toolbar);
        fillList();

        // create tile
        updateTile();

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0)
            super.onBackPressed();
        else {
            getSupportFragmentManager().popBackStack();
            adapter.notifyDataSetChanged();
            fab.show();
            updateTile();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings)
            startActivity(new Intent(this, SettingsActivity.class));
        return false;
    }

    /**
     * Create adapter and fill it
     */
    private void fillList() {
        adapter = new CommandAdapter(this, datastore.getCommands());
        list.setAdapter(adapter);
    }


    /**
     * create fragment with argument to open a command item
     */
    public void updateCommand(Command command) {
        Editor editor = new Editor();
        Bundle args = new Bundle();
        args.putInt(Constant.ICON_EXTRA, command.getIcon());
        args.putString(Constant.EVENT_EXTRA, command.getEvent());
        args.putString(Constant.DATA_EXTRA, command.getData());
        editor.setArguments(args);
        transition(editor);
    }

    /**
     * Add a new editor fragment with animation
     */
    private void transition(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
        ft.add(R.id.container, fragment).addToBackStack("editor").commit();
        fab.hide();
    }

    /**
     * Send Broadcast to {@link TileReceiver}
     */
    public void updateTile() {
        sendBroadcast(new Intent(Constant.UPDATE_TILE));
    }

}
