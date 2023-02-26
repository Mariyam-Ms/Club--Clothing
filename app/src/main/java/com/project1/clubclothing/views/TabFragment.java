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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.project1.clubclothing.databinding.FragmentTabBinding;
import com.project1.clubclothing.utils.TabPagerAdapter;


public class TabFragment extends Fragment {

   private FragmentTabBinding binding;
   NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentTabBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController= Navigation.findNavController(view);

        TabLayout tabLayout=binding.tablayoutid;
        ViewPager viewPager=binding.viewPager;

        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ClothingFragment(), "Jersey");
        adapter.addFragment(new ShoesFragment(), "Shoes");



        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}