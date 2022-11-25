/*
 * @(#)PaletteColorWheelChooser.java 
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.gui.plaf.palette.colorchooser;

import java.awt.*;
import javax.swing.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import org.jhotdraw.color.HSVColorSpace;
import org.jhotdraw.color.JColorWheel;
import org.jhotdraw.gui.plaf.palette.PaletteLookAndFeel;
import org.jhotdraw.gui.plaf.palette.PalettePanelUI;

/**
 * A HSB color chooser, which displays a hue/saturation color wheel, and a 
 * brightness slider.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class PaletteColorWheelChooser extends AbstractColorChooserPanel implements UIResource {
    private static final long serialVersionUID = 1L;

    private JColorWheel colorWheel;
    private PaletteColorSliderModel ccModel = new PaletteColorSliderModel(new HSVColorSpace());
    private int updatingChooser;

    /**
     * Creates a new instance.
     */
    public PaletteColorWheelChooser() {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        brightnessSlider = new javax.swing.JSlider();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 6, 6, 6));
        setLayout(new java.awt.BorderLayout());

        brightnessSlider.setMajorTickSpacing(50);
        brightnessSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        brightnessSlider.setPaintTicks(true);
        add(brightnessSlider, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void buildChooser() {
        initComponents();
        setUI(PalettePanelUI.createUI(this));

        int textSliderGap = PaletteLookAndFeel.getInstance().getInt("ColorChooser.textSliderGap");
        if (textSliderGap != 0) {
            BorderLayout layout = (BorderLayout) getLayout();
            layout.setHgap(textSliderGap);
        }

        colorWheel = new JColorWheel();
        add(colorWheel);

        ccModel.configureSlider(2, brightnessSlider);
        brightnessSlider.setMaximum(200);


        colorWheel.setModel(ccModel);

        ccModel.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent evt) {
                setColorToModel(ccModel.getColor());
            }
        });
    }

    @Override
    public String getDisplayName() {
        return PaletteLookAndFeel.getInstance().getString("ColorChooser.colorWheel");
    }

    @Override
    public javax.swing.Icon getLargeDisplayIcon() {
        return PaletteLookAndFeel.getInstance().getIcon("ColorChooser.colorWheelIcon");
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return getLargeDisplayIcon();
    }

    @Override
    public void updateChooser() {
        updatingChooser++;
        ccModel.setColor(getColorFromModel());
        updatingChooser--;
    }

    public void setColorToModel(Color color) {
        if (updatingChooser == 0) {
            getColorSelectionModel().setSelectedColor(color);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider brightnessSlider;
    // End of variables declaration//GEN-END:variables
}
