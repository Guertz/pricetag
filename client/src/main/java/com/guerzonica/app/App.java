package com.guerzonica.app;
import com.guerzonica.app.pages.Page;
import javafx.stage.Stage;
import com.guerzonica.app.pages.DashboardPage;
import com.guerzonica.app.providers.PageProvider;
import com.guerzonica.app.providers.ProductsProvider;

import io.reactivex.functions.Consumer;

import com.guerzonica.app.models.data.*;

import javafx.application.Application;

public class App extends Application {
    // private DomPage<HBox, GridPane, HBox> dashboard;
    // private PageProvider<Page> navCtrl = new PageProvider<Page>();
    public static PageProvider<Page> pageController = new PageProvider<Page>();
    public static void main(String[] args) {
        launch(args);
    }
    public void bootstrap(){

      // System.out.println(Font.loadFont(App.class.getResource("fonts/OpenSans.ttf").toExternalForm(), 10));
      // ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      // InputStream is = classloader.getResourceAsStream("fonts/OpenSans.ttf");
      // Font.loadFont(is, 10);

      //
      // Toolbar toolbar = new Toolbar("Title");
      // toolbar.setFullSize(stage);
      //
      // container.getChildren().add(toolbar);
      // Scene n  = new Scene(container);
      // n.getStylesheets().add("css/pricetheme.css");
      // stage.setScene(n);
      // stage.show();
    }
    @Override
    public void start(Stage stage) {

        //Init pages
        bootstrap();


        pageController.push(new DashboardPage(stage));

        try {
            ProductsProvider p = ProductsProvider.getProvider();

               p.getStream()
                .flatMapIterable(x -> x)
                .subscribe(new Consumer<ProductDetails>() {
                    @Override public void accept(ProductDetails item) {
                        System.out.println("Handler: " + item.getId());
                    }
                });


        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.show();
        //
        // pageController.getActivePage().show();
        // pageController.showActivePage();
    }
}
