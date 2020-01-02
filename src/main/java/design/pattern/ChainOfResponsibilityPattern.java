package design.pattern;

/**
 * 责任链模式
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/4 11:53 PM,
 */
public class ChainOfResponsibilityPattern{
	public static void main(String[] args){
		//组装责任链
		Handler handler1 = new ConcreteHandler1();
		Handler handler2 = new ConcreteHandler2();
		handler1.setNext(handler2);
		//提交请求
		handler1.handleRequest("two");
	}
}

//抽象处理着角色
abstract class Handler{
	private Handler next;
	public void setNext(Handler handler){
		this.next = handler;
	}
	public Handler getNext(){
		return next;
	}
	public abstract void handleRequest(String request);
}

class ConcreteHandler1 extends Handler{

	@Override
	public void handleRequest(String request){
		if(request.equals("one")){
			System.out.println("具体处理者1负责处理该请求！");
		}else{
			if(getNext()!=null){
				getNext().handleRequest(request);
			}else{
				System.out.println("没有人处理请求！");
			}
		}
	}
}

class ConcreteHandler2 extends Handler{

	@Override
	public void handleRequest(String request){
		if(request.equals("two")){
			System.out.println("具体处理者2负责处理该请求");
		}else{
			if(getNext()!=null){
				getNext().handleRequest(request);
			}else{
				System.out.println("没有人处理该请求！");
			}
		}
	}
}
