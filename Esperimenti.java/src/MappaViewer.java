import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.swing.*;

public class MappaViewer extends Application {

    private static String indirizzo;

    // Metodo per lanciare la mappa da Swing
    public static void mostraMappa(JFrame parent) {
        indirizzo = "Via Legione Gallieno 52, Roma"; // Puoi cambiare l'indirizzo
        SwingUtilities.invokeLater(() -> {
            new Thread(() -> Application.launch(MappaViewer.class)).start();
        });
    }

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();

        // URL Google Maps con embed
        String url = "https://maps.google.com/maps?q=" + indirizzo.replace(" ", "%20") + "&output=embed";
        webView.getEngine().load(url);

        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setTitle("Mappa CERN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
