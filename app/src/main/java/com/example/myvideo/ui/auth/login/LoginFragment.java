package com.example.myvideo.ui.auth.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentLoginBinding;
import com.example.myvideo.ui.auth.reg.RegFragment;
import com.example.myvideo.ui.baseHome.HomeFragment;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.barLogin.setVisibility(View.GONE);

        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){
        binding.regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, new RegFragment(),"reg").addToBackStack("reg").commit();
            }
        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validation();
            }
        });
    }

    private void Validation() {


        String email = binding.emailEdit.getText().toString().trim();
        String password = binding.passEdit.getText().toString().trim();


        if (email.isEmpty()) {
            binding.emailEdit.setError("required");
        } else if (password.isEmpty()) {
            binding.passEdit.setError("required");
        }


        else{
            binding.barLogin.setVisibility(View.VISIBLE);
            login(email, password);

        }
    }
    private void login (String email ,String password){
        viewModel.login(email,password);

        viewModel.loged.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, new HomeFragment()).commit();
                binding.barLogin.setVisibility(View.GONE);
            }
        });

        viewModel.notloged.observe(getViewLifecycleOwner(), new Observer<Exception>() {
            @Override
            public void onChanged(Exception e) {
                Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                binding.barLogin.setVisibility(View.GONE);
            }
        });
    }

}