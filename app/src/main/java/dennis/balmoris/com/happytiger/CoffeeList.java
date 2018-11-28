package dennis.balmoris.com.happytiger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CoffeeList extends AppCompatActivity {

    ListView mListView;

    int[] images = {R.drawable.sb,
                    R.drawable.bo,
                    R.drawable.seattle,
                    R.drawable.coff,
                    R.drawable.ichill
                    };

    String[] Names = {"Starbucks Coffee",
                      "Bo's Coffee",
                      "Seattle's Best Coffee",
                      "Cafe Khivan Coffee",
                      "iChill Theater Cafe"
                        };

    @Override
    public void onCreate(Bundle savedInstanceStatete) {
        super.onCreate(savedInstanceStatete);
        setContentView(R.layout.coffee_list);

        mListView = findViewById(R.id.pizzaListView);

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

            View view = getLayoutInflater().inflate(R.layout.coffee_custom, null);
            ImageView mImageView = view.findViewById(R.id.pizzaImageView);
            TextView mTextView = (TextView) view.findViewById(R.id.pizzaTextView);
            mImageView.setImageResource(images[position]);
            mTextView.setText(Names[position]);

            return view;
        }
    }
}
