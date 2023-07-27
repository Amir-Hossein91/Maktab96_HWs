#echo -n "Enter the name of a country: "
#read COUNTRY
#
#echo -n "The official language of $COUNTRY is "
#
#case $COUNTRY in
#
#  Lithuania)
#    echo -n "Lithuanian"
#    ;;
#
#  Romania | Moldova)
#    echo -n "Romanian"
#    ;;
#
#  Italy | "San Marino" | Switzerland | "Vatican City")
#    echo -n "Italian"
#    ;;
#
#  *)
#    echo -n "unknown"
#    ;;
#esac
showUndoneMenu() {
  while ( true ) do
  clear
  undoneMenu=("Show Undone List" "Search in Undone List" "Back")
  for (( i = 0; i < ${#undoneMenu[@]}; i++ )); do
        echo "$((i+1))) " ${undoneMenu[i]}
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
  for (( i = 0; i < ${#mainList[@]}; i++ )); do
      echo "$((i+1))) " ${mainList[i]}
  done
    read -p "Choose an option: " choice
    case $choice in
    1)
      showUndoneMenu
      ;;
    2)
      cd tasks || return
      cat -n < Done.txt
      cd ..
      echo
      read -p "press enter to go back"
      ;;
    3)
      echo "you want to see the removed tasks"
      read -p "press enter to go back"
      ;;

    4)
      exit
      ;;
    *)
      echo "Wrong Entry!"
      ;;
    esac


done


