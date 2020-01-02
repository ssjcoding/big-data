package basic.reflection;

/**
 * 图书对象
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/3 7:05 AM,
 */
public class Book{
	private final static String TAG = "BookTag";
	private String name;
	private String author;

	public Book() {
	}

	private Book(String name, String author) {
		this.name = name;
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book{" +
				"name='" + name + '\'' +
				", author='" + author + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	private String declaredMethod(int index) {
		String string = null;
		switch (index) {
			case 0:
				string = "I am declaredMethod 1 !";
				break;
			case 1:
				string = "I am declaredMethod 2 !";
				break;
			default:
				string = "I am declaredMethod 1 !";
		}

		return string;
	}
}
