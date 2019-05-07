package com.example.amrgamal.moviesapp2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by amrgamal on 15/12/2018.
 */

@SuppressWarnings("ALL")
class MoviesUtils {
    private MoviesUtils(){

    }
    private static URL createUrl(String Url)
    {
        URL url=null;
        try {
            url = new URL(Url);
        } catch (MalformedURLException e) {
            Log.e(MoviesUtils.class.getName(), "Problem building the URL ", e);
        }
        return url;
    }
    private static ArrayList<MoviesInfo> extraxtMovies(String json){
        ArrayList<MoviesInfo> moviesArray = new ArrayList<>();

        try {
            JSONObject baseObject= new JSONObject(json);
            JSONArray result=baseObject.getJSONArray("results");
            for (int i=0;i<result.length();i++)
            {
                JSONObject object=result.getJSONObject(i);
                  moviesArray.add(new MoviesInfo(
                          object.getString("original_title"),
                          object.getString("poster_path"),
                          object.getString("overview"),
                          object.getString("release_date"),
                          object.getString("vote_average"),
                                  Integer.valueOf(object.getString("id"))
                          )
                  );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesArray;

    }
    private static String makeHttpRequest(URL url) throws IOException {
        String JsonResponse ="";
        if (url==null)
            return null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream=null;
        try {
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode()==200)
            {
                inputStream=httpURLConnection.getInputStream();
                JsonResponse=readFromStream(inputStream);
            }
            else {
                Log.e(MoviesUtils.class.getName(), "Error response code: " +httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }

        return JsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    public static ArrayList<MoviesInfo> fetchdata(String Url)
    {
        URL url =createUrl(Url);
        String jsonData=null;
        try {
            jsonData=makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(MoviesUtils.class.getName(), "Problem making the HTTP request.", e);
        }

        return extraxtMovies(jsonData);
    }
}
