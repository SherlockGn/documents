private boolean find(TreeNode treeNode, int element) {
	// judge
	if (treeNode == null)
		return false;
	if (element == treeNode.getData())
		return true;
	if (element < treeNode.getData()) {
		return find(treeNode.getLeftChild(), element); /* find left child */
	} else {
		return find(treeNode.getRightChild(), element);
	}
}
// This is find function
public boolean find(element) {
	find(rootNode, element);
}
// asymptotic time complexity: O(log(n))