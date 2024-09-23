package dad.AdivinApp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class AdivinApp extends Application {


    private Label numeroLabel;
    private TextField numeroTextField;
    private Button comprobarButton;
    private Alert ganadoAlert;
    private Alert falladoAlert;
    private Alert errorAlert;
    private int numeroIntentos;
    private int numeroAleatorio;
    private Random random;

    @Override
    public void start(Stage stage) throws Exception {

        numeroIntentos = 0;

        random = new Random();
        numeroAleatorio = random.nextInt(100 - 1 + 1) + 1;

        ganadoAlert = new Alert(Alert.AlertType.INFORMATION);
        ganadoAlert.setTitle("AdivinApp");
        ganadoAlert.setHeaderText("¡Has ganado!");

        falladoAlert = new Alert(Alert.AlertType.WARNING);
        falladoAlert.setTitle("AdivinApp");
        falladoAlert.setHeaderText("¡Has fallado!");

        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("AdivinApp");
        errorAlert.setHeaderText("Error");
        errorAlert.setContentText("El numero introducido no es valido");

        numeroLabel = new Label("Introduce un numero del 1 al 100");

        numeroTextField = new TextField();
        numeroTextField.setMaxWidth(100);

        comprobarButton = new Button("Comprobar");
        comprobarButton.setDefaultButton(true);
        comprobarButton.setOnAction(e -> comprobar());

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        root.getChildren().addAll(numeroLabel , numeroTextField , comprobarButton);

        Scene scene = new Scene(root , 640 , 480);

        stage.setTitle("AdivinApp");
        stage.setScene(scene);
        stage.show();

    }

    private void comprobar() {
        numeroIntentos++;
        if (!numeroTextField.getText().matches("\\d+") || Integer.parseInt(numeroTextField.getText()) > 100 || Integer.parseInt(numeroTextField.getText()) <= 0){
            errorAlert.show();
        }
        else {
            if (numeroTextField.getText().equals(numeroAleatorio + "")) {
                ganadoAlert.setContentText("Solo has necesitado " + numeroIntentos + " intentos" + "\n\nVuelve a jugar");
                ganadoAlert.showAndWait();
            } else {
                if (Integer.parseInt(numeroTextField.getText()) > numeroAleatorio) {
                    falladoAlert.setContentText("El numero a adivinar es menor que " + numeroTextField.getText() + "\n\nVuelve a intentarlo");
                } else {
                    falladoAlert.setContentText("El numero a adivinar es mayor que " + numeroTextField.getText() + "\n\nVuelve a intentarlo");
                }
                falladoAlert.showAndWait();
            }
        }
    }
}
