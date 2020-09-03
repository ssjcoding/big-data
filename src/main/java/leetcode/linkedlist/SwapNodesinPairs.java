package leetcode.linkedlist;

/**
 * 	leetcode path: https://leetcode.com/problems/swap-nodes-in-pairs/description/
 *
 *  Description:
 * 		Given a linked list, swap every two adjacent nodes and return its head.
 *
 * 	Note:
 * 		（1）Your algorithm should use only constant extra space.
 * 		（2）You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * 	Example:
 * 		Input: 1->2->3->4
 *		Output: 2->1->4->3
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/12/9 9:25 AM,
 */
public class SwapNodesinPairs{

	public static void main(String[] args){
//		ListNode head = new ListNode(1);
//		ListNode tmp = head;
//		for(int i=2; i<=4; i++){
//			tmp.next = new ListNode(i);
//			tmp = tmp.next;
//		}

//		ListNode head = new ListNode(3);
//		head.next = new ListNode(5);
		ListNode head = null;
		ListNode output = head;
		while(output!=null){
			System.out.print(output.val + "-->");
			output = output.next;
		}
		System.out.print("null");
		System.out.println("");

		ListNode result = swapPairs(head);
		while(result!=null){
			System.out.print(result.val + "-->");
			result = result.next;
		}
		System.out.print("null");
	}

	/**
	 * 	(1) 采用哨兵方法，创建哨兵节点，并将哨兵后记指向head
	 *  (2) 分别将preNode，currentNode，nextNode指向需要互换的单元的前一位，第一个节点，第二个节点
	 *  (3) 对三个节点进行互换：preNode.next = nextNode，currentNode.next = nextNode.next，nextNode.next = currentNode;
	 *  (4) 分别将preNode，currentNode，nextNode节点各项后面移动两个位置
	 * @param head
	 * @return
	 */
	public static ListNode swapPairs(ListNode head) {
		if(head==null){
			return head;
		}
		ListNode sentryNode = new ListNode(0);
		sentryNode.next = head;
		ListNode preNode = sentryNode;
		ListNode currentNode = sentryNode.next;
		ListNode nextNode = currentNode.next;
		while(nextNode != null){
			preNode.next = nextNode;
			currentNode.next = nextNode.next;
			nextNode.next = currentNode;
			preNode = currentNode;
			currentNode = currentNode.next;
			nextNode = currentNode==null? null : currentNode.next;
		}
		return sentryNode.next;
	}
}
