import java.awt.Color;
import java.util.ArrayList;

public class Cell {
    private int row;
    private int col;
    private String state;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;

        // random start
        setState(Math.random() > .66 ? "R" : (Math.random() > .5 ? "P" : "S"));

        // non-random start
        row = row - Main.HEIGHT / 2;
        col = col - Main.WIDTH / 2;
        if (row > col && row > -col) {
            setState("R");
        } else if (col > 0) {
            setState("P");
        } else {
            setState("S");
        }
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public boolean beats(Cell other) {
        return (this.state == "R" && other.getState() == "S"
            || this.state == "S" && other.getState() == "P"
            || this.state == "P" && other.getState() == "R");
    }

    public String getNewState(Cell[][] cells) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        for (int r = -1; r < 2; r++) {
            for (int c = -1; c < 2; c++) {
                if (!(r == 0 && c == 0)) {
                    int row = this.row + r;
                    int col = this.col + c;
                    if (row >= 0 && row < Main.HEIGHT && col >= 0 && col < Main.WIDTH) {
                        neighbors.add(cells[row][col]);
                    }
                }
            }
        }

        Cell randomCell = neighbors.get((int) (Math.random() * neighbors.size()));
        return randomCell.beats(this) ? randomCell.getState() : this.getState();
    }

    public Color getColor() {
        return this.state == "R" ? Color.RED : (this.state == "P" ? Color.GREEN : Color.BLUE);
    }
}
