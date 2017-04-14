package application;
	
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import rbTrees.Node;
import rbTrees.RBTree;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		RBTree<Integer, Integer> tree = new RBTree<Integer, Integer>();
		
		BorderPane pane = new BorderPane();
		BTView view = new BTView(tree);
		pane.setCenter(view);
		
		TextField tfKey = new TextField();
		TextField tfValue = new TextField();
		tfKey.setPrefColumnCount(3);
		tfKey.setAlignment(Pos.BASELINE_RIGHT);
		tfValue.setPrefColumnCount(3);
		tfValue.setAlignment(Pos.BASELINE_RIGHT);
		Button btInsert = new Button("Insert");
		Button btOrder = new Button("Order");
		HBox hBox = new HBox(5);
		hBox.getChildren().addAll(new Label("Enter a key"), tfKey, new Label("Enter a value"), tfValue, btInsert, btOrder);
		hBox.setAlignment(Pos.CENTER);
		pane.setBottom(hBox);
		
		btInsert.setOnAction(e -> {
			Integer key = Integer.parseInt(tfKey.getText());
			Integer value = Integer.parseInt(tfValue.getText());
			Node<Integer, Integer> node = new Node<Integer, Integer>(key, value);
			if(tree.find(node)){
				view.displayTree();
				view.setStatus(key + " is already in the tree");
			}
			else{
				tree.RBInsert(node);
				view.displayTree();
				view.setStatus(key + " is inserted in the tree");
			}
		});
		
		btOrder.setOnAction(e -> {
			String[] orderOptions = {"Pre-Order", "In-Order", "Post-Order", "Bredth-First"};
			int choice = JOptionPane.showOptionDialog(null, "Which Ordering Method?", "Ordering", 0, JOptionPane.QUESTION_MESSAGE, null, orderOptions, "null");
		
			switch(choice){
			
			case 0:
				JOptionPane.showMessageDialog(null, tree.preorder());
				break;
			
			case 1:
				JOptionPane.showMessageDialog(null, tree.inorder());
				break;
			case 2:
				JOptionPane.showMessageDialog(null, tree.postorder());
			case 3:
				JOptionPane.showMessageDialog(null, tree.bredthfirst());
			}
		});
		
		Scene scene = new Scene(pane, 450, 250);
		primaryStage.setTitle("Red Black Tree");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
