protected Object doInBackground(Object[] params) {
ArrayList<ToDo> newList = new ArrayList<ToDo>();
/ / import mode
if (MODE_HTTP_URL_CONN == (int)params[0]) {
    try {
        if(HttpURLConnectionHandler.checkInternetConnection(context)){
            / / use HttpURLConnection
            / /newList = HttpURLConnectionHandler.readStreamFrom(params[1].toString());
            / / use the deprecated Apache HttpClient
            newList = HttpClientHandler.readStreamFrom(params[1].toString());

        } else {
            Log.i(TAG, "No connection.");
            return new Result(false, context.getString(R.string.no_connection));
        }
    } catch (Exception e){
        e.printStackTrace();
        return new Result(false, e.getMessage());
    }
}

for (ToDo td: newList) {
    DataManager.getInstance().addTodo(td);
}
return new Result(true, "");
}