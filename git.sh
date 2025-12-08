#!/bin/bash

message=$1

if [ -z "$message" ];

then echo "commit message can not be empty"

exit 1
    
fi
echo "++++++++++++++++++++++"
echo "checking git status"
echo "++++++++++++++++++++++"
git status

echo "++++++++++++++++++++++"
echo "adding to git"
echo "++++++++++++++++++++++"
git add .

echo "++++++++++++++++++++++"
echo "commiting git"
echo "++++++++++++++++++++++"
git commit -m"$message"

echo "++++++++++++++++++++++"
echo "opening  Gitkraken "
echo "++++++++++++++++++++++"
gitkraken