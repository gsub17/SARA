package com.example.shubhajitghosh.sara;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public  SliderAdapter (Context context){

        this.context = context;
    }

//arrays

    public  int[] slideimage = {
            R.drawable.sara1,
            R.drawable.sara2,
            R.drawable.sara5,
    };

    public String[] slideheading = {
            "Mapped your shop",
            "Support local business",
            "Grow your business",
    };

    public String[] slidedescription = {
            "Point your talent on a\n"+ "Digital Platform \n" + " show what you are good at",
            "Get timely and good quality items \n" + "  Get Independent ",
            "If you are good at something \n" +
                    " never do it for free"
    };



    @Override
    public int getCount() {
        return slideheading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
           return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater =  (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);


        ImageView slide_image = (ImageView)view.findViewById(R.id.slide_image);
        TextView slide_heading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slidedesc = (TextView)view.findViewById(R.id.slide_desc);


        slide_image.setImageResource(slideimage[position]);
        slide_heading.setText(slideheading[position]);
        slidedesc.setText(slidedescription[position]);


        container.addView(view);

        return  view;
    }
//help , when we reach last page , as we don't want to move further
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout)object);
    }
}
