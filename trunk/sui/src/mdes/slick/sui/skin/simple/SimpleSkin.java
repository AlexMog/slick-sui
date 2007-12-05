/*
 * SimpleSkin.java
 *
 * Created on November 7, 2007, 6:52 PM
 */

package mdes.slick.sui.skin.simple;

import mdes.slick.sui.*;
import mdes.slick.sui.skin.ComponentAppearance;
import mdes.slick.sui.skin.SuiSkin;
import mdes.slick.sui.SuiTheme;
import mdes.slick.sui.skin.*;
import org.newdawn.slick.fills.*;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.gui.*;

/**
 *
 * @author davedes
 */
public class SimpleSkin implements SuiSkin {
    
    private Image checkBoxImage;
    private Image closeButtonImage;
    private Image resizerImage;
    private Font font;
    
    private static boolean roundRectanglesEnabled = true;
           
    //we can cache some of our appearances, others need to be created & attached to components
    private ComponentAppearance containerAppearance = new SimpleContainerAppearance();
    private ComponentAppearance toolTipAppearance = new SimpleToolTipAppearance();
    private ComponentAppearance labelAppearance = new SimpleLabelAppearance();
    private ComponentAppearance scrollPaneAppearance = new SimpleScrollPaneAppearance();
    private ComponentAppearance textFieldAppearance = new SimpleTextFieldAppearance();
    
    private WindowAppearance windowAppearance = new SimpleWindowAppearance();
    private ScrollBarAppearance scrollBarAppearance = new SimpleScrollBarAppearance();
    private SliderAppearance sliderAppearance = new SimpleSliderAppearance();
        
    public static boolean isRoundRectanglesEnabled() {
        return roundRectanglesEnabled;
    }

    public static void setRoundRectanglesEnabled(boolean aRoundRectanglesEnabled) {
        roundRectanglesEnabled = aRoundRectanglesEnabled;
    }
    
    public String getName() {
        return "Simple";
    }
    
    public boolean isThemeable() {
        return true;
    }
    
    public void install() throws SlickException {
                ///////////////////
                // CACHE OBJECTS //
                ///////////////////
        
        //try loading
        //ResourceLoader will spit out a log message if there are problems
        
        //images
        if (checkBoxImage==null)
            checkBoxImage = tryImage("res/skin/simple/checkbox.png");
        if (closeButtonImage==null)
            closeButtonImage = tryImage("res/skin/simple/closewindow.png");
        
        //fonts
        if (font==null)
            font = tryFont("res/skin/shared/verdana.fnt", "res/skin/shared/verdana.png");
    }
    
    private Image tryImage(String s) {
        try { return new ImageUIResource(s); }
        catch (Exception e) { return null; }
    }
    
    private Font tryFont(String s1, String s2) {
        try { return new FontUIResource.AngelCodeFont(s1, s2); }
        catch (Exception e) { return null; }
    }

    public void uninstall() throws SlickException {
    }
    
    public Image getCheckBoxImage() {
        return checkBoxImage;
    }
    
    public Image getCloseButtonImage() {
        return closeButtonImage;
    }
    
    public Font getFont() {
        return font;
    }
    
    public ComponentAppearance getContainerAppearance(SuiContainer comp) {
        return containerAppearance;
    }

    public ComponentAppearance getCheckBoxAppearance(SuiCheckBox comp) {
        return new SimpleCheckBoxAppearance(comp);
    }

    public WindowAppearance getWindowAppearance(SuiWindow comp) {
        return windowAppearance;
    }

    public ComponentAppearance getButtonAppearance(SuiButton comp) {
        return new SimpleButtonAppearance(comp);
    }

    public ComponentAppearance getToolTipAppearance(SuiToolTip comp) {
        return toolTipAppearance;
    }

    public ComponentAppearance getLabelAppearance(SuiLabel comp) {
        return labelAppearance;
    }
    
    public ComponentAppearance getToggleButtonAppearance(SuiToggleButton comp) {
        return new SimpleButtonAppearance(comp);
    }

    public ScrollBarAppearance getScrollBarAppearance(SuiScrollBar comp) {
        return scrollBarAppearance;
    }

    public ComponentAppearance getScrollPaneAppearance(SuiScrollPane comp) {
        return scrollPaneAppearance;
    }
    
    public SliderAppearance getSliderAppearance(SuiSlider comp) {
        return sliderAppearance;
    }
    
    public ComponentAppearance getTextFieldAppearance(SuiTextField comp) {
        return textFieldAppearance;
    }
}
