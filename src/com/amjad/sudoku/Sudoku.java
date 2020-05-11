import com.amjad.sudoku.enums.BoardSize;
import com.amjad.sudoku.enums.Difficulty;

public class Sudoku {

	private final BoardSize BOARD_SIZE;
	private final int EMPTY_CELL = 0;
	private final Difficulty DIFFICULTY;
	private int[][] GAME_BOARD;

	/**
	 * 
	 */
	private int squareRootOfSize;

	public Sudoku(BoardSize boardSize, Difficulty difficulty) {
		BOARD_SIZE = boardSize;
		DIFFICULTY = difficulty;
		GAME_BOARD = new int[BOARD_SIZE.getValue()][BOARD_SIZE.getValue()];
		double SR = Math.sqrt(BOARD_SIZE.getValue());
		squareRootOfSize = (int) SR;
	}

	private boolean isSafe(int row, int col, int value) {
		return isColSafe(col, value) && isRowSafe(row, value)
				&& isSquareSafe(row - row % squareRootOfSize, col - col % squareRootOfSize, value);
	}

	private boolean isRowSafe(int row, int value) {
		for (int i = 0; i < BOARD_SIZE.getValue(); i++)
			if (GAME_BOARD[row][i] == value)
				return false;
		return true;
	}

	private boolean isSquareSafe(int rowStart, int colStart, int value) {
		for (int i = 0; i < squareRootOfSize; i++)
			for (int j = 0; j < squareRootOfSize; j++)
				if (GAME_BOARD[i + rowStart][j + colStart] == value)
					return false;
		return true;
	}

	private boolean isColSafe(int col, int value) {
		for (int i = 0; i < BOARD_SIZE.getValue(); i++)
			if (GAME_BOARD[i][col] == value)
				return false;
		return true;
	}


	
	/**
	 * 1 - Fill all the diagonal 3x3 matrices.
	 * 2 - Fill recursively rest of the non-diagonal matrices.
	 *     For every cell to be filled, we try all numbers until
	 *     we find a safe number to be placed. 
	 */
	private void fillBoard() {
		fillDiagonals();
		fillRest();
	}

	private void fillRest() {
		int num;
		for(int i = 0 ;i<BOARD_SIZE.getValue();i++) {
			for(int j = 0 ;j<BOARD_SIZE.getValue();j++) {

					do {
						num = randomGenerator(BOARD_SIZE.getValue());
					} while (!isSafe(i, j, num));
					GAME_BOARD[i][j] = num;

			}
		}
	}

	private void fillBox(int row, int col) {
		int num;
		for(int i = 0 ;i<squareRootOfSize;i++) {
			for(int j = 0 ;j<squareRootOfSize;j++) {
				do {
					num = randomGenerator(BOARD_SIZE.getValue());
				} while (!isSquareSafe(row, col, num));
				GAME_BOARD[row + i][col + j] = num;
			}
		}
	}

	private int randomGenerator(int value) {
		return (int) Math.floor((Math.random() * value + 1));
	}

	private void fillDiagonals() {
		for (int i = 0; i < BOARD_SIZE.getValue(); i += squareRootOfSize){
			fillBox(i, i);
		}
	}

	private void seed(int numberOfCells){
		// todo seed random cells before fillRecursively
		for (int i = 0; i < numberOfCells; i++) {
				int row = randomGenerator(BOARD_SIZE.getValue()-1);
				int col = randomGenerator(BOARD_SIZE.getValue()-1);
				int num;
				do{
					num = randomGenerator(BOARD_SIZE.getValue());
				}while(!isSafe(row, col, num));
			GAME_BOARD[row][col] = num;
		}
	}

	private boolean fillRecursively(){
		for (int i = 0; i < BOARD_SIZE.getValue(); i++) {
			for (int j = 0; j < BOARD_SIZE.getValue(); j++) {
				if(GAME_BOARD[i][j] == EMPTY_CELL){
					for (int k = 1; k <= BOARD_SIZE.getValue(); k++) {
						if(isSafe(i,j,k)){
							GAME_BOARD[i][j] = k;
							if(fillRecursively())
								return true;
							else
								GAME_BOARD[i][j] = EMPTY_CELL;
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder board = new StringBuilder();
		for (int row = 0; row < BOARD_SIZE.getValue(); row++) {
			for (int col = 0; col < BOARD_SIZE.getValue(); col++)
				board.append(GAME_BOARD[row][col]).append(" ");
			board.append("\n");
		}
		return board.toString();
	}

	public static void main(String[] args) {
		Sudoku board = new Sudoku(BoardSize.NineByNine, Difficulty.Medium);
		//board.fillBoard();
		board.seed(2);
		board.fillRecursively();

		System.out.println(board);

//		board = new Sudoku(BoardSize.NineByNine, Difficulty.Medium);
//		board.fillRecursively();
//		System.out.println(board);
	}
}
