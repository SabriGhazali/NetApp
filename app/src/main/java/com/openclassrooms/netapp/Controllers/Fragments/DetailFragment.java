package com.openclassrooms.netapp.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.netapp.Models.GithubUserInfo;
import com.openclassrooms.netapp.R;
import com.openclassrooms.netapp.Utils.GithubStreams;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class DetailFragment extends Fragment {



    @BindView(R.id.fragment_detail_image_view) ImageView mImageViewAvatar;
    @BindView(R.id.fragment_detail_text_repos) TextView mTextViewRepos;
    @BindView(R.id.fragment_detail_text_followers) TextView mTextViewFollowers;
    @BindView(R.id.fragment_detail_text_following) TextView mTextViewFollowing;
    @BindView(R.id.fragment_detail_text_login) TextView mTextViewLogin;

    private Disposable disposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        configGithubUser();

        return view;
    }



    private void configGithubUser() {

        Bundle bundle=getArguments();
        final String GithubUserString = bundle.getString("GithubUserObject");
        this.disposable= GithubStreams
                .streamFetchUserInfos(GithubUserString)
                .subscribeWith(new DisposableObserver<GithubUserInfo>() {
                                   @Override
                                   public void onNext(GithubUserInfo githubUserInfo) {

                                       if(githubUserInfo!=null){

                                       Glide.with(getContext()).load(githubUserInfo.getAvatarUrl()).into(mImageViewAvatar);
                                       mTextViewLogin.setText(githubUserInfo.getLogin().toString());
                                       mTextViewFollowers.setText(githubUserInfo.getFollowers().toString());
                                       mTextViewFollowing.setText(githubUserInfo.getFollowing().toString());
                                       mTextViewRepos.setText(githubUserInfo.getPublicRepos().toString());}

                                   }

                                   @Override
                                   public void onError(Throwable e) {

                                       Log.d("DetailFragment", "onError() returned: " +e.getMessage() );
                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposeWhenDestroy();
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }


}
