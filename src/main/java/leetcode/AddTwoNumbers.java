package leetcode;

/**
 * Description:
 * 		You are given two non-empty linked lists representing two non-negative integers.
 * 		The digits are stored in reverse order and each of their nodes contain a single digit.
 * 		Add the two numbers and return it as a linked list.
 * 		You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 * 	Example:
 * 		Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *		Output: 7 -> 0 -> 8
 *		Explanation: 342 + 465 = 807.
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/3/15 下午3:31,
 */
public class AddTwoNumbers{

	/**
	 * my solution
	 *
	 * Solution Description:
	 * 		Bitwise summation
	 *
	 * Submission Detail:
	 * 		1562 / 1562 test cases passed.
	 * 		Runtime: 154 ms.
	 * 		runtime beats 1.55 % of java submissions.
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result;
		String resultLine = "";
		ListNode addition1 = l1;
		ListNode addition2 = l2;
		boolean flag = false;
		int resultTmp = 0;
		do{
			int a = 0;
			int b = 0;
			if(addition1 != null){
				a = addition1.val;
			}
			if(addition2 != null){
				b = addition2.val;
			}
			if(flag){
				resultTmp = a + b +1;
			}else{
				resultTmp = a + b;
			}

			if(resultTmp>9){
				flag = true;
				resultTmp = resultTmp-10;
			}else{
				flag = false;
			}

			resultLine = resultLine + resultTmp + ",";

			try{
				addition1 = addition1.next;
			}catch(NullPointerException e){
				addition1 = null;
			}
			try{
				addition2 = addition2.next;
			}catch(NullPointerException e){
				addition2 = null;
			}

		}while(addition1 != null || addition2 != null);

		if(flag){
			resultLine = resultLine + 1 + ",";
		}

		resultLine = resultLine.substring(0, resultLine.length()-1);

		result = construct(resultLine);

		return result;

	}

	/**
	 * 构造函ListNode数据对象
	 * @param line
	 * @return
	 */
	public static ListNode construct(String line){
		ListNode listNode = null;
		String[] values = line.split(",");
		for(int i=values.length-1; i>=0; i--){
			int value = Integer.valueOf(values[i]);
			if(listNode == null){
				listNode = new ListNode(value);
			}else{
				ListNode placeholder = listNode;
				listNode = new ListNode(value);
				listNode.next = placeholder;
			}
		}
		return listNode;
	}

	public static void main(String[] args){
		int a = Integer.valueOf("0123");
		System.out.println(a);
		String valuesLine1 = "2,4,3";
		String valuesLine2 = "5,6,4";

		ListNode l1 = construct(valuesLine1);
		ListNode l2 = construct(valuesLine2);

		ListNode listNode = addTwoNumbers(l1, l2);

		int i = 0;
		do{
			System.out.print(listNode.val);
			try{
				listNode = listNode.next;
			}catch(NullPointerException e){
				listNode = null;
			}
			i++;
		}while(listNode != null);

		System.out.println("=========="+i);
	}


	//---------Official Solution-------

	/**
	 * Approach #1 (Brute Force)
	 *
	 * 	Solution Description:
	 * 		Just like how you would sum two numbers on a piece of paper, we begin by summing the least-significant digits,
	 * 		which is the head of l1 and l2. Since each digit is in the range of 0...9, summing two digits may "overflow".
	 * 		For examples 5+7=12. In this case, we set the current digit to 2 and bring over the carry=1 to the next interation.
	 * 		carry must be either 0 or 1 because the largest possible sum of two digits (including the carry) is 9+9+1=19.
	 *
	 *
	 * 	The pseudocode is as following:
	 * 		Initialize current node to dummy head of the returning list.
	 * 		Initialize carry to 0.
	 * 		Initialize p and q to head of l1 and l2 respectively.
	 * 		Loop through lists l1 and l2 until you reach both ends.
	 * 			Set x to node p's value. If p has reached the end of l1, set to 0.
	 * 			Set y to node q's value. If q has reached the end of l2, set to 0.
	 * 			Set sum = x+y+carry.
	 * 			Update carry = sum/10.
	 * 			Create a new node with the digit value of (sum mod 10) and set it to current node's next, then advance current node to next.
	 *			Advance both p and q.
	 *		Check if carry=1, if so append a new node with digit 1 to the returning list.
	 *		Return dummp head's next node.
	 *
	 *	Tips：
	 *		Note that we use a dummy head to simplify the code.
	 *		Without a dummy head, you would have a write extra conditional statements to initialize the head's value.
	 *
	 * 	Complexity Analysis
	 * 		Time complexity: O(max(m,n)).
	 * 			Assume that m and n represents the length of l1 and l2 respectively,
	 * 			the algorithm above iterates at most max(m,n) times.
	 *		Space complexity: O(max(m,n)).
	 *			The length of the new list is at most max(m,n) + 1.
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers_1(ListNode l1, ListNode l2){

		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr=dummyHead;
		int carry = 0;

		while(p!=null || q!=null){
			int x = (p!=null) ? p.val : 0;
			int y = (q!=null) ? q.val : 0;
			int sum = x + y + carry;
			carry = sum/10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if(p!=null){
				p=p.next;
			}
			if(q!=null){
				q=q.next;
			}
		}

		if(carry>0){
			curr.next = new ListNode(carry);
		}

		return dummyHead.next;
	}


	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

}


