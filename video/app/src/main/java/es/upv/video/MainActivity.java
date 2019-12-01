package es.upv.video;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends Activity {
    private VideoView mVideoView;
    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = findViewById(R.id.surface_view);
        //de forma alternative si queremos un streaming usar
        //mVideoView.setVideoURI(Uri.parse(URLstring));
        //mVideoView.setVideoPath("/mnt/sdcard/video.mp4");
        mVideoView.setVideoPath("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        mVideoView.start();
        mVideoView.requestFocus();
    }
}