package com.example.android.mymusicapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SmallItemsAdapter extends RecyclerView.Adapter<SmallItemsAdapter.ViewHolder> {

    private SmallItemsData[] itemsData;

    public SmallItemsAdapter(SmallItemsData[] itemsData) {
        this.itemsData = itemsData;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SmallItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.small_items, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        viewHolder.txtViewArtist.setText(itemsData[position].getArtist());
        viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageID());
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public TextView txtViewArtist;
        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.album_title);
            txtViewArtist = (TextView) itemLayoutView.findViewById(R.id.album_artist);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.album_image);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.length;
    }
}
