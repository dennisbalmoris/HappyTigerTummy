package dennis.balmoris.com.happytiger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class FoodStores extends AppCompatActivity {

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
                    startActivity(new Intent(FoodStores.this, MainActivity.class));
                    Toast.makeText(FoodStores.this, "Home Page", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.foodStores)
                {
                    Toast.makeText(FoodStores.this, "You are in this page", Toast.LENGTH_SHORT).show();
                }
                else  if(id == R.id.favorites)
                {
                    startActivity(new Intent(FoodStores.this, Favorites.class));
                    Toast.makeText(FoodStores.this, "Favorites", Toast.LENGTH_SHORT).show();
                }

                else  if(id == R.id.settings)
                {
                    startActivity(new Intent(FoodStores.this, Settings.class));
                    Toast.makeText(FoodStores.this, "Settings", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return adbt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
