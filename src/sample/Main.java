package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
        Scene s = new Scene(root, 300, 50);
        primaryStage.setTitle("pip");
        primaryStage.setScene(s);
        // primaryStage.initStyle(StageStyle.DECORATED); change if you want to
        primaryStage.show();
    }

    public void vid(String vid) throws Exception {
        Stage s2 = new Stage();
        s2.setWidth(1280.0D);
        s2.setHeight(720.0D);
        s2.setAlwaysOnTop(true);
        s2.getIcons().add(new Image(this.getClass().getResourceAsStream("icon2.png")));
        final WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webView.getEngine().setUserStyleSheetLocation(this.getClass().getResource("style.css").toExternalForm());
        webEngine.loadContent(vid);
        s2.setOnCloseRequest((EventHandler) event -> {s2.close(); webView.getEngine().loadContent("");});
        Scene s3 = new Scene(webView, s2.getWidth(), s2.getHeight());
        s2.setScene(s3);
        s2.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


