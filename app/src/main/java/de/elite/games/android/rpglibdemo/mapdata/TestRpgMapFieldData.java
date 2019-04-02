package de.elite.games.android.rpglibdemo.mapdata;

import de.elite.games.mazelib.mapdata.MazeMapFieldData;

public class TestRpgMapFieldData extends MazeMapFieldData {

    private boolean isMarkedAsPath;

    public void markAsPath(boolean isMarkedAsPath) {
        this.isMarkedAsPath = isMarkedAsPath;
    }

    public boolean isMarkedAsPath() {
        return isMarkedAsPath;
    }
}
