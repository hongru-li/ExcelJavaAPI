#/bin/bash

for dict in `ls`
do 
	if test -d $dict
	then 
		cd $dict
		fps=`cat fps`
		for file in `ls | grep *\.yuv`
		do
			`AL_Encoder.exe -cfg ../VQ_LOWB_264.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 1000 --max-bitrate 1200 -o ${file%.*}_1M.264` 
			`AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 2000 --max-bitrate 2400 -o ${file%.*}_2M.264` 
			`AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 3000 --max-bitrate 4500 -o ${file%.*}_3M.264` 
			`AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 4000 --max-bitrate 6000 -o ${file%.*}_4M.264` 
			`AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 5000 --max-bitrate 7500 -o ${file%.*}_5M.264`
			mkdir v205_avc_cuton_3B
			mv *.264 v205_avc_cuton_3B
			`AL_Encoder.exe -cfg ../VQ_LOWB_264.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 1000 --max-bitrate 1200 -o ${file%.*}_1M.264`                                           
                        `AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 2000 --max-bitrate 2400 -o ${file%.*}_2M.264`                                            
                        `AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 3000 --max-bitrate 4500 -o ${file%.*}_3M.264`                                            
                        `AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 4000 --max-bitrate 6000 -o ${file%.*}_4M.264`                                            
                        `AL_Encoder.exe -cfg ../VQ_ADA_264.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 5000 --max-bitrate 7500 -o ${file%.*}_5M.264`
              		mkdir v205_avc_cuton_0B                                                       
              		mv *.264 v205_avc_cuton_0B
			`AL_Encoder.exe -cfg ../VQ_LOWB_CUTOFF.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 1000 --max-bitrate 1200 -o ${file%.*}_1M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 2000 --max-bitrate 2400 -o ${file%.*}_2M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 3000 --max-bitrate 4500 -o ${file%.*}_3M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 4000 --max-bitrate 6000 -o ${file%.*}_4M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 3 --gop-length $[2*fps] --framerate $fps --bitrate 5000 --max-bitrate 7500 -o ${file%.*}_5M.264`
                        mkdir v205_avc_cutoff_3B
                        mv *.264 v205_avc_cutoff_3B
                        `AL_Encoder.exe -cfg ../VQ_LOWB_CUTOFF.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 1000 --max-bitrate 1200 -o ${file%.*}_1M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 2000 --max-bitrate 2400 -o ${file%.*}_2M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 3000 --max-bitrate 4500 -o ${file%.*}_3M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 4000 --max-bitrate 6000 -o ${file%.*}_4M.264`
                        `AL_Encoder.exe -cfg ../VQ_ADA_CUTOFF.cfg -i $file  --gop-numB 0 --gop-length $[2*fps] --framerate $fps --bitrate 5000 --max-bitrate 7500 -o ${file%.*}_5M.264`
                        mkdir v205_avc_cutoff_0B
                        mv *.264 v205_avc_cutoff_0B
		done
		cd ../
	fi
done		
