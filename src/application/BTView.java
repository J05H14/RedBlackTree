package application;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import rbTrees.Node;
import rbTrees.RBTree;

public class BTView extends Pane{
	private RBTree<Integer, Integer> tree = new RBTree<Integer, Integer>();
	private double radius = 15;
	private double vGap = 50;
	
	BTView(RBTree<Integer, Integer> tree){
		this.tree = tree;
		setStatus("Tree is Empty");
	}
	
	public void setStatus(String msg){
		getChildren().add(new Text(20, 20, msg));
	}
	
	public void displayTree(){
		this.getChildren().clear();
		if(tree.getRoot() != null){
			displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
		}
	}
	public void displayTree(Node<Integer, Integer> root, double x, double y, double hGap){
		if(root.getLeft() != null){
			getChildren().add(new Line(x - hGap, y + vGap, x, y));
			displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2);
		}
		if(root.getRight() != null){
			getChildren().add(new Line(x + hGap, y + vGap, x, y));
			displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2);
		}
		
		Circle circle = new Circle(x, y, radius);
		
		if(root.getColor() == 'B'){
			circle.setFill(Color.BLACK);
		}
		else if(root.getColor() == 'R'){
			circle.setFill(Color.RED);
		}
		Text key = new Text(x- 4, y + 4, root.getKey() + "");
		key.setFill(Color.WHITE);
		getChildren().addAll(circle, key);
	}
}
