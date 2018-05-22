package de.hsa.games.fatsquirrel.core;

public class XY {
    private int x;
    private int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public XY(XY xy) {
        this.x = xy.x;
        this.y = xy.y;
    }

    protected XY randomizer() {
        XY xy = mover((int) (Math.random() * 9 + 1));
        return xy;
    }

    protected XY mover(int i) {
        switch (i) {
            case 8:
                x = getX();
                y = getY() + 1;
                return new XY(x, y);
            case 9:
                x = getX() + 1;
                y = getY() + 1;
                return new XY(x, y);
            case 6:
                x = getX() + 1;
                y = getY();
                return new XY(x, y);
            case 3:
                x = getX() + 1;
                y = getY() - 1;
                return new XY(x, y);
            case 2:
                x = getX();
                y = getY() - 1;
                return new XY(x, y);
            case 1:
                x = getX() - 1;
                y = getY() - 1;
                return new XY(x, y);
            case 4:
                x = getX() - 1;
                y = getY();
                return new XY(x, y);
            case 7:
                x = getX() - 1;
                y = getY() + 1;
                return new XY(x, y);
            case 5:
                return new XY(x, y);
            default:
                return new XY(x, y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return (" x: " + x + " y: " + y);
    }

    public static int Distance(XY pos, XY postarget) {
        int disx = 0;
        int disy = 0;
        if (postarget.getX() < pos.getX()) {
            disx = pos.getX() - postarget.getX();
        } else {
            disx = postarget.getX() - pos.getX();
        }
        if (postarget.getY() < pos.getY()) {
            disy = pos.getY() - postarget.getY();
        } else {
            disy = postarget.getY() - pos.getY();
        }
        if (disx < disy) {
            return disy;
        } else {
            return disx;
        }
    }


}
