/*
 * SimpleSliderAppearance.java
 *
 * Created on November 16, 2007, 4:09 PM
 */

package mdes.slick.sui.skin.simple;

import mdes.slick.sui.skin.*;
import mdes.slick.sui.event.*;
import mdes.slick.sui.*;
import mdes.slick.sui.skin.SuiSkin;
import mdes.slick.sui.theme.*;
import org.newdawn.slick.*;
import org.newdawn.slick.fills.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author davedes
 */
public class SimpleSliderAppearance extends SimpleContainerAppearance implements SliderAppearance {
        
    public void render(GUIContext ctx, Graphics g, SuiComponent comp, SuiSkin skin, SuiTheme theme) {
        SuiSlider slider = (SuiSlider)comp;
         
        SkinUtil.renderComponentBase(g, slider);
        Rectangle bounds = slider.getAbsoluteBounds();
        if (!slider.isOpaque() || slider.getBackground()==null) {
            Color back = theme.getPrimary3();
            g.setColor(back);
            g.fill(bounds);
            Color down = theme.getPrimaryBorder1();
            if (slider.isTrackDown()) {
                g.setColor(down);
                Rectangle selection = slider.getAbsoluteTrackSelectionBounds();
                g.fill(selection);
            }
        }
        
        g.setColor(theme.getPrimaryBorder2());
        g.draw(bounds);
    }
    
    /**
     * This is the knob or thumb button whic appears on the slider.
     */
    public SuiButton createThumbButton(SuiSlider slider) {
        SuiButton btn = new SuiButton() {
            public void updateAppearance() {
                setAppearance(new SimpleButtonAppearance(this) {
                    protected RoundedRectangle createRoundedBounds() {
                        return new RoundedRectangle(0f,0f,0f,0f,3f,50);
                    }
                });
            }
        };
        btn.setSize(26, 26);
        return btn;
    }
}