package com.example.shubhajitghosh.sara;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class
RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> imageurl=new ArrayList<>();
    ArrayList<String> phone=new ArrayList<>();
    ArrayList<String> address=new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> condition = new ArrayList<>();
    ArrayList<String> fees = new ArrayList<>();
    ArrayList<String> email=new ArrayList<>();
    ArrayList<Integer> flag=new ArrayList<>();

    Context context;

   public RecyclerAdapter(ArrayList<String> name, ArrayList<String> phone, ArrayList<String> address, ArrayList<String> imageurl,ArrayList<String> description,ArrayList<String> condition,ArrayList<String> fees,ArrayList<String> email,ArrayList<Integer> flag,Context context){

       this.name=name;
       this.phone=phone;
       this.address=address;
       this.context=context;
       this.imageurl=imageurl;
       this.description = description;
       this.condition = condition;
       this.fees = fees;
       this.email=email;
       this.flag=flag;

   }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview,parent,false);




       return new ViewHolder(view);
    }//end of ()....




    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, final int position) {


       if(flag.get(position)==1) {
           holder.card.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   Intent intent = new Intent(context, customerlast.class);
                   intent.putExtra("image", imageurl.get(position));
                   intent.putExtra("name", name.get(position));
                   intent.putExtra("phoneno", phone.get(position));
                   intent.putExtra("address", address.get(position));
                   intent.putExtra("description", description.get(position));
                   intent.putExtra("condition", condition.get(position));
                   intent.putExtra("fees", fees.get(position));
                   intent.putExtra("email", email.get(position));
                   //intent.putExtra("flag",true);
                   context.startActivity(intent);

               }
           });


           holder.txtname.setText("Name:" + name.get(position));
           holder.txtaddress.setText("Address:" + address.get(position));
           holder.txtphone.setText("Phone:" + phone.get(position));
           Picasso.get().load(imageurl.get(position)).into(holder.profile);
       }

       else{
           //Toast.makeText(context,"The service provider is not available right now.",Toast.LENGTH_SHORT).show();
       }
    }//end of ()......

    @Override
    public int getItemCount() {
        return name.size();
    }


        public class ViewHolder extends RecyclerView.ViewHolder{

           TextView txtname,txtphone,txtaddress;
           ImageView profile;
           CardView card;

            public ViewHolder(View itemview)
            {
                super(itemview);
                card=itemview.findViewById(R.id.cardview);
                profile=itemview.findViewById(R.id.profileimage);
                txtname=(TextView) itemview.findViewById(R.id.name);
                txtphone=(TextView)itemview.findViewById(R.id.phone);
                txtaddress=(TextView)itemview.findViewById(R.id.address);

            }//end of ViewHolder.............................
        }//end of inner class.......



}//end of outer class