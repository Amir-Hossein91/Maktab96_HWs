showUndoneMenu() {
  while (true); do
    clear
    undoneMenu=("Show ToDo List" "Mark a Task As Done" "Add a New Task" "Remove a Task" "Search in ToDo List" "Back")
    for ((i = 0; i < ${#undoneMenu[@]}; i++)); do
      echo "$((i + 1))) " ${undoneMenu[i]}
    done
    read -p "Choose an option: " choice2
    case $choice2 in
    1)

      echo "Here are the list of your tasks:"
      cat -b <tasks/Undone.txt
      echo
      read -p "press enter to continue"
      ;;
    2)
      IFS="$(printf '\t')"
      echo "you want to mark a task as done"
      cat -b <tasks/Undone.txt
      echo
      read -p "Which task has been accomplished?" line
      read -ra newArray <<<"$(sed -n "$line p" tasks/Undone.txt)"
      echo -e ${newArray[0]}"\t"$(date +"%Y-%m-%d %T") >>tasks/Done.txt
      sed -i "$line d" tasks/Undone.txt
      read -p "Great job! press enter to continue"
      ;;
    3)
      read -p "What is the new task?" task
      regesterDate=$(date +"%Y-%m-%d %T")
      echo -e $task"\t"$regesterDate >>tasks/Undone.txt
      read -p "New task added! press enter to continue"
      ;;
    4)
      IFS="$(printf '\t')"
      echo "you want to remove a task"
      cat -b <tasks/Undone.txt
      echo
      read -p "Which task do you want to remove?" line
      read -ra newarray <<<"$(sed -n "$line p" tasks/Undone.txt)"
      echo -e ${newarray[0]}"\t"$(date +"%Y-%m-%d %T") >>tasks/Removed.txt
      sed -i "$line d" tasks/Undone.txt
      read -p "Task removed! press enter to continue"
      ;;
    5)
      read -p "Enter the phrase you're searching for: " phrase
      result=$(grep -n "$phrase" tasks/Undone.txt)
      if [ -z "$result" ]
      then
        echo "No results found!"
      else
        echo "$result"
      fi
      read -p "press enter to continue"
      ;;
    6)
      break
      ;;
    *)
      echo "Wrong Entry!"
      read -p "press enter to go continue"
      ;;
    esac
  done
}



mkdir -p tasks
cd tasks || return
touch Undone.txt Done.txt Removed.txt
cd ..
while (true); do
  clear
  mainList=("ToDo Tasks" "Done Tasks" "Removed Tasks" "Search" "Exit")
  for ((i = 0; i < ${#mainList[@]}; i++)); do
    echo "$((i + 1))) " ${mainList[i]}
  done
  read -p "Choose an option: " choice
  case $choice in
  1)
    showUndoneMenu
    ;;
  2)
    cat -b <tasks/Done.txt
    echo
    read -p "press enter to continue"
    ;;
  3)
    cat -b <tasks/Removed.txt
    echo
    read -p "press enter to continue"
    ;;

  4)
    read -p "Enter the phrase you're searching for: " phrase
          result=$(grep -n "$phrase" tasks/Undone.txt tasks/Done.txt tasks/Removed.txt)
          if [ -z "$result" ]
          then
            echo "No results found!"
          else
            echo "$result"
          fi
          read -p "press enter to continue"
    ;;
  5)
    clear
    exit
    ;;
  *)
    echo "Wrong Entry!"
    read -p "press enter to go continue"
    ;;
  esac
done
