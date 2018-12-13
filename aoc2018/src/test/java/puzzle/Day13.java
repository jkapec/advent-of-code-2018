package puzzle;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class Day13 {

    private String[][] prodPaths;
    private String[][] testPaths;
    private String[][] paths;
    private List<Cart> carts = new ArrayList<>();

    @Test
    public void run() {
        paths = getPathsFromFile("puzzle/Day13_prod.txt");
        int tick = 0;
        try {
            do {
                tick++;
                carts.stream().sorted((c1, c2) -> {
                    int compare = Integer.valueOf(c1.y).compareTo(Integer.valueOf(c2.y));
                    return compare != 0 ? compare : Integer.valueOf(c1.x).compareTo(Integer.valueOf(c2.x));
                }).forEach(Cart::move);
            } while (true);
        } catch (StopException e) {
            log();
            System.out.println("Collision during tick " + tick + " occurs at " + e.x + "," + e.y);
        }

        tick = 0;
        carts = new ArrayList<>();
        paths = getPathsFromFile("puzzle/Day13_prod.txt");
        Set<Cart> colidedCarts = new HashSet<>();
        do {
            tick++;
            carts.stream().sorted((c1, c2) -> {
                int compare = Integer.valueOf(c1.y).compareTo(Integer.valueOf(c2.y));
                return compare != 0 ? compare : Integer.valueOf(c1.x).compareTo(Integer.valueOf(c2.x));
            }).forEach(cart -> {
                if (!colidedCarts.contains(cart)) {
                    cart.move(false);
                    Cart collidedCart = hasCollision(cart.x, cart.y, cart);
                    if (collidedCart != null) {
                        colidedCarts.add(cart);
                        colidedCarts.add(collidedCart);
                    }
                }
            });
            carts.removeAll(colidedCarts);
        } while (carts.size() > 1);
        log();
        System.out.println("Only " + carts.size() + " left: " + carts);
    }

    private String[][] getPathsFromFile(String fileName) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                return new String[0][0];
            }

            List<String> lines = IOUtils.readLines(is, "UTF-8");
            int maxX = lines.stream()
                    .max(comparing(String::length))
                    .orElse("")
                    .length();
            int maxY = lines.size();

            String[][] result = new String[maxX][maxY];
            for (int y = 0; y < maxY; y++) {
                String line = lines.get(y);
                for (int x = 0; x < maxX; x++) {
                    if (x < line.length()) {
                        String point = line.substring(x, x + 1);

                        if (point.equalsIgnoreCase("V")) {
                            point = "|";
                            carts.add(new Cart(x, y, Direction.DOWN));
                        } else if (point.equals("^")) {
                            point = "|";
                            carts.add(new Cart(x, y, Direction.UP));
                        } else if (point.equals(">")) {
                            point = "-";
                            carts.add(new Cart(x, y, Direction.RIGHT));
                        } else if (point.equals("<")) {
                            point = "-";
                            carts.add(new Cart(x, y, Direction.LEFT));
                        }

                        result[x][y] = point;
                    } else {
                        result[x][y] = " ";
                    }
                }
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0][0];
    }

    private void log() {
        for (int y = 0; y < paths[0].length; y++) {
            for (int x = 0; x < paths.length; x++) {
                String point = paths[x][y];
                final int xx = x;
                final int yy = y;
                List<Cart> cartsAtPosition = carts.stream().filter(c -> c.x == xx && c.y == yy).collect(toList());
                if (cartsAtPosition.size() == 1) {
                    Cart cart = cartsAtPosition.get(0);
                    point = cart.direction == Direction.UP ? "^"
                            : cart.direction == Direction.DOWN ? "V"
                                    : cart.direction == Direction.LEFT ? "<"
                                            : cart.direction == Direction.RIGHT ? ">" : null;
                } else if (cartsAtPosition.size() > 1) {
                    point = "X";
                }
                System.out.print(point);
            }
            System.out.println();
        }
        System.out.println();
    }

    private class Cart {

        private int x, y;
        private Direction direction;
        private Turn doTurn = Turn.LEFT;

        @Override
        public String toString() {
            return x + "," + y + "; direction " + direction;
        }

        public Cart(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        private void move() {
            move(true);
        }

        private void move(boolean checkCollision) {
            String way = paths[this.x][this.y];
            if (way.equals("+")) {
                direction = direction.turn(doTurn);
                doTurn = doTurn.next();
                if (direction == Direction.UP) {
                    y--;
                } else if (direction == Direction.DOWN) {
                    y++;
                } else if (direction == Direction.LEFT) {
                    x--;
                } else if (direction == Direction.RIGHT) {
                    x++;
                }
            } else if (way.equals("|")) {
                if (direction == Direction.UP) {
                    y--;
                } else if (direction == Direction.DOWN) {
                    y++;
                }
            } else if (way.equals("-")) {
                if (direction == Direction.LEFT) {
                    x--;
                } else if (direction == Direction.RIGHT) {
                    x++;
                }
            } else if (way.equals("\\")) {
                if (direction == Direction.UP) {
                    direction = Direction.LEFT;
                    x--;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.RIGHT;
                    x++;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.UP;
                    y--;
                } else if (direction == Direction.RIGHT) {
                    direction = Direction.DOWN;
                    y++;
                }
            } else if (way.equals("/")) {
                if (direction == Direction.UP) {
                    direction = Direction.RIGHT;
                    x++;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.LEFT;
                    x--;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.DOWN;
                    y++;
                } else if (direction == Direction.RIGHT) {
                    direction = Direction.UP;
                    y--;
                }
            }

            if (checkCollision) {
                Cart colliedCart = hasCollision(x, y, this);
                if (colliedCart != null) {
                    throw new StopException(x, y, this, colliedCart);
                }
            }
        }
    }

    private Cart hasCollision(int x, int y, Cart actual) {
        List<Cart> colidedCarts = carts.stream().filter(c -> c.x == x && c.y == y).collect(toList());

        if (colidedCarts.size() > 1) {
            return colidedCarts.stream().filter(cart -> !cart.equals(actual)).findFirst().orElse(null);
        }
        return null;
    }

    private enum Direction {
        UP("LEFT", "RIGHT"),
        RIGHT("UP", "DOWN"),
        DOWN("RIGHT", "LEFT"),
        LEFT("DOWN", "UP");

        String toLeft;
        String toRight;

        Direction(String toLeft, String toRight) {
            this.toLeft = toLeft;
            this.toRight = toRight;
        }

        Direction turn(Turn turn) {
            if (turn == Turn.LEFT) {
                return Direction.valueOf(toLeft);
            } else if (turn == Turn.RIGHT) {
                return Direction.valueOf(toRight);
            }
            return this;
        }
    }

    private enum Turn {
        LEFT("STRAIGHT"),
        STRAIGHT("RIGHT"),
        RIGHT("LEFT");

        String nextToPush;

        private Turn next() {
            return Turn.valueOf(nextToPush);
        }

        Turn(String nextToPush) {
            this.nextToPush = nextToPush;
        }

    }

    @AllArgsConstructor
    private class StopException extends RuntimeException {

        private int x;
        private int y;
        private Cart cart1;
        private Cart cart2;

    }
}