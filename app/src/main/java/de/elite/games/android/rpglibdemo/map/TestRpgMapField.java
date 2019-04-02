package de.elite.games.android.rpglibdemo.map;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapFieldData;
import de.elite.games.drawlib.Point;
import de.elite.games.drawlib.Shape;
import de.frank.martin.games.rpgguilib.map.RpgMapField;

public class TestRpgMapField extends RpgMapField<TestRpgMapFieldData, TestRpgMapField, TestRpgMapEdge, TestRpgMapNode> {

    private final Paint linePaint = new Paint();
    private final Paint pointPaint = new Paint();


    public TestRpgMapField(TestRpgMapFieldData testFieldData) {
        super(testFieldData);
    }

    @Override
    public void draw(Object graphics) {
//        Color color =
//                getData().isMarkedAsPath() ? Color.YELLOW :
//                        getData().isDeadEnd() ? Color.LIGHTGRAY :
//                                getData().isBlocked() ? Color.DARKGRAY : Color.WHITE;
//
//        GraphicsContext gc = (GraphicsContext) graphics;

        linePaint.setARGB(0xFF,0xFF,0x66,0xFF);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeWidth(3);

        pointPaint.setARGB(0xFF,0,0,0xFF);
        pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        pointPaint.setStrokeWidth(3);

        Canvas canvas = (Canvas) graphics;
//        gc.setFill(color);
        Shape shape = getShape().getTransformed();
        int amount = shape.getPoints().size();
        Path wallPath = new Path();
        wallPath.moveTo((float)shape.getPoints().get(0).getX(), (float)shape.getPoints().get(0).getY());
        for(int i = 1; i < amount; i++){
            Point p = shape.getPoints().get(i);
            wallPath.lineTo((float)p.getX(), (float)p.getY());
        }
        wallPath.lineTo((float)shape.getPoints().get(0).getX(), (float)shape.getPoints().get(0).getY());

        canvas.drawPath(wallPath, linePaint);
        canvas.drawPoint((float)shape.getCenter().getX(),(float)shape.getCenter().getY(), pointPaint);

//        gc.fillPolygon(xs, ys, amount);
        for (TestRpgMapEdge e : getEdges()) {
            e.draw(graphics);
        }
//        for (TestRpgMapNode n : getNodes()) {
//            n.draw(graphics);
//        }
//
//        gc.setLineWidth(1);
//        gc.setStroke(Color.GREEN);
//
//        if (!getData().isBlocked()) {
//
//            String text = "" + getData().getCounter();
//            double width = Toolkit.getToolkit().getFontLoader().computeStringWidth(text, gc.getFont());
//            double height = Toolkit.getToolkit().getFontLoader().getFontMetrics(gc.getFont()).getDescent();
//            gc.setStroke(Color.MISTYROSE);
//            gc.strokeText(text, shape.getCenter().getX() - width / 2, shape.getCenter().getY() + height);
//        }
    }

}
