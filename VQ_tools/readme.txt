1. encode and collect encoded bitstreams like in Tencent (refer to encode.sh)
2. PSNR calculation using measure2.py (to reduce file size, raw yuv files are deleted in this case)
3. calculate bitrate using extract_bitrate.sh (need correct frame and fps info in file 'frames' and 'fps')
4. modify 'prop' 
5. java -jar draw.jar