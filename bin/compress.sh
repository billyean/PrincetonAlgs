#!/usr/bin/env bash

# sh bin/compress.sh percolation Percolation PercolationStats
# sh bin/compress.sh queues Deque RandomizedQueue Permutation
# sh bin/compress.sh collinear Point BruteCollinearPoints FastCollinearPoints
component=$1
shift

for file in $@
do
  zip ${component}.zip ${component}/src/main/java/${file}.java
done
