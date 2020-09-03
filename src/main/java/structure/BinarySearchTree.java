package structure;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 二叉查找树，也叫有序二叉树（ordered binary tree），排序二叉树（sorted binary tree），
 * 是指一颗空树或者具有下列性质的二叉树
 * 	（1）如果任意节点的左子树不空，则左子树上任一节点的值均小于它的根节点的值
 * 	（2）如果任意节点的右子树不空，则右子树上任一节点的值均大于它的根节点的值
 * 	（3）任意节点的左、右子树也分别为二叉查找树
 * 	（4）没有键值相等的节点（no duplicate nodes）
 *
 * 	深度优先遍历：先序遍历，中序遍历，后序遍历
 * 	广度优先遍历：层遍历
 *
 * 	进阶：
 * 		（1）如果数据存在重复该怎么进行存储：
 * 			a、将重复数据都存储在同一节点上，采用链表一次存储
 * 			b、把重复数据统一放到右子树
 *
 * 	搜索,插入,删除的复杂度等于树高，O(log(n)).
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/3/4 10:37 PM,
 */
public class BinarySearchTree{

	/**
	 * 根节点
	 */
	Node root;

	/**
	 * 查找：
	 *
	 * @param key 查询键
	 * @return
	 */
	public int find(int key){
		Node currentNode = root;
		while(currentNode != null){
			if(currentNode.getKey() == key){
				return key;
			}else if(currentNode.getKey() < key){
				if(currentNode.getRightChildren() != null){
					currentNode = currentNode.getRightChildren();
				}else{
					return -1;
				}
			}else if(currentNode.getKey() > key){
				if(currentNode.getLeftChildren() != null){
					currentNode = currentNode.getLeftChildren();
				}else{
					return -1;
				}
			}
		}
		return -1;
	}

	/**
	 * 插入
	 *	遵循只能在叶子节点插入原则
	 * @param key 查询键
	 * @return
	 */
	public void insert(int key){
		Node insertNode = new Node(key);
		Node currentNode = root;
		if(root == null){
			root = insertNode;
		}

		while(currentNode != null){
			if(currentNode.getKey() < key){
				if(currentNode.getRightChildren() != null){
					currentNode = currentNode.getRightChildren();
				}else{
					currentNode.setRightChildren(insertNode);
					break;
				}
			}else if(currentNode.getKey() > key){
				if(currentNode.getLeftChildren() != null){
					currentNode = currentNode.getLeftChildren();
				}else{
					currentNode.setLeftChildren(insertNode);
					break;
				}
			}
		}
	}

	/**
	 * 删除
	 * 	遵循原则：
	 * 	（1）如果被删除的节点为叶子节点，则直接删除
	 * 	（2）如果被删除的节点不是叶子节点，则将删除节点的右子树中的最小节点(或者是左子树最大节点)替换到当前删除节点位置
	 *
	 * @param key 查询键
	 * @return
	 */
	public void delete(int key){
		Node preNode = null;
		Node currentNode = root;
		while(currentNode != null){
			//查找要删除的节点
			if(currentNode.getKey() == key){
				if(currentNode.getLeftChildren()==null && currentNode.getRightChildren()==null){
					//叶子节点直接删除
					if(preNode.getLeftChildren() == currentNode){
						preNode.setLeftChildren(null);
					}else if(preNode.getRightChildren() == currentNode){
						preNode.setRightChildren(null);
					}
				}else if(currentNode.getLeftChildren()!=null && currentNode.getRightChildren()==null){
					//只有左子树，没有右子树，直接删除节点，让pre节点指向左子树节点
					if(preNode.getRightChildren().getKey() == key){
						preNode.setRightChildren(currentNode.getLeftChildren());
					}else{
						preNode.setLeftChildren(currentNode.getLeftChildren());
					}
				}else if(currentNode.getLeftChildren()==null && currentNode.getRightChildren()!=null){
					//只有右子树，没有左子树，直接删除节点，让pre节点指向右子树节点
					if(preNode.getRightChildren().getKey() == key){
						preNode.setRightChildren(currentNode.getRightChildren());
					}else{
						preNode.setLeftChildren(currentNode.getRightChildren());
					}
				}else{
					//具有左右子树，则将右子树最小节点替换当前节点，并删除
					Node minNode = getMinNode(currentNode.getRightChildren());
					System.out.println(minNode.getKey());
					minNode.setLeftChildren(currentNode.getLeftChildren());
					minNode.setRightChildren(currentNode.getRightChildren());
					if(preNode.getRightChildren().getKey() == key){
						preNode.setRightChildren(minNode);
					}else{
						preNode.setLeftChildren(minNode);
					}
				}
				break;
			}else if(currentNode.getKey() < key){
				if(currentNode.getRightChildren() != null){
					preNode = currentNode;
					currentNode = currentNode.getRightChildren();
				}else{
					break;
				}
			}else if(currentNode.getKey() > key){
				if(currentNode.getLeftChildren() != null){
					preNode = currentNode;
					currentNode = currentNode.getLeftChildren();
				}else{
					break;
				}
			}
		}

	}

	/**
	 * 获取树的最小值，并且切断父节点与其的连接
	 * @param node
	 * @return
	 */
	private Node getMinNode(Node node){
		Node minNode = null;
		if(node.getLeftChildren() == null){
			minNode = node;
		}else{
			minNode = getMinNode(node.getLeftChildren());
			if(minNode == node.getLeftChildren()){
				node.setLeftChildren(null);
			}
		}
		return minNode;
	}

	/**
	 * 遍历输出
	 * 	按照key值，从小到大输出（由于二叉查找树比当前节点小的key全位于左子树上，大的节点全位于右子树上，所以采用中序遍历就可以按照顺序输出）
	 *
	 * @return
	 */
	public void iterate(){
		if(root != null){
			theInOrderTraversal(root);
		}
	}

	/**
	 * 中序遍历
	 * 	递归实现方法
	 * @param node
	 */
	private void theInOrderTraversal(Node node){
		if(node.getLeftChildren() != null){
			theInOrderTraversal(node.getLeftChildren());
		}
		System.out.print(node.getKey());
		System.out.print(",");
		if(node.getRightChildren() != null){
			theInOrderTraversal(node.getRightChildren());
		}
	}

	/**
	 * 后序遍历
	 * @param node
	 */
	private void thePostOrderTraversal(Node node){
		if(node.getLeftChildren() != null){
			thePostOrderTraversal(node.getLeftChildren());
		}
		if(node.getRightChildren() != null){
			thePostOrderTraversal(node.getRightChildren());
		}
		System.out.print(node.getKey());
		System.out.print(",");
	}

	/**
	 * 先序遍历
	 * @param node
	 */
	private void thePreOrderTraversal(Node node){
		System.out.print(node.getKey());
		System.out.print(",");

		if(node.getLeftChildren() != null){
			thePreOrderTraversal(node.getLeftChildren());
		}
		if(node.getRightChildren() != null){
			thePreOrderTraversal(node.getRightChildren());
		}
	}

	/**
	 * 层遍历
	 * 	采用一个队列，
	 * 	（1）首先把头节点加入队列
	 * 	（2）出队一个节点，如果此节点存在左右子节点，则将左右子节点加入队列
	 * 	（3）依次执行第二步骤，直至队列为空
	 *
	 * @param node
	 */
	private void theLayerOrderTraversal(Node node){
		Queue<Node> queue = new LinkedBlockingDeque<>();

		if(node != null){
			queue.add(node);
			Node tmp = queue.poll();
			while(tmp != null){
				System.out.print(tmp.getKey());
				System.out.print(",");
				if(tmp.getLeftChildren() != null){
					queue.add(tmp.getLeftChildren());
				}
				if(tmp.getRightChildren() != null){
					queue.add(tmp.getRightChildren());
				}
				tmp = queue.poll();
			}
		}
	}


	public static void main(String[] args){
		int[] keys = {9,8,17,14,23,13,16,19,25,18,27};
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		for(int key : keys){
			binarySearchTree.insert(key);
		}
		int findKey = 25;
		if(binarySearchTree.find(findKey) == findKey){
			System.out.println("key=" + findKey + "在二叉查找树上");
		}else{
			System.out.println("key=" + findKey + "不在二叉查找树上");
		}
		binarySearchTree.iterate();
		System.out.println("");
		System.out.println("开始层遍历");
		binarySearchTree.theLayerOrderTraversal(binarySearchTree.root);
		System.out.println("");
		System.out.println("开始删除");
		binarySearchTree.delete(findKey);
		binarySearchTree.iterate();
	}
}

class Node{

	/**
	 * 键
	 */
	private int key;
	/**
	 * 左子树
	 */
	private Node leftChildren;
	/**
	 * 右子树
	 */
	private Node rightChildren;

	public Node(int key){
		this.key = key;
	}

	public int getKey(){
		return key;
	}

	public void setKey(int key){
		this.key = key;
	}

	public Node getLeftChildren(){
		return leftChildren;
	}

	public void setLeftChildren(Node leftChildren){
		this.leftChildren = leftChildren;
	}

	public Node getRightChildren(){
		return rightChildren;
	}

	public void setRightChildren(Node rightChildren){
		this.rightChildren = rightChildren;
	}
}


