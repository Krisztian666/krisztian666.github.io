
    /**
     * Create Navigation Bar menu
     */
    private void addDrawerListItems() {
        String[] menuArray = { "Add New ToDo", "Settings", "About" };//{img:"scr-menu2.png", speak:"Modify menu"}
        navBarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray);
        navBarDrawerList.setAdapter(navBarAdapter);
        navBarDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TestTag", "Navigation Bar List item selected: " + position);
                switch (position) {//{speak:"Use 3 menu items"}
                    case 0:
                        Intent myIntent = new Intent();
                        myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                        startActivity(myIntent);
                        break;
                    case 1:
                        break;
                    case 2:
                        showAboutDialog();
                        break;
                }

            }
        });
    }

...

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        / Handle action bar item clicks here. The action bar will
        / automatically handle clicks on the Home/Up button, so long
        / as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /noinspection SimplifiableIfStatement
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
        }


        return super.onOptionsItemSelected(item);
    }