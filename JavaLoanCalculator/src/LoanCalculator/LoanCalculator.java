package LoanCalculator;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

 

public class LoanCalculator extends Application {
	
// Declaring variables for the textFields
	static TextField rate_TF;
	static TextField year_TF;
	static TextField amount_TF;
	static TextField monthlypayment_TF;
	static TextField totalPayment_TF;
	static Button calculate_BTN;
    	static Button clear_BTN;
	
	public static void main(String[] args) {
		launch(args);
		}
@Override

public void start(Stage primaryStage) {
	
// Setting up GridPane
	try {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 30, 25, 30));
		Scene scene = new Scene(grid, 350, 300);

		addComponents(grid);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Loan Calculator");//Title
		primaryStage.show();//Will show Stage

//Setting action for calculate button
calculate_BTN.setOnAction(new EventHandler<ActionEvent>() {

   @Override
// This will make sure nothing is left empty
   public void handle(ActionEvent e) {

       if(rate_TF.getText().equals(""))
       {	   
        displayDialog("Please enter the Annual Interest Rate!!");
       }
       
       else if(year_TF.getText().equals(""))
       {
        displayDialog("Please enter the number of years!!");
       }
       
       else if(amount_TF.getText().equals(""))
       {
        displayDialog("Please enter the Loan Amount!!");
       }
       
       else
       {
        calculateLoanPayment();
       }
   }
});

} 

catch(Exception e) {

	e.printStackTrace();

}

//Setting action for Clear button
	clear_BTN.setOnAction(new EventHandler<ActionEvent>(){
		 public void handle(ActionEvent e) {
			 rate_TF.setText("");
			 year_TF.setText("");
			 amount_TF.setText("");
			 monthlypayment_TF.setText("");
			 totalPayment_TF.setText("");
		 }
	}); 
	}
public static void displayDialog(String message)
{
   Alert alert = new Alert(Alert.AlertType.ERROR);
   alert.setTitle("Error");
   alert.setHeaderText("Empty Field");
   alert.setContentText(message);
   alert.showAndWait();
}

//Adding Components(LAbels, textFields, Buttons etc ) Using addComponent method 
public static void addComponents(GridPane grid)
{
Label Interest = new Label("Annual Interest Rate: ");
grid.add(Interest, 0, 1);

rate_TF = new TextField();

grid.add(rate_TF, 1, 1);

Label years = new Label("Number of Years: ");

grid.add(years, 0, 2);

year_TF = new TextField();

grid.add(year_TF, 1, 2);

Label Amount = new Label("Loan Amount: ");

grid.add(Amount, 0, 3);

amount_TF = new TextField();

grid.add(amount_TF, 1, 3);

Label monthlyPayment = new Label("Monthly Payment: ");

grid.add(monthlyPayment, 0, 4);

monthlypayment_TF = new TextField();

grid.add(monthlypayment_TF, 1, 4);

Label totalPayment = new Label("Total Payment: ");

grid.add(totalPayment, 0, 5);

totalPayment_TF = new TextField();

grid.add(totalPayment_TF, 1, 5);

//Calculate Button

calculate_BTN = new Button("Calculate");
HBox calc_btn = new HBox(10);
calc_btn.setAlignment(Pos.BOTTOM_RIGHT);
calc_btn.getChildren().add(calculate_BTN);
grid.add(calc_btn, 0, 6);
monthlypayment_TF.setEditable(false);
totalPayment_TF.setEditable(false);

//Clear Button

clear_BTN = new Button("Clear");
HBox clear_btn = new HBox(10);
clear_btn.setAlignment(Pos.BOTTOM_RIGHT);
clear_btn.getChildren().add(clear_BTN);
grid.add(clear_btn, 1, 6);

}

//Calculating Loan payment 
public static void calculateLoanPayment()

{
    double rate;
    double amount;    
    int years;
    
    try
    {
    rate = Double.parseDouble(rate_TF.getText());
    years = Integer.parseInt(year_TF.getText());
    amount = Double.parseDouble(amount_TF.getText());
    Loan loan = new Loan(rate, years, amount);
    monthlypayment_TF.setText(String.format("$"+"%.2f", loan.getMonthlyPayment()));
    totalPayment_TF.setText(String.format("$"+"%.2f", loan.getTotalPayment()));
    }

    catch(NumberFormatException ex)
    {
    displayDialog("Enter numbers Only");
    }
}


}                  
