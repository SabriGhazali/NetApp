package com.openclassrooms.netapp.Controllers.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.netapp.Controllers.Fragments.DetailFragment;
import com.openclassrooms.netapp.R;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        View toolBar =findViewById(R.id.tool_bar_detail_main);
        TextView textViewToolBar = toolBar.findViewById(R.id.toolbar_name);
        textViewToolBar.setText("Detail");

        ImageView imageViewReturnButton = toolBar.findViewById(R.id.toolbar_return_button);
        imageViewReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
finish();            }
        });






        this.configureAndShowDetailFragment();






    }

    private void configureAndShowDetailFragment() {

        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.activity_detail_frame_layout);

        if (detailFragment == null) {
            {   detailFragment = new DetailFragment();
            Intent intent=getIntent();
            String GithubUserString=intent.getStringExtra("GithubUserObject");
            Bundle bundle = new Bundle();
            bundle.putString("GithubUserObject",GithubUserString );
            detailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_detail_frame_layout, detailFragment)
                    .commit();}
        }
    }



}



