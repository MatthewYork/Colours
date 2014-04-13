package com.mattyork.colours;

import android.graphics.Color;

/**
 * The type Colour.
 */
public class Colour extends Color {
	
	//Color Scheme Enumeration (for color scheme generation)
		public enum ColorScheme {
			ColorSchemeAnalagous, ColorSchemeMonochromatic, ColorSchemeTriad, ColorSchemeComplementary
		}

        public enum ColorDistanceFormula {
            ColorDistanceFormulaCIE76, ColorDistanceFormulaCIE94, ColorDistanceFormulaCIE2000
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
	 * @param color (Color)
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
	 * @param color (Color)
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

    // CMYK
    /**
     * Color to cMYK.
     *
     * @param color the color int
     * @return float [ ]
     */
    public static float[] colorToCMYK(int color)
    {
        float r = Colour.red(color);
        float g = Colour.green(color);
        float b = Colour.blue(color);
        float c = 1 - r/255;
        float m = 1 - g/255;
        float y = 1 - b/255;
        float k = Math.min(1, Math.min(c, Math.min(m, y)));
        if (k == 1) {
            c = 0;
            m = 0;
            y = 0;
        }
        else {
            c = (c - k)/(1 - k);
            m = (m - k)/(1 - k);
            y = (y - k)/(1 - k);
        }

        return new float[]{c, m, y, k};
    }


    /**
     * CMYK to color.
     *
     * @param cmyk the cmyk array
     * @return color
     */
    public static int CMYKtoColor(float[] cmyk)
    {
        float c = cmyk[0] * (1 - cmyk[3]) + cmyk[3];
        float m = cmyk[1] * (1 - cmyk[3]) + cmyk[3];
        float y = cmyk[2] * (1 - cmyk[3]) + cmyk[3];
        return Colour.rgb((int)((1-c)*255), (int)((1-m)*255), (int)((1-y)*255));
    }

    /**
     * Color to cIE _ lAB.
     *
     * @param color the color int
     * @return double[]
     */
    public static double[] colorToCIE_LAB(int color)
    {
        // Convert Color to XYZ format first
        double r = Colour.red(color)/255.0;
        double g = Colour.green(color)/255.0;
        double b = Colour.blue(color)/255.0;

        // Create deltaRGB
        r = (r > 0.04045) ? Math.pow((r + 0.055)/1.055, 2.40) : (r/12.92);
        g = (g > 0.04045) ? Math.pow((g + 0.055)/1.055, 2.40) : (g/12.92);
        b = (b > 0.04045) ? Math.pow((b + 0.055)/1.055, 2.40) : (b/12.92);

        // Create XYZ
        double X = r*41.24 + g*35.76 + b*18.05;
        double Y = r*21.26 + g*71.52 + b*7.22;
        double Z = r*1.93 + g*11.92 + b*95.05;

        // Convert XYZ to L*a*b*
        X = X/95.047;
        Y = Y/100.000;
        Z = Z/108.883;
        X = (X > Math.pow((6.0/29.0), 3.0)) ? Math.pow(X, 1.0/3.0) : (1/3)*Math.pow((29.0/6.0), 2.0) * X + 4/29.0;
        Y = (Y > Math.pow((6.0/29.0), 3.0)) ? Math.pow(Y, 1.0/3.0) : (1/3)*Math.pow((29.0/6.0), 2.0) * Y + 4/29.0;
        Z = (Z > Math.pow((6.0/29.0), 3.0)) ? Math.pow(Z, 1.0/3.0) : (1/3)*Math.pow((29.0/6.0), 2.0) * Z + 4/29.0;
        double CIE_L = 116*Y - 16;
        double CIE_a = 500 * (X - Y);
        double CIE_b = 200 * (Y - Z);
        return new double[]{CIE_L, CIE_a, CIE_b};
    }

    /**
     * CIE _ lab to color.
     *
     * @param cie_lab the double[]
     * @return color
     */
    public static int CIE_LabToColor(double[] cie_lab)
    {
        double L = cie_lab[0];
        double A = cie_lab[1];
        double B = cie_lab[2];
        double Y = (L + 16.0)/116.0;
        double X = A/500 + Y;
        double Z = Y - B/200;
        X = (Math.pow(X, 3.0) > 0.008856) ? Math.pow(X, 3.0) : (X - 4/29.0)/7.787;
        Y = (Math.pow(Y, 3.0) > 0.008856) ? Math.pow(Y, 3.0) : (Y - 4/29.0)/7.787;
        Z = (Math.pow(Z, 3.0) > 0.008856) ? Math.pow(Z, 3.0) : (Z - 4/29.0)/7.787;
        X = X*.95047;
        Y = Y*1.00000;
        Z = Z*1.08883;

        // Convert XYZ to RGB
        double R = X*3.2406 + Y*-1.5372 + Z*-0.4986;
        double G = X*-0.9689 + Y*1.8758 + Z*0.0415;
        double _B = X*0.0557 + Y*-0.2040 + Z*1.0570;
        R = (R > 0.0031308) ? 1.055 * (Math.pow(R, (1/2.4))) - 0.055 : R * 12.92;
        G = (G > 0.0031308) ? 1.055 * (Math.pow(G, (1/2.4))) - 0.055 : G * 12.92;
        _B = (_B > 0.0031308) ? 1.055 * (Math.pow(_B, (1/2.4))) - 0.055 : _B * 12.92;
        return Colour.rgb((int)(R*255), (int)(G*255), (int)(_B*255));
    }

    public static double distanceBetweenColors(int colorA, int colorB) {
        return distanceBetweenColorsWithFormula(colorA, colorB, ColorDistanceFormula.ColorDistanceFormulaCIE94);
    }

    public static double distanceBetweenColorsWithFormula(int colorA, int colorB, ColorDistanceFormula formula) {
        double[] lab1 = Colour.colorToCIE_LAB(colorA);
        double[] lab2 = Colour.colorToCIE_LAB(colorB);
        double L1 = lab1[0];
        double A1 = lab1[1];
        double B1 = lab1[2];
        double L2 = lab2[0];
        double A2 = lab2[1];
        double B2 = lab2[2];

        // CIE76 first
        if (formula == ColorDistanceFormula.ColorDistanceFormulaCIE76) {
            double distance = Math.sqrt(Math.pow((L1 - L2), 2) + Math.pow((A1 - A2), 2) + Math.pow((B1 - B2), 2));
            return distance;
        }

        // More Common Variables
        double kL = 1;
        double kC = 1;
        double kH = 1;
        double k1 = 0.045;
        double k2 = 0.015;
        double deltaL = L1 - L2;
        double C1 = Math.sqrt((A1 * A1) + (B1 * B1));
        double C2 = Math.sqrt((A2 * A2) + (B2 * B2));
        double deltaC = C1 - C2;
        double deltaH = Math.sqrt(Math.pow((A1 - A2), 2.0) + Math.pow((B1 - B2), 2.0) - Math.pow(deltaC, 2.0));
        double sL = 1;
        double sC = 1 + k1*(Math.sqrt((A1 * A1) + (B1 * B1)));
        double sH = 1 + k2*(Math.sqrt((A1 * A1) + (B1 * B1)));

        // CIE94
        if (formula == ColorDistanceFormula.ColorDistanceFormulaCIE94) {
            return Math.sqrt(Math.pow((deltaL / (kL * sL)), 2.0) + Math.pow((deltaC / (kC * sC)), 2.0) + Math.pow((deltaH / (kH * sH)), 2.0));
        }

        // CIE2000
        // More variables
        double deltaLPrime = L2 - L1;
        double meanL = (L1 + L2)/2;
        double meanC = (C1 + C2)/2;
        double aPrime1 = A1 + A1/2*(1 - Math.sqrt(Math.pow(meanC, 7.0) / (Math.pow(meanC, 7.0) + Math.pow(25.0, 7.0))));
        double aPrime2 = A2 + A2/2*(1 - Math.sqrt(Math.pow(meanC, 7.0) / (Math.pow(meanC, 7.0) + Math.pow(25.0, 7.0))));
        double cPrime1 = Math.sqrt((aPrime1 * aPrime1) + (B1 * B1));
        double cPrime2 = Math.sqrt((aPrime2 * aPrime2) + (B2 * B2));
        double cMeanPrime = (cPrime1 + cPrime2)/2;
        double deltaCPrime = cPrime1 - cPrime2;
        double hPrime1 = Math.atan2(B1, aPrime1);
        double hPrime2 = Math.atan2(B2, aPrime2);
        hPrime1 = hPrime1 % RAD(360.0);
        hPrime2 = hPrime2 % RAD(360.0);
        double deltahPrime = 0;
        if (Math.abs(hPrime1 - hPrime2) <= RAD(180.0)) {
            deltahPrime = hPrime2 - hPrime1;
        }
        else {
            deltahPrime = (hPrime2 <= hPrime1) ? hPrime2 - hPrime1 + RAD(360.0) : hPrime2 - hPrime1 - RAD(360.0);
        }
        double deltaHPrime = 2 * Math.sqrt(cPrime1 * cPrime2) * Math.sin(deltahPrime / 2);
        double meanHPrime = (Math.abs(hPrime1 - hPrime2) <= RAD(180.0)) ? (hPrime1 + hPrime2)/2 : (hPrime1 + hPrime2 + RAD(360.0))/2;
        double T = 1 - 0.17*Math.cos(meanHPrime - RAD(30.0)) + 0.24*Math.cos(2 * meanHPrime)+0.32*Math.cos(3*meanHPrime + RAD(6.0)) - 0.20*Math.cos(4 * meanHPrime - RAD(63.0));
        sL = 1 + (0.015 * Math.pow((meanL - 50), 2))/Math.sqrt(20 + Math.pow((meanL - 50), 2));
        sC = 1 + 0.045*cMeanPrime;
        sH = 1 + 0.015*cMeanPrime*T;
        double Rt = -2 * Math.sqrt(Math.pow(cMeanPrime, 7) / (Math.pow(cMeanPrime, 7) + Math.pow(25.0, 7))) * Math.sin(RAD(60.0) * Math.exp(-1 * Math.pow((meanHPrime - RAD(275.0)) / RAD(25.0), 2)));

        // Finally return CIE2000 distance
        return Math.sqrt(Math.pow((deltaLPrime / (kL * sL)), 2) + Math.pow((deltaCPrime / (kC * sC)), 2) + Math.pow((deltaHPrime / (kH * sH)),  Rt * (deltaC / (kC * sC)) * (deltaHPrime / (kH * sH))));
    }
    
    private static double RAD(double degree) {
        return degree * Math.PI/180;
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
