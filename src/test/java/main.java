import org.junit.Test;

import java.util.*;

public class main {


    @Test
    public void mainTest(){

        SortedMap<Integer, String> map = new TreeMap<Integer, String>();

        map.put(10, "10");
        map.put(18, "18");
        map.put(51, "51");
        map.put(8, "8");
        map.put(2, "2");
        map.put(5, "5");


        Iterator iterator = map.keySet().iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }

}
