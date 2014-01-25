package com.mattyork.colours;

import java.util.ArrayList;

import android.graphics.Color;

public class Colours extends Color{
	
	// Color Scheme Enumeration (for color scheme generation)
	enum ColorScheme {
		ColorSchemeAnalagous,
	    ColorSchemeMonochromatic,
	    ColorSchemeTriad,
	    ColorSchemeComplementary
	}

	/////////////////////////////////////
	// 4 Color Scheme from Color
	/////////////////////////////////////
	/**
	 Creates an ArrayList<Integer> of 4 Colors that complement the Color.
	 @param type ColorSchemeAnalagous, ColorSchemeMonochromatic, ColorSchemeTriad, ColorSchemeComplementary
	 @return    ArrayList<Integer>
	 */
	public static ArrayList<String> colorSchemeOfType(int color, ColorScheme type){ 
		float[] hsv = new float[3];
		Color.colorToHSV( color, hsv );
	    
	    switch (type) {
	        case ColorSchemeAnalagous:
	            return Colours.analagousColors(hsv);
	        case ColorSchemeMonochromatic:
	            return Colours.monochromaticColors(hsv);
	        case ColorSchemeTriad:
	            return Colours.triadColors(hsv);
	        case ColorSchemeComplementary:
	            return Colours.complementaryColors(hsv);
	        default:
	            return null;
	    }
	}
	
	public static ArrayList<String> analagousColors(float[] hsv)
	{
		float[] CA1 = {Colours.addDegrees(hsv[0], 15), (float) (hsv[1]-0.05), (float) (hsv[2]-0.05)};
		String colorAbove1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA1));
		float[] CA2 = {Colours.addDegrees(hsv[0], 30), (float) (hsv[1]-0.05), (float) (hsv[2]-0.1)};
		String colorAbove2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA2));
		float[] CB1 = {Colours.addDegrees(hsv[0], -15), (float) (hsv[1]-0.05), (float) (hsv[2]-0.05)};
		String colorBelow1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB1));
		float[] CB2 = {Colours.addDegrees(hsv[0], -30), (float) (hsv[1]-0.05), (float) (hsv[2]-0.1)};
		String colorBelow2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB2));
	    
		ArrayList<String> colorStrings = new ArrayList<String>();
		colorStrings.add(colorAbove2);
		colorStrings.add(colorAbove1);
		colorStrings.add(colorBelow1);
		colorStrings.add(colorBelow2);
		
	    return colorStrings;
	}

	public static ArrayList<String> monochromaticColors(float[] hsv)
	{
		float[] CA1 = {hsv[0], (float) (hsv[1]), (float) (hsv[2]/2)};
		String colorAbove1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA1));
		float[] CA2 = {hsv[0], (float) (hsv[1]/2), (float) (hsv[2]/3)};
		String colorAbove2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA2));
		float[] CB1 = {hsv[0], (float) (hsv[1]/3), (float) (hsv[2]*2/3)};
		String colorBelow1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB1));
		float[] CB2 = {hsv[0], (float) (hsv[1]), (float) (hsv[2]*4/5)};
		String colorBelow2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB2));
	    
		ArrayList<String> colorStrings = new ArrayList<String>();
		colorStrings.add(colorAbove2);
		colorStrings.add(colorAbove1);
		colorStrings.add(colorBelow1);
		colorStrings.add(colorBelow2);
		
	    return colorStrings;

	}

	public static ArrayList<String> triadColors(float[] hsv)
	{
		
		float[] CA1 = {Colours.addDegrees(hsv[0], 120), (float) (hsv[1]), (float) (hsv[2])};
		String colorAbove1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA1));
		float[] CA2 = {Colours.addDegrees(hsv[0], 120), (float) (hsv[1]*7/6), (float) (hsv[2]-0.05)};
		String colorAbove2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA2));
		float[] CB1 = {Colours.addDegrees(hsv[0], 240), (float) (hsv[1]), (float) (hsv[2])};
		String colorBelow1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB1));
		float[] CB2 = {Colours.addDegrees(hsv[0], 240), (float) (hsv[1]*7/6), (float) (hsv[2]-0.05)};
		String colorBelow2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB2));
	    
		ArrayList<String> colorStrings = new ArrayList<String>();
		colorStrings.add(colorAbove2);
		colorStrings.add(colorAbove1);
		colorStrings.add(colorBelow1);
		colorStrings.add(colorBelow2);
		
	    return colorStrings;
	}

	public static ArrayList<String> complementaryColors(float[] hsv)
	{
		float[] CA1 = {hsv[0], (float) (hsv[1]*5/7), (float) (hsv[2])};
		String colorAbove1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA1));
		float[] CA2 = {hsv[0], (float) (hsv[1]), (float) (hsv[2]*4/5)};
		String colorAbove2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CA2));
		float[] CB1 = {Colours.addDegrees(hsv[0], 180), (float) (hsv[1]), (float) (hsv[2])};
		String colorBelow1 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB1));
		float[] CB2 = {Colours.addDegrees(hsv[0], 180), (float) (hsv[1]*5/7), (float) (hsv[2])};
		String colorBelow2 = String.format("#%06X", 0xFFFFFF & Color.HSVToColor(CB2));
	    
		ArrayList<String> colorStrings = new ArrayList<String>();
		colorStrings.add(colorAbove2);
		colorStrings.add(colorAbove1);
		colorStrings.add(colorBelow1);
		colorStrings.add(colorBelow2);
		
	    return colorStrings;

	}

	public static float addDegrees(float addDeg, float staticDeg)
	{
	    staticDeg += addDeg;
	    if (staticDeg > 360) {
	        float offset = staticDeg - 360;
	        return offset;
	    }
	    else if (staticDeg < 0) {
	        return -1 * staticDeg;
	    }
	    else {
	        return staticDeg;
	    }
	}
}
