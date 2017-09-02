package edx_course.SentimentAnalysis;

/**
 * Created by prakash on 2/8/17.
 */
public class BinarySearchCode {
    class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static Node root;

    public BinarySearchCode(){
        this.root = root;
    }

    public boolean find(int id){
        Node current = root;
        while(current!=null){
            if (current.data == id){
                return true;
            }
            else if (current.data> id){
                current = current.left;
            }
            else current = current.right;
        }
        return false;
    }

    public void insert(int id){
        Node newNode = new Node(id);
        if(root == null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while(true){
            parent = current;
            if(id<current.data){
                current = current.left;
                if(current == null){
                    parent.left = newNode;
                    return;
                }
            }
            else {
                current = current.right;
                if (current == null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public void display(Node root){
        if(root!=null){
            display(root.left);
            System.out.println(" "+ root.data);
            display(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchCode b = new BinarySearchCode();
        b.insert(1);
        b.insert(4);
        b.insert(5);b.insert(8);
        b.insert(25); b.insert(3);b.insert(2);
        b.display(b.root);
    }






}
