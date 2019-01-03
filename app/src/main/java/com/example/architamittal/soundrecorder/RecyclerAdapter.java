package com.example.architamittal.soundrecorder;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<SoundFile> files;
    private Context context;
    RecyclerAdapter.RecyclerViewHolder previousHolder=null;
    MediaPlayer mediaPlayer;
    public RecyclerAdapter(Context context,ArrayList<SoundFile> files)
    {
        this.files=files;
        this.context=context;
    }
    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        Log.d("Adapter", String.valueOf(files.size()));
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.RecyclerViewHolder holder, final int position) {
        final SoundFile file = files.get(position);
        holder.textView.setText(file.getFilename());
        //Log.d("AdapterTag", String.valueOf(holder.);
        holder.listplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.listplay.setVisibility(View.INVISIBLE);
                holder.listpause.setVisibility(View.VISIBLE);
                //String mFileName = file.getFilePath()+file.getFilename();
                //Log.d("Adapter", file.getFilePath());
                Uri uri = Uri.parse(file.getFilePath());
                //Log.d("AdapterUri", uri.toString());
                //Log.d("Adapter", file.getFilePath());
                if(mediaPlayer!=null)
                {
                    Log.d("adapter", "I am in ");
                    Log.d("adapter", String.valueOf(mediaPlayer));
                    Log.d("in", "1");
                    previousHolder.listpause.setVisibility(View.INVISIBLE);
                    previousHolder.listplay.setVisibility(View.VISIBLE);
                    mediaPlayer.release();

                }

                previousHolder = holder;
               mediaPlayer = new MediaPlayer();
                Log.d("in", "2");
   
                try {
                    mediaPlayer.setDataSource(file.getFilePath());
                    mediaPlayer.prepareAsync();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        holder.listpause.setVisibility(View.INVISIBLE);
                        holder.listplay.setVisibility(View.VISIBLE);

                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer=null;
                        Log.d("adapter", String.valueOf(mediaPlayer));
                    }
                });
            }

        });

        holder.listpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.listpause.setVisibility(View.INVISIBLE);
                holder.listplay.setVisibility(View.VISIBLE);
                if(mediaPlayer!=null)
                {
                    mediaPlayer.pause();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageButton listplay,listpause;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            listplay = itemView.findViewById(R.id.listplay);
            listpause = itemView.findViewById(R.id.listpause);
        }

    }

}
