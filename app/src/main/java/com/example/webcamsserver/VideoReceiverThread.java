package com.example.webcamsserver;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class VideoReceiverThread extends Thread {
    private String serverIp;
    private int port;
    public VideoReceiverThread(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;    }
    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket clientSocket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            while (!isInterrupted()) {
                // Receive frame data (you'll need a protocol to define frame structure)
                byte[] sizeBytes = new byte[4];
                inputStream.readFully(sizeBytes);
                int frameSize = ByteBuffer.wrap(sizeBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
                //int frameSize = inputStream.readInt();
                Log.v("frame size",String.valueOf(frameSize));
                byte[] frameData = new byte[frameSize];
                inputStream.readFully(frameData);
                // Decode the frame
                // ... (Use VideoDecoder or suitable library)
                // Update the UI to display the decoded frame (use a mechanism to communicate with MainActivity)

                }
        } catch (IOException e) {            e.printStackTrace();        }    }}
