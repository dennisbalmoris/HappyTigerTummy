package dennis.balmoris.com.happytiger;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import dennis.balmoris.com.happytiger.Carinderia.Barcolli;
import dennis.balmoris.com.happytiger.Carinderia.Cantunan;
import dennis.balmoris.com.happytiger.Carinderia.Ilars;
import dennis.balmoris.com.happytiger.Carinderia.Liempuhan;
import dennis.balmoris.com.happytiger.Carinderia.MangTootz;
import dennis.balmoris.com.happytiger.CoffeeStores.BosCoffee;
import dennis.balmoris.com.happytiger.CoffeeStores.CafeKhivan;
import dennis.balmoris.com.happytiger.CoffeeStores.IChill;
import dennis.balmoris.com.happytiger.CoffeeStores.Seattles;
import dennis.balmoris.com.happytiger.CoffeeStores.Starbucks;
import dennis.balmoris.com.happytiger.Restaurant.ArmyNavy;
import dennis.balmoris.com.happytiger.Restaurant.Ichiro;
import dennis.balmoris.com.happytiger.Restaurant.Pancake;
import dennis.balmoris.com.happytiger.Restaurant.Sbarro;
import dennis.balmoris.com.happytiger.Restaurant.Shakeys;
import dennis.balmoris.com.happytiger.StreetFoods.Antonio;
import dennis.balmoris.com.happytiger.StreetFoods.Asturias;
import dennis.balmoris.com.happytiger.StreetFoods.EspanaGate1;
import dennis.balmoris.com.happytiger.StreetFoods.HepaLane;
import dennis.balmoris.com.happytiger.StreetFoods.PCampa;

public class StreetfoodList extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle adbt;

    ListView mListView;

    int[] images = {R.drawable.street1,
            R.drawable.street2,
            R.drawable.street3,
            R.drawable.hepa,
            R.drawable.anton,
    };

    String[] Names = {"Espana Street",
            "Asturias Street.",
            "Padre Campa Street.",
            "Padre Noval Street",
            "Antonio Street."
    };

    @Override
    public void onCreate(Bundle savedInstanceStatete) {
        super.onCreate(savedInstanceStatete);
        setContentView(R.layout.streetfood_list);

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
                    startActivity(new Intent(StreetfoodList.this, ProfileActivity.class));
                    Toast.makeText(StreetfoodList.this, "My Profile", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.foodStores)
                {
                    Toast.makeText(StreetfoodList.this, "You are in this page", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.messages)
                {
                    startActivity(new Intent(StreetfoodList.this, MessageActivity.class));
                    Toast.makeText(StreetfoodList.this, "Discuss Now", Toast.LENGTH_SHORT).show();
                }



                else  if(id == R.id.signout)
                {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(StreetfoodList.this, MainActivity.class));
                    Toast.makeText(StreetfoodList.this, "You have signed out successfully", Toast.LENGTH_LONG).show();
                }


                return true;
            }
        });



        mListView = findViewById(R.id.streetfoodListView);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if(position==0){
                    Intent intent = new Intent(StreetfoodList.this, EspanaGate1.class);
                    startActivity(intent);
                }

                if(position==1){
                    Intent intent = new Intent(StreetfoodList.this, Asturias.class);
                    startActivity(intent);
                }

                if(position==2){
                    Intent intent = new Intent(StreetfoodList.this, PCampa.class);
                    startActivity(intent);
                }

                if(position==3){
                    Intent intent = new Intent(StreetfoodList.this, HepaLane.class);
                    startActivity(intent);
                }

                if(position==4){
                    Intent intent = new Intent(StreetfoodList.this, Antonio.class);
                    startActivity(intent);
                }



            }
        });
    }

    class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.streetfood_custom, null);
            ImageView mImageView = view.findViewById(R.id.streetfoodImageView);
            TextView mTextView = (TextView) view.findViewById(R.id.streetfoodTextView);
            mImageView.setImageResource(images[position]);
            mTextView.setText(Names[position]);

            return view;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return adbt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
