#!/usr/bin/env bash

all_inputs=`ls *.txt`

CP=./out/production/percolation/:${HOME}/Downloads/algs4.jar

for input in ${all_inputs}
do
    input_prefix=`echo ${input}|cut -d'.' -f1`
    open ${input_prefix}.png
    java -cp $CP PercolationVisualizer $input
done