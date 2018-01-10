#!/usr/bin/env bash

all_inputs=`ls ../collinearPoints/src/main/resources/input8.txt`


CP=../target/classes:../libs/algs4.jar

for input in ${all_inputs}
do
    base_name=`basename ${input}`
    dir_name=`dirname ${input}`
    input_prefix=`echo ${base_name}|cut -d'.' -f1`
    open ${dir_name}/${input_prefix}.png

    java -cp $CP BruteCollinearPoints $input
    java -cp $CP FastCollinearPoints $input
done