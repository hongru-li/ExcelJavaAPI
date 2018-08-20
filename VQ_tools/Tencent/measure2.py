###############################################################################
#
# Hongru Li from Aupera Tech(www.auperatech.com)
# 2018-05-07
# only works on windows
# 
###############################################################################

import os
import sys
import getopt
import subprocess
import commands
import re

#-------------------------------------------------------------------------------
# AUTOMATIC SETTINGS
#-------------------------------------------------------------------------------

#TODO

#-------------------------------------------------------------------------------
# FUNCTIONS
#-------------------------------------------------------------------------------

# execute command, and return the output  
def execCmd(cmd):  
    r = os.popen(cmd)  
    text = r.read()  
    r.close()  
    return text 

#-------------------------------------------------------------------------------
# MAIN
#-------------------------------------------------------------------------------

# reg=ur'log'
# if re.match(reg, 'video'):
# 	print('success')

itemList = os.listdir(os.getcwd())

for item in itemList:
	if os.path.isdir(item):
		os.chdir(item)
		videoList = os.listdir(os.getcwd())
		for video in videoList:							#*.y4m raw YUV file used for base
			if os.path.splitext(video)[1] == '.yuv':	
				subdir = os.listdir(os.getcwd())		#sub directory in folder
				res = execCmd('type res')
				#width = res.split('x')[0]
				#height = res.split('x')[1]
				width = '1920'
				height = '1080'
				for folder in subdir:
					reg = ur'video'
					if folder=='v205_avc_cutoff_0B' or folder=='v205_avc_cuton_0B' or folder=='v205_avc_cuton_3B' or folder=='v205_avc_cutoff_3B' or folder=='x264':			#is a video foler
						#use MSU to compare
						os.chdir(folder)
						encodedList = os.listdir(os.getcwd())		#all encoded files
						os.chdir('../')
						for encoded in encodedList:
							command = 'msu_metric_64.exe -f '+video+' IYUV -yw '+width+' -yh '+height+' -f '+folder+'/'+encoded+' -cng CUSTOM "PSNR_SSIM_'+encoded.split('.')[0]+'.csv"  -cod "'+folder+'" -metr psnr -cc YYUV -metr ssim_precise -cc YYUV -sc 1 -af2 average.csv'
							command = command.replace('\n','')
							#print(command)
							os.system(command)
		os.chdir('../')