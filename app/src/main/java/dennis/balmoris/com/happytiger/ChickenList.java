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

public class ChickenList extends AppCompatActivity {

    ListView mListView;

    int[] images = {R.drawable.kfc,
            R.drawable.mcdo,
            R.drawable.jollibee,
            R.drawable.inasa,
            R.drawable.alejandro
    };

    String[] Names = {"KFC",
            "Mcdo",
            "Jolibee",
            "Mang Inasal",
            "Alejandro's"
    };

    @Override
    public void onCreate(Bundle savedInstanceStatete) {
        super.onCreate(savedInstanceStatete);
        setContentView(R.layout.chicken_list);

        mListView = findViewById(R.id.chickenListView);

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

            View view = getLayoutInflater().inflate(R.layout.chicken_custom, null);
            ImageView mImageView = view.findViewById(R.id.chickenImageView);
            TextView mTextView = (TextView) view.findViewById(R.id.chickenTextView);
            mImageView.setImageResource(images[position]);
            mTextView.setText(Names[position]);

            return view;
        }
    }
}
