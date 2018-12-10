package dennis.balmoris.com.happytiger.CoffeeStores;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import dennis.balmoris.com.happytiger.MainActivity;
import dennis.balmoris.com.happytiger.MessageActivity;
import dennis.balmoris.com.happytiger.ProfileActivity;
import dennis.balmoris.com.happytiger.R;

public class IChill extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle adbt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ichill);


        Button checkMap = (Button)findViewById(R.id.btnMap);

        findViewById(R.id.btnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null, chooser =null;

                intent = new Intent(android.content.Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("geo:14.610644, 120.986921"));
                intent.setData(Uri.parse("geo: 14.612761, 120.990953 ?q= 14.612761, 120.990953 (IChill Theatre Cafe )"));

                chooser=Intent.createChooser(intent,"Launch Maps");
                startActivity(intent);
            }
        });


        //drawer layout
        dl = (DrawerLayout) findViewById(R.id.dl);
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
                    startActivity(new Intent(IChill.this, ProfileActivity.class));
                    Toast.makeText(IChill.this, "My Profile", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.foodStores)
                {
                    Toast.makeText(IChill.this, "You are in this page", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.messages)
                {
                    startActivity(new Intent(IChill.this, MessageActivity.class));
                    Toast.makeText(IChill.this, "Discuss Now", Toast.LENGTH_SHORT).show();
                }


                else  if(id == R.id.signout)
                {
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(IChill.this, MainActivity.class));
                    Toast.makeText(IChill.this, "You have signed out successfully", Toast.LENGTH_LONG).show();
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
