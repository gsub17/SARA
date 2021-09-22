package com.example.shubhajitghosh.sara;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shubhajitghosh.sara.Modules.MyNode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link talent_address.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link talent_address#newInstance} factory method to
 * create an instance of this fragment.
 */
public class talent_address extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public talent_address() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment talent_address.
     */
    // TODO: Rename and change types and number of parameters
    public static talent_address newInstance(String param1, String param2) {
        talent_address fragment = new talent_address();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    ArrayList<MyNode> node=new ArrayList<>();

    LinearLayoutManager linearLayoutManager;
    FirebaseDatabase database;
    DatabaseReference reference;
    MyNode myNode;
    RecyclerView recyclerView;
    String email="";
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_talent_address, container, false);

        final View talent = inflater.inflate(R.layout.fragment_talent_address, container, false);


        database=FirebaseDatabase.getInstance();
        reference=database.getReference("node");

        sharedPreferences=getContext().getSharedPreferences("user",0);
        email=sharedPreferences.getString("email",null);

        linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView=talent.findViewById(R.id.recycleviewthree);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MyBookingAdapter adapter=new MyBookingAdapter(node,getActivity());
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d:dataSnapshot.getChildren())
                {
                    for (DataSnapshot E:d.getChildren()) {
                        myNode = new MyNode();
                        myNode = E.getValue(MyNode.class);

                        Log.e("sufiyan1234", "mail "+myNode.getServicemail());

                        if (email.equals(myNode.getServicemail()) && email != null) {

                            node.add(myNode);

                            adapter.notifyDataSetChanged();
                        }
                    }
                }

               // Boolean flag=getS;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return talent;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
