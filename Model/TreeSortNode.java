package Model;

/**
 * Created by Sinelnikov on 23.04.2017.
 */
public class TreeSortNode {
    private int field;
    public TreeSortNode left;
    public TreeSortNode right;

    public TreeSortNode(int field) {
        this(field, null, null);
    }

    TreeSortNode(int field, TreeSortNode left, TreeSortNode right) {
        this.field = field;
        this.left = left;
        this.right = right;
    }

    public int getField() {
        return field;
    }
}
