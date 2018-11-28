package dennis.balmoris.com.happytiger;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FoodStores extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    private DrawerLayout dl;
    private ActionBarDrawerToggle adbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodstores);
        dl = (DrawerLayout)findViewById(R.id.dl);
        adbt = new ActionBarDrawerToggle(this, dl, R.string.Open,R.string.Close);
        adbt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(adbt);
        adbt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.homePage)

                {
                    finish();
                    startActivity(new Intent(FoodStores.this, ProfileActivity.class));
                    Toast.makeText(FoodStores.this, "My Profile", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.foodStores)
                {
                    Toast.makeText(FoodStores.this, "You are in this page", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.messages)
                {
                    finish();
                    startActivity(new Intent(FoodStores.this, MessageActivity.class));
                    Toast.makeText(FoodStores.this, "Discuss Now", Toast.LENGTH_SHORT).show();
                }

                else  if(id == R.id.settings)
                {
                    finish();
                    startActivity(new Intent(FoodStores.this, Settings.class));
                    Toast.makeText(FoodStores.this, "Settings", Toast.LENGTH_SHORT).show();
                }

                else  if(id == R.id.signout)
                {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(FoodStores.this, MainActivity.class));
                    Toast.makeText(FoodStores.this, "You have signed out successfully", Toast.LENGTH_LONG).show();
                }


                return true;
            }
        });
    }

    public void onClickPizza(View v){
        startActivity(new Intent(FoodStores.this, CoffeeList.class));
    }

    public void onClickChicken(View v){
        startActivity(new Intent(FoodStores.this, FastFoodList.class));
    }

    public void onClickCarinderia(View v){
        startActivity(new Intent(FoodStores.this, CarinderiaList.class));
    }

    public void onClickShakes(View v){
        startActivity(new Intent(FoodStores.this, ShakesList.class));
    }

    public void onClickRestaurants(View v){
        startActivity(new Intent(FoodStores.this, RestaurantsList.class));
    }

    public void onClickStreetfood(View v){
        startActivity(new Intent(FoodStores.this, StreetfoodList.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return adbt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tap Again to Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

