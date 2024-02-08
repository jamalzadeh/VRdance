package com.example.webcamsserver;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.webcamsserver.VideoReceiverThread;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    VideoReceiverThread receiverThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        // Start the receiver thread
        receiverThread = new VideoReceiverThread("192.168.137.1", 8080);
        receiverThread.start();    }
        // ... (Handle thread updates to play video - See VideoReceiverThread)
        @Override
        protected void onDestroy() {
        super.onDestroy();
        if (receiverThread != null) {
            receiverThread.interrupt();
        }
    }}