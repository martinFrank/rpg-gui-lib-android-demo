package de.elite.games.android.rpglibdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import de.elite.games.android.rpglibdemo.gui.AndroidMapCanvas;
import de.elite.games.android.rpglibdemo.gui.MapView;
import de.elite.games.android.rpglibdemo.map.TestRpgMap;
import de.elite.games.android.rpglibdemo.map.TestRpgMapFactory;
import de.elite.games.android.rpglibdemo.map.TestRpgMapPartFactory;
import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.MazeGenerationParams;
import de.frank.martin.games.rpgguilib.input.InputHandler;
import de.frank.martin.games.rpgguilib.input.event.InputDirectionEvent;

public class MainActivity extends AppCompatActivity {

    private TestRpgMap rpgMap;
    private InputHandler inputHandler;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestRpgMapPartFactory mapPartFactory = new TestRpgMapPartFactory();
        TestRpgMapFactory mapFactory = new TestRpgMapFactory(mapPartFactory);
//        rpgMap = mapFactory.createMap(12, 8, MapStyle.SQUARE);
        rpgMap = mapFactory.createMap(6, 12, MapStyle.HEX_VERTICAL);
        rpgMap.scale(34f);

        rpgMap.generateMaze(new MazeGenerationParams(MazeGenerationParams.AlgorithmType.RECURSIVE_BACKTRACKER_PASSAGES));

        mapView = findViewById(R.id.map_view);
        mapView.setMap(rpgMap);


        AndroidMapCanvas mapCanvas = new AndroidMapCanvas(rpgMap, mapView);
        inputHandler = new InputHandler(mapCanvas);


        mapView.setOnTouchListener(new View.OnTouchListener(){

            private float x;
            private float y;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    return true;
                }

                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    float dx = motionEvent.getX() - x;
                    float dy = motionEvent.getY() - y;

                    if (Math.sqrt(dx*dx+dy*dy) > 50){
                        int xDir = 0;
                        int yDir = 0;
                        if (Math.abs(dx) > 40){
                            xDir = (int) Math.signum(dx);
                        }
                        if (Math.abs(dy) > 40){
                            yDir = (int) Math.signum(dy);
                        }
                        inputHandler.handleInputEvent(new InputDirectionEvent(xDir,yDir));
                    }

                    return false;
                }
                return true;
            }

        });


    }
}
