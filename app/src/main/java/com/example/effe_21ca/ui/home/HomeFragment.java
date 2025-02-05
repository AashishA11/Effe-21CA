package com.example.effe_21ca.ui.home;

  import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.effe_21ca.Adaptors.Users_Adapter;
import com.example.effe_21ca.databinding.FragmentHomeBinding;
import com.example.effe_21ca.models.Users;
  import com.google.firebase.auth.FirebaseAuth;
  import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
  import java.util.HashMap;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseStorage storage;

    ArrayList<Users>list =new ArrayList<>();
    Users user;
    com.example.effe_21ca.Adaptors.Users_Adapter Users_Adapter;

    FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        database = FirebaseDatabase.getInstance();


        Users_Adapter adaptor=new Users_Adapter(getContext(), list);
        binding.leaderBoardRecycleView.setAdapter(adaptor);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.leaderBoardRecycleView.setLayoutManager(layoutManager);


//        binding
//        HashMap<String,Object> obj=new HashMap<>();
//         obj.put("score",status);

//        database.getReference().child("Users")
//                .child(FirebaseAuth.getInstance().getUid())
//                .updateChildren(obj);


        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Users users=dataSnapshot.getValue(Users.class);
                    users.getUserId(dataSnapshot.getKey());
                    list.add(users);

                }
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}