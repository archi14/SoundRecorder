package com.example.architamittal.soundrecorder;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


public class TabFragment extends Fragment {
    String filename;
    int position;
    Chronometer timer;
    OnItemAddedListener listener;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    ImageButton btn;
    TextView mess;
    Button play;
    private static String mFileName = null;
    MediaRecorder mediaRecorder;
    File root = Environment.getExternalStorageDirectory();
    public TabFragment() {

    }
   /* public static Fragment getInstance(int position)
    {
       Bundle bundle = new Bundle();
       bundle.putInt("pos",position);
       TabFragment tabFragment = new TabFragment();
       tabFragment.setArguments(bundle);
       return tabFragment;

    }*/

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_tab, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            Log.d("Tag", "onViewCreated: ");
            functionsforRecord(view);
        /*else
        {
            recyclerAdapter = new RecyclerAdapter();
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setAdapter(recyclerAdapter);

        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnItemAddedListener)context;

    }

    private void functionsforRecord(View view) {
        btn = view.findViewById(R.id.btn);
        mess = view.findViewById(R.id.mess);
        btn.setSelected(false);
        play = view.findViewById(R.id.play);
        timer = view.findViewById(R.id.timer);
        //mFileName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/recording.3gp";
        /*mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
*/

        File file = new File(root.getAbsolutePath()+"/SoundRecorder/Audios");
        if(!file.exists())
        {
            file.mkdirs();
        }
        //mFileName = root.getAbsolutePath()+"/SoundRecorder/Audios/"+String.valueOf(System.currentTimeMillis()+".mp3");
        //Log.d("filename", mFileName);

        /*mediaRecorder.setOutputFile(mFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn.isSelected())
                {
                    Log.d("tag2", "onClick: ");
                    btn.setBackgroundResource(R.drawable.ic_record);
                    mess.setText("Tap the button to start recording");
                    btn.setSelected(false);
                    stopRecording();
                    /*mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder=null;*/
                }else
                {
                    //Log.d("tag3", "onClick: ");
                    btn.setVisibility(View.VISIBLE);
                    btn.setBackgroundResource(R.drawable.ic_stop);
                    //btn.setImageResource(R.drawable.ic_stop);

                    mess.setText("Recording.....");
                    btn.setSelected(true);
                    startRecording();
                    /*try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
*/

                }
            }

        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(mFileName);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.reset();
                        mediaPlayer.release();
                    }
                });
            }
        });


    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
        mediaRecorder=null;
        //timer.stop();
        stoptimer();
        listener.onItemAdded(new SoundFile(filename,root.getAbsolutePath()+"/SoundRecorder/Audios/"+filename));
        play.setVisibility(View.VISIBLE);
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        filename = String.valueOf(System.currentTimeMillis())+".mp3";
        mFileName = root.getAbsolutePath()+"/SoundRecorder/Audios/"+filename;
        Log.d("filename", mFileName);

        mediaRecorder.setOutputFile(mFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            //timer.start();
            starttimer();
            Toast.makeText(getContext(), "Recording", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public interface OnItemAddedListener
    {
        public void onItemAdded(SoundFile soundfile);
    }

    public void starttimer()
    {
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    public void stoptimer()
    {
        timer.stop();
    }
}
