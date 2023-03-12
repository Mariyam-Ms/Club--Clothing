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

import com.project1.clubclothing.databinding.FragmentShoeCartBinding;
import com.project1.clubclothing.model.DataItem;
import com.project1.clubclothing.utils.ShoeCartAdapter;
import com.project1.clubclothing.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoeCartFragment extends Fragment implements ShoeCartAdapter.CartClickedListeners {

  private FragmentShoeCartBinding binding;
  NavController navController;
    private ShoeCartAdapter adapter;
    private MyViewModel viewModel;
    private List<DataItem> shoeItemList;
    private List<DataItem> allItemList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentShoeCartBinding.inflate(inflater,container,false);
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

        ShoeCartAdapter adapter = new ShoeCartAdapter(this);
        binding.cartRecyclerView.setAdapter(adapter);
        binding.cartRecyclerView.setHasFixedSize(true);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



    viewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {

                allItemList.addAll(dataItems);

                double price = 0;
                shoeItemList.clear();
                for (int i=0;i<dataItems.size();i++){
                    if(dataItems.get(i).getShoeName()!=null) {
                        shoeItemList.add(dataItems.get(i));
                        Log.i("AREYOUCOMING", "onChanged: " + dataItems.size());
                        price = price + dataItems.get(i).getTotalShoesPrice();
                    }
                }
                adapter.setShoeCartList(shoeItemList);
                adapter.notifyDataSetChanged();

                binding.shoecartTotalPriceTv .setText(String.valueOf(price));
            }
        });
        binding.shoeCheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<allItemList.size();i++){
                    if(allItemList.get(i).getShoeName()!=null){
                        viewModel.deleteById(allItemList.get(i).getId());
                    }
                }

            }
        });
    }



    private void initializeVariables() {
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        allItemList = new ArrayList<>();
        shoeItemList = new ArrayList<>();
    }



    @Override
    public void onDeleteClicked(DataItem dataItem) {
        viewModel.deleteShoeItem(dataItem);

    }

    @Override
    public void onPlusClicked(DataItem dataItem) {
//        adapter.notifyDataSetChanged();
        int quantity = dataItem.getShoequantity() + 1;
        viewModel.updateShoeQuantity(dataItem.getId() , quantity);
        viewModel.updateShoePrice(dataItem.getId() , quantity*dataItem.getShoePrice());
//

    }

    @Override
    public void onMinusClicked(DataItem dataItem) {

        int quantity = dataItem.getShoequantity() - 1;
        if (quantity != 0){
            viewModel.updateShoeQuantity(dataItem.getId() , quantity);
            viewModel.updateShoePrice(dataItem.getId() , quantity*dataItem.getShoePrice());
          //  adapter.notifyDataSetChanged();
        }else{
            viewModel.deleteShoeItem(dataItem);
        }


    }
}