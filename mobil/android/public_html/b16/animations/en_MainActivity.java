...
    private void showAboutDialog(){//{img:"scr-about.png", speak:"Translate About"}
        Log.i("TestTag", "Creating About AlertDialog...");
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        builder.setMessage(R.string.about_txt);//{img:"scr-about-hu.png", speak:"Translate About"}
        builder.setTitle(R.string.about);
        builder.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TestTag", "About AlertDialog Button clicked.");
            }
        });
        builder.show();
...
    /**
     * Create Navigation Bar menu
     */
    private void addDrawerListItems() {
        String[] menuArray = { getResources().getString(R.string.add_new_todo),//{img:"scr-main-hu.png", speak:"Translate menu"}
                getResources().getString(R.string.action_settings),
                getResources().getString(R.string.about) };
        navBarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray);
        navBarDrawerList.setAdapter(navBarAdapter);
        navBarDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TestTag", "Navigation Bar List item selected: " + position);
                switch (position) {
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

