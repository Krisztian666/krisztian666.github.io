package com.example.todo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

/**
 * ImportActivity
 */
public class ImportActivityTest extends ActivityInstrumentationTestCase2<ImportActivity> {
    public ImportActivityTest() {
        super(ImportActivity.class);
    }


    /**
     *  Test if we configured our app and test code correctly
     *  Test import JSON
     */
    public void testActivityExists() {
        ImportActivity activity = getActivity();
        assertNotNull(activity);
        Button jsonButton =
                (Button) activity.findViewById(R.id.importJSONButton);

        TouchUtils.clickView(this, jsonButton);
    }


}
