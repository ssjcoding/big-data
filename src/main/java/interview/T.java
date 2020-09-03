package interview;

/**
 * ${DESCRIPTION}
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/25 1:38 AM,
 */
public class T{
	public static void main(String[] args){
//		for(int i = 0; ; i++){
//			System.out.println(i);
//			new Thread(()->{
//				try{
//					TimeUnit.SECONDS.sleep(20);}catch(InterruptedException e){e.printStackTrace();}
//			}, String.valueOf(i)).start();
//		}
		int a=3;
		int b=3;
		String aa = "b";
		String bb = "b";
		Person person1 = new Person("b");
		Person person2 = new Person("b");

		System.out.println(person1 == person2);
	}
}
class Person{
	private String b;
	public Person(String b){
		this.b = b;
	}

	public String getB(){
		return b;
	}

	@Override
	public boolean equals(Object o){
		return this.b .equals(((Person)o).getB())? true : false;
	}

	@Override
	public int hashCode(){
		return b != null ? b.hashCode() : 0;
	}
}
