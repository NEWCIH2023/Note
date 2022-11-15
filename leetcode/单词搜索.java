import javax.xml.transform.Templates;

public class 单词搜索 {
    public static void main(String[] args) {
        单词搜索 s = new 单词搜索();
        System.out.println(s.exist(
                new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } }, "ABCCED"));
    }

    public boolean exist(char[][] board, String word) {
        boolean temp = new boolean[board.length][board[0].length];
        return subLoop(board, word, 0, temp);
    }

    public boolean subLoop(char[][] board, String word, int index,int px, int py, boolean[][] temp) {
        if (index == word.length()) {
            return true;
        }

        for (i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (!temp[i][j] && board[i][j] == word.charAt(index)) {
                    temp[i][j] = true;
                    index
                }
            }
        }

    }
}