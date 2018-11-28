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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RestaurantsList extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle adbt;

    ListView mListView;

    int[] images = {R.drawable.shakeys,
            R.drawable.pancake,
            R.drawable.army,
            R.drawable.sbarro,
            R.drawable.ichiro
    };

    String[] Names = {"Shakeys",
            "Pancake House",
            "Army Navy",
            "Sbarro",
            "Ichiro Ramen"
    };

    @Override
    public void onCreate(Bundle savedInstanceStatete) {
        super.onCreate(savedInstanceStatete);
        setContentView(R.layout.restaurants_list);

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
                    startActivity(new Intent(RestaurantsList.this, ProfileActivity.class));
                    Toast.makeText(RestaurantsList.this, "My Profile", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.foodStores)
                {
                    Toast.makeText(RestaurantsList.this, "You are in this page", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.messages)
                {
                    startActivity(new Intent(RestaurantsList.this, MessageActivity.class));
                    Toast.makeText(RestaurantsList.this, "Discuss Now", Toast.LENGTH_SHORT).show();
                }

                else  if(id == R.id.settings)
                {
                    startActivity(new Intent(RestaurantsList.this, Settings.class));
                    Toast.makeText(RestaurantsList.this, "Settings", Toast.LENGTH_SHORT).show();
                }

                else  if(id == R.id.signout)
                {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(RestaurantsList.this, MainActivity.class));
                    Toast.makeText(RestaurantsList.this, "You have signed out successfully", Toast.LENGTH_LONG).show();
                }


                return true;
            }
        });


        mListView = findViewById(R.id.restaurantsListView);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);
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

            View view = getLayoutInflater().inflate(R.layout.restaurants_custom, null);
            ImageView mImageView = view.findViewById(R.id.restaurantsImageView);
            TextView mTextView = (TextView) view.findViewById(R.id.restaurantsTextView);
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
