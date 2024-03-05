package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        State state = (State) request.getSession().getAttribute("state");
        if (state == null) {
            newGame(request, view);
            return;
        }
        view.put("state", state);
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        State state = (State) request.getSession().getAttribute("state");

        if (state.phase != Phase.RUNNING) {
            view.put("state", state);
            return;
        }

        for (String key : request.getParameterMap().keySet()) {
            if (key.startsWith("cell_") && key.length() == 7) {
                int row = Integer.parseInt(key.substring(key.length() - 2, key.length() - 1));
                int col = Integer.parseInt(key.substring(key.length() - 1));

                state.move(row, col);
            }
        }

        request.getSession().setAttribute("state", state);
        view.put("state", state);
    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        State state = new State();
        request.getSession().setAttribute("state", state);
        view.put("state", state);
    }

    public enum Phase {
        RUNNING, WON_X, WON_O, DRAW
    }

    public enum Cell {
        X, O
    }

    public static class State {
        private static final int DEFAULT_SIZE = 3;
        private final int size;
        private int countOfPoints;
        private Phase phase;
        private boolean crossesMove;
        private final Cell[][] cells;


        public State() {
            this.size = DEFAULT_SIZE;
            this.countOfPoints = 0;
            this.phase = Phase.RUNNING;
            this.crossesMove = true;
            this.cells = new Cell[DEFAULT_SIZE][DEFAULT_SIZE];
        }

        public void move(int row, int col) {
            if (row < 0 || getSize() <= row || col < 0 || getSize() <= col) {
                throw new IllegalArgumentException("Incorrect position of move!");
            }
            if (cells[row][col] != null) {
                return;
            }

            countOfPoints++;
            cells[row][col] = (isCrossesMove() ? Cell.X : Cell.O);
            crossesMove = !isCrossesMove();
            phase = check();
        }

        public Phase check() {
            boolean isWin;

            for (int row = 0; row < getSize(); row++) {
                isWin = true;
                for (int col = 1; col < getSize(); col++) {
                    if (cells[row][col - 1] == null || cells[row][col] == null ||
                            !cells[row][col - 1].equals(cells[row][col])) {
                        isWin = false;
                        break;
                    }
                }
                if (isWin) {
                    return (cells[row][0] == Cell.X ? Phase.WON_X : Phase.WON_O);
                }
            }

            for (int col = 0; col < getSize(); col++) {
                isWin = true;
                for (int row = 1; row < getSize(); row++) {
                    if (cells[row - 1][col] == null || cells[row][col] == null ||
                            !cells[row - 1][col].equals(cells[row][col])) {
                        isWin = false;
                        break;
                    }
                }
                if (isWin) {
                    return (cells[0][col] == Cell.X ? Phase.WON_X : Phase.WON_O);
                }
            }

            isWin = true;
            for (int ind = 1; ind < getSize(); ind++) {
                if (cells[ind - 1][ind - 1] == null || cells[ind][ind] == null ||
                        !cells[ind - 1][ind - 1].equals(cells[ind][ind])) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                return (cells[0][0] == Cell.X ? Phase.WON_X : Phase.WON_O);
            }

            isWin = true;
            for (int ind = 1; ind < getSize(); ind++) {
                if (cells[ind - 1][getSize() - ind] == null || cells[ind][getSize() - ind - 1] == null ||
                        !cells[ind - 1][getSize() - ind].equals(cells[ind][getSize() - ind - 1])) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                return (cells[0][getSize() - 1] == Cell.X ? Phase.WON_X : Phase.WON_O);
            }

            if (countOfPoints == size * size) {
                return Phase.DRAW;
            } else {
                return Phase.RUNNING;
            }

        }

        public void clear() {
            countOfPoints = 0;
            phase = Phase.RUNNING;
            crossesMove = true;

            for (int row = 0; row < getSize(); row++) {
                for (int col = 0; col < getSize(); col++) {
                    cells[row][col] = null;
                }
            }
        }

        public int getSize() {
            return size;
        }

        public Phase getPhase() {
            return phase;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        public Cell[][] getCells() {
            return cells;
        }
    }
}
