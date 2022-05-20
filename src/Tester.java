public class Tester {
    public static void main(String[] args){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Bob", 10);
        map.put("Honey", 121);
        map.put("Honey", 123123);
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
        System.out.println(map.get("Wiggles"));
        System.out.println(map.remove("Ernie"));
        System.out.println(map.get("Ernie"));
    }
}
