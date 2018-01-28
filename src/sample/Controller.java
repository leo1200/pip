package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;

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
        URL ur = new URL(url);
        if(url.contains("ipfs")){
            new Main().vid("<html><video width=\"100%\" height=\"100%\" src=\"" + url + "\" frameborder=\"0\" allowfullscreen controls autoplay></video></html>");
        }
        else {
            String vid = "https://" + ur.getHost() + "/embed/" + this.getId(url);
            new Main().vid("<html><iframe width=\"100%\" height=\"100%\" src=\"" + vid + "\" frameborder=\"0\" allowfullscreen></iframe></html>");
        }

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