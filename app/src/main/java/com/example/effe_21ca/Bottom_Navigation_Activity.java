package com.example.effe_21ca;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.effe_21ca.databinding.ActivityBottomNavigationBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Bottom_Navigation_Activity extends AppCompatActivity {

private ActivityBottomNavigationBinding binding;

private DrawerLayout drawerLayout;
private NavigationView navigationView;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
        ImageView imgnoti;
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.award, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
         auth=FirebaseAuth.getInstance();
         imgnoti=findViewById(R.id.imgNotification);
         imgnoti.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(Bottom_Navigation_Activity.this,NotificationActivity.class);
                 startActivity(intent);
             }
         });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.signout){
            auth.signOut();
            Intent intent=new Intent(Bottom_Navigation_Activity.this,SignInUpActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You click on ", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.Contacts){
            Intent intent =new Intent(Bottom_Navigation_Activity.this,Contacts_Activity.class);
            startActivity(intent);
        }
        else if(id==R.id.AboutUs){
             Intent intent =new Intent(Bottom_Navigation_Activity.this,About_Us.class);
             startActivity(intent);
        }
        else if(id==R.id.AboutCA){
            Intent intent =new Intent(Bottom_Navigation_Activity.this,AboutCA_Activity.class);
            startActivity(intent);
        }

        return true;
    }

}