package Java.zuo;
//非递归遍历树 -> 栈

import java.util.Stack;

public class TreeNoErgodic {
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
        // System.out.println("---中序遍历---");
        // inOrder(treeNode);
        // System.out.println("---后序遍历---");
        // posOrder(treeNode);

    }

    // 先序遍历
    public static void preOrder(TreeNode1 head) {
        if (head == null) {
            return;
        }
        // 创建一个栈
        Stack<TreeNode1> stack = new Stack<>();
        // 将头节点压入
        stack.push(head);
        // 只要栈不为空就循环
        while (!stack.isEmpty()) {
            // 将栈顶弹出
            head = stack.pop();
            // 打印
            System.out.print(head.value);
            // 将左右节点压入 压入的时候还需判断
            // 由于是先序 所以先压右节点
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
            // 换行
            System.out.println();
        }
    }

    // 中序遍历
    /*
     * 
     * 步骤：1.子树左边界完全进栈
     * 2.栈弹出节点打印，节点右树重复步骤1
     * 结束条件：没子树且栈空
     */
    public static void inOrder(TreeNode1 head) {
        if (head == null) {
            return;
        }
        // 创建一个栈
        Stack<TreeNode1> stack = new Stack<>();
        // 当前节点不为空或者栈不为空
        while (head != null || !stack.isEmpty()) {

            if (head != null) {
                // 压入节点
                stack.push(head);
                // 进入节点左子树
                head = head.left;
            } else {
                // 弹出栈
                head = stack.pop();
                // 打印值
                System.out.println(head.value);
                // 进入右树
                head = head.right;

            }

        }

    }

    // 后序遍历
    public static void posOrder(TreeNode1 head) {
        // 创建一个栈
        Stack<TreeNode1> stack = new Stack<>();

    }

}

// 树结构
class TreeNode1 {

    TreeNode1 left;
    TreeNode1 right;
    int value;

    public TreeNode1(int value) {
        this.value = value;
    }

    public TreeNode1() {

    }

}