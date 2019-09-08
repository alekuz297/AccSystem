package controller;


import dao.ExportXmlDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.stage.FileChooser;
import org.w3c.dom.Document;
import util.CSVLoader;
import util.DBUtil;
import util.ExportXmlUtil;

import java.io.*;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alex on 5/5/2017.
 */
public class Import_ExportController {

    private Button importBtn;
    @FXML
    private Button find;
    @FXML
    private TextField Load;
    @FXML  TextArea showXml;
    @FXML TextArea showPricesEod;


    @FXML
    private void importCsv() {
        try {
            CSVLoader loader = new CSVLoader(DBUtil.getCon());


            loader.loadCSV(Load.getText(), "price_eod", true);
            showPricesEod.setText("Prices successfully loaded");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML private void importFX(){
        try {
            CSVLoader loader = new CSVLoader(DBUtil.getCon());


            loader.loadCSV(Load.getText(), "exchange_rates", true);
            showPricesEod.setText("FX_Rates successfully loaded");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   @FXML
    private void findCsvFile (ActionEvent actionEvent)throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("prices_EOD_05.05.2017 (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        Node node = (Node) actionEvent.getSource();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
         String path =file.getAbsolutePath();
         Load.setText(path);

         System.out.println(Load.getText());

    }

    @FXML private  void    createAndSaveXmlFile(ActionEvent actionEvent)throws Exception{


        ExportXmlDAO dao = new ExportXmlDAO();
        Document doc =dao.getAccount_NEList();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Export_NE (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        Node node = (Node) actionEvent.getSource();
        File file = fileChooser.showSaveDialog(node.getScene().getWindow());
       try {
           if (file != null) {
               SaveFile(ExportXmlUtil.serialize(doc), file);
               showXml.setText(ExportXmlUtil.serialize(doc));
           } }catch (Exception e) {System.out.println("\"Error occurred while create and export XML file from DB.\\n\"+e");}
       }





    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Import_ExportController.class.getName()).log(Level.SEVERE, null, ex);
            showPricesEod.setText(String.valueOf(ex));
        }

    }

    @FXML private void showPrices(ActionEvent actionEvent)throws FileNotFoundException {

        showPricesEod.clear();
        try {
            Scanner s = new Scanner(new File(Load.getText())).useDelimiter(",");
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    showPricesEod.appendText(s.nextInt() + " "); // display the found integer
                } else {
                    showPricesEod.appendText(s.next() + " "); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }


        }


}
