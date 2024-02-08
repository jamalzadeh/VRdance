import cv2
import subprocess
import requests  # Or any suitable HTTP library

# Webcam and encoding settings
webcam_index = 2
video_codec = 'libx264'
hls_time = 5  # Length of each video segment in seconds

# Server settings
server_url = "http://localhost:8000/upload"

# Start webcam capture
cap = cv2.VideoCapture(webcam_index)

# FFMPEG command for HLS encoding
ffmpeg_cmd = [
    'ffmpeg',
    '-i', 'pipe:0',  # Input from the pipe
    '-c:v', video_codec,
    '-f', 'rawvideo',
    '-hls_time', str(hls_time), 
    '-hls_list_size', '0',  # No limit to the playlist size
    '-hls_flags', 'delete_segments',  # Delete old segments
    'out.m3u8'  # Output playlist
]

# Start FFMpeg process
p = subprocess.Popen(ffmpeg_cmd, stdin=subprocess.PIPE)

while True:
    ret, frame = cap.read()
    
    if not ret:
        break

    # Check if the frame is valid
    if frame is None:
        print("Error: Invalid frame received from webcam")
        continue 

    # Debug: Save the frame temporarily 
    cv2.imwrite("debug_frame.jpg", frame) 

    # Send frame to FFMpeg's stdin
    p.stdin.write(frame.tobytes())

    # Send HLS chunks as they become available
    # (Implement sending logic to your server using 'requests' or similar)

cap.release()
p.terminate()