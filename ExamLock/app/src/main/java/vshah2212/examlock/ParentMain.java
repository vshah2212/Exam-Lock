package vshah2212.examlock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ParentMain extends AppCompatActivity {

    ListView list;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_main);

        list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<String>();

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

        // Here, you set the data in your ListView
        list.setAdapter(adapter);

        // this line adds the data of your EditText and puts in your array
        arrayList.add("Hello");
        // next thing you have to do is check if your adapter has changed
        adapter.notifyDataSetChanged();

        arrayList.add("Hello1");
        // next thing you have to do is check if your adapter has changed
        adapter.notifyDataSetChanged();

        arrayList.add("Hello2");
        // next thing you have to do is check if your adapter has changed
        adapter.notifyDataSetChanged();
    }
}
