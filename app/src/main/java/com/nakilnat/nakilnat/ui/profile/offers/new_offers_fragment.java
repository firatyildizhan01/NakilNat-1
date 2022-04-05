package com.nakilnat.nakilnat.ui.profile.offers;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.nakilnat.nakilnat.R;




import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class new_offers_fragment extends Fragment {

    new_offers_adapter New_offers_adapter  ;



  

    @BindView(R.id.new_offers_rv)
    RecyclerView new_offers_rv;


    Context context;



    List<String> newoffers;
    int btnflag=0;

    final LinearLayoutManager mLayoutManager  = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

    Unbinder unbinder;

    boolean isInternet;



 

    public new_offers_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_offers_new, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getContext();




        newoffers = new ArrayList<>();
        newoffers.add("A");
        newoffers.add("B");
        newoffers.add("C");
        newoffers.add("D");
        New_offers_adapter = new new_offers_adapter (newoffers, context, true);





        //  newTaskRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        new_offers_rv.setLayoutManager(mLayoutManager);
        new_offers_rv.setItemAnimator(new DefaultItemAnimator());
        new_offers_rv.setAdapter(New_offers_adapter);




      


    }

   


    @Override
    public void onResume() {
        super.onResume();
  





    }

}


