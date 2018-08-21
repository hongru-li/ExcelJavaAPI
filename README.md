# ExcelJavaAPI

After you encode and measure PSNR of each encoded bistreams following the instrustions of [VQ Tool Chain](https://github.com/lhrotk/VQtest), 
use this to draw RD curves. This is a eclipse project, so you'd better import the code to eclipse in order to run.

## Run

You can directly run this in eclipse or export an excutable jar. You need to specify the path to your VQ test
folder in 'prop'. Sample executable jar, detailed instructions and sample PSNR data can be used for RD curves can be found [HERE](https://github.com/lhrotk/ExcelJavaAPI/tree/master/VQ_tools)

## Warning

Currently this only works on Windows because of the path format. And this version only use Y-PSNR to draw RD curves.(namely only read the first column in average.csv) You can modify ChartMaker.java: 56 for other accounting methods.
