 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
/*Inflate the menu; this adds items to the action bar if it is present.*/
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/* Handle action bar item clicks here. The action bar will
 automatically handle clicks on the Home/Up button, so long
 as you specify a parent activity in AndroidManifest.xml.*/
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
            case R.id.action_settings_display:
                Log.i("TestTag", "Display Settings menu selected");
                return true;
            case R.id.action_settings_system:
                Log.i("TestTag", "System Settings menu selected");
                return true;
            case R.id.action_about:
 /*Create new method for AboutDialog*/
                showAboutDialog(); //{speak:"Create new method."}
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
