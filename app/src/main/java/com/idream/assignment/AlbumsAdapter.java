package com.idream.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>  {

    private Context mContext;
    private ArrayList<Album> albumList;
    LayoutInflater inflater;





    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //  public TextView title, count;
        public TextView title;
        public ImageView thumbnail, overflow;
        ItemClickListener itemClickListener;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
           // count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            view.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }


    public AlbumsAdapter(Context mContext, ArrayList<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card1, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Album album = albumList.get(position);
        holder.title.setText(album.getName());
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);



        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                albumList.get(pos).getId().equals(album.getId());
                    Intent i=new Intent(mContext, Main2Activity.class);
//                Toast.makeText(mContext,album.getId(), Toast.LENGTH_LONG).show();
                    i.putExtra("categoryId", album.getId());
                    i.putExtra("actionBarTitle", album.getName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(i);

                }

        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

