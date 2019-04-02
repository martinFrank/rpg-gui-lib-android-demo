package de.elite.games.android.rpglibdemo.gui;

import android.os.Handler;

import de.elite.games.android.rpglibdemo.map.TestRpgMap;
import de.frank.martin.games.rpgguilib.gui.GuiListener;
import de.frank.martin.games.rpgguilib.gui.MapCanvas;

public class AndroidMapCanvas extends MapCanvas<TestRpgMap, MapView> {

    private Handler handler;
    private int step;
    private static final int STEPS = 8;
    private static final int SLEEP = 20;

    private double deltaPanX;
    private double deltaPanY;

    private double panX;
    private double panY;



    public AndroidMapCanvas(final TestRpgMap rpgMap, final MapView canvas ) {
        super(rpgMap, canvas);
        handler = new Handler();
    }

    @Override
    public void startAnimation(TestRpgMap rpgMap, GuiListener guiListener, int dx, int dy) {
//        Log.d("MapCanvas", "startAnimation");
        guiListener.setInputLock();
        step = 0;
        panX = 0;
        panY = 0;
        deltaPanX = dx * rpgMap.getScrollWidth(dx, dy) / STEPS;
        deltaPanY = dy * rpgMap.getScrollHeight(dx, dy) / STEPS;

        handler.postDelayed(createAnimationRunnable(rpgMap, guiListener), 20);
    }

    private Runnable createAnimationRunnable(final TestRpgMap map, final GuiListener guiListener){
        return new Runnable() {
            @Override
            public void run() {
                panX = panX + deltaPanX;
                panY = panY + deltaPanY;
                step = step + 1;
                if (step == STEPS){
                    map.pan(0,0);
                    guiListener.releaseInputLock();
                }else{
                    map.pan(panX, panY);
                    handler.postDelayed(this, SLEEP);
                }
                getCanvas().invalidate();
            }
        };
    }
}