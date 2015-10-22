public View getView(int position, final View convertView, ViewGroup parent) {

Log.v("ToDoAdapter", "getView " + position);
...
TextView urlView = (TextView)itemView.findViewById(R.id.urlTextView);
urlView.setText(todo.getUrl());
urlView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.i("ToDoAdapter", "URL clicked");

/ / Notify the user if mobile network is used, if url is opened

        TelephonyManager tm = (TelephonyManager)v.getContext()
            .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm.getDataState() != TelephonyManager.DATA_DISCONNECTED) {
            Toast.makeText(v.getContext(), "Mobile network.", 
                Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(v.getContext(), "No mobile network.", 
                Toast.LENGTH_SHORT).show();
        }
        openWebPageInWebView(itemView, todo.getUrl());
    }
});


return itemView;
}
