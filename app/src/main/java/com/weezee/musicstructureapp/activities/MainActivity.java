package com.weezee.musicstructureapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.weezee.musicstructureapp.R;
import com.weezee.musicstructureapp.adapters.SongsArrayAdapter;
import com.weezee.musicstructureapp.modeling.SongDataModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.weezee.musicstructureapp.Constants.*;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_view_songs)
    ListView listView;
    private ArrayList<SongDataModel> songs = new ArrayList<>();
    private int lastSelectedSongIndex = -1;//no song was pressed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Songs List");
        for (int i = 0; i < 2; i++) {
            songs.add(new SongDataModel("I'll Take Everything", "James Blunt", R.drawable.original_image, "Back To Beldham"));
            songs.add(new SongDataModel("Got to be there", "Michel Jackson", R.drawable.michel_jackson, "GG everywhere"));
            songs.add(new SongDataModel("No tears", "James Blunt", R.drawable.some_kind_of_trouble, "Some kind of trouble"));
            songs.add(new SongDataModel("Dancing", "Gymnastiz", R.drawable.temp_album_art, "Dancing is what to do"));
            songs.add(new SongDataModel("Triangles everywhere", "Mr. Geometry", R.drawable.triangle, "Prism"));
            /*pics taken from:
            1-https://laughingsquid.com/famous-music-album-covers-reimagined-with-comic-superheroes/
            2-https://www.ranker.com/crowdranked-list/greatest-album-covers
            3-https://en.wikipedia.org/wiki/Some_Kind_of_Trouble
            4-https://www.billboard.com/photos/6715351/best-album-covers-of-all-time
            5-https://wallpapercave.com/album-cover-wallpaper
            6-https://www.pixelstalk.net/download-free-album-cover-background/
             */

        }
        SongsArrayAdapter adapter = new SongsArrayAdapter(this, songs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, NowPlayingActivity.class);
                intent.putExtra(KEY_LIST_OF_SONGS, songs);
                intent.putExtra(KEY_SELECTED_SONG_INDEX, i);
                lastSelectedSongIndex = i;
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.go_to_now_playing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.go_to_now_playing_action_button:
                case R.id.nowPlaying:
                Intent intent = new Intent(MainActivity.this, NowPlayingActivity.class);
                intent.putExtra(KEY_LIST_OF_SONGS, songs);
                intent.putExtra(KEY_SELECTED_SONG_INDEX, lastSelectedSongIndex);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
