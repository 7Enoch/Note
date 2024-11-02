package Java.zuo;
//递归遍历树
public class TreeErgodic {
    public static void main(String[] args) {
        TreeNode1 treeNode1 = new TreeNode1();
        TreeNode1 treeNodeleft11 = new TreeNode1();
        TreeNode1 treeNoderight11 = new TreeNode1();
        TreeNode1 treeNodeleft21 = new TreeNode1();
        TreeNode1 treeNoderight21 = new TreeNode1();
        TreeNode1 treeNodeleft31 = new TreeNode1();
        TreeNode1 treeNoderight31 = new TreeNode1();

        treeNode1.value = 1;
        treeNodeleft11.value = 2;
        treeNoderight11.value = 3;
        treeNodeleft21.value = 4;
        treeNoderight21.value = 5;
        treeNodeleft31.value = 6;
        treeNoderight31.value = 7;
        treeNode1.left = treeNodeleft11;
        treeNode1.right = treeNoderight11;
        treeNodeleft11.left = treeNodeleft21;
        treeNodeleft11.right = treeNoderight21;
        treeNoderight11.left = treeNodeleft31;
        treeNoderight11.right = treeNoderight31;
        System.out.println("---先序遍历---");
        preOrder(treeNode1);
        System.out.println("---中序遍历---");
        inOrder(treeNode1);
        System.out.println("---后序遍历---");
        posOrder(treeNode1);

    }

    // 先序遍历
    public static void preOrder(TreeNode1 head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        preOrder(head.left);
        preOrder(head.right);

    }

    // 中序遍历
    public static void inOrder(TreeNode1 head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.println(head.value);
        inOrder(head.right);
    }

    // 后序遍历
    public static void posOrder(TreeNode1 head) {
        if (head == null) {
            return;
        }
        posOrder(head.left);
        posOrder(head.right);
        System.out.println(head.value);
    }

}

// 树结构

class TreeNode {
    TreeNode1 left;
    TreeNode1 right;
    int value;

}