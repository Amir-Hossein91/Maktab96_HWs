public class Main {
    public static void main(String[] args) {
        HW10Q1 question1 = new HW10Q1();
        HW10Q2 question2 = new HW10Q2();
        HW10Q3 question3 = new HW10Q3();
        HashMapWhitOrder<String, Integer> map = new HashMapWhitOrder<>();

        //question1.run();

       // question2.run();

       //question3.run();

        map.put(null, null);
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", null);
        map.put("chaharom" , 4);
        map.put("oldKey" , 5);
        map.put("fourth" , 4);
        map.put("chaharom" , 8);

        System.out.println("has key null? " + map.hasKey(null));
        System.out.println("is map empty? " + map.isEmpty());
        System.out.println("all the entries: " + map.entryList());
        map.setKeyFor(5,"oldKey", "newKey");
        System.out.println("all the entries: " + map.entryList());
    }
}
