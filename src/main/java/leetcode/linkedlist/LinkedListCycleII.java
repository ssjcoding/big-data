package leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 *  leetcode path: https://leetcode.com/problems/linked-list-cycle-ii/description/
 *
 *  Description:
 * 		Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/12/9 8:07 PM,
 */
public class LinkedListCycleII{

	public static void main(String[] args){
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		ListNode result = hasCycle2(head);
		System.out.print(result.val);
	}

	/**
	 * 采用Set进行存储，如果存在里面，则说明有重复
	 * @param head
	 * @return
	 */
	public static ListNode hasCycle(ListNode head) {
		Set<ListNode> tmpNodes = new HashSet<>();
		while(head!=null){
			if(tmpNodes.contains(head)){
				return head;
			}
			tmpNodes.add(head);
			head = head.next;
		}

		return null;
	}

	/**
	 * 采用快慢指针
	 * 链表头是X，环的第一个节点是Y，slow和fast第一次的交点是Z。各段的长度分别是a,b,c，如图所示。环的长度是L。slow和fast的速度分别是v,2v。
	 * 由slow和fast第一次在点Z相遇，我们可以得出以下等式：2(a+b)=(a+2b+c) => a=c
	 * 由此可见，a和c的长度一样。因此我们可以将slow重新定位到头结点，然后fast与slow以相同的速度前进，相遇的节点Y则是环的开始节点。
	 * @param head
	 * @return
	 */
	public static ListNode hasCycle2(ListNode head) {
		if(head==null || head.next==null){
			return null;
		}

		ListNode slowNode = head;
		ListNode fastNode = head;
		while(fastNode!=null && fastNode.next!=null){
			slowNode = slowNode.next;
			fastNode = fastNode.next.next;
			if(slowNode==fastNode){
				break;
			}
		}
		if(fastNode==null || fastNode.next==null){
			return null;
		}
		slowNode = head;
		while(slowNode!=fastNode){
			slowNode = slowNode.next;
			fastNode = fastNode.next;
		}
		if(slowNode==fastNode){
			return slowNode;
		}
		return null;
	}

}
