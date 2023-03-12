package com.project1.clubclothing.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project1.clubclothing.databinding.FragmentClothingCartBinding;
import com.project1.clubclothing.model.DataItem;
import com.project1.clubclothing.utils.ClothesCartAdapter;
import com.project1.clubclothing.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;


public class ClothingCartFragment extends Fragment implements ClothesCartAdapter.JerseyClickedListeners{

    private FragmentClothingCartBinding binding;
    NavController navController;
    private ClothesCartAdapter adapter;
    private MyViewModel viewModel;
    private List<DataItem> clothItemList;
    private List<DataItem> allItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentClothingCartBinding.inflate(inflater,container,false);
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

        ClothesCartAdapter adapter=new ClothesCartAdapter(this);
        initializeVariables();
        binding.jerseycartRecyclerView.setAdapter(adapter);
        binding.jerseycartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




    viewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
//                for(int i=0;i<dataItems.size();i++){
                //   Log.i("DATAOFF", "onChanged: " + dataItems);
//                    if(dataItems.get(i).getJerseyName()!=null){
//                        clothItemList.add(dataItems.get(i));
//                    }
//                }
                    allItemList.addAll(dataItems);

                double price = 0;
                clothItemList.clear();
                for (int i=0;i<dataItems.size();i++){
                    if(dataItems.get(i).getJerseyName()!=null) {



                        clothItemList.add(dataItems.get(i));

                        price = price + dataItems.get(i).getTotalJerseyPrice();

                    }
                }
                Log.i("AREYOU", "onChanged: dataItems" + dataItems.size());
                Log.i("AREYOU", "onChanged: clothingList " + clothItemList.size());

                adapter.setClothesCartList(clothItemList);
                adapter.notifyDataSetChanged();
                binding.jerseycartTotalPriceTv .setText(String.valueOf(price));
            }
        });
        binding.jerseyCheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<allItemList.size();i++){
                    if(allItemList.get(i).getJerseyName()!=null){
                        viewModel.deleteById(allItemList.get(i).getId());
                    }
                }
            }
        });
    }

//    private void remove() {
//    }

    private void initializeVariables() {

        allItemList   = new ArrayList<>();
        clothItemList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

      //  adapter = new ClothesCartAdapter(this);
    }

    @Override
    public void onDeleteJerseyClicked(DataItem dataItem) {

        viewModel.deleteJerseyItem(dataItem);
    }

    @Override
    public void onPlusJerseyClicked(DataItem dataItem) {

        int quantity = dataItem.getJerseyquantity() + 1;
        viewModel.updateJerseyQuantity(dataItem.getId() , quantity);
        viewModel.updateJerseyPrice(dataItem.getId() , quantity*dataItem.getJerseyPrice());

    }

    @Override
    public void onMinusJerseyClicked(DataItem dataItem) {

        int quantity = dataItem.getJerseyquantity() - 1;
        if (quantity != 0){
            viewModel.updateJerseyQuantity(dataItem.getId() , quantity);
            viewModel.updateJerseyPrice(dataItem.getId() , quantity*dataItem.getJerseyPrice());
        }else{
            viewModel.deleteJerseyItem(dataItem);
        }
    }
}