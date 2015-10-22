    public class MainActivity extends ListActivity {
...
    private ListView navBarDrawerList; //{speak:"Add ListView"}
    private ArrayAdapter<String> navBarAdapter; //{speak:"Add ArrayAdapter"}

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
...
        /*Navigation Bar*/
        navBarDrawerList = (ListView)findViewById(R.id.navBarList);
        addDrawerListItems();
...
    }
    
...
    /**
     * Create Navigation Bar menu
     */
    private void addDrawerListItems() {
        String[] menuArray = { "Add New ToDo", //{speak:"Ceate menu items."}
            "Display Settings", 
            "System Settings", 
            "About" 
        }};
        navBarAdapter = new ArrayAdapter<String>(this, 
                android.R.layout.simple_list_item_1, menuArray);
        navBarDrawerList.setAdapter(navBarAdapter);//{img:"scr-navbar-menu.png", speak:"Set navigation bar adapter"}
        navBarDrawerList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                    View view, int position, long id) {
                Log.i("TestTag", 
                        "Navigation Bar List item selected: " + position);
                switch (position) {
                    case 0:
                        Intent myIntent = new Intent();
                        myIntent.setClassName("com.example.todo", 
                                "com.example.todo.AddNewActivity");
                        startActivity(myIntent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        showAboutDialog();
                        break;
                }

            }
        });
    }

 