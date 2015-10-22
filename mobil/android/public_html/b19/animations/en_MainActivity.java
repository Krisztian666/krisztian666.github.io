package com.example.todo;

import android.app.ActionBar;
...

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("TestTag", "Creating MainActivity...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Delete the addButton //{speak:"Delete the addButton"}
        button = (ImageButton)findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                startActivity(myIntent);
            }
        });
*/

...
        /Action Bar
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);//{speak:"Enable home button"}
...

    }

...

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_addnew:
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                startActivity(myIntent);
                return true;
            case R.id.action_settings:
                Log.i("TestTag", "Settings menu selected");
                return true;
            case R.id.action_about:
                showAboutDialog();
                return true;
            case android.R.id.home://{speak:"Handle ActionBar Home selected"}
                Log.i("TestTag", "ActionBar Home selected");
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
