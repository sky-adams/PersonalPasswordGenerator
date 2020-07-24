import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Orientation;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PersonalPasswordGenerator extends Application {

    @Override
    public void start(Stage stage) {
        
        GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text scenetitle = new Text("Personal Password Generator");
		scenetitle.setId("title");
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Text subtitle = new Text("Answer the questions below and then click \"Generate\" to generate a personalized password that's easy to remember but hard to guess.");
        subtitle.setWrappingWidth(420); //width is in px
        grid.add(subtitle,0,1,2,1);//span 2 columns
		
		Label friendName = new Label("What is your best friend's first name?");
		grid.add(friendName, 0, 2);

		TextField friendNameField = new TextField();
		grid.add(friendNameField, 1, 2);

		Label colorLabel = new Label("What is your favorite color?");
		grid.add(colorLabel, 0, 3);

		TextField colorField = new TextField();
		grid.add(colorField, 1, 3);
		
		Button btn = new Button("Generate");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT); //position the button in the bottom right of its grid cell
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		Text pw = new Text();
        grid.add(pw, 1, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
			@Override
			public void handle(ActionEvent e) {
				String password = colorField.getText() + friendNameField.getText();
				//add a special character
				int aLoc = password.indexOf("a");
				int sLoc = password.indexOf("S");
				if (sLoc < 0)
				{
					sLoc = password.indexOf("s");
				}
				int lLoc = password.indexOf("l");
				if(aLoc >= 0)
				{
					password = password.substring(0,aLoc) + "@" + password.substring(aLoc+1);
				}
				else if(sLoc >= 0)
				{
					password = password.substring(0,sLoc) + "$" + password.substring(sLoc+1);
				}
				else if(lLoc >= 0)
				{
					password = password.substring(0,lLoc) + "(" + password.substring(lLoc+1);
				}
				else
				{
					password = password + "?";
				}
				//add a number
				int eLoc = password.indexOf("E");
				int bLoc = password.indexOf("b");
				int oLoc = password.indexOf("o");
				if(eLoc >= 0)
				{
					password = password.substring(0,eLoc) + "3" + password.substring(eLoc+1);
				}
				else if(bLoc >= 0)
				{
					password = password.substring(0,bLoc) + "6" + password.substring(bLoc+1);
				}
				else if(oLoc >= 0)
				{
					password = password.substring(0,oLoc) + "0" + password.substring(oLoc+1);
				}
				else
				{
					password = password + (int)(10 * Math.random());
				}
				//check length
				if(password.length() < 10)
				{
					int extendBy = 10 - password.length();
					password = password + (int)(Math.pow(10, extendBy) * Math.random());
				}
				pw.setText(password);
			}
		});

		Scene scene = new Scene(grid, 600, 300);
		stage.setScene(scene);
		scene.getStylesheets().add("style.css");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}