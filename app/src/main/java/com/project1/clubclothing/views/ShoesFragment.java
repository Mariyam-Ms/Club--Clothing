package com.project1.clubclothing.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.project1.clubclothing.R;
import com.project1.clubclothing.databinding.FragmentShoesBinding;
import com.project1.clubclothing.model.DataItem;
import com.project1.clubclothing.model.Shoecart;
import com.project1.clubclothing.utils.ShoeItemAdapter;
import com.project1.clubclothing.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;


public class ShoesFragment extends Fragment implements ShoeItemAdapter.ShoeClickedListeners{

    private FragmentShoesBinding binding;
    NavController navController;
    private List<Shoecart> shoeCartList;
    private List<DataItem> shoeItemList;
    private ShoeItemAdapter adapter;
    private MyViewModel viewModel;
    private CoordinatorLayout coordinatorLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentShoesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        viewModel = new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication())).get(MyViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         navController = Navigation.findNavController(view);

        initializeVariables();
        setUpList();

        adapter.setShoecartList(shoeCartList);
        binding.mainRecyclerView.setAdapter(adapter);

        binding.shoecartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_tabFragment_to_shoeCartFragment);
            }});}


    @Override
    public void onResume() {
        super.onResume();
      viewModel.getAllItems().observe(this, new Observer<List<DataItem>>() {
        @Override
        public void onChanged(List<DataItem> dataItems) {

            for(int i=0;i<dataItems.size();i++){
                if(dataItems.get(i).getShoeName()!=null){
                    shoeItemList.add(dataItems.get(i));
                }
            }
           // shoeItemList.addAll(dataItems);
        }
    });

   }
    private void initializeVariables() {
        shoeCartList = new ArrayList<>();
        shoeItemList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.mainRecyclerView.setHasFixedSize(true);
        binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShoeItemAdapter(this);

    }

    private void setUpList() {
        shoeCartList.add(new Shoecart("Nike Blue","Nike",R.drawable.wonderwoman,370));
        shoeCartList.add(new Shoecart("BatMan ","Nike",R.drawable.batman,190));
        shoeCartList.add(new Shoecart("Nike Air","Nike",R.drawable.nikea,380));
        shoeCartList.add(new Shoecart("Nike Pink","Nike",R.drawable.nikepink,260));
        shoeCartList.add(new Shoecart("Nike Solid Brown","Nike",R.drawable.nikebrown,3960));
        shoeCartList.add(new Shoecart("Nike Green Lanther","Nike",R.drawable.greenlanther,170));
        shoeCartList.add(new Shoecart("Nike pop","Nike",R.drawable.nikeorange,200));
        shoeCartList.add(new Shoecart("Nike Acing","Nike",R.drawable.nikeracing,220));
        shoeCartList.add(new Shoecart("Nike Purple","Nike",R.drawable.nikepurple,300));
        shoeCartList.add(new Shoecart("Nike AguaMan","Nike",R.drawable.aquaman,400));
        shoeCartList.add(new Shoecart("Nike Superman","Nike",R.drawable.superman,370));
        shoeCartList.add(new Shoecart("Nike Harleyquinn","Nike",R.drawable.harleyquinn,350));
        shoeCartList.add(new Shoecart("Nike MrFreeze","Nike",R.drawable.mrfreeze,290));
        shoeCartList.add(new Shoecart("Nike Airr","Nike",R.drawable.nikeair,69));
    }


    @Override
    public void onAddToCartBtnClicked(Shoecart shoecart) {

        DataItem dataItem = new DataItem();
        dataItem.setShoeName(shoecart.getShoeName());
        dataItem.setShoeBrandName(shoecart.getShoeBrandName());
        dataItem.setShoePrice(shoecart.getShoePrice());
        dataItem.setShoeImage(shoecart.getShoeImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!shoeItemList.isEmpty()) {
            for (int i = 0; i < shoeItemList.size(); i++) {
                if (dataItem.getShoeName().equals(shoeItemList.get(i).getShoeName())) {
                    quantity[0] = shoeItemList.get(i).getShoequantity();
                    quantity[0]++;
                    id[0] = shoeItemList.get(i).getId();
                }
            }

        }

            if (quantity[0] == 1) {
                dataItem.setShoequantity(quantity[0]);
                dataItem.setTotalShoesPrice(quantity[0] * dataItem.getShoePrice());
                viewModel.insertShoeItem(dataItem,id);
            } else {
                viewModel.updateShoeQuantity(id[0], quantity[0]);
                viewModel.updateShoePrice(id[0], quantity[0] * dataItem.getShoePrice());
            }



    View view = getView();
        if (view != null) {
            Snackbar.make(view, "Item Added to Cart", Snackbar.LENGTH_LONG)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        navController.navigate(R.id.action_tabFragment_to_shoeCartFragment);

                    }
                })
                    .setBackgroundTint(Color.LTGRAY)
                    .setActionTextColor(Color.BLUE)
                .show();
    }
    }
}
