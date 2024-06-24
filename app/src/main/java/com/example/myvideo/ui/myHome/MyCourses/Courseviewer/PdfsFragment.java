package com.example.myvideo.ui.myHome.MyCourses.Courseviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvideo.R;
import com.example.myvideo.adapters.PdfsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentPdfsBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class PdfsFragment extends Fragment {

    FragmentPdfsBinding binding;
    PdfsRecyclerAdapter adapter = new PdfsRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pdfs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPdfsBinding.bind(view);

        adapter.setList((ArrayList<CourseModel.PdfsDTO>) SharedModel.getSelected_course().getPdfs());
        binding.recyclerc.setAdapter(adapter);


        adapter.setOnItemClick(new PdfsRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(CourseModel.PdfsDTO pdf) {
                SharedModel.setSelected_pdf(pdf.getLink());
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new PdfViewerFragment(),"pdfv").addToBackStack("pdfv").commit();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}