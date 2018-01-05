package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField textfield;

    public void starte() throws Exception {
        String url = textfield.getText();
        if (!url.contains(".") && !url.contains("/")) {
            url = new redirect().redirection("https://www.google.de/search?btnI=vid&hl=de&q=" + url.replace(" ", "+"));
        } else if (url.contains("youtu.be")) {
            url = new redirect().redirection(url);
        }


        new Main().vid("src=\"" + this.getDom(url)+ "embed/"+ this.getId(url) + "\"");

    }

    public String getDom(String url) {
        int count = 0;
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '/') {
                count++;
            }
            if (count == 3) {
                System.out.println(url.substring(0, i + 1));
                return url.substring(0, i + 1);
            }
        }
        return "wrong";
    }

    public String getId(String url) {
        String erg="";
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '=') {
                erg = url.substring(i+1, url.length());
                break;
            }
        }
        String erg2 = erg;
        if(erg.contains(".be")){
            erg2 = erg.substring(0, erg.length()-17);
            System.out.println(erg2);
        }
        return erg2;
    }
}