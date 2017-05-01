package com.example.android.mymusicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LibraryItemsSongsAdapter
        extends RecyclerView.Adapter<LibraryItemsSongsAdapter.ViewHolder> {

    private LibraryItemsData[] itemsData;
    private ArrayList<String> SongsList;

    public LibraryItemsSongsAdapter(LibraryItemsData[] itemsData, ArrayList<String> SongsList) {
        this.itemsData = itemsData;
        this.SongsList = SongsList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LibraryItemsSongsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.library_list_items, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewSong.setText(SongsList.get(position));

        int i = 0;

        while (i < itemsData.length) {
            if (itemsData[i].getImageForSong(SongsList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                break;
            }
            else if (itemsData[i].getImageForSong(SongsList.get(position)) == 0) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                i++;
            } else {
                viewHolder.imgViewIcon.setImageResource(itemsData[i].getImageID());
                break;
            }
        }
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return SongsList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewSong;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewSong = (TextView) itemLayoutView.findViewById(R.id.library_field);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

