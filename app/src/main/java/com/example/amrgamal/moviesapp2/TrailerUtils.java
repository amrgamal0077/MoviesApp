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
 * Created by AmrGamal on 01/02/2019.
 */

@SuppressWarnings("ALL")
class TrailerUtils {
    private static URL createUrl(String Url)
    {
        URL url=null;
        try {
            url = new URL(Url);
        } catch (MalformedURLException e) {
            Log.e(TrailerUtils.class.getName(), "Problem building the URL ", e);
        }
        return url;
    }
    private static ArrayList<TrailerInfo> extraxtTrailer(String json){
        ArrayList<TrailerInfo> TrailerArray = new ArrayList<>();

        try {
            JSONObject baseObject= new JSONObject(json);
            JSONArray result=baseObject.getJSONArray("results");
            for (int i=0;i<result.length();i++)
            {
                JSONObject object=result.getJSONObject(i);
                TrailerArray.add(new TrailerInfo(
                                object.getString("name"),
                                object.getString("key")
                                )
                        );

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return TrailerArray;

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
                Log.e(TrailerUtils.class.getName(), "Error response code: " +httpURLConnection.getResponseCode());
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
    public static ArrayList<TrailerInfo> fetchdata(String Url)
    {

        URL url =createUrl(Url);
        String jsonData=null;
        try {
            jsonData=makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(MoviesUtils.class.getName(), "Problem making the HTTP request.", e);
        }

        return extraxtTrailer(jsonData);
    }
}
