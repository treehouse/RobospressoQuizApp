#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

for dir in "$DIR"/* ; do
  [ -d "${dir}" ] || continue # if not a directory, skip
  gcloud beta test android run --type instrumentation --app "$dir"/app/build/outputs/apk/app-debug-unaligned.apk --test "$dir"/app/build/outputs/apk/app-debug-androidTest-unaligned.apk --device-ids Nexus6 --os-version-ids 23 --locales en --orientations portrait
done


#for dir in "$DIR"/* ; do
#  [ -d "${dir}" ] || continue # if not a directory, skip
#  for tuf in "$dir"/app/build/outputs/apk/* ; do
#    if [[ $tuf == *"unaligned"* ]]; then
#      echo "$tuf"
#    fi
#  done
#done