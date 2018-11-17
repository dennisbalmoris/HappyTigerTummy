package dennis.balmoris.com.happytiger;

import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RestaurantsList extends AppCompatActivity {

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
}
