package com.example.android.androidskeletonapp.ui.programs;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.android.androidskeletonapp.R;
import com.example.android.androidskeletonapp.data.Sdk;
import com.example.android.androidskeletonapp.ui.base.ListActivity;

import org.hisp.dhis.android.core.program.Program;
import org.hisp.dhis.android.core.program.ProgramType;

public class ProgramsActivity extends ListActivity implements OnProgramSelectionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp(R.layout.activity_programs, R.id.programsToolbar, R.id.programsRecyclerView);
        this.observePrograms();

    }

    @Override
    public void onProgramSelected(String programUid, ProgramType programType) {
    }

    private void observePrograms() {
        ProgramsAdapter adapter = new ProgramsAdapter(this);

        recyclerView.setAdapter(adapter);

        LiveData<PagedList<Program>> programs = Sdk.d2().programModule().programs.withStyle().withProgramStages()
                .getPaged(20);

        programs.observe(this, programPagedList -> {
            adapter.submitList(programPagedList);
        });
    }
}
