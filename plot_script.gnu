set terminal png size 800,600
set output 'src/main/java/mdisc/sprintc/graphsAndPng/us17_output_graph.png'
set datafile separator ';'
set xlabel 'Input size'
set ylabel 'Execution Time (ns)'
set title 'Execution Time vs Input Size'
plot 'src/main/java/mdisc/sprintc/output/us18_output.csv' using 1:2 with points
set xrange [0:*]
set yrange [0:*]
