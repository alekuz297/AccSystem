import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class Main extends Application {


    private  static BorderPane root=new BorderPane();

    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL RootLayoutUrl = getClass().getResource("/view/RootLayout.fxml");
        MenuBar bar = FXMLLoader.load( RootLayoutUrl );
        URL TradeInputUrl = getClass().getResource("/view/TradeInput.fxml");
       AnchorPane TradeInput = FXMLLoader.load( TradeInputUrl );
        root.setTop(bar);
       root.setCenter(TradeInput);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }



/*
private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Bakalaura Darbs");
        initRootLayout();
        showTradeInput();

    }



    public void initRootLayout() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("initRootLayout loaded");
        } catch (IOException e){ e.printStackTrace();}
    }

    private void showTradeInput() {
       try {
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(Main.class.getResource("/view/TradeInput.fxml"));
           AnchorPane tradeOperationsView = (AnchorPane) loader.load();
           rootLayout.setCenter(tradeOperationsView);
           System.out.println("showTradeInput loaded");
       }catch (IOException e) {e.printStackTrace();}

    }

    private void showInstrument() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/Instrument.fxml"));
            AnchorPane tradeOperationsView = (AnchorPane) loader.load();
            rootLayout.setCenter(tradeOperationsView);
            System.out.println("Instrument loaded");
        }catch (IOException e) {e.printStackTrace();}

    }
*/

    public static void main(String[] args) {
        launch(args);
    }


}
