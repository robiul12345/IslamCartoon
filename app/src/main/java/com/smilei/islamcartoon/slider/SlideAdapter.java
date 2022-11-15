package com.smilei.islamcartoon.slider;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.smilei.islamcartoon.ActivityDeginSecendPaje;
import com.smilei.islamcartoon.R;
import com.smilei.islamcartoon.ScendActivityYoutubePlayActivity;

import java.util.List;

public class SlideAdapter extends SliderViewAdapter<SlideAdapter.SliderViewHolder> {

    private int[] image;
    Context context;

    public SlideAdapter(int[] image, Context context) {
        this.image = image;
        this.context = context;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {

        viewHolder.imageView.setImageResource(image[position]);
        viewHolder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ScendActivityYoutubePlayActivity.class));
            }
        });
    }

    @Override
    public int getCount() {
        return image.length;
    }

    class SliderViewHolder extends SliderViewAdapter.ViewHolder{
        FrameLayout frameLayout;
        ImageView imageView;
        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_auto_image_slider);
            frameLayout=itemView.findViewById(R.id.frameLayoutId);
        }
    }

}
