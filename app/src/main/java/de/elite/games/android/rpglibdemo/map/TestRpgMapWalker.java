package de.elite.games.android.rpglibdemo.map;

import java.util.List;

import de.frank.martin.games.rpgguilib.map.RpgMapWalker;

public class TestRpgMapWalker extends RpgMapWalker<TestRpgMapField, TestRpgMapEdge, TestRpgMapNode> {

    @Override
    public boolean canEnter(TestRpgMapField from, TestRpgMapField into) {
//        Optional<TestRpgMapEdge> edgeOpt = from.getEdge(into);
//        if (edgeOpt.isPresent() ){
//            return  edgeOpt.get().getData().getPassage().isOpen();
//        }else{
//            return false;
//        }
//        return edge.map(testMapEdge -> testMapEdge.getData().getPassage().isOpen()).orElse(false);
        TestRpgMapEdge edge = from.getEdge(into);
        return edge.getData().getPassage().isOpen();
    }

    @Override
    public int getEnterCosts(TestRpgMapField from, TestRpgMapField into) {
        return 10;
    }

    @Override
    public List<TestRpgMapField> getNeighbours(TestRpgMapField field) {
        return getNeighboursFromEdges(field);
    }
}
