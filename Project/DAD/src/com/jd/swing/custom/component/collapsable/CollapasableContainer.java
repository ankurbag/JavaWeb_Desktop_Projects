/**
 * Copyright (c) 2012, Dhilshuk Reddy All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute SwingJD software is freely
 * granted, provided that this notice is preserved.
 */
package com.jd.swing.custom.component.collapsable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import sun.awt.VerticalBagLayout;

import com.jd.swing.util.Colors;
import com.jd.swing.util.Theme;

/**
 * 
 * @author Dhilshuk Reddy
 * 
 */
public class CollapasableContainer extends JPanel {

	private int theme = Theme.SILVER_THEME;
	private VerticalBagLayout layout;
	private String themeType;
	private BufferedImage panelImage;

	/**
	 * Default Constructor
	 */
	public CollapasableContainer() {
		init();
	}

	/**
	 * Constructor with button theme as parameter.
	 * 
	 * @param theme
	 *            button theme
	 */
	public CollapasableContainer(int theme) {
		this.theme = theme;
		init();
	}

	/**
	 * Initializes
	 */
	private void init() {
		this.themeType = getThemeType();
		layout = new VerticalBagLayout();
		setLayout(layout);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int h = getHeight();
		int w = getWidth();
		if (panelImage == null || panelImage.getWidth() != w
				|| panelImage.getHeight() != h) {
			createPanelImage();
		}
		g2d.drawImage(panelImage, null, 0, 0);

	}

	/**
	 * Creates the Panel Image.
	 */
	private void createPanelImage() {
		panelImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = panelImage.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
				java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		Paint bgColor = null;
		List colors = null;
		if (themeType.equalsIgnoreCase("GLOSSY")) {
			colors = Colors.getInStance().getGlossyColor(theme, 0,
					getHeight() / 2);
			bgColor = (Paint) colors.get(1);

		} else if (themeType.equalsIgnoreCase("Standard")) {
			colors = Colors.getInStance().getStandardColor(theme, 0,
					getHeight());
			bgColor = (Paint) colors.get(0);

		} else {
			colors = Colors.getInStance().getGradientColor(theme, 0,
					getHeight());
			bgColor = (Paint) colors.get(0);

		}
		g2d.setPaint(bgColor);
		g2d.fillRect(0, 0, w, h);
		g2d.setColor(new Color(93, 93, 93));
		g2d.drawRect(1, 1, w - 3, h - 3);
		g2d.setColor(new Color(0, 0, 0));
		g2d.drawRect(0, 0, w - 1, h - 1);
	}

	/**
	 * Returns the Theme.
	 * 
	 * @return theme
	 */
	public int getTheme() {
		return theme;
	}

	/**
	 * Sets the Cotianer Theme.
	 * 
	 * @param theme
	 *            int value
	 */
	public void setTheme(int theme) {
		this.theme = theme;
	}

	/**
	 * Returns the Container Theme Type.
	 * 
	 * @return String value
	 */
	public String getThemeType() {
		if (theme > 200) {
			themeType = "STANDARD";
		} else if (theme > 100) {
			themeType = "GRADIENT";
		} else {
			themeType = "GLOSSY";
		}

		return themeType;
	}
}
