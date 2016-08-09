private boolean find(TreeNode treeNode, int element) {
	if (treeNode == null)
		return false;
	if (element == treeNode.getData())
		return true;
	if (element < treeNode.getData()) {
		return find(treeNode.getLeftChild(), element);
	} else {
		return find(treeNode.getRightChild(), element);
	}
}
public boolean find(element) {
	find(rootNode, element);
}
// the number of tree nodes is n
// asymptotic time complexity: O(log(n))