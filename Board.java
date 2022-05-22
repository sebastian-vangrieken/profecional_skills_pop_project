package fact.it.model;

public class Board {
    public Tiles[][] board;
    int grids = 4;
    int border = 0;
    public int score = 0;

    // board classe aanmaken, grote van het bord op 4x4 zetten
    public Board() {
        this.board = new Tiles[4][4];

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                this.board[i][j] = new Tiles();
            }
        }

    }

    public Board(int grids) {
        this.grids = grids;
        this.board = new Tiles[grids][grids];

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                this.board[i][j] = new Tiles();
            }
        }

    }

    public Board(int lose, int grids) {
        this.grids = grids;
        this.board = new Tiles[grids][grids];

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                this.board[i][j] = new Tiles((lose + i + j) * (i + j));
            }
        }

    }

    public Tiles[][] getBoard() {
        return this.board;
    }

    //getter om de score op te halen
    public int getScore() {
        return this.score;
    }

    //getter om de tegel met de hoogste score op te halen
    public int getHighTile() {
        int high = this.board[0][0].getValue();

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                if (this.board[i][j].getValue() > high) {
                    high = this.board[i][j].getValue();
                }
            }
        }

        return high;
    }

    public void print() {
        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                String s = this.board[i][j].toString() + " ";
                System.out.print(s);
            }

            System.out.println("");
        }

        System.out.println("Score: " + this.score);
    }

    public String toString() {
        String s = "";

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                s = s + this.board[i][j].toString() + " ";
            }

            s = s + "\n";
        }

        return s;
    }

    //methode om het initieel bord op te stellen
    public void spawn() {
        boolean empty = true;

        while(empty) {
            int row = (int)(Math.random() * 4.0D);
            int col = (int)(Math.random() * 4.0D);
            double x = Math.random();
            if (this.board[row][col].getValue() == 0) {
                if (x < 0.2D) {
                    this.board[row][col] = new Tiles(4);
                    empty = false;
                } else {
                    this.board[row][col] = new Tiles(2);
                    empty = false;
                }
            }
        }

    }

    public boolean blackOut() {
        int count = 0;

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                if (this.board[i][j].getValue() > 0) {
                    ++count;
                }
            }
        }

        if (count == 16) {
            return true;
        } else {
            return false;
        }
    }

    //methode om te bepalen wanneer het spel gedaan is
    public boolean gameOver() {
        int count = 0;

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[i].length; ++j) {
                if (this.board[i][j].getValue() > 0) {
                    if (i == 0 && j == 0) {
                        if (this.board[i][j].getValue() != this.board[i + 1][j].getValue() && this.board[i][j].getValue() != this.board[i][j + 1].getValue()) {
                            ++count;
                        }
                    } else if (i == 0 && j == 3) {
                        if (this.board[i][j].getValue() != this.board[i + 1][j].getValue() && this.board[i][j].getValue() != this.board[i][j - 1].getValue()) {
                            ++count;
                        }
                    } else if (i == 3 && j == 3) {
                        if (this.board[i][j].getValue() != this.board[i - 1][j].getValue() && this.board[i][j].getValue() != this.board[i][j - 1].getValue()) {
                            ++count;
                        }
                    } else if (i == 3 && j == 0) {
                        if (this.board[i][j].getValue() != this.board[i - 1][j].getValue() && this.board[i][j].getValue() != this.board[i][j + 1].getValue()) {
                            ++count;
                        }
                    } else if (i == 0 && (j == 1 || j == 2)) {
                        if (this.board[i][j].getValue() != this.board[i + 1][j].getValue() && this.board[i][j].getValue() != this.board[i][j + 1].getValue() && this.board[i][j].getValue() != this.board[i][j - 1].getValue()) {
                            ++count;
                        }
                    } else if (i != 3 || j != 1 && j != 2) {
                        if (j == 0 && (i == 1 || i == 2)) {
                            if (this.board[i][j].getValue() != this.board[i][j + 1].getValue() && this.board[i][j].getValue() != this.board[i - 1][j].getValue() && this.board[i][j].getValue() != this.board[i + 1][j].getValue()) {
                                ++count;
                            }
                        } else if (j == 3 && (i == 1 || i == 2)) {
                            if (this.board[i][j].getValue() != this.board[i][j - 1].getValue() && this.board[i][j].getValue() != this.board[i - 1][j].getValue() && this.board[i][j].getValue() != this.board[i + 1][j].getValue()) {
                                ++count;
                            }
                        } else if (this.board[i][j].getValue() != this.board[i][j - 1].getValue() && this.board[i][j].getValue() != this.board[i][j + 1].getValue() && this.board[i][j].getValue() != this.board[i - 1][j].getValue() && this.board[i][j].getValue() != this.board[i + 1][j].getValue()) {
                            ++count;
                        }
                    } else if (this.board[i][j].getValue() != this.board[i - 1][j].getValue() && this.board[i][j].getValue() != this.board[i][j + 1].getValue() && this.board[i][j].getValue() != this.board[i][j - 1].getValue()) {
                        ++count;
                    }
                }
            }
        }

        if (count == 16) {
            return true;
        } else {
            return false;
        }
    }

    //methode maken om naar boven te kunnen bewegen
    public void up() {
        for(int i = 0; i < this.grids; ++i) {
            this.border = 0;

            for(int j = 0; j < this.grids; ++j) {
                if (this.board[j][i].getValue() != 0 && this.border <= j) {
                    this.verticalMove(j, i, "up");
                }
            }
        }

    }

    //methode maken om naar beneden te kunnen bewegen
    public void down() {
        for(int i = 0; i < this.grids; ++i) {
            this.border = this.grids - 1;

            for(int j = this.grids - 1; j >= 0; --j) {
                if (this.board[j][i].getValue() != 0 && this.border >= j) {
                    this.verticalMove(j, i, "down");
                }
            }
        }

    }

    //methode om naar boven en beneden te kunnen bewegen
    private void verticalMove(int row, int col, String direction) {
        Tiles initial = this.board[this.border][col];
        Tiles compare = this.board[row][col];
        if (initial.getValue() != 0 && initial.getValue() != compare.getValue()) {
            if (direction.equals("down")) {
                --this.border;
            } else {
                ++this.border;
            }

            this.verticalMove(row, col, direction);
        } else if (row > this.border || direction.equals("down") && row < this.border) {
            int addScore = initial.getValue() + compare.getValue();
            if (initial.getValue() != 0) {
                this.score += addScore;
            }

            initial.setValue(addScore);
            compare.setValue(0);
        }

    }

    //methode om naar links te kunnen bewegen
    public void left() {
        for(int i = 0; i < this.grids; ++i) {
            this.border = 0;

            for(int j = 0; j < this.grids; ++j) {
                if (this.board[i][j].getValue() != 0 && this.border <= j) {
                    this.horizontalMove(i, j, "left");
                }
            }
        }

    }

    //methode om naar rechts te kunnen bewegen
    public void right() {
        for(int i = 0; i < this.grids; ++i) {
            this.border = this.grids - 1;

            for(int j = this.grids - 1; j >= 0; --j) {
                if (this.board[i][j].getValue() != 0 && this.border >= j) {
                    this.horizontalMove(i, j, "right");
                }
            }
        }

    }

    //methode om naar links en rechts te kunnen bewegen
    private void horizontalMove(int row, int col, String direction) {
        Tiles initial = this.board[row][this.border];
        Tiles compare = this.board[row][col];
        if (initial.getValue() != 0 && initial.getValue() != compare.getValue()) {
            if (direction.equals("right")) {
                --this.border;
            } else {
                ++this.border;
            }

            this.horizontalMove(row, col, direction);
        } else if (col > this.border || direction.equals("right") && col < this.border) {
            int addScore = initial.getValue() + compare.getValue();
            if (initial.getValue() != 0) {
                this.score += addScore;
            }

            initial.setValue(addScore);
            compare.setValue(0);
        }

    }
}
