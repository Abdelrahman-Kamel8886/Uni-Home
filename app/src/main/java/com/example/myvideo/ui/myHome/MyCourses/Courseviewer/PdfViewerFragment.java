package com.example.myvideo.ui.myHome.MyCourses.Courseviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentPdfViewerBinding;
import com.example.myvideo.utils.SharedModel;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class PdfViewerFragment extends Fragment {

    FragmentPdfViewerBinding binding;
    BottomNavigationView nav;
    String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pdf_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPdfViewerBinding.bind(view);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        url = SharedModel.getSelected_pdf();
        new RetrivePdfStream().execute(url);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {

                // adding url
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // if url connection response code is 200 means ok the execute
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            // if error return null
            catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        @Override
        // Here load the pdf and dismiss the dialog box
        protected void onPostExecute(InputStream inputStream) {
            binding.viewer.fromStream(inputStream)
                    .enableSwipe(true)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false)
                    .scrollHandle(null)
                    .enableDoubletap(true)
                    .swipeHorizontal(false)
                    .fitEachPage(true)
                    .spacing(2)
                    .scrollHandle(new DefaultScrollHandle(getContext()))
                    .autoSpacing(true)
                    .enableAntialiasing(true)
                    .pageFling(true)
                    .load();

            binding.viewer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                    binding.bar.setVisibility(View.GONE);
                }
            });

        }

    }
}