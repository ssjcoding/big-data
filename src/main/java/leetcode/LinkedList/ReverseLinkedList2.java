package leetcode.LinkedList;

/**
 * 	leetcode path: https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 * 	Description:
 * 		Reverse a linked list from position m to n. Do it in one-pass.
 *
 * 	Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * 	Example:
 * 		Input: 1->2->3->4->5->NULL, m = 2, n = 4
 *		Output: 1->4->3->2->5->NULL
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/12/9 12:55 AM,
 */
public class ReverseLinkedList2{
	public static void main(String[] args){
//		ListNode head = new ListNode(1);
//		ListNode tmp = head;
//		for(int i=2; i<=5; i++){
//			tmp.next = new ListNode(i);
//			tmp = tmp.next;
//		}

		ListNode head = new ListNode(3);
		head.next = new ListNode(5);
		ListNode output = head;
		while(output!=null){
			System.out.print(output.val + "-->");
			output = output.next;
		}
		System.out.print("null");
		System.out.println("");

		ListNode result = reverseBetweenImprovement(head, 1, 2);
		while(result!=null){
			System.out.print(result.val + "-->");
			result = result.next;
		}
		System.out.print("null");
	}

	/**
	 *  （1）首先定位要反转的起始位置，然后进行反转
	 * 	（2）定义preNode，currentNode，nextNode三个节点，开始preNode为null，currentNode为第一个节点，
	 * 	nextNode为currentNode的后记；
	 * 	（3）循环执行：将currentNode的后记指向preNode，preNode=currentNode，currentNode=nextNode，nextNode后移一个节直到nextNode为null
	 * 	（4）最后将currentNode节点的后记指向preNode，并返回currentNode
	 *
	 * @param head
	 * @return
	 */
	public static ListNode reverseBetween(ListNode head, int m, int n) {

		if(n<=m){
			return head;
		}
		ListNode preEndNode = null;
		ListNode tmpNode = head;
		for(int index=1; index<m; index++){ //遍历位于反转前面的节点
			preEndNode = tmpNode;
			tmpNode = tmpNode.next;
		}

		ListNode preNode, reverseStartNode=null, currentNode, nextNode = null;

		if(preEndNode==null){
			preNode = null;
			currentNode = head;
			nextNode = head.next;
		}else{
			preNode = null;
			reverseStartNode = preEndNode.next;
			currentNode = preEndNode.next;
			nextNode = preEndNode.next.next;
		}
		for(int i=m; i<n; i++){
			currentNode.next = preNode;
			preNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.next;
		}
		currentNode.next = preNode;
		if(preEndNode==null){
			head.next = nextNode;
			return currentNode;
		}else{
			reverseStartNode.next = nextNode;
			preEndNode.next = currentNode;
			return head;
		}
	}


	/**
	 *  （1）首先定位要反转的起始位置，然后进行反转
	 * 	（2）定义preNode，currentNode，nextNode三个节点，开始preNode为null，currentNode为第一个节点，
	 * 	nextNode为currentNode的后记；
	 * 	（3）循环执行：将currentNode的后记指向preNode，preNode=currentNode，currentNode=nextNode，nextNode后移一个节直到nextNode为null
	 * 	（4）最后将currentNode节点的后记指向preNode，并返回currentNode
	 *	 改进地方：
	 *		使用哨兵原理，减少部分情况判断情况判断(比如，是否从第一节点位置开始反转)
	 * @param head
	 * @return
	 */
	public static ListNode reverseBetweenImprovement(ListNode head, int m, int n) {

		if(n<=m){
			return head;
		}

		ListNode sentryNode = new ListNode(0);
		sentryNode.next = head;
		ListNode preEndNode = null;
		ListNode tmpNode = sentryNode;
		//遍历位于反转前面的节点
		for(int index=0; index<m; index++){
			preEndNode = tmpNode;
			tmpNode = tmpNode.next;
		}
		ListNode preNode = null;
		ListNode reverseStartNode = preEndNode.next;
		ListNode currentNode = preEndNode.next;
		ListNode nextNode = preEndNode.next.next;
		for(int i=m; i<n; i++){
			currentNode.next = preNode;
			preNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.next;
		}
		currentNode.next = preNode;
		reverseStartNode.next = nextNode;
		preEndNode.next = currentNode;
		return sentryNode.next;
	}

}
