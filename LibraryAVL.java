class AVLNode {
    int key, height;
    AVLNode left, right;

    AVLNode(int d) {
        key = d;
        height = 1;
    }
}

public class LibraryAVL {

    AVLNode root;

    int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    AVLNode insert(AVLNode node, int key) {

        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    AVLNode deleteNode(AVLNode root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);

        else if (key > root.key)
            root.right = deleteNode(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {

                AVLNode temp = null;

                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {

                AVLNode temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    boolean search(AVLNode root, int key) {

        if (root == null)
            return false;

        if (root.key == key)
            return true;

        if (key < root.key)
            return search(root.left, key);

        return search(root.right, key);
    }

    void inorder(AVLNode node) {

        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        LibraryAVL tree = new LibraryAVL();

        int books[] = {50, 30, 70, 20, 40, 60, 80, 10, 25};

        for (int book : books)
            tree.root = tree.insert(tree.root, book);

        System.out.println("Initial AVL Tree (Inorder):");
        tree.inorder(tree.root);

        System.out.println("\n\nSearching Book ID 40:");
        System.out.println(tree.search(tree.root, 40) ? "Book Found" : "Book Not Found");

        tree.root = tree.insert(tree.root, 35);
        tree.root = tree.insert(tree.root, 65);

        System.out.println("\nAVL Tree after inserting 35 and 65:");
        tree.inorder(tree.root);

        tree.root = tree.deleteNode(tree.root, 20);

        System.out.println("\n\nAVL Tree after deleting Book ID 20:");
        tree.inorder(tree.root);

        System.out.println("\n\nSearching Book ID 65:");
        System.out.println(tree.search(tree.root, 65) ? "Book Found" : "Book Not Found");
    }
}