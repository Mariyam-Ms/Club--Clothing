package com.project1.clubclothing.views;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.project1.clubclothing.R;
import com.project1.clubclothing.databinding.FragmentClothingBinding;
import com.project1.clubclothing.model.ClothesCart;
import com.project1.clubclothing.model.DataItem;
import com.project1.clubclothing.utils.ClothesItemAdapter;
import com.project1.clubclothing.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;


public class ClothingFragment extends Fragment implements ClothesItemAdapter.JerseyItemClickedListeners {

    private FragmentClothingBinding binding;
    NavController navController;
    private List<ClothesCart> clothesCartList;
    private List<DataItem> clothItemList;
    private ClothesItemAdapter adapter;
    private MyViewModel viewModel;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClothingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(requireActivity().getApplication())).get(MyViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        initializeVariables();
        setUpList();

        adapter.setClothcartList(clothesCartList);
        binding.clothingRecyclerView.setAdapter(adapter);

        binding.clothingcartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_tabFragment_to_clothingCartFragment);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllItems().observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                for(int i=0;i<dataItems.size();i++){
                    if(dataItems.get(i).getJerseyName()!=null){
                        clothItemList.add(dataItems.get(i));
                    }
                }

                Log.i("ITSCLOTHFRAGMENT", "onChanged: "+ clothItemList.size());
            }
        });

    }

    private void initializeVariables() {
        clothesCartList = new ArrayList<>();
        clothItemList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.clothingRecyclerView.setHasFixedSize(true);
        binding.clothingRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new ClothesItemAdapter(this);

    }

    private void setUpList() {
        clothesCartList.add(new ClothesCart("Messi","Psg",R.drawable.messi,3700));
        clothesCartList.add(new ClothesCart("Gavi","Barca",R.drawable.gavi,4700));
        clothesCartList.add(new ClothesCart("Griezman","Atletico",R.drawable.griezman,81));

        clothesCartList.add(new ClothesCart("Ering Haaland","mancity",R.drawable.eringhaaland,2800));
        clothesCartList.add(new ClothesCart("Erisken","Manchester United",R.drawable.eriksen,3700));
        clothesCartList.add(new ClothesCart("Dybla","Madrid",R.drawable.dybla,3700));
        clothesCartList.add(new ClothesCart("Debrunye","mancity",R.drawable.debrunye,3700));
        clothesCartList.add(new ClothesCart("Mbappe","Psg",R.drawable.mbappe,3700));
        clothesCartList.add(new ClothesCart("Pedri","Barca",R.drawable.pedri,3700));
        clothesCartList.add(new ClothesCart("JoaoFelix","Atletico",R.drawable.joaofelix,3700));


    }

    @Override
    public void onAddtoJerseyCartBtnClicked(ClothesCart clothescart) {

        DataItem dataItem = new DataItem();
        dataItem.setJerseyName(clothescart.getJerseyName());
        dataItem.setJerseyClubName(clothescart.getJerseyClubName());
        dataItem.setJerseyPrice(clothescart.getJerseyPrice());
        dataItem.setJerseyImage(clothescart.getJerseyImage());

        final int[] jerseyquantity = {1};
        final int[] id = new int[1];

      //  Log.i("ITSCLOTHFRAGMENT", "onAddtoJerseyCartBtnClicked:  outside"  + clothItemList.size());

        if (!clothItemList.isEmpty()) {
            for (int i = 0; i < clothItemList.size(); i++) {
              //  Log.i("ITSCLOTHFRAGMENT", "onAddtoJerseyCartBtnClicked: "+clothItemList.size());
                if (dataItem.getJerseyName().equals(clothItemList.get(i).getJerseyName())) {
                   // Log.i("ITSCLOTHFRAGMENT", "onAddtoJerseyCartBtnClicked: "+clothItemList.get(i).getJerseyName());

                    jerseyquantity[0] = clothItemList.get(i).getJerseyquantity();
                    jerseyquantity[0]++;
                   id [0] = clothItemList.get(i).getId();
                }
            }

        }

        if (jerseyquantity[0] == 1) {
          //  Log.i("ITSCLOTHFRAGMENT", "onAddtoJerseyCartBtnClicked:  okay "+jerseyquantity[0]);

            dataItem.setJerseyquantity(jerseyquantity[0]);
            dataItem.setTotalJerseyPrice(jerseyquantity[0] * dataItem.getJerseyPrice());
            viewModel.insertJerseyItem(dataItem,id);
        } else {
            //Log.i("ITSCLOTHFRAGMENT", "onAddtoJerseyCartBtnClicked: Nope " + dataItem.getId());

            viewModel.updateJerseyQuantity(id[0], jerseyquantity[0]);
            viewModel.updateJerseyPrice(id[0], jerseyquantity[0] * dataItem.getJerseyPrice());
        }



            View view = getView();
            if (view != null) {
                Snackbar.make(view, "Item Added to Cart", Snackbar.LENGTH_LONG)
                        .setAction("Go to Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                navController.navigate(R.id.action_tabFragment_to_clothingCartFragment);

                            }
                        })
                        .setBackgroundTint(Color.LTGRAY)
                        .setActionTextColor(Color.BLUE)
                        .show();
            }

    }
}

