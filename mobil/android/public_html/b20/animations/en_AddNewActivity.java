    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_main);//{speak:"Set main pager layout"}

        /Pager
        ToDoPagerAdapter tdpAdapter = new ToDoPagerAdapter();
        ViewPager mainPager = (ViewPager)findViewById(R.id.viewPagerMain);//{speak:"Find ViewPager"}
        mainPager.setAdapter(tdpAdapter);
        mainPager.setCurrentItem(1);//{speak:"Set the default page, add new."}


/*        Moved to ToDoPagerAdapter  instantiateItem method.

          final Calendar today = Calendar.getInstance();
          date = (DatePicker)findViewById(R.id.date);
          date.setMinDate(today.getTimeInMillis() - 10000);
*/
..
    }