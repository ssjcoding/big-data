package leetcode.linkedlist;

/**
 * 	leetcode path: https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 *
 * 	Description:
 * 		Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * 		k is a positive integer and is less than or equal to the length of the linked list.
 * 		If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * 	Note:
 * 		Only constant extra memory is allowed.
 * 		You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * 	Example:
 * 		Given this linked list: 1->2->3->4->5
 *		For k = 2, you should return: 2->1->4->3->5
 *		For k = 3, you should return: 3->2->1->4->5
 *
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/12/9 12:18 PM,
 */
public class ReverseNodesInKGroup{

	public static void main(String[] args){
//		ListNode head = new ListNode(1);
//		ListNode tmp = head;
//		for(int i=2; i<=5; i++){
//			tmp.next = new ListNode(i);
//			tmp = tmp.next;
//		}
//		ListNode head = null;
		ListNode head = new ListNode(1);
		ListNode output = head;
		while(output!=null){
			System.out.print(output.val + "-->");
			output = output.next;
		}
		System.out.print("null");
		System.out.println("");

		ListNode result = reverseKGroup(head, 2);
		while(result!=null){
			System.out.print(result.val + "-->");
			result = result.next;
		}
		System.out.print("null");
	}

	/**
	 * 	(1) 采用哨兵方法，创建哨兵节点，并将哨兵后记指向head
	 * 	(2) 定位需要反转的k的节点组合的开始位置reverseStartNode与reversePreEndNode开始前一个节点
	 *  (3) 分别将preNode，currentNode，nextNode指向需要互换的单元的前一位，第一个节点，第二个节点
	 *  (4) 判断currentNode开始的链表是否大于等于k，如果大于k进行反转，否则不进行反转
	 *  (5) 对k个节点进行k次循环并执行互换步骤：currentNode.next = preNode;preNode = currentNode;currentNode = nextNode;nextNode = currentNode==null?null:currentNode.next;
	 *  (6) 将未进行反转的链表与翻转后的链表进行连接，并移动reverseStartNode与reversePreEndNode到下一次反转相应位置，
	 *  (7) 判断是否继续反转，如果反转则执行(3)、(4)、(5)、(6)， 否则返回sentryNode.next
	 *
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
		if(head==null){
			return head;
		}
		ListNode sentryNode = new ListNode(0);
		sentryNode.next = head;
		//标识列表反转部分前一个节点，用于反转后的连接
		ListNode reversePreEndNode = sentryNode;
		//需要反转列表的第一个节点，用于反转后的连接
		ListNode reverseStartNode = sentryNode.next;
		//需要反转的前一个节点
		ListNode preNode = sentryNode;
		//需要反转的当前节点
		ListNode currentNode = sentryNode.next;
		//需要反转的下一个节点
		ListNode nextNode = currentNode.next;
		while(judge(currentNode, k)){
			//进行一个反转组合间的链表反转
			for(int i=0; i<k; i++){
				currentNode.next = preNode;
				preNode = currentNode;
				currentNode = nextNode;
				nextNode = currentNode==null?null:currentNode.next;
			}
			//进行反转后的链表连接
			reversePreEndNode.next = preNode;
			reverseStartNode.next = currentNode;
			//重新移动reverseStartNode与reverseStartNode
			reversePreEndNode = reverseStartNode;//reversePreEndNode移动到reverseStartNode，而不是preNode，是因为已经对中间链表进行了反转reverseStartNode成为了链表尾部
			reverseStartNode = currentNode;
		}
		return sentryNode.next;
	}

	/**
	 * 判断当前链表中是否大于等于k的节点
	 * @param head
	 * @param k
	 * @return
	 */
	public static boolean judge(ListNode head, int k){
		if(head==null){
			return false;
		}
		for(; k>1; k--){
			head = head.next;
			if(head==null){
				return false;
			}
		}
		return true;
	}
}
