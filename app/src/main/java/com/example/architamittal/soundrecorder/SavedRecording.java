package com.example.architamittal.soundrecorder;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;


public class SavedRecording extends Fragment {

    RecyclerAdapter recyclerAdapter;
    RecyclerView rv;
    ArrayList<SoundFile> sfiles;
    public SavedRecording() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_saved_recording, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.recyclerView);
        sfiles= fetchRecordings();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerAdapter = new RecyclerAdapter(getContext(),sfiles);
        rv.setAdapter(recyclerAdapter);
    }

    public ArrayList<SoundFile> fetchRecordings()
    {
        sfiles = new ArrayList<>();
        File root = Environment.getExternalStorageDirectory();
        String path = root.getAbsolutePath()+"/SoundRecorder/Audios/";
        Log.d("Files Path", path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d("Files size", String.valueOf(files.length));
        for(int i=0;i<files.length;i++)
        {
            Log.d("files name", files[i].getName());
            String filename = files[i].getName();
            String filepath = path+filename;
            SoundFile soundFile = new SoundFile(filename,filepath);
            sfiles.add(soundFile);
        }
      return sfiles;
    }


}

