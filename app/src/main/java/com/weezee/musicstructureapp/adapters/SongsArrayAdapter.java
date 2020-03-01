package com.weezee.musicstructureapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weezee.musicstructureapp.R;
import com.weezee.musicstructureapp.modeling.SongDataModel;

import java.util.ArrayList;
import java.util.List;

public class SongsArrayAdapter extends ArrayAdapter<SongDataModel> {
    private Context context;
    private ArrayList<SongDataModel> listOfSongs;

    public SongsArrayAdapter(Context context, List<SongDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.listOfSongs=(ArrayList<SongDataModel>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final SongDataModel currentSongItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.song_item_list, parent, false);
            TextView title = convertView.findViewById(R.id.song_list_item__title);
            TextView artist = convertView.findViewById(R.id.song_list_item_artist);
            ImageView albumArt = convertView.findViewById(R.id.song_list_item_album_art);
            viewHolder = new ViewHolder(title, artist, albumArt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.songListItemTitle.setText(currentSongItem.getTitle());
        viewHolder.songListItemArtist.setText(currentSongItem.getArtist());
        viewHolder.songListItemAlbumArt.setImageDrawable(viewHolder.songListItemAlbumArt.getResources().getDrawable(currentSongItem.getAlbumArtResId()));
        return convertView;
    }

    public ArrayList<SongDataModel> getListOfSongs() {
        return listOfSongs;
    }

    private class ViewHolder {
        private TextView songListItemTitle;
        private TextView songListItemArtist;
        private ImageView songListItemAlbumArt;

        public ViewHolder(TextView songListItemTitle, TextView songListItemArtist, ImageView songListItemAlbumArt) {
            this.songListItemTitle = songListItemTitle;
            this.songListItemArtist = songListItemArtist;
            this.songListItemAlbumArt = songListItemAlbumArt;
        }
    }


}
