import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();

        for (String val : sc.nextLine().trim().split("\\s+")) {
            bst.insert(Integer.parseInt(val));
        }

        int target = 0;
        if (sc.hasNextInt()) {
            target = sc.nextInt();
        }

        System.out.print("Preorder  : ");
        bst.preOrder();
        System.out.print("Inorder   : ");
        bst.inOrder();
        System.out.print("Postorder : ");
        bst.postOrder();
        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());

        sc.close();
    }
}