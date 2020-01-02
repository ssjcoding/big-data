package basic.generic.pair1;

import basic.generic.Pair;

/**
 * 泛型测试
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 9:33 AM,
 */
public class PairTest1{
	public static void main(String[] args){
//		String[] words = {"Mary", "had", "a", "little", "lamb"};
//		Pair<String> mm = ArrayAlg.minmax(words);
//		System.out.println("min=" + mm.getFirst());
//		System.out.println("max=" + mm.getSecond());

		Object middle = ArrayAly.getMiddle("Hello", 0, null);
		System.out.println(middle);
	}
}

class ArrayAlg{
	/**
	 * Gets the minimum and maximum of an array of strings.
	 * @param a an array of strings
	 * @return a pair with the min and max value, or null if a is null or empty
	 */
	public static Pair<String> minmax(String[] a){
		if(a==null || a.length==0){
			return null;
		}
		String min = a[0];
		String max = a[0];
		for(int i=1; i<a.length; i++){
			if(min.compareTo(a[i]) > 0){
				min = a[i];
			}
			if(max.compareTo(a[i]) < 0){
				max = a[i];
			}
		}
		return new Pair<>(min, max);
	}

	public static <T extends Comparable> T min(T[] a){
		if(a==null || a.length==0){
			return null;
		}
		T smallest = a[0];
		for(int i=1; i<a.length; i++){
			if(smallest.compareTo(a[i]) > 0){
				smallest = a[i];
			}
		}
		return smallest;
	}
}


class ArrayAly{
	public static <T> T getMiddle(T... a){
		return a[a.length/2];
	}
}