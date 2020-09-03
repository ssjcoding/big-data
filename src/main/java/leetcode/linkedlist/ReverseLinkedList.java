package leetcode.linkedlist;

/**
 *  leetcode path: https://leetcode.com/problems/reverse-linked-list/
 *
 *  Description:
 * 		Reverse a singly linked list.
 * 	Example:
 * 		Input: 1->2->3->4->5->NULL
 *		Output: 5->4->3->2->1->NULL
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/12/8 11:40 PM,
 */
public class ReverseLinkedList{

	public static void main(String[] args){
		ListNode head = new ListNode(1);
		ListNode tmp = head;
		for(int i=2; i<=5; i++){
			tmp.next = new ListNode(i);
			tmp = tmp.next;
		}

		ListNode output = head;
		while(output!=null){
			System.out.print(output.val + "-->");
			output = output.next;
		}
		System.out.print("null");
		System.out.println("");

		ListNode result = reverseList(head);
		while(result!=null){
			System.out.print(result.val + "-->");
			result = result.next;
		}
		System.out.print("null");
	}

	/**
	 * 从头到尾反转列表
	 * 	（1）定义preNode，currentNode，nextNode三个节点，开始preNode为null，currentNode为第一个节点，
	 * 	nextNode为currentNode的后记；
	 * 	（2）循环执行：将currentNode的后记指向preNode，preNode=currentNode，currentNode=nextNode，nextNode后移一个节直到nextNode为null
	 * 	（3）最后将currentNode节点的后记指向preNode，并返回currentNode
	 *
	 * @param head
	 * @return
	 */
	public static ListNode reverseList(ListNode head) {

		if(head==null || head.next==null){
			return head;
		}
		ListNode preNode = null;
		ListNode currentNode = head;
		ListNode nextNode = head.next;
		while(nextNode!=null){
			currentNode.next = preNode;
			preNode = currentNode;
			currentNode = nextNode;
			nextNode = nextNode.next;
		}
		currentNode.next = preNode;
		return currentNode;
	}
}
