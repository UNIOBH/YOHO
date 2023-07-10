import java.util.HashMap;

public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("132", 13);
        Object o = hashMap.get("1");
        System.out.println(o);
//        System.out.println(o.toString());
    }

    @org.junit.jupiter.api.Test
    public void t() {
        int a = 1;
        int b = 2;
        s(a, b);
        System.out.println(a);
        System.out.println(b);
    }

    public void s(int a, int b) {
        int z = 0;
        z = a;
        a = b;
        b = z;
        System.out.println(a);
        System.out.println(b);
    }
}
