package paul.barthuel.testrxjava.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import paul.barthuel.testrxjava.R;
import paul.barthuel.testrxjava.viewmodel.ListViewModel;

public class MainActivity extends AppCompatActivity {

    private ListViewModel viewModel;
    private final CountryListAdapter adapter = new CountryListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ListViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.main_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.fetch();
        viewModel.countries.observe(this, adapter::submitList);
    }
}