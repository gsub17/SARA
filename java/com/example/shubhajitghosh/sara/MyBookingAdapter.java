package com.example.shubhajitghosh.sara;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shubhajitghosh.sara.Modules.MyNode;

import java.util.ArrayList;

public class MyBookingAdapter  extends RecyclerView.Adapter<MyBookingAdapter.ViewHolder>{

    public static ArrayList<MyNode> node=new ArrayList<>();
    Boolean flag;

    Context context;
   // LayoutInflater inflater;

    public MyBookingAdapter(ArrayList<MyNode> n,Context context){

        this.context=context;
     //   inflater=LayoutInflater.from(context);

        this.node=n;

       // Log.e("sufiyan3",this.name.toString());
    }



    @NonNull
    @Override
    public MyBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewtwo,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingAdapter.ViewHolder holder, final int position) {


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag=context.getSharedPreferences("user",0).getBoolean("isuser",false);

                if(flag) {
                    Intent intent = new Intent(context, user_bookings.class);
                    intent.putExtra("name",node.get(position).getServicename());
                    intent.putExtra("phone",node.get(position).getServicephone());
                    intent.putExtra("mail",node.get(position).getServicemail());
                    intent.putExtra("address",node.get(position).getServiceaddress());
                    intent.putExtra("date",node.get(position).getDate());
                    intent.putExtra("flag",node.get(position).getFlag());

                    context.startActivity(intent);

                }
                else {
                    Intent intent = new Intent(context, talent_service_booking.class);
                    intent.putExtra("name",node.get(position).getUsername());
                    intent.putExtra("phone",node.get(position).getUserphonr());
                    intent.putExtra("mail",node.get(position).getUsermain());
                    intent.putExtra("address",node.get(position).getServiceaddress());
                    intent.putExtra("date",node.get(position).getDate());
                    intent.putExtra("flag",node.get(position).getFlag());

                    context.startActivity(intent);
                }


            }
        });

        flag=context.getSharedPreferences("user",0).getBoolean("isuser",false);
        if(flag) {
            holder.txtname.setText(node.get(position).getServicename());
            holder.txtmail.setText(node.get(position).getServicemail());
            holder.txtaddress.setText(node.get(position).getServiceaddress());
            holder.txtmobile.setText(node.get(position).getServicephone());
            holder.txtdate.setText(node.get(position).getDate());

            if(node.get(position).getFlag()==0)
                holder.tststatus.setText("Order");
            else if(node.get(position).getFlag()==1)
                holder.tststatus.setText("Confirm");
            else if(node.get(position).getFlag()==2)
                holder.tststatus.setText("Deliver");
            else if(node.get(position).getFlag()==-1)
                holder.tststatus.setText("Not Accepted");
            else if(node.get(position).getFlag()==-2)
                holder.tststatus.setText("Cancel");
        }
        else{
            holder.txtname.setText(node.get(position).getUsername());
            holder.txtmail.setText(node.get(position).getUsermain());
            holder.txtaddress.setText(node.get(position).getServiceaddress());
            holder.txtmobile.setText(node.get(position).getUserphonr());
            holder.txtdate.setText(node.get(position).getDate());

            if(node.get(position).getFlag()==0)
                holder.tststatus.setText("Order");
            else if(node.get(position).getFlag()==1)
                holder.tststatus.setText("Confirm");
            else if(node.get(position).getFlag()==2)
                holder.tststatus.setText("Deliver");
            else if(node.get(position).getFlag()==-1)
                holder.tststatus.setText("Not Accept");
            else if(node.get(position).getFlag()==-2)
                holder.tststatus.setText("Cancel");

        }

    }



    @Override
    public int getItemCount() {
        return node.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtmail,txtaddress,txtmobile,txtdate,tststatus;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtname=(TextView) itemView.findViewById(R.id.sname);
            txtmail=(TextView) itemView.findViewById(R.id.smail);
            txtmobile=(TextView) itemView.findViewById(R.id.sphoneno);
            txtaddress=(TextView) itemView.findViewById(R.id.saddress);
            txtdate=(TextView)itemView.findViewById(R.id.sdate);
            tststatus=(TextView)itemView.findViewById(R.id.status);
            cardView=itemView.findViewById(R.id.cardviewbooking);
        }
    }
}
