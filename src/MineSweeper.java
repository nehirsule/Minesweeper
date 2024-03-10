import java.util.Scanner;
import java.util.Random;

public class MineSweeper {
    private final char MINE = '*'; // Mayın sembolü
    private final char CLOSED = '-'; // Açılmamış kutu sembolü
    private final char[] NEIGHBORS = {'u', 'd', 'l', 'r', 'n', 's', 'w', 'e'}; // Komşu hücreleri temsil eden semboller

    private char[][] gameBoard; // Oyun tahtası
    private char[][] mineBoard; // Mayınların bulunduğu tahta
    private boolean[][] openedBoxes; // Açılmış kutuların takip edildiği tahta
    private int rowSize; // Tahtadaki satır sayısı
    private int columnSize; // Tahtadaki sütun sayısı
    private int remainingBoxes; // Açılmamış kutu sayısı
    private int remainingMineCount; // Bulunacak kalan mayın sayısı

    public MineSweeper(int rowSize, int columnSize) {
        if (rowSize <= 1 || columnSize <= 1) {
            System.out.println("Daha büyük bir değer giriniz. En az 2*2 olmalıdır.");
            return;
        }

        this.rowSize = rowSize;
        this.columnSize = columnSize;
        this.remainingBoxes = rowSize * columnSize;
        this.remainingMineCount = remainingBoxes / 4; // Mayınlar kutuların 1/4'ü
        this.gameBoard = new char[rowSize][columnSize]; // Oyun tahtası oluşturuluyor
        this.mineBoard = new char[rowSize][columnSize]; // Mayınların bulunduğu tahta oluşturuluyor
        this.openedBoxes = new boolean[rowSize][columnSize]; // Açılmış kutuları takip etmek için tahta oluşturuluyor

        prepareBoard(); // Oyun tahtası başlangıç durumuyla dolduruluyor
        placeMines(); // Mayınlar rastgele yerleştiriliyor
        calculateNeighborMines(); // Her hücrenin komşularındaki mayın sayısı hesaplanıyor
    }
    //Oyun tahtasının başlangıç doldurması
    private void prepareBoard() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                gameBoard[i][j] = CLOSED;
            }
        }
    }

    // Mayınları rastgele yerleştirir
    private void placeMines() {
        Random random = new Random();

        while (remainingMineCount > 0) {
            int row = random.nextInt(rowSize);
            int column = random.nextInt(columnSize);
            if (mineBoard[row][column] != MINE) {
                mineBoard[row][column] = MINE;
                remainingMineCount--;
            }
        }
    }

    // Açılan kutuda mayın yoksa komşularındaki mayın sayısı hesaplanır.
    private void calculateNeighborMines() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (mineBoard[i][j] != MINE) {
                    int mines = 0;
                    for (char neighbor : NEIGHBORS) {
                        int r = i, c = j;
                        switch (neighbor) {
                            case 'u':
                                r--;
                                break;
                            case 'd':
                                r++;
                                break;
                            case 'l':
                                c--;
                                break;
                            case 'r':
                                c++;
                                break;
                            case 'n':
                                r--;
                                c--;
                                break;
                            case 's':
                                r--;
                                c++;
                                break;
                            case 'w':
                                r++;
                                c--;
                                break;
                            case 'e':
                                r++;
                                c++;
                                break;
                        }
                        if (validBox(r, c) && mineBoard[r][c] == MINE) {
                            mines++;
                        }
                    }
                    mineBoard[i][j] = Character.forDigit(mines, 10);
                }
            }
        }
    }

    // Kullanıcı tarafından mayın için seçilen satır ve sütunun tahtanın sınırları içinde olup olmadığını kontrol eder.
    private boolean validBox(int row, int column) {
        return row >= 0 && row < rowSize && column >= 0 && column < columnSize;
    }

    // Oyunu oynar
    public void play() {
        Scanner scanner = new Scanner(System.in);
        printBoard(); // Oyun tahtasını kullanıcıya göster

        while (remainingBoxes > 0) {
            System.out.print("Satırı Girin (1-" + rowSize + ") : ");
            int row = scanner.nextInt() - 1; // Sıfır tabanlı dizinleme için düzeltme yap
            System.out.print("Sütunu Girin (1-" + columnSize + ") : ");
            int column = scanner.nextInt() - 1; // Sıfır tabanlı dizinleme için düzeltme yap

            if (!validBox(row, column)) {
                System.out.println("Geçersiz koordinatlar, lütfen tekrar deneyin!");
                continue;
            }

            if (openedBoxes[row][column]) {
                System.out.println("Bu kutu önceden açmıştınız, lütfen başka bir tane seçin.");
                continue;
            }

            openedBoxes[row][column] = true; // Seçilen kutuyu açık olarak işaretle
            remainingBoxes--;

            if (mineBoard[row][column] == MINE) {
                System.out.println("Kaybettin :( Bir mayına çarptınız! Oyun Bitti!");
                gameOver();
                return;
            } else {
                gameBoard[row][column] = mineBoard[row][column]; // Seçilen kutunun değerini (mayın sayısı) oyun tahtasına yaz
                printBoard(); // Güncellenmiş oyun tahtasını göster
            }
        }

        System.out.println("Tebrikler! Kazandın!");
    }

    // Oyun tahtasını kullanıcıya gösterir
    private void printBoard() {
        System.out.println("===========================");
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    // Oyunun sonlanmasına neden olan durumda tüm mayınları ve oyun tahtasını kullanıcıya gösterir
    private void gameOver() {
        System.out.println("Mayın Konumları");
        System.out.println("===========================");
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(mineBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rowSize, columnSize;
        do {
            System.out.print("Satır sayısını girin: ");
            rowSize = scanner.nextInt();
            if (rowSize <= 1) {
                System.out.println("Daha büyük bir değer giriniz. En az 2 satır olmalıdır.");
            }
        } while (rowSize <= 1);

        do {
            System.out.print("Sütun sayısını girin: ");
            columnSize = scanner.nextInt();
            if (columnSize <= 1) {
                System.out.println("Daha büyük bir değer giriniz. En az 2 sütun olmalıdır.");
            }
        } while (columnSize <= 1);

        MineSweeper mineSweeper = new MineSweeper(rowSize, columnSize);
        mineSweeper.play();
    }
}