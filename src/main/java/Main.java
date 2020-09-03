import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/3/13 下午7:56,
 */
public class Main{
	public static void main(String[] args) throws InterruptedException{
		Collection<String> coll = new ArrayList<>();
		coll.add("abc1");
		coll.add("abc2");
		coll.add("abc3");
		coll.add("abc4");

		Iterator<String> it = coll.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}

		for(Iterator it2=coll.iterator(); it2.hasNext();){
			System.out.println(it2.next());
		}
	}

}
