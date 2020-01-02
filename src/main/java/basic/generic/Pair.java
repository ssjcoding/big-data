package basic.generic;

/**
 * 泛型示例
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/11/30 9:26 AM,
 */
public class Pair<T>{
	private T first;
	private T second;

	public Pair(){
		first = null;
		second = null;
	}

	public Pair(T first, T second){
		this.first = first;
		this.second = second;
	}

	public void setFirst(T newValue){
		this.first = newValue;
	}

	public void setSecond(T newValue){
		this.second = newValue;
	}

	public T getFirst(){
		return first;
	}

	public T getSecond(){
		return second;
	}


}
