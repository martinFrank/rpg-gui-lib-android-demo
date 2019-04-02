package de.elite.games.android.rpglibdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import de.elite.games.android.rpglibdemo.gui.AndroidMapCanvas;
import de.elite.games.android.rpglibdemo.gui.MapView;
import de.elite.games.android.rpglibdemo.map.TestRpgMap;
import de.elite.games.android.rpglibdemo.map.TestRpgMapFactory;
import de.elite.games.android.rpglibdemo.map.TestRpgMapField;
import de.elite.games.android.rpglibdemo.map.TestRpgMapPartFactory;
import de.elite.games.android.rpglibdemo.map.TestRpgMapWalker;
import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.MazeGenerationParams;
import de.frank.martin.games.rpgguilib.input.InputHandler;
import de.frank.martin.games.rpgguilib.input.event.InputDirectionEvent;

public class MainActivity extends AppCompatActivity {

    private TestRpgMap rpgMap;
    private InputHandler inputHandler;
    private MapView mapView;

    private TestRpgMapField from;
    private TestRpgMapField into;
    private TestRpgMapWalker walker;

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

        walker = mapPartFactory.createWalker();

        mapView = findViewById(R.id.map_view);
        mapView.setMap(rpgMap);


        AndroidMapCanvas mapCanvas = new AndroidMapCanvas(rpgMap, mapView);
        inputHandler = new InputHandler(mapCanvas);


        mapView.setOnTouchListener(new View.OnTouchListener(){

            private float x;
            private float y;

            private long timeStamp;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                view.performClick();

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    x = motionEvent.getX();
                    y = motionEvent.getY();
                    timeStamp = System.currentTimeMillis();
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
                        if (Math.abs(dx) > 2 * Math.abs(dy)) {
                            yDir = 0;
                        }
                        if (Math.abs(dy) > 2 * Math.abs(dx)) {
                            xDir = 0;
                        }
                        inputHandler.handleInputEvent(new InputDirectionEvent(xDir,yDir));
                    } else {
                        if (System.currentTimeMillis() - timeStamp > 1500) {
                            TestRpgMapField field = rpgMap.getFieldAt(x, y);
                            Log.d("MapView", "MapView.LongClick... on field: " + field);
                            if (field != null) {
                                setField(field);
                            }
                            if (from != null && into != null) {
                                calculatePath();
                            }
                        }
                    }
                    return false;
                }

                return true;
            }

        });


    }

    private void calculatePath() {
        for (TestRpgMapField any : rpgMap.getFields()) {
            any.getData().markAsPath(false);
        }
        List<TestRpgMapField> path = rpgMap.aStar(from, into, walker, 50);
        Log.d("MainActivity", "astar size: " + path.size());
        for (TestRpgMapField any : path) {
            any.getData().markAsPath(true);
        }
        mapView.invalidate();
    }

    private void setField(TestRpgMapField field) {
        if (from == null) {
            Log.d("MainActivity", "set from: " + field);
            from = field;
            return;
        }
        if (field.equals(from)) {
            Log.d("MainActivity", "reset from");
            from = null;
            return;
        }
        if (into == null) {
            Log.d("MainActivity", "set into: " + field);
            into = field;
            return;
        }
        if (field.equals(into)) {
            Log.d("MainActivity", "reset into");
            into = null;
        }
    }
}
