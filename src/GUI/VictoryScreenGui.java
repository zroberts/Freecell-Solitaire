package GUI;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class VictoryScreenGui extends StackPane{
    private final String RED = "rgba(255,0,0,.7)";
    private final String GREEN = "rgba(0,255,0,.7)";
    private final String BLUE = "rgba(0,0,255,.7)";
    private URL baretUrl = this.getClass().getResource("/assets/img/baret.png");
    BufferedImage tempBaretImage;;

    /**
     * VictoryScreenGui() - create the scene for the victory screen
     */
    public VictoryScreenGui() {
        // set basic styles
        String styles = "-fx-width: 800px; -fx-height: 650px;";
        setStyle(styles);
        setTranslateX(-25);
        setTranslateY(305);
        setWidth(850);
        setHeight(650);

        // pull in the image
        try {
            tempBaretImage = ImageIO.read(baretUrl);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
        ImageView baretImage = new ImageView(SwingFXUtils.toFXImage(tempBaretImage, null));
        baretImage.setFitHeight(650);
        baretImage.setFitWidth(800);
        baretImage.setTranslateX(0);
        baretImage.setTranslateY(0);

        //Set the labels, and style them
        Label theLabel = new Label();
        Label subText = new Label();
        theLabel.setText("Winner!");
        subText.setText("PRESS \"R\" TO START A NEW GAME");
        theLabel.setTextFill(Color.web("#ffffff"));
        theLabel.setStyle("-fx-font-size: 60px;");
        subText.setStyle("-fx-font-size: 10px;");
        subText.setTextFill(Color.web("#ffffff"));
        subText.setTranslateY(35);

        // create the color animations, this flashes the background colors behind the dancing image
        Timeline colorAnimation  = new Timeline();
        KeyFrame redFrame = createKeyFrameColor(RED, 0);
        KeyFrame blueFrame = createKeyFrameColor(BLUE, 1000);
        KeyFrame greenFrame = createKeyFrameColor(GREEN, 2000);
        colorAnimation.getKeyFrames().addAll(redFrame, blueFrame, greenFrame);
        colorAnimation.setCycleCount(Animation.INDEFINITE);
        colorAnimation.setAutoReverse(true);

        // create the dancing image
        Timeline hatAnimation = new Timeline();
        KeyFrame hatOne = baretAnimation(baretImage, 0, 45);
        KeyFrame hatTwo = baretAnimation(baretImage, 500, -45);
        hatAnimation.getKeyFrames().addAll(hatOne, hatTwo);
        hatAnimation.setCycleCount(Animation.INDEFINITE);
        hatAnimation.setAutoReverse(true);

        // Create a parallel transition, and add the animations to it
        ParallelTransition transitions = new ParallelTransition();
        transitions.getChildren().addAll(hatAnimation, colorAnimation);

        //play the transitions
        transitions.play();

        //add everything to the scene
        getChildren().addAll(baretImage, theLabel, subText);
    }
    // Builder method for the color animations, pass in the color and the animation start
    private KeyFrame createKeyFrameColor(String suppliedColor, double animationStart){
        return new KeyFrame(Duration.millis(animationStart),
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        setStyle("-fx-background-color:" + suppliedColor + ";");
                    }
                }
        );
    }
    // Builder method for the baretAnimation, pass in the Image, the animation start time, and the rotation
    private KeyFrame baretAnimation(ImageView image, double animationStart, double rotation){
        return new KeyFrame(Duration.millis(animationStart),
             new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                     image.setRotate(rotation);
                 }
             }
        );
    }
}
