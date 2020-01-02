package leetcode.LinkedList;

import java.util.HashSet;
import java.util.Set;

/**
 *  leetcode path: https://leetcode.com/problems/linked-list-cycle/description/
 *
 *  Description:
 * 		Given a linked list, determine if it has a cycle in it.
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/12/9 5:35 PM,
 */
public class LinkedListCycle{

	public static void main(String[] args){
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		boolean result = hasCycle2(head);
		System.out.print(result);
	}

	/**
	 * 采用Set进行存储，如果存在里面，则说明有重复
	 * @param head
	 * @return
	 */
	public static boolean hasCycle(ListNode head) {
		Set<ListNode> tmpNodes = new HashSet<>();
		while(head!=null){
			if(tmpNodes.contains(head)){
				return true;
			}
			tmpNodes.add(head);
			head = head.next;
		}

		return false;
	}

	/**
	 * 采用快慢指针，进行检验环，快指针一次走两个节点，慢指针一次走一个节点
	 * 当快指针为null时还没有相遇，说明没有换，如果快指针与慢指针相遇，说明有环
	 * @param head
	 * @return
	 */
	public static boolean hasCycle2(ListNode head) {
		if(head==null || head.next==null){
			return false;
		}

		ListNode slowNode = head;
		ListNode fastNode = head;
		while(fastNode!=null && fastNode.next!=null){
			slowNode = slowNode.next;
			fastNode = fastNode.next.next;
			if(slowNode==fastNode){
				return true;
			}
		}

		return false;
	}
}
