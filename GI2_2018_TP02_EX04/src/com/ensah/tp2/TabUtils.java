package com.ensah.tp2;

public class TabUtils {

	public static int[] compareTab(int[] tab1, int[] tab2) {

		if (tab1.length != tab2.length) {

			throw new RuntimeException();
		}

		int[] res = new int[tab1.length];

		for (int i = 0; i < tab1.length; i++) {

			if (tab1[i] != tab2[i]) {
				res[i] = 1;
			} else {
				res[i] = 0;
			}

		}

		return res;

	}

	public static int[] compareTabV2(int[] tab1, int[] tab2) {

		if (tab1.length != tab2.length) {

			throw new RuntimeException();
		}

		int[] res = new int[tab1.length];

		for (int i = 0; i < tab1.length; i++) {

			if (tab1[i] != tab2[i]) {
				res[i] = 1;
			} else {
				res[i] = 0;
			}

		}

		return res;

	}

	public static void main(String[] args) {

		int[] tab1 = { 1, 2, 3 };
		int[] tab2 = { 1, 1, 3 };
		int[] r = compareTab(tab1, tab2);
		for (int it : r) {
			System.out.println(it);
		}
	}

}
