package com.weezee.musicstructureapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.weezee.musicstructureapp.Constants.*;

public class CurrentlyPlayingFragment extends Fragment {
    @BindView(R.id.album_art) ImageView albumArt;
    @BindView(R.id.song_name_textView) TextView songTitle;
    @BindView(R.id.artist_name_textView)TextView artistName;
    @BindView(R.id.album_name_textView) TextView albumName;

    public void createBundleWithValuesAndSendItToCurrentFragment(int albumArtResourceId, String songTitle, String artistName, String albumName) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_SONG_TITLE, songTitle);
        bundle.putInt(KEY_ALBUM_ART_RESOURCE_ID, albumArtResourceId);
        bundle.putString(KEY_ARTIST_NAME, artistName);
        bundle.putString(KEY_ALBUM_NAME, albumName);
        setArguments(bundle);
    }

    public void retrieveValuesFromBundleAndAssignValueToAllNeededFieldsOfCurrentFragment() {
        Bundle bundle = getArguments();
        if (bundle != null) {
           Picasso.get().load(bundle.getInt(KEY_ALBUM_ART_RESOURCE_ID)).into(albumArt);
           //using picasso is more efficient when rendering album arts
           //I could've done it like below:
           //albumArt.setImageDrawable(getResources().getDrawable(bundle.getInt(KEY_ALBUM_ART_RESOURCE_ID )));
           songTitle.setText(bundle.getString(KEY_SONG_TITLE));
           artistName.setText(bundle.getString(KEY_ARTIST_NAME));
           albumName.setText(bundle.getString(KEY_ALBUM_NAME));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.now_playing_fragment,container,false );
        ButterKnife.bind(this,view);
        retrieveValuesFromBundleAndAssignValueToAllNeededFieldsOfCurrentFragment();
        return view;


    }
}
