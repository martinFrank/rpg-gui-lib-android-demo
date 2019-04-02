package de.elite.games.android.rpglibdemo.map;

import de.frank.martin.games.rpgguilib.map.RpgMapFactory;

public class TestRpgMapFactory extends RpgMapFactory<TestRpgMap, TestRpgMapField, TestRpgMapEdge, TestRpgMapNode, TestRpgMapWalker> {

    public TestRpgMapFactory(TestRpgMapPartFactory mapPartFactory) {
        super(mapPartFactory);
    }

}
