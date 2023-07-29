#! /bin/bash

PS3="select an option: "
select choice in "create files" "write hello world" "replace world with bash"; do
  case $choice in
  "create files")
    for ((i = 1; i <= 5; i++)); do
      touch "file_$i".txt
    done
    ;;
  "write hello world")
    for ((i = 1; i <= 5; i++)); do
      echo "hello world" >"file_$i".txt
    done
    ;;
  "replace world with bash")
    for ((i = 1; i <= 5; i++)); do
      sed -i 's/world/bash/g' "file_$i".txt
    done
    ;;
  esac
done
