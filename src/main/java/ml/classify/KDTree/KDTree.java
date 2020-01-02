package ml.classify.KDTree;

import java.util.ArrayList;
import java.util.List;

/**
 * k-Dimension Tree
 *
 * 		描述：
 * 			(1) kd树是二叉树。kd树是一种对k维空间中的实例点进行存储以便对其进行快速检索的数形数据结构
 * 			(2) kd树是二叉树，标识对k维空间的一个划分（partition）。构造kd树相当于不断地用垂直于坐标轴的超平面将k维空间切分，构成一系列的k维超矩形区域。
 * 				kd树的每个节点对应于一个k维超巨型区域。
 *
 *
 * 		kd构建算法：
 * 			(1) 在K维数据集合中选择具有最大方差的维度k（最大方差表示维度上取值的离散程度；也可以顺序取各个维度），然后在该维度上选择中值m为pivot对该数据集合进行划分，
 * 				得到两个子集合；同时创建一个树节点node，用于存储；
 * 			(2) 对两个子集合重复(1)步骤的过程，直至所有子集合中的数据保存到叶子节点（left node）。
 *
 * 		最近邻查找算法：
 * 			(1) 将查询数据Q从根节点开始，按照Q与各个节点的比较结果向下访问kd-tree，直至到达叶子节点。
 * 					其中Q与节点的比较指的是Q对应于节点中的k维度上的值与m进行比较，若Q(k)<m，则访问左子树，否则访问右子树。达到叶子节点时，计算Q与叶子节点上保存的数据之间的距离，
 * 					记录下最小距离对应的数据点，记为当前"最近邻点"，它们之间的距离小于Dcur。
 * 			(2) 进行回溯（Backtracking）操作，该操作是为了找到离Q更近的"最近邻点"。即判断未被访问过的分支里是否还有离Q最近的点，它们之间的距离小于Dcur。
 * 					如果Q与其父节点下的未被访问过的分支之间的距离小于Dcur，则认为该分支中存在离P更近的数据，进入该节点，进行(1)步骤一样的查找过程，如果找到更
 * 					近的数据点，则更新为当前的"最近邻点"Pcur，并更新Dcur。
 * 					如果Q与其父节点下的未被访问过的分支之间距离大于Dcur，则说明该分支内不存在与Q更近的点。
 * 				回溯的过程是从下往上进行的，知道回溯到根节点时已经不存在与P更近的分支为止。
 *
 * 		怎样判断未被访问过的树分支Branch里是否还有离Q更近的点？
 * 			(1) 从几何空间上来看，就是判断以Q为中心center和以Dcur为半径Radius的超球面（Hypersphere）与树分支Branch代表的超矩形（Hyperrectangle）之间是否相交。
 * 			(2) 在实现中，我们可以有两种方式来求Q与树分支Branch之间的距离。第一种是在构造树的过程中，就记录下每个子树中包含的所有数据在该子树对应的维度k上的边界参数[min, max]；第二种是在构造树的过程中，记录下每个子树所在的分割维度k和分割值m，（k, m），Q与子树的距离则为|Q(k) - m|。
 *
 *		kd树特性：
 * 			kd树算法，用于降低knn中训练数据查找时间
 *			如果	实例点是随机分布的，kd树搜索的平均计算复杂度是O(logN), 这里N是训练示例数。
 *			kd树更适合用于训练实例数远大于空间维数是的k近邻搜索。
 *			当空间维数接近训练实例数时，它的效率会迅速下降，几乎接近限行扫描
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2018/6/13 下午6:01,
 */
public class KDTree{

	/**
	 * 构建kd树 返回根节点
	 * 		在此次构建过程中，在K维数据集合中选择维度k的方法为：L=(J mod k)+1 （依次顺序选择）
	 *			L=(J mod k)+1。其中，j为当前节点的节点深度，k为k维空间（给定实例点的k个维度）。根节点的节点深度为0.此公式可看为：依次循环实例点的k个维所对应的坐标轴。
	 *			Kd树的节点（分割点）为L维上所有实例点的中位数
	 *
	 * @param nodeList
	 * @param index
	 * @return
	 */
	public Node buildKDTree(List<Node> nodeList, int index){
		if(nodeList==null || nodeList.size()==0){
			return null;
		}
		quickSortForMedian(nodeList, index, 0, nodeList.size()-1);//中位数排序
		Node root = nodeList.get(nodeList.size()/2); //中位数 当做根节点
		root.dim = index;
		List<Node> leftNodeList = new ArrayList<>(); //放入左侧区域的节点 包括包含与中位数等值的节点
		List<Node> rightNodeList = new ArrayList<>();

		for(Node node : nodeList){
			if(root != node){
				if(node.getData(index) <= root.getData(index)){
					leftNodeList.add(node); //左子区域 包含与中位数等值的节点
				}else{
					rightNodeList.add(node);
				}
			}
		}

		int newIndex = index+1;//进入下一个维度
		if(newIndex >= root.data.length){
			newIndex = 0;
		}

		root.left = buildKDTree(leftNodeList, newIndex);//添加左右子区域
		root.right = buildKDTree(rightNodeList, newIndex);

		if(root.left != null){
			root.left.parent = root; //添加父指针
		}
		if(root.right != null){
			root.right.parent = root; //添加父指针
		}

		return root;
	}

	/**
	 * 查询最近邻
	 *
	 *
	 * @param root kd树
	 * @param q 查询点
	 * @param k k值
	 * @return
	 */
	public List<Node> searchKNN(Node root, Node q, int k){
		List<Node> knnList = new ArrayList<>();
		searchBorther(knnList, root, q, k);
		return knnList;
	}

	/**
	 * 搜索最近邻实现方法
	 *
	 * @param knnList
	 * @param root
	 * @param q
	 * @param k
	 */
	public void searchBorther(List<Node> knnList, Node root, Node q, int k){
		Node leafNNode = searchLeaf(root, q);
		double curD = q.computeDiatance(leafNNode); //最近近似点与查询点的距离，也就是球体的半径
		leafNNode.distance = curD;
		maintainMaxHeap(knnList, leafNNode, k);
//		System.out.println("leaf1" + leafNNode.getData(leafNNode.parent.dim));
		while(leafNNode != root){
			if(getBrother(leafNNode) != null){
				Node brother = getBrother(leafNNode);
//				System.out.println("brother1" + brother.getData(brother.parent.dim));
				if(curD > Math.abs(q.getData(leafNNode.parent.dim)-
				leafNNode.parent.getData(leafNNode.parent.dim)) || knnList.size()<k){
					//这样可能在另一个子区域中存在更近似的点
					searchBorther(knnList, brother, q, k);
				}
			}
//			System.out.println("leaf2" + leafNNode.getData(leafNNode.parent.dim));
			leafNNode = leafNNode.parent;//返回上一级
			double rootD = q.computeDiatance(leafNNode);//最近近似点与查询点的距离 也就是球体的半径
			leafNNode.distance = rootD;
			maintainMaxHeap(knnList, leafNNode, k);
		}
	}

	/**
	 * 获取兄弟节点
	 *
	 * @param node
	 * @return
	 */
	public Node getBrother(Node node){
		if(node == node.parent.left){
			return node.parent.right;
		}else{
			return node.parent.left;
		}
	}

	/**
	 * 查询到叶子节点
	 *
	 * @param root
	 * @return
	 */
	public Node searchLeaf(Node root, Node q){
		Node leaf=root, next=null;
		int index = 0;
		while(leaf.left!=null || leaf.right!=null){
			if(q.getData(index) < leaf.getData(index)){
				next = leaf.left; //进入左侧
			}else if(q.getData(index) > leaf.getData(index)){
				next = leaf.right;
			}else{
				//当取到中位数时，判断左右子区域哪个更加近
				if(q.computeDiatance(leaf.left) < q.computeDiatance(leaf.right)){
					next = leaf.left;
				}else{
					next = leaf.right;
				}
			}
			if(next == null){
				break;//下一个节点是空时 结束了
			}else{
				leaf = next;
				if(++index>=root.data.length){
					index=0;
				}
			}
		}
		return leaf;
	}

	/**
	 * 维护一个k的最大堆
	 *
	 * @param listNode
	 * @param newNode
	 * @param k
	 */
	public void maintainMaxHeap(List<Node> listNode, Node newNode, int k){

		if(listNode.size() < k){
			maxHeapFixUp(listNode, newNode);
		}else if(newNode.distance < listNode.get(0).distance){
			//比堆顶的要小 还需要向下修复 覆盖堆顶
			maxHeapFixDown(listNode, newNode);
		}

	}

	/**
	 * 从上往下修复 将会覆盖第一个节点
	 *
	 * @param listNode
	 * @param newNode
	 */
	private void maxHeapFixDown(List<Node> listNode, Node newNode){
		listNode.set(0, newNode);
		int i = 0;
		int j = i*2+1;
		while(j<listNode.size()){
			if(j+1<listNode.size() && listNode.get(j).distance<listNode.get(j+1).distance){
				// 选出子节点中较大的点，第一个条件是满足右子树不为空
				j++;
			}

			if(listNode.get(i).distance >= listNode.get(j).distance){
				break;
			}

			Node t = listNode.get(i);
			listNode.set(i, listNode.get(j));
			listNode.set(j, t);

			i = j;
			j = i*2+1;
		}
	}


	/**
	 * 从下往上修复
	 *
	 * @param listNode
	 * @param newNode
	 */
	private void maxHeapFixUp(List<Node> listNode, Node newNode){
		listNode.add(newNode);
		int j = listNode.size()-1;
		int i = (j+1)/2-1; //i是j的parent节点
		while(i>=0){
			if(listNode.get(i).distance>=listNode.get(j).distance){
				break;
			}
			Node t = listNode.get(i);
			listNode.set(i, listNode.get(j));
			listNode.set(j, t);

			j=i;
			i=(j+1)/2-1;
		}
	}

	/**
	 * 使用快排进行一个中位数的查找 完了之后返回的数组size/2即中位数
	 *
	 * @param nodeList
	 * @param index
	 * @param left
	 * @param right
	 */
	private void quickSortForMedian(List<Node> nodeList, int index, int left, int right){

		if(left>=right || nodeList.size()<=0){
			return;
		}

		Node kn = nodeList.get(left);
		//取得向量指定索引的值
		double k = kn.getData(index);
		int i = left, j = right;

		//控制每一次遍历的结束条件，i与j相遇
		while(i < j){

			//从右向左找一个小于i处的值，并填入i的位置
			while(nodeList.get(j).getData(index)>=k && i<j){
				j--;
			}
			nodeList.set(i, nodeList.get(j));
			while(nodeList.get(i).getData(index)<=k && i<j){
				i++;
			}
			nodeList.set(j, nodeList.get(i));
		}

		nodeList.set(i, kn);

		if(i==nodeList.size()/2){
			/**
			 * 完成中位数的排序了，但并不是完成所有数的排序，这个终止条件只是保证中位数是正确的。
			 * 去掉该条件，可以保证在递归的作用下，将所有的数进行排序
			 */
			return;
		}else if(i<nodeList.size()/2){
			//只需要排序右边就可以了
			quickSortForMedian(nodeList, index, i+1, right);
		}else{
			//只需要排序左边就可以了
			quickSortForMedian(nodeList, index, left, i-1);
		}

	}

	public static void main(String[] args){
		List<Node> nodeList=new ArrayList<Node>();

		nodeList.add(new Node(new double[]{5,4}));
		nodeList.add(new Node(new double[]{9,6}));

		nodeList.add(new Node(new double[]{8,1}));
		nodeList.add(new Node(new double[]{7,2}));
		nodeList.add(new Node(new double[]{2,3}));
		nodeList.add(new Node(new double[]{4,7}));
		nodeList.add(new Node(new double[]{4,3}));
		nodeList.add(new Node(new double[]{1,3}));

		KDTree kdTree=new KDTree();
		Node root=kdTree.buildKDTree(nodeList,0);
		for (Node node : nodeList) {
			if(node!=null && node.left!=null && node.right!=null){
				System.out.println(node.toString()+"-->"+node.left.toString()+"-->"+node.right.toString());
			}else if(node!=null && node.left!=null && node.right==null){
				System.out.println(node.toString()+"-->"+node.left.toString());
			}else if(node!=null && node.left==null && node.right!=null){
				System.out.println(node.toString()+"-->"+node.left.toString());
			}

		}
		System.out.println(root);
		System.out.println(kdTree.searchKNN(root,new Node(new double[]{2.1,3.1}),2));
		System.out.println(kdTree.searchKNN(root,new Node(new double[]{2,4.5}),1));
		System.out.println(kdTree.searchKNN(root,new Node(new double[]{2,4.5}),3));
		System.out.println(kdTree.searchKNN(root,new Node(new double[]{6,1}),5));
	}
}
