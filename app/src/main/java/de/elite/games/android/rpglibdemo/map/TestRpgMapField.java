package de.elite.games.android.rpglibdemo.map;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapFieldData;
import de.elite.games.drawlib.Point;
import de.elite.games.drawlib.Shape;
import de.frank.martin.games.rpgguilib.map.RpgMapField;

public class TestRpgMapField extends RpgMapField<TestRpgMapFieldData, TestRpgMapField, TestRpgMapEdge, TestRpgMapNode> {

    private final Paint polygonPaint = new Paint();
    private final Paint pointPaint = new Paint();


    public TestRpgMapField(TestRpgMapFieldData testFieldData) {
        super(testFieldData);
    }

    @Override
    public void draw(Object graphics) {
        int[] color =
                getData().isMarkedAsPath() ? new int[]{0xFF, 0xFF, 0x00} :
                        getData().isDeadEnd() ? new int[]{0xCC, 0xCC, 0xCC} :
                                getData().isBlocked() ? new int[]{0x99, 0x99, 0x99} : new int[]{0xFF, 0xFF, 0xFF};

        polygonPaint.setARGB(0xFF, color[0], color[1], color[2]);

        polygonPaint.setStyle(Paint.Style.FILL);
        polygonPaint.setStrokeWidth(3);

        pointPaint.setARGB(0xFF,0,0,0xFF);
        pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        pointPaint.setStrokeWidth(3);

        Canvas canvas = (Canvas) graphics;
        Shape shape = getShape().getTransformed();
        int amount = shape.getPoints().size();
        Path wallPath = new Path();
        wallPath.moveTo((float)shape.getPoints().get(0).getX(), (float)shape.getPoints().get(0).getY());
        for(int i = 1; i < amount; i++){
            Point p = shape.getPoints().get(i);
            wallPath.lineTo((float)p.getX(), (float)p.getY());
        }
        wallPath.lineTo((float)shape.getPoints().get(0).getX(), (float)shape.getPoints().get(0).getY());

        canvas.drawPath(wallPath, polygonPaint);
        canvas.drawPoint((float)shape.getCenter().getX(),(float)shape.getCenter().getY(), pointPaint);

        for (TestRpgMapEdge e : getEdges()) {
            e.draw(graphics);
        }
    }

}
