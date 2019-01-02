package com.example.architamittal.soundrecorder;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TabFragment.OnItemAddedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sound Recoder");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = findViewById(R.id.pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onItemAdded(SoundFile soundfile) {
        String tag = "android:switcher:"+R.id.pager+":"+1;
        SavedRecording fragment = (SavedRecording)getSupportFragmentManager().findFragmentByTag(tag);
        fragment.change(soundfile);

    }
}
