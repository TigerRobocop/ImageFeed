package com.unibratec.livia.picturefeed;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Livia on 20/10/2015.
 */
public class ImageListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    List<Image> mListImages;
    ImageAdapter mAdapter;

    SwipeRefreshLayout mSwipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_image, null);
        mSwipe = (SwipeRefreshLayout) v.findViewById(R.id.refresh);
        mSwipe.setOnRefreshListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListImages = new ArrayList<Image>();
        mAdapter = new ImageAdapter(getActivity(), mListImages);
        setListAdapter(mAdapter);

        LoadList();
    }

    private void LoadList() {

        ConnectivityManager ccnnMrg = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = ccnnMrg.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            ImageTask executeT = new ImageTask();
            executeT.execute();
        } else {
            Toast.makeText(getActivity(), "No connection available", Toast.LENGTH_SHORT).show();
            mSwipe.setRefreshing(false);
        }
    }

    public interface  OnImageClick{
        void imageClick(Image img);
    }


    @Override
    public void onRefresh() {
        LoadList();
    }

    private class ImageTask extends AsyncTask<Void, Void, List<Image>>{

        @Override
        protected void onPostExecute(List<Image> images) {
            super.onPostExecute(images);

            mListImages.addAll(images);
            mAdapter.notifyDataSetChanged();

            mSwipe.setRefreshing(false);
        }

        @Override
        protected List<Image> doInBackground(Void... params) {
            List<Image> result = new ArrayList<Image>();
            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://jsonplaceholder.typicode.com/photos")
                        .build();

                // Read the stream

                try {
                    Response response = client.newCall(request).execute();

                    String jsonString = response.body().string();

                    Gson gson = new Gson();

                    result = Arrays.asList(gson.fromJson(jsonString, Image[].class));

                } catch (Throwable e) {  //ao invés de IOException - classe parent de exception que abrange erros + exceptions
                    //para pegar erros no json string response por exemplo
                    e.printStackTrace();
                }

                return result;
            } catch (Throwable t) {
                t.printStackTrace();
            }

            return null;
        }
    }
}
