package com.weezee.musicstructureapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.weezee.musicstructureapp.R;
import com.weezee.musicstructureapp.adapters.CustomFragmentPagerAdapter;
import com.weezee.musicstructureapp.custom_viewPager.ViewPagerWithCustomDuration;
import com.weezee.musicstructureapp.custom_viewPager.ZoomOutPageTransformer;
import com.weezee.musicstructureapp.modeling.SongDataModel;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.weezee.musicstructureapp.Constants.*;

public class NowPlayingActivity extends AppCompatActivity {
    @BindView(R.id.play_pause_button)
    ImageButton playPauseButton;
    @BindView(R.id.next_button)
    ImageButton playNextButton;
    @BindView(R.id.previous_button)
    ImageButton playPreviousButton;
    @BindView(R.id.view_pager)
    ViewPagerWithCustomDuration viewPager;
    private boolean isNotPlaying = true;
    //    private int currentIndex = -1;
    private int maxNumberOfSongs = 0;
    private CustomFragmentPagerAdapter customFragmentPagerAdapter;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageButton button = (ImageButton) view;
            if (button.getId() == R.id.play_pause_button) {
                changePlayPauseButtonState();
            } else {
                goToNextOrPreviousSong(view);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        playNextButton.setOnClickListener(listener);
        playPreviousButton.setOnClickListener(listener);
        playPauseButton.setOnClickListener(listener);
        maxNumberOfSongs = ((ArrayList<SongDataModel>) getIntent().getSerializableExtra(KEY_LIST_OF_SONGS)).size();
        customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), (ArrayList<SongDataModel>) getIntent().getSerializableExtra(KEY_LIST_OF_SONGS));
        viewPager.setAdapter(customFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(8);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());//changing the default animation of the pager
        viewPager.setScrollDurationFactor(2.5);//making animation a bit slower when next/previous buttons pressed so that user can see question being smoothly swiped
        viewPager.setCurrentPlayingSongIndex(getIntent().getIntExtra(KEY_SELECTED_SONG_INDEX, 0));//if no song was selected show the last playing song
        if (viewPager.getCurrentPlayingSongIndex() == -1) {
            viewPager.setCurrentItem(0, true);
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentPlayingSongIndex(), true);

        }
        changePlayPauseButtonState();

    }

    private void changePlayPauseButtonState() {
        if (viewPager.getCurrentPlayingSongIndex() != -1) {
            if (isNotPlaying) {
                playPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                isNotPlaying = false;
            } else {
                playPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
                isNotPlaying = true;
            }
        }

    }

    private void goToNextOrPreviousSong(View nextOrPreviousButton) { //responding to button click event by  going next or previous fragment in viewPager
        if (nextOrPreviousButton.getId() == R.id.next_button) {
            if (viewPager.getCurrentPlayingSongIndex() == -1) {
                viewPager.setCurrentPlayingSongIndex(0);
            } else if (viewPager.getCurrentPlayingSongIndex() != maxNumberOfSongs - 1)
                viewPager.setCurrentPlayingSongIndex(viewPager.getCurrentPlayingSongIndex() + 1);
            viewPager.setCurrentItem(viewPager.getCurrentPlayingSongIndex(), true);
        } else {
            if (viewPager.getCurrentPlayingSongIndex() > 0) {
                viewPager.setCurrentPlayingSongIndex(viewPager.getCurrentPlayingSongIndex() - 1);
                viewPager.setCurrentItem(viewPager.getCurrentPlayingSongIndex(), true);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
