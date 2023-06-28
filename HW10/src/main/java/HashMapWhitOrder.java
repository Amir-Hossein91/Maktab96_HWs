import java.util.*;

public class HashMapWhitOrder<K , V> {
    private List<K> keys;
    private List<V> values;

    public HashMapWhitOrder(){
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public static void main(String[] args) {
        Map<String, Integer>  realMap = new HashMap<>();

        HashMapWhitOrder<String, Integer> map = new HashMapWhitOrder<>();
        map.put(null, null);
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", null);
        map.put("chaharom" , 5);
        map.put("next" , 4);
        map.put("fourth" , 4);
        map.put("chaharom" , 8);


        System.out.println("has key null? " + map.hasKey(null));
        System.out.println("is map empty? " + map.isEmpty());
        System.out.println("all the entries: " + map.entryList());
        map.setKeyFor(4,"amir", "bonus");
        System.out.println("all the entries: " + map.entryList());

    }

    public void put (K key , V value){
        if(!keys.contains(key)){
            keys.add(key);
            values.add(value);
        }
        else {
            values.set(keys.indexOf(key), value);
        }
    }

    public boolean hasKey(K key){
        return keys.contains(key);
    }

    public boolean isEmpty(){
        return keys.isEmpty();
    }

    public List<String> entryList(){
        List<String> entryList = new ArrayList<>();
        for(int i = 0 ; i < keys.size(); i++){
            String entry;
            if(keys.get(i) == null){
                if(values.get(i) == null)
                    entry = null + " = " + null;
                else
                    entry = null + " = " + values.get(i).toString();
            }
            else {
                if(values.get(i) == null)
                    entry = keys.get(i).toString() + " = " + null;
                else
                    entry = keys.get(i).toString() + " = " + values.get(i).toString();
            }

            entryList.add(entry);
        }
        return entryList;
    }

    public boolean remove(K key){
        if(!keys.contains(key))
            return false;

        int index = keys.indexOf(key);
        keys.remove(key);
        values.remove(index);
        return true;
    }

    public boolean remove(K key , V value){

        if(!keys.contains(key) || values.get(keys.indexOf(key)) != value)
            return false;

        int index = keys.indexOf(key);
        keys.remove(key);
        values.remove(index);
        return true;
    }

    public void setKeyFor(V value, K oldKey, K newKey){
        if(!remove(oldKey , value))
            return;

        put(newKey, value);
    }


}
