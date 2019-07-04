package com.example.android.androidskeletonapp.ui.d2_errors;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.android.androidskeletonapp.R;
import com.example.android.androidskeletonapp.data.Sdk;
import com.example.android.androidskeletonapp.ui.base.ListActivity;

import org.hisp.dhis.android.core.maintenance.D2Error;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class D2ErrorActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp(R.layout.activity_d2_errors, R.id.d2ErrorsToolbar, R.id.d2ErrorsRecyclerView);
        observeErrors();

    }


    private void observeErrors() {
        D2ErrorAdapter errorAdapter = new D2ErrorAdapter();

        recyclerView.setAdapter(errorAdapter);

        LiveData<PagedList<D2Error>> errorsPageListLiveData = Sdk.d2().maintenanceModule().d2Errors.getPaged(10);

        errorsPageListLiveData.observe(this, d2ErrorList -> {
            errorAdapter.submitList(d2ErrorList);
            findViewById(R.id.d2ErrorsNotificator).setVisibility(d2ErrorList.isEmpty() ? View.VISIBLE : View.GONE);
        });



    }

}
