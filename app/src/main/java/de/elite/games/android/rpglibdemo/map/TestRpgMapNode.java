package de.elite.games.android.rpglibdemo.map;

import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapNodeData;
import de.frank.martin.games.rpgguilib.map.RpgMapNode;

public class TestRpgMapNode extends RpgMapNode<TestRpgMapNodeData, TestRpgMapField, TestRpgMapEdge, TestRpgMapNode> {

    public TestRpgMapNode(TestRpgMapNodeData testPointData) {
        super(testPointData);
    }


    @Override
    public void draw(Object graphics) {
//        GraphicsContext gc = (GraphicsContext) graphics;
//        gc.setStroke(Color.DARKCYAN);
//        gc.setLineWidth(3);
//        double x = getPoint().getTransformed().getX();
//        double y = getPoint().getTransformed().getY();
//        gc.strokeLine(x, y, x, y);
    }

}

