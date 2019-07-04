package com.example.android.androidskeletonapp.data.service;

import com.example.android.androidskeletonapp.data.Sdk;

import org.hisp.dhis.android.core.common.State;
import org.hisp.dhis.android.core.trackedentity.TrackedEntityInstance;

import java.util.List;

import static com.google.common.collect.Iterables.isEmpty;

public class SyncStatusHelper {

    public static int programCount() {
        return Sdk.d2().programModule().programs.count();
    }

    public static int dataSetCount() {
        return Sdk.d2().dataSetModule().dataSets.count();
    }

    public static int trackedEntityInstanceCount() {
        return Sdk.d2().trackedEntityModule().trackedEntityInstances.byState().neq(State.RELATIONSHIP).count();
    }

    public static int singleEventCount() {
        return Sdk.d2().eventModule().events.byEnrollmentUid().isNull().count();
    }

    public static int dataValueCount() {
        return Sdk.d2().dataValueModule().dataValues.count();
    }

    public static boolean isThereDataToUpload() {
        return Sdk.d2().trackedEntityModule()
                .trackedEntityInstances
                .byState()
                .in(State.TO_POST, State.TO_UPDATE, State.TO_DELETE).get().size() > 0;
    }
}
