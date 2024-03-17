package com.example.pregapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pregapplication.Adapters.ItemAdapter;
import com.example.pregapplication.R;
import com.example.pregapplication.models.ModelItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;


public class fragment extends Fragment {



        ArrayList <ModelItems> items = new ArrayList <>();
        ItemAdapter adapter;

        Context context;
        int pos;
        public fragment() {

        }

        public fragment(int i) {

                this.pos = i;
        }



        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                return inflater.inflate(R.layout.fragment_fragment, container, false);

        }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);

                final RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);


                String urltemp="";
                if (pos == 0) {
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=vice-news&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                }
                else if(pos == 1)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=ary-news&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 2)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 3)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 4)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=usa-today&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 5)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=cnn&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 6)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=fox-news&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 7)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=google-news&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 8)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=the-verge&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else if(pos == 9)
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=news24&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                else
                {
                        urltemp = "http://newsapi.org/v2/top-headlines?sources=abc-news&apiKey=83a54e7379dc4695aa33b4ace07230f9";
                }

                Ion.with(this).load("GET", urltemp).asJsonObject().setCallback(new FutureCallback <JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {

                                String status = result.get("status").getAsString();

                                if (status.equals("ok")) {

                                        JsonArray array = result.get("articles").getAsJsonArray();

                                        for (int i = 0; i < array.size(); i++) {
                                                JsonObject object = array.get(i).getAsJsonObject();

                                                String author = object.get("author").toString();

                                                String title = object.get("title").toString();
                                                title = title.substring(1, title.length() - 1);

                                                String url = object.get("url").toString();
                                                url = url.substring(1, url.length() - 1);

                                                String urlToImage = object.get("urlToImage").toString();
                                                urlToImage = urlToImage.substring(1, urlToImage.length() - 1);

                                                String date = object.get("publishedAt").toString();
                                                String content = object.get("content").toString();
                                                content = content.substring(1, content.length() - 1);

                                                items.add(new ModelItems(title, author, date, urlToImage, url));


                                        }

                                        adapter = new ItemAdapter(getActivity(), items);
                                        recyclerView.setAdapter(adapter);

                                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                        recyclerView.setLayoutManager(layoutManager);


                                        Log.e("Prashant", "success");
                                } else {
                                        Log.e("Prashant", "error");
                                }
                        }
                });

        }



}