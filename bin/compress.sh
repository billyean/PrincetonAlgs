#!/usr/bin/env bash

component=$1
shift

for file in $$
do
  zip ${component}.zip component/src/main/java/${file}.java
done
