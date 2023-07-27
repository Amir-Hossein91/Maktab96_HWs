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
      cd tasks || return
      cat -n < Undone.txt
      cd ..
      echo
      read -p "press enter to go back"
      ;;
    2)
      echo "you want to search in the list"
      read -p "press enter to go back"
      ;;
    3)
      read -p "press enter to go back"
      ;;
    4)
      read -p "press enter to go back"
      ;;
    5)
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
  mainList=("Undone Tasks" "Done Tasks" "Removed Tasks" "Exit")
  for ((i = 0; i < ${#mainList[@]}; i++)); do
    echo "$((i + 1))) " ${mainList[i]}
  done
  read -p "Choose an option: " choice
  case $choice in
  1)
    showUndoneMenu
    ;;
  2)
    cat -b < tasks/Done.txt
    echo
    read -p "press enter to go back"
    ;;
  3)
    echo "you want to see the removed tasks"
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
