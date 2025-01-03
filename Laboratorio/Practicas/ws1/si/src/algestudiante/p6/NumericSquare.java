package algestudiante.p6;

public class NumericSquare extends NumericSquareBase {

	private String[][] m;
	private String[][] og;
	private String[][] sol;
	private boolean solucion = false;

	public NumericSquare(String[][] table) {
		m = table;
		og = copy(m);
	}

	private String[][] copy(String[][] matriz) {
		String[][] r = new String[matriz.length][matriz.length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				r[i][j] = matriz[i][j];
			}

		}
		return r;
	}

	public void backtracking() {
		backtrackingR(0, 0);

	}

	private void backtrackingR(int i, int j) {
		if (i == m.length - 1 && j == 0) {
			solucion = true;
			sol = copy(m);
			return;
		} else {
			for (int k = 0; k <= 9; k++) {
				if (!solucion) {

					if (m[i][j].equals("?")) {
						m[i][j] = k + "";
					}

					if (!fallaOperacion(i, j)) {
						if (j != m.length - 3)
							backtrackingR(i, j + 2);
						else
							backtrackingR(i + 2, 0);

					}
					m[i][j] = og[i][j];
					if (!m[i][j].equals("?"))
						break;

				}
			}
		}

	}

	private boolean fallaOperacion(int i, int j) {
		if (!(j == m.length - 3 || i == m.length - 3)) {
			return false;
		}
		if (j == m.length - 3) {
			int r = Integer.parseInt(m[i][0]);
			for (int o = 1; o < m.length - 3; o += 2) {
				switch (m[i][o]) {
				case "*":
					r *= Integer.parseInt(m[i][o + 1]);
					break;
				case "+":
					r += Integer.parseInt(m[i][o + 1]);
					break;
				case "-":
					r -= Integer.parseInt(m[i][o + 1]);
					break;
				case "/":
					String a = m[i][o + 1];
					if (a.equals("0")) {
						r = 0;
					} else if (r % Integer.parseInt(m[i][o + 1]) != 0) {
						return true;
					} else
						r = r / Integer.parseInt(m[i][o + 1]);
					break;

				default:
					break;
				}
			}
			if (Integer.parseInt(m[i][m.length - 1]) != r)
				return true;
		}
		if (i == m.length - 3) {
			int r = Integer.parseInt(m[0][j]);
			for (int o = 1; o < m.length - 3; o += 2) {
				switch (m[o][j]) {
				case "*":
					r *= Integer.parseInt(m[o + 1][j]);
					break;
				case "+":
					r += Integer.parseInt(m[o + 1][j]);
					break;
				case "-":
					r -= Integer.parseInt(m[o + 1][j]);
					break;
				case "/":
					String a = m[o + 1][j];
					if (a.equals("0")) {
						r = 0;
					} else if (r % Integer.parseInt(m[o + 1][j]) != 0) {
						return true;
					} else
						r = r / Integer.parseInt(m[o + 1][j]);
					break;

				default:
					break;
				}
			}
			if (Integer.parseInt(m[m.length - 1][j]) != r)
				return true;

		}

		return false;
	}

	public String[][] getSol() {
		return sol;
	}

	@Override
	public Object getNumberOfSolutions() {
		if (solucion)
			return 1;
		else
			return 0;
	}

}
