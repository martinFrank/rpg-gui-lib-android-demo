package de.elite.games.android.rpglibdemo.map;

import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapData;
import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapEdgeData;
import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapFieldData;
import de.elite.games.android.rpglibdemo.mapdata.TestRpgMapNodeData;
import de.elite.games.maplib.MapStyle;
import de.frank.martin.games.rpgguilib.map.RpgMapPartFactory;

public class TestRpgMapPartFactory extends RpgMapPartFactory<TestRpgMap, TestRpgMapField, TestRpgMapEdge, TestRpgMapNode, TestRpgMapWalker> {

    @Override
    public TestRpgMapNode createMapNode() {
        return new TestRpgMapNode(new TestRpgMapNodeData());
    }

    @Override
    public TestRpgMapEdge createMapEdge() {
        return new TestRpgMapEdge(new TestRpgMapEdgeData());
    }

    @Override
    public TestRpgMapField createMapField() {
        return new TestRpgMapField(new TestRpgMapFieldData());
    }

    @Override
    public TestRpgMap createMap(int columns, int rows, MapStyle style) {
        return new TestRpgMap(columns, rows, style, new TestRpgMapData());
    }

    @Override
    public TestRpgMapWalker createWalker() {
        return new TestRpgMapWalker();
    }
}
