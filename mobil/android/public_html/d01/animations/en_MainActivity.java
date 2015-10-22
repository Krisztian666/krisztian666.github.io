    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_addnew:
...
            case R.id.action_settings://{speak:"Call ConfigureActivity from menu"}
                Log.i("TestTag", "Settings menu selected");
                Intent myIntentS = new Intent();
                myIntentS.setClassName("com.example.todo", 
                        "com.example.todo.ConfigureActivity");
                startActivity(myIntentS);
                return true;
            case R.id.action_about:
                showAboutDialog();
                return true;
...
        }

        return super.onOptionsItemSelected(item);
    }
