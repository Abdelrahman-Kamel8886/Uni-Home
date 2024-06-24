package com.example.myvideo.ui.Explore.Article;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentArticleBinding;
import com.example.myvideo.ui.Explore.ExploreViewModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ArticleFragment extends Fragment {

    FragmentArticleBinding binding;
    BottomNavigationView nav;
    ExploreViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentArticleBinding.bind(view);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        viewModel = new ViewModelProvider(this).get(ExploreViewModel.class);

        binding.toolbar.setTitle(SharedModel.getSelected_article().getTitle());

        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.loadUrl(SharedModel.getSelected_article().getLink());
        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}