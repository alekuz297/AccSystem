import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;

public class RootLayoutController {
    public void handleExit(ActionEvent actionEvent) {
        System.exit(0);
    }
    public void handleHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program information");
        alert.setHeaderText("This is the bachelor thesis program");
        alert.setContentText("You can search, edit, update, insert a new deals with this program");
        alert.show();
    }
    @FXML MenuItem Trades;
    @FXML MenuItem Instruments;
    @FXML MenuItem Accounts;
    @FXML
    private void switchToTrades(ActionEvent event) {
        try {
            URL RootLayoutUrl = getClass().getResource("/view/TradeInput.fxml");
            AnchorPane RootLayout = FXMLLoader.load(RootLayoutUrl);
            BorderPane border = Main.getRoot();
            border.setCenter(RootLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToInstruments(ActionEvent event) {

        try {
            URL InstrumentsUrl = getClass().getResource("/view/Instrument.fxml");
            AnchorPane Instruments = FXMLLoader.load(InstrumentsUrl);
            BorderPane border = Main.getRoot();
            border.setCenter(Instruments);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void switchToAccounts(ActionEvent event) {

        try {

            URL AccountsUrl = getClass().getResource("/view/Accounts.fxml");
            AnchorPane Accounts = FXMLLoader.load(AccountsUrl);

            BorderPane border = Main.getRoot();
            border.setCenter(Accounts);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void switchToImport_Export(ActionEvent event) {

        try {

            URL Import_ExportUrl = getClass().getResource("/view/Import_Export.fxml");
            AnchorPane Import_Export = FXMLLoader.load(Import_ExportUrl);

            BorderPane border = Main.getRoot();
            border.setCenter(Import_Export);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}



