package de.elite.games.android.rpglibdemo.map;


import android.graphics.Canvas;
import android.graphics.Paint;

import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapEdgeData;
import de.elite.games.drawlib.Line;
import de.elite.games.drawlib.Point;
import de.frank.martin.games.rpgguilib.map.RpgMapEdge;

public class TestRpgMapEdge extends RpgMapEdge<TestRpgMapEdgeData, TestRpgMapField, TestRpgMapEdge, TestRpgMapNode> {

    private final Paint solidEdgePaint = new Paint();
    private final Paint openEdgePaint = new Paint();

    public TestRpgMapEdge(TestRpgMapEdgeData testEdgeData) {
        super(testEdgeData);
    }

    @Override
    public void draw(Object graphics) {
        solidEdgePaint.setARGB(0xFF,0,0,0);
        solidEdgePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        solidEdgePaint.setStrokeWidth(4);

        openEdgePaint.setARGB(0xFF,0xBB,0xBB,0xBB);
        openEdgePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        openEdgePaint.setStrokeWidth(3);

        Canvas canvas = (Canvas) graphics;

        Line line = getLine().getTransformed();
        Point a = line.getA();
        Point b = line.getB();
//
        if (!getData().getPassage().isOpen()){
            canvas.drawLine((float)a.getX(), (float)a.getY(), (float)b.getX(), (float)b.getY(), solidEdgePaint);
        }
//        Paint paint = getData().getPassage().isOpen()?openEdgePaint:solidEdgePaint;
//        canvas.drawLine((float)a.getX(), (float)a.getY(), (float)b.getX(), (float)b.getY(), paint);


    }

}
