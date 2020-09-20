package com.example.songpay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ContestActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView1;

    Button button1;

    YouTubePlayer.OnInitializedListener listener; // 이벤트처리, 리스너가 초기화가 됐을 때,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);

        // 화면이 만들어졌을 때의 이벤트처리
        button1 = (Button) findViewById(R.id.button1);

        youTubePlayerView1=(YouTubePlayerView) findViewById(R.id.youtubeView1);

        listener = new YouTubePlayer.OnInitializedListener(){// 초기 이벤트 결정


            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("YPYwU2jeXIc"); // 영상 아이디, 여기서 영상을 띄움
                 }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };

        button1.setOnClickListener(new View.OnClickListener(){ // 버튼을 클릭하고,

            @Override
            public void onClick(View v) { // youtube가 받은 뒤, 해당 api를 초기화하고, 초기화 됐으
                youTubePlayerView1.initialize("AIzaSyCiKLJK44vLg0nMpFQ8fnjXFch7wVvMme8", listener); // api키 입력 후 리스너에 연결
            }
        });
    }
}
