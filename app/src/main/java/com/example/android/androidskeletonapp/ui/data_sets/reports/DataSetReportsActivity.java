package com.example.android.androidskeletonapp.ui.data_sets.reports;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.android.androidskeletonapp.R;
import com.example.android.androidskeletonapp.data.Sdk;
import com.example.android.androidskeletonapp.ui.base.ListActivity;

import org.hisp.dhis.android.core.arch.helpers.UidsHelper;
import org.hisp.dhis.android.core.datavalue.DataSetReport;
import org.hisp.dhis.android.core.organisationunit.OrganisationUnit;

public class DataSetReportsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp(R.layout.activity_data_set_reports, R.id.dataSetReportsToolbar, R.id.dataSetReportsRecyclerView);
        observeDataSetReports();
    }

    private void observeDataSetReports() {
        DataSetReportsAdapter adapter = new DataSetReportsAdapter();
        recyclerView.setAdapter(adapter);

        // Get test organisation unit
        OrganisationUnit orgUnit = Sdk.d2().organisationUnitModule()
                .organisationUnits.byLevel().eq(4).get().get(0);

        String orgUnitUid = UidsHelper.getUidOrNull(orgUnit);

        LiveData<PagedList<DataSetReport>> dataSetReportListPage = Sdk.d2().dataValueModule().dataSetReports
                .byOrganisationUnitUid().eq(orgUnitUid).byPeriod().eq("201903").getPaged(10);

        dataSetReportListPage.observe(this, dataSetReports -> {
            adapter.submitList(dataSetReports);
            findViewById(R.id.dataSetReportsNotificator)
                    .setVisibility(dataSetReports.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }
}
