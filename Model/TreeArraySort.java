package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sinelnikov on 23.04.2017.
 */
public class TreeArraySort {
    private List<Integer> list;
    private TreeSortNode tree = null;

    public void sortArray(int[] array) {
        list = new ArrayList<>();
        tree = new TreeSortNode(array[0]);
        for (int index = 1; index < array.length; index++) {
            addNode(array[index], tree);
        }
        inOrder(tree);
    }

    private TreeSortNode addNode(int number, TreeSortNode rootNode) {
        if (rootNode == null) {
            return new TreeSortNode(number);
        }
        if (number < rootNode.getField()) {
            rootNode.left = addNode(number, rootNode.left);
        } else {
            rootNode.right = addNode(number, rootNode.right);
        }
        return rootNode;
    }

    private void inOrder(TreeSortNode node) {
        if (node != null) {
            inOrder(node.left);
            list.add(node.getField());
            inOrder(node.right);
        }
    }
}
