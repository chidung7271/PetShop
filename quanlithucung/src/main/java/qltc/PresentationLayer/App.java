
package qltc.PresentationLayer;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import qltc.BussinessLogicLayer.Client;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("qltc\\Resource\\FXML_CSS\\Scence.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader()
                .getResource("qltc/Resource/FXML_CSS/MakeSthMoreBeautiful.css").toExternalForm());
        primaryStage.setTitle("Pet Shop Management Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    // @Override
    // public void stop() {
    //     try {
    //         System.out.println("Closing the client socket");
    //         Client.close();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void main(String[] args) throws IOException {
        try {
            Client.ClientConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
        System.out.println("Closing the client socket");
        Client.close();
    }
}
