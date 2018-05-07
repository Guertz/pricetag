package com.guerzonica.app.pages;
//https://stackoverflow.com/questions/40750526/javafx-best-practice-for-navigating-between-ui-screens

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import com.guerzonica.app.interfaces.Templatable;



public class DomPage<H extends Pane, B extends Pane, F extends Pane> extends Page implements Templatable<H,B,F>{

  protected Scene scene;

  protected Pane root;

  protected H header;

  protected B body;

  protected F footer;

  private Stage stage;

  public DomPage(Stage stage, H h, B b, F f)
    throws InstantiationException, IllegalAccessException {
    super(stage);
    this.stage = stage;
    this.body = b;
    this.header = h;
    this.footer = f;

    // header();
    // body();
    // footer();

    this.init(stage, h, b, f);

  }
  private void init(Stage stage, H h, B b, F f){
    BorderPane container = new BorderPane();

    container.setTop(this.header);
    container.setCenter(this.body);

    container.prefHeightProperty().bind(stage.heightProperty());
    container.prefWidthProperty().bind(stage.widthProperty());

    this.root = new Pane();
    this.root.getChildren().add(container);
    // this.root.getNgetId("root").setTop()


    ScrollPane scrollPane = new ScrollPane();
      scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
      scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    scrollPane.setContent(this.root);

    // this.root = scrollPane;
    this.scene = new Scene(scrollPane);

    this.scene.getStylesheets().add("css/pricetheme.css");

  }
  public void show(){
    super.show();
  }

  public void setScene(){
    super.setScene(this.scene);
  }
  //
  // private B body(B body)
  //   throws InstantiationException, IllegalAccessException {
  //   body.setPadding(new Insets(5, 5, 5, 5));
  //   body.getStyleClass().add("body");
  //   return body;
  // }
  // private H header(H h)
  //   throws InstantiationException, IllegalAccessException {
  //   h.setPadding(new Insets(10, 10, 10, 10));
  //   h.getStyleClass().add("header");
  //   return h;
  // }

  public <B extends Pane> B getContent(){
    return (B) this.getBody();
  }

  public Pane getRoot(){ return this.root; }
  public H getHeader(){ return this.header;}
  public B getBody(){ return this.body; }
  public F getFooter(){ return null; }

  public void header(){
    this.header.setPadding(new Insets(10, 10, 10, 10));
    this.header.getStyleClass().add("header");
    this.header.prefWidthProperty().bind(this.stage.widthProperty());
  }

  public void body(){

    this.body.setPadding(new Insets(5, 5, 5, 5));
    this.body.getStyleClass().add("body");

   }

  public void footer(){  }

  public void forceLoad(){ header(); body(); footer(); }

  public void transition() { /* Nothing here */ }


    // @Override
    // public void setRoot(Class<? extends Control> t){
    //     ScrollPane scrollPane = new ScrollPane();
    //     scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    //     scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    //
    //     this.root =
    // } //wrap everthing into the single root

    // public DomPage(Stage stage, Supplier<H> h, Supplier<B> b, Supplier<F> f )
    // //   // public DomPage<H extends Pane, B extends Pane, F extends Pane>(Stage stage)
    // //
    //   throws InstantiationException, IllegalAccessException {
    //   super(stage);
    //   // DomPage<H,B,F>(stage, (H) h.get(), (B) b.get(), (F)f.get());
    //
    // }
}