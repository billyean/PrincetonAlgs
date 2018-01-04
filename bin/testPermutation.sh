#!/usr/bin/env bash

CP=../target/classes:../libs/algs4.jar

java -cp $CP Permutation 3 < ../queues/src/main/resources/distinct.txt