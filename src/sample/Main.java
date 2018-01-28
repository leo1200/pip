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
        s2.initStyle(StageStyle.TRANSPARENT);
        final WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webView.getEngine().setUserStyleSheetLocation(this.getClass().getResource("style.css").toExternalForm());
        webEngine.loadContent(vid);
        Scene s3 = new Scene(webView, s2.getWidth(), s2.getHeight());
        s2.setScene(s3);
        (new Main()).resize(s2, webView, s3);
        s2.show();
    }

    public void resize(final Stage s, WebView h, Scene h10) throws IOException {
        final Stage s4 = new Stage();
        s4.setWidth(1300.0D);
        s4.setHeight(800.0D);
        s4.getIcons().add(new Image(this.getClass().getResourceAsStream("icon2.png")));
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("sample2.fxml"));
        Scene sc = new Scene(root, s4.getWidth(), s4.getHeight());
        s4.setScene(sc);
        s4.show();
        s4.setOnCloseRequest((EventHandler) event -> {s.close(); h.getEngine().loadContent("");});
        s4.widthProperty().addListener((obs, oldVal, newVal) -> {
            s.setWidth(((Double)newVal).doubleValue() - 20.0D);
        });
        s4.heightProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> s.setHeight(((Double)newValue).doubleValue() - 80.0D));
        s4.xProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                s.setX(s.getX() + ((Double)newValue).doubleValue() - ((Double)oldValue).doubleValue());
            }
        });
        s4.yProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                s.setY(s.getY() + ((Double)newValue).doubleValue() - ((Double)oldValue).doubleValue());
            }
        });

        sc.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.B) {
                s4.toBack();
            }
        });

        h10.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.B) {
                s4.toFront();
            }
        });

    }



    public static void main(String[] args) {
        launch(args);
    }
}


