package rbTrees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * My rotate methods don't update parent nodes, when the parent is the root. I don't know how to fix this. 
 */
public class RBTree <K extends Comparable<K>, V extends Comparable<V>> {

	private Node<K, V> root;
	private final Node<K, V> NIL = new Node<K, V> (null, null, 'B');

	//"default" constructor
	public RBTree(){
		this.root = NIL;
	}

	//constructor
	public RBTree(K key, V value){
		this.root = new Node<K, V>(key, value, 'B');
		this.root.left = NIL;
		this.root.right = NIL;
	}

	//find grandparent
	public Node<K, V> getGrandparent(Node<K, V> curr){
		if(curr.parent == null || curr.parent.parent == null){
			return null;
		}
		return curr.parent.parent;
	}
	//find uncle
	public Node<K, V> getUncle(Node<K, V> curr){
		if(getGrandparent(curr) == null){
			return null;
		}
		else if(getGrandparent(curr).left == null && curr.parent == getGrandparent(curr).right){
			return null;
		}
		else if(getGrandparent(curr).right == null && curr.parent == getGrandparent(curr).left){
			return null;
		}
		else if(curr.parent == getGrandparent(curr).left){
			return getGrandparent(curr).right;
		}
		else{
			return getGrandparent(curr).left;
		}
	}

	//left rotate
	public void leftRotate(Node<K, V> root){
		System.out.println("Left Rotate Start");
		Node<K, V> pivot = root.right;

		root.right = pivot.left;


		System.out.println("root: " + root + " pivot : " + pivot);
		//updating parent nodes
		
		pivot.parent = root.parent;

		if(root.parent == null){	//see if we're at the root
			this.root = pivot;
		}
		else{
			if(root == (root.parent).left){	//root was left child
				root.parent.left = pivot;
			}
			else{	//root was right child
				root.parent.right = pivot;
			}
		}
		
		pivot.left = root;
		root.parent = pivot;
		System.out.println("root: " + root + " pivot : " + pivot);
		System.out.println(this.root);
		System.out.println("Left Rotate End");
	}

	//right rotate
	public void rightRotate(Node<K, V> root){
		Node<K, V> pivot = root.left;

		root.left = pivot.right;

		//updating parent nodes
		if(pivot.right != NIL){
			pivot.right.parent = root;
		}
		pivot.parent = root.parent;

		if(root.parent == null){	//if we're at the root
			this.root = pivot;
		}
		else{
			if(root == (root.parent).right){	//root was right child
				root.parent.right = pivot;
			}
			else{	//root was left child
				root.parent.left = pivot;
			}
		}

		pivot.right = root;
		root.parent = pivot;
	}

	//find
	public boolean find(Node<K, V> search){
		Node <K, V> curr = this.root;
		K key = search.getKey();
		while(curr != NIL){
			if(key.compareTo(curr.getKey()) < 0){
				curr = curr.left;
			}
			else if(key.compareTo(curr.getKey()) > 0){
				curr = curr.right;
			}
			else{
				return true;
			}
		}
		return false;
	}

	//bst insert
	public boolean BSTInsert(Node<K, V> newNode){

		if(this.root == NIL){
			this.root = newNode;
			newNode.left = NIL;
			newNode.right = NIL;
		}
		else{
			Node<K, V> current = this.root;
			Node<K, V> parent = current.parent;

			while(current != NIL){
				if(newNode.getKey().compareTo(current.getKey()) < 0){
					parent = current;
					current = current.left;
				}
				else if(newNode.getKey().compareTo(current.getKey()) > 0){
					parent = current;
					current = current.right;
				}
				else{
					return false;
				}
			}
			if(newNode.getKey().compareTo(parent.getKey()) < 0){
				parent.left = newNode;
				newNode.parent = parent;
				newNode.left = NIL;
				newNode.right = NIL;
			}
			else{
				parent.right = newNode;
				newNode.parent = parent;
				newNode.left = NIL;
				newNode.right = NIL;
			}
		}
		return true;
	}



	//rb insert
	public void RBInsert(Node<K, V> newNode){
		BSTInsert(newNode);
		newNode.setColor('R');
		//case 1
		if(newNode == this.root){
			newNode.setColor('B');
			return;
		}
		//case 2
		if(newNode.parent.getColor() == 'B'){
			return;
		}
		//case 3
		if(newNode.parent.getColor() == 'R' && getUncle(newNode).getColor() == 'R'){
			newNode.parent.setColor('B');
			getUncle(newNode).setColor('B');
			getGrandparent(newNode).setColor('R');
			RBInsert(getGrandparent(newNode));
			return;
		}
		//case 4
		if(newNode.parent.getColor() == 'R' && getUncle(newNode).getColor() == 'B'){
			System.out.println(newNode + " | " + newNode.parent);
			if(newNode.parent.right == newNode && newNode.parent == getGrandparent(newNode).left){
				leftRotate(newNode.parent);			
				newNode = newNode.parent;
				System.out.println(newNode);
			}
			if(newNode.parent.left == newNode && newNode.parent == getGrandparent(newNode).right){
				rightRotate(newNode.parent);
				newNode = newNode.parent;
			}

			System.out.println(newNode);
		}
		//case 5
		if(newNode.parent.getColor() == 'R' && getUncle(newNode).getColor() == 'B'){
			if(newNode.parent.left == newNode && newNode.parent == getGrandparent(newNode).left){
				newNode.parent.setColor('B');
				getGrandparent(newNode).setColor('R');
				rightRotate(newNode);
			}
			if(newNode.parent.right == newNode && newNode.parent == getGrandparent(newNode).right){
				newNode.parent.setColor('B');
				getGrandparent(newNode).setColor('R');
				leftRotate(getGrandparent(newNode));
			}
		}

	}

	public String printOrder(ArrayList<Node<K, V>> list){
		String result = "";
		for(int i = 0; i < list.size(); i ++){
			if(i == list.size() - 1){
				result = result + "KEY: " + list.get(i).getKey() + " VALUE: " + list.get(i).getValue() + " COLOR: " + list.get(i).getColor();
			}
			else{
				result = result + "KEY: " + list.get(i).getKey() + " VALUE: " + list.get(i).getValue() + " COLOR: " + list.get(i).getColor() + "\n";
			}
		}
		return result;
	}

	public String preorder(){
		NodeStack<K, V> stack = new NodeStack<K, V>();
		ArrayList<Node<K, V>> list = new ArrayList<Node<K, V>>();

		stack.push(this.root);

		while(!stack.isEmpty()){
			Node<K, V> current = stack.pop();
			list.add(current);
			if(current.right != NIL){
				stack.push(current.right);
			}
			if(current.left != NIL){
				stack.push(current.left);
			}
		}

		return printOrder(list);
	}

	public String inorder(){
		NodeStack<K, V> stack = new NodeStack<K, V>();
		ArrayList<Node<K, V>> list = new ArrayList<Node<K, V>>();

		Node<K, V> current = this.root;

		while(!stack.isEmpty() || current != NIL){
			if(current != NIL){
				stack.push(current);
				current = current.left;
			}
			else{
				current = stack.pop();
				list.add(current);
				current = current.right;
			}
		}

		return printOrder(list);
	}

	public String postorder(){
		NodeStack <K, V> stack = new NodeStack<K, V>();
		ArrayList<Node<K, V>> list = new ArrayList<Node<K, V>>();
		Set<Node<K, V>> visited = new HashSet<Node<K, V>>();;

		stack.push(this.root);

		while(!stack.isEmpty()){
			Node<K, V> current = stack.peek();

			if(current.left != NIL && !(visited.contains(current.left))){
				stack.push(current.left);
			}
			else if(current.right != NIL && !(visited.contains(current.right))){
				stack.push(current.right);
			}
			else{
				list.add(current);
				visited.add(current);
				stack.pop();
			}
		}

		return printOrder(list);
	}

	public String bredthfirst(){
		Queue<Node<K, V>> queue = new LinkedList<Node<K, V>>();
		ArrayList<Node<K, V>> list = new ArrayList<Node<K, V>>();

		queue.add(this.root);
		list.add(this.root);

		while(!queue.isEmpty()){
			Node<K, V> current = queue.remove();
			if(current.left != NIL){
				queue.add(current.left);
				list.add(current.left);
			}
			if(current.right != NIL){
				queue.add(current.right);
				list.add(current.right);
			}
		}

		return printOrder(list);

	}


	public Node<K, V> getRoot(){
		return this.root;
	}

	public Node<K, V> getNIL() {
		return NIL;
	}
}
