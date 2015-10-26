package com.example.todo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.data.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle ToDo elements
 */
public class ToDoAdapter extends BaseAdapter {

    private final List<ToDo> toDoList;
    private Context context;

    public ToDoAdapter(final Context pContext, ArrayList<ToDo> pToDoList) {
        this.toDoList = pToDoList;
        this.context = pContext;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    public void addItem(ToDo pToDo) {
        toDoList.add(pToDo);
    }

    public void setList(List<ToDo> pToDoList) {
        toDoList.clear();
        for (ToDo td:pToDoList) {
            toDoList.add(td);
        }
    }

    public void deleteRow(int position){
        toDoList.remove(position);
    }

    @Override
    public Object getItem(int position) {
        return toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Setting displayed line items
     */
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        Log.v("ToDoAdapter", "getView " + position);
        final ToDo todo = toDoList.get(position);
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.todorow, null);

        TextView textViewName = (TextView) itemView.findViewById(R.id.nameTextView);
        textViewName.setText(todo.getName());

        TextView textViewDate = (TextView) itemView.findViewById(R.id.dateTextView);
        textViewDate.setText(todo.getFormattedDate());

        TextView urlView = (TextView)itemView.findViewById(R.id.urlTextView);
        urlView.setText(todo.getUrl());
        urlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ToDoAdapter", "URL clicked");
                TelephonyManager tm = (TelephonyManager)v.getContext().getSystemService(Context.TELEPHONY_SERVICE);
                if (tm.getDataState() != TelephonyManager.DATA_DISCONNECTED) {
                    Toast.makeText(v.getContext(), "Mobile network.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "No mobile network.", Toast.LENGTH_SHORT).show();
                }
                openWebPageInWebView(itemView, todo.getUrl());
            }
        });


        return itemView;
    }

    private void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    private void openWebPageInWebView(View view, String url) {
        if (!url.startsWith("http")){
            url = "http://" + url;
        }

        final Dialog dialog = new Dialog(view.getContext());
        //Disable Title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.webview);
        dialog.setCancelable(true);
        TextView turl = (TextView)dialog.findViewById(R.id.weburl);
        turl.setText(url);

        WebView webView = (WebView)dialog.findViewById(R.id.webview);
        //Sets the WebView built-in zoom mechanisms.
        webView.getSettings().setBuiltInZoomControls(true);
        //Enables content URL access within WebView.
        webView.getSettings().setAllowContentAccess(true);
        //Enable JavaScript execution.
        webView.getSettings().setJavaScriptEnabled(true);
        //Set the WebView to overview mode.It zooms out the content to fit on screen by width.
        webView.getSettings().setLoadWithOverviewMode(true);
        //Clears the resource cache.
        webView.clearCache(true);

        //Load the linked pages in the webView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //Load the url.
        webView.loadUrl(url);
        dialog.show();

    }

}
