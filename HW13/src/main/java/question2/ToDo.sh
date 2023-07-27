showUndoneMenu() {
  while (true); do
    clear
    undoneMenu=("Show ToDo List" "Mark a New Task As Done" "Add a Task" "Remove a Task" "Search in ToDo List" "Back")
    for ((i = 0; i < ${#undoneMenu[@]}; i++)); do
      echo "$((i + 1))) " ${undoneMenu[i]}
    done
    read -p "Choose an option: " choice2
    case $choice2 in
    1)

      echo "Here are the list of your tasks:"
      cat -n <tasks/Undone.txt
      echo
      read -p "press enter to go back"
      ;;
    2)
      echo "you want to mark a task as done"
      cat -n <tasks/Undone.txt
      echo
      read -p "Which task has been accomplished?" line
      sed -n "$line p" tasks/Undone.txt >>tasks/Done.txt
      sed -i "$line d" tasks/Undone.txt
      read -p "Good job! press enter to go back"
      ;;
    3)
      read -p "What is the new task?" task
      echo $task >>tasks/Undone.txt

      read -p "New task added! press enter to go back"
      ;;
    4)
      echo "you want to remove a task"
      cat -n <tasks/Undone.txt
      echo
      read -p "Which task do you want to remove?" line
      sed -n "$line p" tasks/Undone.txt >>tasks/Removed.txt
      sed -i "$line d" tasks/Undone.txt
      read -p "Task removed! press enter to go back"
      ;;
    5)
      echo "you want to search for a task"
      read -p "press enter to go back"
      ;;
    6)
      break
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
  mainList=("ToDo Tasks" "Done Tasks" "Removed Tasks" "Exit")
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
    read -p "press enter to go back"
    ;;
  3)
    cat -b <tasks/Removed.txt
    echo
    read -p "press enter to go back"
    ;;

  4)
    clear
    exit
    ;;
  *)
    echo "Wrong Entry!"
    ;;
  esac

done
