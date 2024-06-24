package com.example.myvideo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myvideo.MainActivity;
import com.example.myvideo.R;
import com.example.myvideo.ui.onboard.onboarding;


public class slideViewAdapter extends PagerAdapter {
    Context ctx;

    @Override
    public int getCount() {
        return 3;
    }

    public slideViewAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater=(LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_screen,container,false);

        ImageView logo=view.findViewById(R.id.slide_screen_image);

        ImageView ind1=view.findViewById(R.id.ind1);
        ImageView ind2=view.findViewById(R.id.ind2);
        ImageView ind3=view.findViewById(R.id.ind3);




        TextView title=view.findViewById(R.id.title);
        TextView desc=view.findViewById(R.id.desc);

        TextView next=view.findViewById(R.id.next);
        TextView back=view.findViewById(R.id.back);
        TextView btn_skip=view.findViewById(R.id.btn_skip);


        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //here we put register screen
                Intent intent=new Intent(ctx, MainActivity.class) ;
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);



            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onboarding.viewPager.setCurrentItem(position+1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onboarding.viewPager.setCurrentItem(position-1);

            }
        });

        switch (position){
            case 0:
                logo.setImageResource(R.drawable.story1);

                ind3.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind1.setImageResource(R.drawable.selected);

                title.setText("Start Learning now!");
                desc.setText("select your academic year and department then Browse Courses and Books Specified for you from your faculty");
                btn_skip.setText("Skip");

                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);

                break;
            case 1:
                logo.setImageResource(R.drawable.story2);

                ind3.setImageResource(R.drawable.unselected);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);

                title.setText("Books & Articles ");
                desc.setText("You can read books and articles now with ease through the application");
                btn_skip.setText("Skip");

                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);

                break;
            case 2:
                logo.setImageResource(R.drawable.story3);

                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                title.setText("Free Courses");
                desc.setText("Browse the free courses , Choose what you need , Start learning and develop your skills ");
                btn_skip.setText("Get Started");

                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                break;


        }

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
