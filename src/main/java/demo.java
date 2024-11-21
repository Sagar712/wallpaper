import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class demo {
    public static void main(String[] args){
        Map map = new HashMap();
        String str1 = new String("Sagar");
        map.put(str1, 22);
        map.put(new String("Sagar"), 23);
        System.out.println(map.get(str1));
    }
}
