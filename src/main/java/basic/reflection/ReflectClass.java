package basic.reflection;

/**
 * 反射逻辑封
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/3 7:07 AM,
 */
public class ReflectClass{
	public static void main(String[] args){
		try{
			Class<?> cls = Class.forName("basic.reflection.Book");
			Book book = (Book)cls.newInstance();
			book.setAuthor("1");
			book.setName("sss");
			System.out.println(book.toString());
			cls.getDeclaredConstructors();
			if(book instanceof Object){
				System.out.println("ok");
			}
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}

	}
}
