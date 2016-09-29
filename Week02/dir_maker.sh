#!/bin/bash

for i in `seq --format "%02.0f" 0 99`
do
	mkdir week-$i
	cd week-$i
	touch notes.txt
	touch hw.txt
	touch schedule.txt
	cd ..
done
