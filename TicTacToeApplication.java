// ______   __  __   __  __    
///\  ___\ /\ \/\ \ /\_\_\_\   
//\ \ \____\ \ \_\ \\/_/\_\/_  
// \ \_____\\ \_____\ /\_\/\_\
//  \/_____/ \/_____/ \/_/\/_/

import javafx.application.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class TicTacToeApplication extends Application {
    private boolean gameEnded = false;
    
    @Override
    public void start(Stage stage){
        BorderPane layout = new BorderPane();
        GridPane innerLayout = new GridPane();
        Button[][] buttons = new Button[3][3];

        char xs = 'X', os = 'O';
        final char[] actual = {xs}; // Use an array to mutate inside lambda
        Label textComponent = new Label("Turn: " + actual[0]);
        textComponent.setFont(Font.font("Monospaced", 40)); 
        
        for (int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                Button btn = new Button(" ");
                buttons[x][y] = btn;
                btn.setFont(Font.font("Monospaced", 40));                
                innerLayout.add(btn, x, y);
                
                btn.setOnAction((event) -> {
                    if (!gameEnded && btn.getText().equals(" ")) {
                        btn.setText(String.valueOf(actual[0]));
                        if(checkForWinner(buttons, actual[0])){
                            textComponent.setText("The end!");
                            gameEnded = true;
                        } else {
                            if(actual[0] == xs){
                                actual[0] = os;
                            }else{
                                actual[0] = xs;
                            }
                            textComponent.setText("Turn: " + actual[0]);
                        }
                    }
                });
            }
        }
        
        innerLayout.setPadding(new Insets(20, 20, 20, 20));
        innerLayout.setHgap(10);
        innerLayout.setVgap(10);

        layout.setCenter(innerLayout);
        layout.setTop(textComponent);
        
        Scene view = new Scene(layout, 400, 300);
        
        stage.setScene(view);
        stage.show();
    }

    private boolean checkForWinner(Button[][] buttons, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(player)) &&
                buttons[i][1].getText().equals(String.valueOf(player)) &&
                buttons[i][2].getText().equals(String.valueOf(player))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(player)) &&
                buttons[1][i].getText().equals(String.valueOf(player)) &&
                buttons[2][i].getText().equals(String.valueOf(player))) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][2].getText().equals(String.valueOf(player))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(player)) &&
            buttons[1][1].getText().equals(String.valueOf(player)) &&
            buttons[2][0].getText().equals(String.valueOf(player))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }
}
