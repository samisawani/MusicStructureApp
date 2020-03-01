package com.weezee.musicstructureapp.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.weezee.musicstructureapp.CurrentlyPlayingFragment;
import com.weezee.musicstructureapp.modeling.SongDataModel;

import java.util.ArrayList;

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    private  SongDataModel[] listOfSongDataModelObjects;
    private CurrentlyPlayingFragment[] listOfFragmentsAdapterHas;


    public CustomFragmentPagerAdapter(FragmentManager fm, ArrayList<SongDataModel> arrayList) {
        super(fm);
        this.listOfSongDataModelObjects = arrayList.toArray(new SongDataModel[arrayList.size()]);
        this.listOfFragmentsAdapterHas =new CurrentlyPlayingFragment[arrayList.size()];
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        listOfFragmentsAdapterHas[position]=(CurrentlyPlayingFragment) super.instantiateItem(container,position );
        return super.instantiateItem(container,position );
    }

    @Override
    public Fragment getItem(int i) {
        return returnFullyInitializedNowPlayingFragmentBasedOnIndex(i);
    }

    @Override
    public int getCount() {
        return listOfSongDataModelObjects.length;
    }


    private CurrentlyPlayingFragment returnFullyInitializedNowPlayingFragmentBasedOnIndex(int i) {
         SongDataModel song= listOfSongDataModelObjects[i];
         CurrentlyPlayingFragment currentlyPlayingFragment =new CurrentlyPlayingFragment();
         currentlyPlayingFragment.createBundleWithValuesAndSendItToCurrentFragment(song.getAlbumArtResId(),
                song.getTitle(),song.getArtist(),song.getAlbumTitle());
         listOfFragmentsAdapterHas[i]= currentlyPlayingFragment;
        return currentlyPlayingFragment;
    }


}
