package HW_20230101_StreamThread;

import java.util.ArrayList;
import java.util.List;

public class TestStream2 {
    List<String> list;

    public static void main(String[] args) {
        TestStream2 testStreamThread = new TestStream2();
        testStreamThread.start1();
        testStreamThread.start2();
    }
    private void initArray(String extra) {
        list = new ArrayList<>();
        list.add("T1 " + extra);
        list.add("T2 " + extra);
        list.add("T3 " + extra);
    }
    private void start1() {
        System.out.print("\n = start = start1==\n");
        initArray("start1");
        list.stream()
                .map(item -> {
                    System.out.println(item);
                    return item;
                });
        System.out.print("\n = finish = start1==");
    }
    private void start2() {
        System.out.print("\n = start = start2==\n");
        initArray("start2");
        list.stream()
                .map(item -> {
                    System.out.println(item);
                    return item;
                })
                .forEach(s -> {
                    System.out.println(s);
                });
        System.out.print("\n = finish = start2==");
    }
}