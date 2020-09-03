package interview;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/24 12:48 AM,
 */
public class WeakHashMapDemo{
	public static void main(String[] args){
		myHashMap();
		System.out.println("======================");
		myWeakHashMap();
	}

	private static void myWeakHashMap(){
		Map<Integer, String> map = new WeakHashMap<>();
		Integer key = new Integer(1);
		String value = "HashMap";
		Integer key2 = new Integer(2);
		String value2 = "HashMap2";
		map.put(key, value);
		map.put(key2, value2);
		System.out.println(map);
		key = null;
		System.out.println(map);
		System.gc();
		System.out.println(map);
	}

	private static void myHashMap(){
		Map<Integer, String> map = new HashMap<>();
		Integer key = new Integer(1);
		String value = "HashMap";
		map.put(key, value);
		System.out.println(map);
		key = null;
		System.out.println(map);
		System.gc();
		System.out.println(map);
	}
}
