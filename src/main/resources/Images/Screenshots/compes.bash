#!/bin/bash
mogrify -format jpg *.png
mogrify -quality 60 *.jpg
rm *.png
