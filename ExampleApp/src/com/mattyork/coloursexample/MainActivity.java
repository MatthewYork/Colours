package com.mattyork.coloursexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.mattyork.colours.Colour;
import com.mattyork.colours.Colour.ColorScheme;

public class MainActivity extends Activity {

	// Analagous XML
	View AnalagousSchemeBelow2;
	View AnalagousSchemeBelow1;
	View AnalagousSchemeBaseColor;
	View AnalagousSchemeAbove1;
	View AnalagousSchemeAbove2;

	// Monochromatic
	View MonochromaticSchemeBelow2;
	View MonochromaticSchemeBelow1;
	View MonochromaticSchemeBaseColor;
	View MonochromaticSchemeAbove1;
	View MonochromaticSchemeAbove2;

	// Triad
	View TriadSchemeBelow2;
	View TriadSchemeBelow1;
	View TriadSchemeBaseColor;
	View TriadSchemeAbove1;
	View TriadSchemeAbove2;

	// Complementary
	View ComplementarySchemeBelow2;
	View ComplementarySchemeBelow1;
	View ComplementarySchemeBaseColor;
	View ComplementarySchemeAbove1;
	View ComplementarySchemeAbove2;

	// Contrasting Color
	TextView LightContrastingColorTextView;
	TextView DarkContrastingColorTextView;
	
	//Complementary Colors
	View ComplementaryColor1;
	View ComplementaryColor2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//Create color schemes
		createAnalagousColors();
		createMonochromaticColors();
		createTriadColors();
		createComplementaryColors();
		
		//Contrasting colors
		createLightContrastingColor();
		createDarkContrastingColor();
		
		//Complementary Colors
		createComplementaryColorLight();
		createComplementaryColorDark();
	}

	// Setup Functions

	private void createAnalagousColors() {
		// Connect XML Views
		AnalagousSchemeBelow2 = findViewById(R.id.AnalagousSchemeBelow2);
		AnalagousSchemeBelow1 = findViewById(R.id.AnalagousSchemeBelow1);
		AnalagousSchemeAbove1 = findViewById(R.id.AnalagousSchemeAbove1);
		AnalagousSchemeAbove2 = findViewById(R.id.AnalagousSchemeAbove2);

		// Go create an analagous color scheme based on the seafoam color
		int[] analagousColors = Colour.colorSchemeOfType(Colour.seafoamColor(),
				ColorScheme.ColorSchemeAnalagous);

		// Apply those colors to the analagous scheme
		AnalagousSchemeBelow2.setBackgroundColor(analagousColors[0]);
		AnalagousSchemeBelow1.setBackgroundColor(analagousColors[1]);
		AnalagousSchemeAbove1.setBackgroundColor(analagousColors[2]);
		AnalagousSchemeAbove2.setBackgroundColor(analagousColors[3]);
	}

	private void createMonochromaticColors() {
		// Connect XML Views
		MonochromaticSchemeBelow2 = findViewById(R.id.MonochromaticSchemeBelow2);
		MonochromaticSchemeBelow1 = findViewById(R.id.MonochromaticSchemeBelow1);
		MonochromaticSchemeAbove1 = findViewById(R.id.MonochromaticSchemeAbove1);
		MonochromaticSchemeAbove2 = findViewById(R.id.MonochromaticSchemeAbove2);

		// Go create an analagous color scheme based on the seafoam color
		int[] monochromaticColors = Colour.colorSchemeOfType(
				Colour.seafoamColor(), ColorScheme.ColorSchemeMonochromatic);

		// Apply those colors to the analagous scheme
		MonochromaticSchemeBelow2.setBackgroundColor(monochromaticColors[0]);
		MonochromaticSchemeBelow1.setBackgroundColor(monochromaticColors[1]);
		MonochromaticSchemeAbove1.setBackgroundColor(monochromaticColors[2]);
		MonochromaticSchemeAbove2.setBackgroundColor(monochromaticColors[3]);
	}

	private void createTriadColors() {
		// Connect XML Views
		TriadSchemeBelow2 = findViewById(R.id.TriadSchemeBelow2);
		TriadSchemeBelow1 = findViewById(R.id.TriadSchemeBelow1);
		TriadSchemeAbove1 = findViewById(R.id.TriadSchemeAbove1);
		TriadSchemeAbove2 = findViewById(R.id.TriadSchemeAbove2);

		// Go create an analagous color scheme based on the seafoam color
		int[] triadColors = Colour.colorSchemeOfType(Colour.seafoamColor(),
				ColorScheme.ColorSchemeTriad);

		// Apply those colors to the analagous scheme
		TriadSchemeBelow2.setBackgroundColor(triadColors[0]);
		TriadSchemeBelow1.setBackgroundColor(triadColors[1]);
		TriadSchemeAbove1.setBackgroundColor(triadColors[2]);
		TriadSchemeAbove2.setBackgroundColor(triadColors[3]);
	}

	private void createComplementaryColors() {
		// Connect XML Views
		ComplementarySchemeBelow2 = findViewById(R.id.ComplementarySchemeBelow2);
		ComplementarySchemeBelow1 = findViewById(R.id.ComplementarySchemeBelow1);
		ComplementarySchemeAbove1 = findViewById(R.id.ComplementarySchemeAbove1);
		ComplementarySchemeAbove2 = findViewById(R.id.ComplementarySchemeAbove2);

		// Go create an analagous color scheme based on the seafoam color
		int[] complementaryColors = Colour.colorSchemeOfType(
				Colour.seafoamColor(), ColorScheme.ColorSchemeComplementary);

		// Apply those colors to the analagous scheme
		ComplementarySchemeBelow2.setBackgroundColor(complementaryColors[0]);
		ComplementarySchemeBelow1.setBackgroundColor(complementaryColors[1]);
		ComplementarySchemeAbove1.setBackgroundColor(complementaryColors[2]);
		ComplementarySchemeAbove2.setBackgroundColor(complementaryColors[3]);
	}

	private void createLightContrastingColor() {
		// Connect XML View
		LightContrastingColorTextView = (TextView) findViewById(R.id.LightContrastingColorTextView);

		// Define background color
		int backgroundColor = Colour.seashellColor(); // Change this to see a
														// new contrasting color
		LightContrastingColorTextView.setBackgroundColor(backgroundColor);

		// Set contrasting color
		LightContrastingColorTextView.setTextColor(Colour
				.blackOrWhiteContrastingColor(backgroundColor));

	}

	private void createDarkContrastingColor() {
		// Connect XML View
		DarkContrastingColorTextView = (TextView) findViewById(R.id.DarkContrastingColorTextView);

		// Define background color
		int backgroundColor = Colour.eggplantColor(); // Change this to see a
														// new contrasting color
		DarkContrastingColorTextView.setBackgroundColor(backgroundColor);

		// Set contrasting color
		DarkContrastingColorTextView.setTextColor(Colour
				.blackOrWhiteContrastingColor(backgroundColor));

	}
	
	//Complementary Colors
	private void createComplementaryColorLight() {
		//Connect XML View
		ComplementaryColor1 = findViewById(R.id.ComplementaryColor1);
		
		//Set complement color
		ComplementaryColor1.setBackgroundColor(Colour.complementaryColor(Colour.wheatColor()));
	}
	
	private void createComplementaryColorDark() {
		//Connect XML View
		ComplementaryColor2 = findViewById(R.id.ComplementaryColor2);
		
		//Set complement color
		ComplementaryColor2.setBackgroundColor(Colour.complementaryColor(Colour.dangerColor()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
}
