public class Tester {
    public static void main(String[] args){
        HashMap<String, Integer> map = new HashMap<>(5, 0.8);
        map.put("Bob", 10);
        map.put("Honey", 121);
        map.put("AJ", 1231);
        map.put("Joshie", 32021);
        map.put("Greg", 590231);
        map.put("Rowley", 104013);
        map.put("Dunce", 2019);
        map.put("Trumpet", 4521);
        map.put("Karl", 90232);
        map.put("Junior", 1930);
        map.put("Zig", 932402);
        map.put("Wiggles", 241231);
        map.put("Ernie", 83722);
        map.put("Crispy", 1230341);
        map.put("Potato Chip Benny", 83402);
        map.put("Skunk", 62731);
        map.put("Bear", 42934);
        map.put("Hunter", 23901);
        map.put("Fregley", 5049);
        map.put("Rodrick", 12345);
        map.put("Steve", 8381);
        map.put("Alex", 429231);

        System.out.println(map.get("Wiggles"));
        System.out.println(map.get("Ernie"));
        System.out.println(map.get("Potato Chip Benny"));
        System.out.println(map.get("Trumpet"));
        System.out.println(map.get("Skunk"));
        System.out.println(map.getFilledSlots());
        System.out.println(map.getAvailableSlots());
        System.out.println(map.containsValue(429231));

        int count = 0;
        for(String key : map){
            count++;
            System.out.print(key + " ");
        }

        System.out.println();
        System.out.print(count);

    }
}
