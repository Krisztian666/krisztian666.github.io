/**
 * Setting displayed line items
 */
@Override
public View getView(int position, final View convertView, ViewGroup parent) {
    Log.i("ToDoAdapter", "getView " + position);
    final ToDo todo = toDoList.get(position);
    LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    final View itemView = inflater.inflate(R.layout.todorow, null);
...
    TextView urlView = (TextView)itemView.findViewById(R.id.urlTextView);
    urlView.setText(todo.getUrl());
    urlView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("ToDoAdapter", "URL clicked");
            openWebPageInWebView(itemView, todo.getUrl());//{speak:"Open page in WebView"}
        }
    });


    return itemView;
}
...

private void openWebPageInWebView(View view, String url) {
    if (!url.startsWith("http")){//{speak:"Fix the URL."}
        url = "http:/ /" + url;
    }

    final Dialog dialog = new Dialog(view.getContext());//{speak:"Create WebView in a dialog."}
    / /Disable Title
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.webview);
    dialog.setCancelable(true);
    TextView turl = (TextView)dialog.findViewById(R.id.weburl);
    turl.setText(url);

    WebView webView = (WebView)dialog.findViewById(R.id.webview);
    / /Sets the WebView built-in zoom mechanisms.
    webView.getSettings().setBuiltInZoomControls(true);
    / /Enables content URL access within WebView.
    webView.getSettings().setAllowContentAccess(true);
    / /Enable JavaScript execution.
    webView.getSettings().setJavaScriptEnabled(true);
    / /Set the WebView to overview mode.
    / /It zooms out the content to fit on screen by width.
    webView.getSettings().setLoadWithOverviewMode(true);
    / /Clears the resource cache.
    webView.clearCache(true);

    / /Load the linked pages in the webView
    webView.setWebViewClient(new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    });

    / /Load the url.
    webView.loadUrl(url);
    dialog.show();

}
