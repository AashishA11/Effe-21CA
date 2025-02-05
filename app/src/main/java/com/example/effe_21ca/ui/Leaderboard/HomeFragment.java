package com.example.effe_21ca.ui.Leaderboard;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.R;
import com.example.effe_21ca.Users_Adapter;
import com.example.effe_21ca.databinding.FragmentHomeBinding;
import com.example.effe_21ca.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseStorage storage;
   // ArrayList<Users> Users;
   ArrayList<Users>list =new ArrayList<>();
    Users user;
    Users_Adapter Users_Adapter;
  // FragmentHomeBinding binding;




//    private Notification_Adapter notificationAdapter;
//    private List<Notification> notificationList;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.list_item_leaderboard, container, false);
//
//        RecyclerView recyclerView = view.findViewById(R.id.leaderBoardRecycleView);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        Users = new ArrayList<>();
//        Users_Adapter = new Users_Adapter(getContext(), Users);
//        recyclerView.setAdapter(Users_Adapter);
//
//        readLeaderboard();
//
//        return view;
//    }
//
//    private void readLeaderboard() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        assert firebaseUser != null;
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Users.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Users users = snapshot.getValue(Users.class);
//                    Users.add(users);
//                }
//
//                Collections.reverse(Users);
//               // Users.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    //private LeaderboardViewModel homeViewModel;
 FragmentHomeBinding binding;
    // Object Users_Adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {


    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        database = FirebaseDatabase.getInstance();
       // storage = FirebaseStorage.getInstance();
//        String name = getActivity().getIntent().getStringExtra("username");
//
//        Users = new ArrayList<>();
//        database.getReference().child("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        user = snapshot.getValue(Users.class);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//        Users_Adapter = new Users_Adapter(getContext(), Users);
//
//
//        binding.leaderBoardRecycleView.setAdapter(Users_Adapter);
//
//
//        String userName = getActivity().getIntent().getStringExtra("userName");
//        binding..setText(userName);
//
//        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Users.clear();
//                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
//                    Users user = snapshot1.getValue(Users.class);
//                //   assert user != null;
//                //   if(!user.getUid().equals(FirebaseAuth.getInstance().getUid()))
//                        Users.add(user);
//                }
//
//                Users_Adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//       // final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        Users_Adapter adaptor=new Users_Adapter(getContext(), list);
        binding.leaderBoardRecycleView.setAdapter(adaptor);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.leaderBoardRecycleView.setLayoutManager(layoutManager);

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