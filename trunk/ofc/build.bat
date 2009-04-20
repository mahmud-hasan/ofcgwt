del open-flash-chart.swf
copy elements\axis\XAxisLabels-UnicodeRange.as elements\axis\XAxisLabels.as 
mxmlc -load-config+=obj\PartialFont.xml -incremental=false -o ..\open-flash-chart.swf
copy ..\open-flash-chart.swf C:\xampp\htdocs\ofc_website\OFC2\open-flash-chart2DZ.swf
mxmlc -load-config+=obj\FullFont.xml -incremental=false -o ..\open-flash-chart-full-embedded-font.swf
copy elements\axis\XAxisLabels-NoUnicodeRange.as elements\axis\XAxisLabels.as 
