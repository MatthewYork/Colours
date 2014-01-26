package com.mattyork.colours;

import android.graphics.Color;

public class Colour extends Color {
	
	//Color Scheme Enumeration (for color scheme generation)
		public enum ColorScheme {
			ColorSchemeAnalagous, ColorSchemeMonochromatic, ColorSchemeTriad, ColorSchemeComplementary
		}

	// ///////////////////////////////////
	// 4 Color Scheme from Color
	// ///////////////////////////////////
	/**
	 * Creates an int[] of 4 Colors that complement the Color.
	 * 
	 * @param type
	 *            ColorSchemeAnalagous, ColorSchemeMonochromatic,
	 *            ColorSchemeTriad, ColorSchemeComplementary
	 * @return ArrayList<Integer>
	 */
	public static int[] colorSchemeOfType(int color, ColorScheme type) {
		float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);

		switch (type) {
		case ColorSchemeAnalagous:
			return Colour.analagousColors(hsv);
		case ColorSchemeMonochromatic:
			return Colour.monochromaticColors(hsv);
		case ColorSchemeTriad:
			return Colour.triadColors(hsv);
		case ColorSchemeComplementary:
			return Colour.complementaryColors(hsv);
		default:
			return null;
		}
	}

	public static int[] analagousColors(float[] hsv) {
		float[] CA1 = { Colour.addDegrees(hsv[0], 15),
				(float) (hsv[1] - 0.05), (float) (hsv[2] - 0.05) };
		float[] CA2 = { Colour.addDegrees(hsv[0], 30),
				(float) (hsv[1] - 0.05), (float) (hsv[2] - 0.1) };
		float[] CB1 = { Colour.addDegrees(hsv[0], -15),
				(float) (hsv[1] - 0.05), (float) (hsv[2] - 0.05) };
		float[] CB2 = { Colour.addDegrees(hsv[0], -30),
				(float) (hsv[1] - 0.05), (float) (hsv[2] - 0.1) };

		return new int[] { Color.HSVToColor(CA1), Color.HSVToColor(CA2),
				Color.HSVToColor(CB1), Color.HSVToColor(CB2) };
	}

	public static int[] monochromaticColors(float[] hsv) {
		float[] CA1 = { hsv[0], (float) (hsv[1]), (float) (hsv[2] / 2) };
		float[] CA2 = { hsv[0], (float) (hsv[1] / 2), (float) (hsv[2] / 3) };
		float[] CB1 = { hsv[0], (float) (hsv[1] / 3), (float) (hsv[2] * 2 / 3) };
		float[] CB2 = { hsv[0], (float) (hsv[1]), (float) (hsv[2] * 4 / 5) };

		return new int[] { Color.HSVToColor(CA1), Color.HSVToColor(CA2),
				Color.HSVToColor(CB1), Color.HSVToColor(CB2) };
	}

	public static int[] triadColors(float[] hsv) {

		float[] CA1 = { Colour.addDegrees(hsv[0], 120), (float) (hsv[1]),
				(float) (hsv[2]) };
		float[] CA2 = { Colour.addDegrees(hsv[0], 120),
				(float) (hsv[1] * 7 / 6), (float) (hsv[2] - 0.05) };
		float[] CB1 = { Colour.addDegrees(hsv[0], 240), (float) (hsv[1]),
				(float) (hsv[2]) };
		float[] CB2 = { Colour.addDegrees(hsv[0], 240),
				(float) (hsv[1] * 7 / 6), (float) (hsv[2] - 0.05) };

		return new int[] { Color.HSVToColor(CA1), Color.HSVToColor(CA2),
				Color.HSVToColor(CB1), Color.HSVToColor(CB2) };
	}

	public static int[] complementaryColors(float[] hsv) {
		float[] CA1 = { hsv[0], (float) (hsv[1] * 5 / 7), (float) (hsv[2]) };
		float[] CA2 = { hsv[0], (float) (hsv[1]), (float) (hsv[2] * 4 / 5) };
		float[] CB1 = { Colour.addDegrees(hsv[0], 180), (float) (hsv[1]),
				(float) (hsv[2]) };
		float[] CB2 = { Colour.addDegrees(hsv[0], 180),
				(float) (hsv[1] * 5 / 7), (float) (hsv[2]) };

		return new int[] { Color.HSVToColor(CA1), Color.HSVToColor(CA2),
				Color.HSVToColor(CB1), Color.HSVToColor(CB2) };
	}

	public static float addDegrees(float addDeg, float staticDeg) {
		staticDeg += addDeg;
		if (staticDeg > 360) {
			float offset = staticDeg - 360;
			return offset;
		} else if (staticDeg < 0) {
			return -1 * staticDeg;
		} else {
			return staticDeg;
		}
	}
	
	/**
	 * Returns black or white, depending on which color would contrast best with the provided color.
	 * 
	 * @param int (Color)
	 *            
	 * @return int
	 */
	public static int blackOrWhiteContrastingColor(int color)
	{
	    int[] rgbaArray = new int[]{Colour.red(color), Colour.green(color), Colour.blue(color)};
	    double a = 1 - ((0.00299 * (double)rgbaArray[0]) + (0.00587 * (double)rgbaArray[1]) + (0.00114 * (double)rgbaArray[2]));
	    return a < 0.5 ? Colour.BLACK : Colour.WHITE;
	}


	/**
	 * This method will create a color instance that is the exact opposite color from another color on the color wheel. The same saturation and brightness are preserved, just the hue is changed.
	 * 
	 * @param int (Color)
	 *            
	 * @return int
	 */
	public static int complementaryColor(int color)
	{
		float[] hsv = new float[3];
		Colour.colorToHSV(color, hsv);
		float newH = Colour.addDegrees(180, hsv[0]);
		hsv[0] = newH;
		
		return Colour.HSVToColor(hsv);
	}

	// Predefined Colors
	// System Colors
	public static int infoBlueColor() {
		return Colour.rgb(47, 112, 225);
	}

	public static int successColor() {
		return Colour.rgb(83, 215, 106);
	}

	public static int warningColor() {
		return Colour.rgb(221, 170, 59);
	}

	public static int dangerColor() {
		return Colour.rgb(229, 0, 15);
	}

	// Whites
	public static int antiqueWhiteColor() {
		return Colour.rgb(250, 235, 215);
	}

	public static int oldLaceColor() {
		return Colour.rgb(253, 245, 230);
	}

	public static int ivoryColor() {
		return Colour.rgb(255, 255, 240);
	}

	public static int seashellColor() {
		return Colour.rgb(255, 245, 238);
	}

	public static int ghostWhiteColor() {
		return Colour.rgb(248, 248, 255);
	}

	public static int snowColor() {
		return Colour.rgb(255, 250, 250);
	}

	public static int linenColor() {
		return Colour.rgb(250, 240, 230);
	}

	// Grays
	public static int black25PercentColor() {
		return Colour.rgb(64, 64, 64);
	}

	public static int black50PercentColor() {
		return Colour.rgb(128, 128, 128);
	}

	public static int black75PercentColor() {
		return Colour.rgb(192, 192, 192);
	}

	public static int warmGrayColor() {
		return Colour.rgb(133, 117, 112);
	}

	public static int coolGrayColor() {
		return Colour.rgb(118, 122, 133);
	}

	public static int charcoalColor() {
		return Colour.rgb(34, 34, 34);
	}

	// Blues
	public static int tealColor() {
		return Colour.rgb(28, 160, 170);
	}

	public static int steelBlueColor() {
		return Colour.rgb(103, 153, 170);
	}

	public static int robinEggColor() {
		return Colour.rgb(141, 218, 247);
	}

	public static int pastelBlueColor() {
		return Colour.rgb(99, 161, 247);
	}

	public static int turquoiseColor() {
		return Colour.rgb(112, 219, 219);
	}

	public static int skyBlueColor() {
		return Colour.rgb(0, 178, 238);
	}

	public static int indigoColor() {
		return Colour.rgb(13, 79, 139);
	}

	public static int denimColor() {
		return Colour.rgb(67, 114, 170);
	}

	public static int blueberryColor() {
		return Colour.rgb(89, 113, 173);
	}

	public static int cornflowerColor() {
		return Colour.rgb(100, 149, 237);
	}

	public static int babyBlueColor() {
		return Colour.rgb(190, 220, 230);
	}

	public static int midnightBlueColor() {
		return Colour.rgb(13, 26, 35);
	}

	public static int fadedBlueColor() {
		return Colour.rgb(23, 137, 155);
	}

	public static int icebergColor() {
		return Colour.rgb(200, 213, 219);
	}

	public static int waveColor() {
		return Colour.rgb(102, 169, 251);
	}

	// Greens
	public static int emeraldColor() {
		return Colour.rgb(1, 152, 117);
	}

	public static int grassColor() {
		return Colour.rgb(99, 214, 74);
	}

	public static int pastelGreenColor() {
		return Colour.rgb(126, 242, 124);
	}

	public static int seafoamColor() {
		return Colour.rgb(77, 226, 140);
	}

	public static int paleGreenColor() {
		return Colour.rgb(176, 226, 172);
	}

	public static int cactusGreenColor() {
		return Colour.rgb(99, 111, 87);
	}

	public static int chartreuseColor() {
		return Colour.rgb(69, 139, 0);
	}

	public static int hollyGreenColor() {
		return Colour.rgb(32, 87, 14);
	}

	public static int oliveColor() {
		return Colour.rgb(91, 114, 34);
	}

	public static int oliveDrabColor() {
		return Colour.rgb(107, 142, 35);
	}

	public static int moneyGreenColor() {
		return Colour.rgb(134, 198, 124);
	}

	public static int honeydewColor() {
		return Colour.rgb(216, 255, 231);
	}

	public static int limeColor() {
		return Colour.rgb(56, 237, 56);
	}

	public static int cardTableColor() {
		return Colour.rgb(87, 121, 107);
	}

	// Reds
	public static int salmonColor() {
		return Colour.rgb(233, 87, 95);
	}

	public static int brickRedColor() {
		return Colour.rgb(151, 27, 16);
	}

	public static int easterPinkColor() {
		return Colour.rgb(241, 167, 162);
	}

	public static int grapefruitColor() {
		return Colour.rgb(228, 31, 54);
	}

	public static int pinkColor() {
		return Colour.rgb(255, 95, 154);
	}

	public static int indianRedColor() {
		return Colour.rgb(205, 92, 92);
	}

	public static int strawberryColor() {
		return Colour.rgb(190, 38, 37);
	}

	public static int coralColor() {
		return Colour.rgb(240, 128, 128);
	}

	public static int maroonColor() {
		return Colour.rgb(80, 4, 28);
	}

	public static int watermelonColor() {
		return Colour.rgb(242, 71, 63);
	}

	public static int tomatoColor() {
		return Colour.rgb(255, 99, 71);
	}

	public static int pinkLipstickColor() {
		return Colour.rgb(255, 105, 180);
	}

	public static int paleRoseColor() {
		return Colour.rgb(255, 228, 225);
	}

	public static int crimsonColor() {
		return Colour.rgb(187, 18, 36);
	}

	// Purples
	public static int eggplantColor() {
		return Colour.rgb(105, 5, 98);
	}

	public static int pastelPurpleColor() {
		return Colour.rgb(207, 100, 235);
	}

	public static int palePurpleColor() {
		return Colour.rgb(229, 180, 235);
	}

	public static int coolPurpleColor() {
		return Colour.rgb(140, 93, 228);
	}

	public static int violetColor() {
		return Colour.rgb(191, 95, 255);
	}

	public static int plumColor() {
		return Colour.rgb(139, 102, 139);
	}

	public static int lavenderColor() {
		return Colour.rgb(204, 153, 204);
	}

	public static int raspberryColor() {
		return Colour.rgb(135, 38, 87);
	}

	public static int fuschiaColor() {
		return Colour.rgb(255, 20, 147);
	}

	public static int grapeColor() {
		return Colour.rgb(54, 11, 88);
	}

	public static int periwinkleColor() {
		return Colour.rgb(135, 159, 237);
	}

	public static int orchidColor() {
		return Colour.rgb(218, 112, 214);
	}

	// Yellows
	public static int goldenrodColor() {
		return Colour.rgb(215, 170, 51);
	}

	public static int yellowGreenColor() {
		return Colour.rgb(192, 242, 39);
	}

	public static int bananaColor() {
		return Colour.rgb(229, 227, 58);
	}

	public static int mustardColor() {
		return Colour.rgb(205, 171, 45);
	}

	public static int buttermilkColor() {
		return Colour.rgb(254, 241, 181);
	}

	public static int goldColor() {
		return Colour.rgb(139, 117, 18);
	}

	public static int creamColor() {
		return Colour.rgb(240, 226, 187);
	}

	public static int lightCreamColor() {
		return Colour.rgb(240, 238, 215);
	}

	public static int wheatColor() {
		return Colour.rgb(240, 238, 215);
	}

	public static int beigeColor() {
		return Colour.rgb(245, 245, 220);
	}

	// Oranges
	public static int peachColor() {
		return Colour.rgb(242, 187, 97);
	}

	public static int burntOrangeColor() {
		return Colour.rgb(184, 102, 37);
	}

	public static int pastelOrangeColor() {
		return Colour.rgb(248, 197, 143);
	}

	public static int cantaloupeColor() {
		return Colour.rgb(250, 154, 79);
	}

	public static int carrotColor() {
		return Colour.rgb(237, 145, 33);
	}

	public static int mandarinColor() {
		return Colour.rgb(247, 145, 55);
	}

	// Browns
	public static int chiliPowderColor() {
		return Colour.rgb(199, 63, 23);
	}

	public static int burntSiennaColor() {
		return Colour.rgb(138, 54, 15);
	}

	public static int chocolateColor() {
		return Colour.rgb(94, 38, 5);
	}

	public static int coffeeColor() {
		return Colour.rgb(141, 60, 15);
	}

	public static int cinnamonColor() {
		return Colour.rgb(123, 63, 9);
	}

	public static int almondColor() {
		return Colour.rgb(196, 142, 72);
	}

	public static int eggshellColor() {
		return Colour.rgb(252, 230, 201);
	}

	public static int sandColor() {
		return Colour.rgb(222, 182, 151);
	}

	public static int mudColor() {
		return Colour.rgb(70, 45, 29);
	}

	public static int siennaColor() {
		return Colour.rgb(160, 82, 45);
	}

	public static int dustColor() {
		return Colour.rgb(236, 214, 197);
	}

}
