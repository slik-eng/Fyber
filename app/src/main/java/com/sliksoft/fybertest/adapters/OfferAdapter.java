package com.sliksoft.fybertest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sliksoft.fybertest.R;
import com.sliksoft.fybertest.entities.Offer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Slik.Eng on 05/02/2017.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    Context mContext;
    List<Offer> mOfferArrayList;

    public OfferAdapter(Context context, List<Offer> offerArrayList) {
        this.mContext = context;
        this.mOfferArrayList = offerArrayList;
    }

    @Override
    public OfferAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_offer, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfferAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mOfferArrayList.get(position).getTitle());
        Glide.with(mContext).load(mOfferArrayList.get(position).getThumbnail().getHires()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.mPictureImageView);
    }

    @Override
    public int getItemCount() {
        return mOfferArrayList.size();
    }

    /**
     * View holder for the recycle view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.picture_image_view)
        ImageView mPictureImageView;
        @BindView(R.id.title_text_view)
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mPictureImageView = (ImageView) itemView.findViewById(R.id.picture_image_view);
            this.mTitleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        }
    }
}
