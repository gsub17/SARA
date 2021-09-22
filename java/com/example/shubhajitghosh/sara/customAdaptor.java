package com.example.shubhajitghosh.sara;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class customAdaptor extends ArrayAdapter{
    int resource;
    LinearLayout linearLayout;
    CardView cardView;
    Context context;
    ArrayList<Model> arrayList;


    public customAdaptor(@NonNull Context context, int resource, @NonNull ArrayList arrayList) {
        super(context, resource, arrayList);
        this.arrayList=arrayList;
        this.context=context;
        this.resource=resource;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(resource,null,false);

        CircleImageView profile=view.findViewById(R.id.profile_image);
        TextView cat=view.findViewById(R.id.categories);

        final Model model=arrayList.get(position);
        //cat.setText("err");
        Log.e("12346", "getView: "+cat );

        profile.setImageResource(model.getImage());
        cat.setText(model.getCategories());

        linearLayout=view.findViewById(R.id.linearlayout);
        cardView=view.findViewById(R.id.card);


        Animation animation = AnimationUtils.loadAnimation(context,R.anim.slide_left);
        view.startAnimation(animation);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(),recycleviewcategory.class);
                intent.putExtra("categories",model.getCategories());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                //Toast.makeText(context,""+model.getCategories(),Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}




