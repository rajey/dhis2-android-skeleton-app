package com.example.android.androidskeletonapp.ui.data_sets;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.android.androidskeletonapp.R;
import com.example.android.androidskeletonapp.data.Sdk;
import com.example.android.androidskeletonapp.ui.base.ListActivity;

import org.hisp.dhis.android.core.dataset.DataSet;

public class DataSetsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp(R.layout.activity_data_sets, R.id.dataSetsToolbar, R.id.dataSetsRecyclerView);
        observerDataSets();
    }

    private void observerDataSets() {
        DataSetsAdapter adapter = new DataSetsAdapter();

        recyclerView.setAdapter(adapter);

        LiveData<PagedList<DataSet>> dataSetListLiveData = Sdk.d2().dataSetModule().dataSets.withStyle().getPaged(10);
        dataSetListLiveData.observe(this, dataSets -> {
            adapter.submitList(dataSets);
            findViewById(R.id.dataSetsNotificator).setVisibility(dataSets.isEmpty() ? View.VISIBLE: View.GONE);
        });

    }

}
