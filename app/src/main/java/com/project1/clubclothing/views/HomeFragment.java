package com.project1.clubclothing.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.project1.clubclothing.R;
import com.project1.clubclothing.databinding.FragmentHomeBinding;
import com.project1.clubclothing.utils.HorizontalRcyAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;
 NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        ImageSlider imageSlider = binding.imageSlider;
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.psg, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.barca, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.realmadrid, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.manchister, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.mancity, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.atletico, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        binding.navigatetoclothtab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_tabFragment);
            }
        });
        binding.recyclerviewHorizontal.setHasFixedSize(true);
        binding.recyclerviewHorizontal.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,false));
        List<Integer> imageList = new ArrayList<>();

        imageList.add(R.drawable.shoe1);
        imageList.add(R.drawable.shoe2);
        imageList.add(R.drawable.shoe3);
        imageList.add(R.drawable.shoe4);
        imageList.add(R.drawable.shoe5);
        imageList.add(R.drawable.shoe6);
        imageList.add(R.drawable.shoe7);
        imageList.add(R.drawable.shoe8);
        imageList.add(R.drawable.shoe9);

        HorizontalRcyAdapter horizontalRcyAdapter = new HorizontalRcyAdapter(imageList);
        binding.recyclerviewHorizontal.setAdapter(horizontalRcyAdapter);


        binding.navgatetoshoetab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_tabFragment);
            }
        });

    }


}