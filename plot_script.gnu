set terminal png size 800,600
set output 'execution_time.png'
set datafile separator ';'
set xlabel 'Input size'
set ylabel 'Execution Time (ns)'
set title 'Execution Time vs Input Size'
plot 'execution_time.csv' using 1:2 with points
set xrange [0:*]
set yrange [0:*]
