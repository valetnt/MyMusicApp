package com.example.android.mymusicapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LibraryItemsArtistsAdapter
        extends RecyclerView.Adapter<LibraryItemsArtistsAdapter.ViewHolder> {

    private LibraryItemsData[] itemsData;
    private ArrayList<String> ArtistsList;

    public LibraryItemsArtistsAdapter(LibraryItemsData[] itemsData, ArrayList<String> ArtistsList) {
        this.itemsData = itemsData;
        this.ArtistsList = ArtistsList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LibraryItemsArtistsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        viewHolder.txtViewArtist.setText(ArtistsList.get(position));

        int i = 0;

        while (i < itemsData.length) {
            if (itemsData[i].getImageForArtist(ArtistsList.get(position)) == -1) {
                viewHolder.imgViewIcon.setImageResource(R.mipmap.ic_music_note_black_24dp);
                break;
            }
            else if (itemsData[i].getImageForArtist(ArtistsList.get(position)) == 0) {
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
        return ArtistsList.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtViewArtist;
        private ImageView imgViewIcon;

        private ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewArtist = (TextView) itemLayoutView.findViewById(R.id.library_field);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.library_thumb);
        }
    }
}

