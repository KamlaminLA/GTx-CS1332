import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> res = new ArrayList<>();
        preorderHelper(root, res);
        return res;

    }

    private void preorderHelper(TreeNode<T> root, List<T> res) {
        if (root == null) {
            return;
        }
        res.add(root.getData());
        preorderHelper(root.getLeft(), res);
        preorderHelper(root.getRight(), res);
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private void inorderHelper(TreeNode<T> root, List<T> res) {
        if (root == null) {
            return;
        }
        inorderHelper(root.getLeft(), res);
        res.add(root.getData());
        inorderHelper(root.getRight(), res);
    }


    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> res = new ArrayList<>();
        postorderHelper(root, res);
        return res;
    }

    private void postorderHelper(TreeNode<T> root, List<T> res) {
        if (root == null) {
            return;
        }
        postorderHelper(root.getLeft(), res);
        postorderHelper(root.getRight(), res);
        res.add(root.getData());
    }
}
