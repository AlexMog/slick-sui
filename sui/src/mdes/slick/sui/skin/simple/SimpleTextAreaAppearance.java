/*
 * SimpleTextAreaAppearance.java
 *
 * Created on December 27, 2007, 2:06 AM
 */

package mdes.slick.sui.skin.simple;

import mdes.slick.sui.Padding;
import mdes.slick.sui.SuiComponent;
import mdes.slick.sui.SuiSkin;
import mdes.slick.sui.SuiTextArea;
import mdes.slick.sui.SuiTheme;
import mdes.slick.sui.skin.SkinUtil;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author davedes
 */
public class SimpleTextAreaAppearance extends SimpleComponentAppearance {
    
    public void install(SuiComponent comp, SuiSkin skin, SuiTheme theme) {
        super.install(comp, skin, theme);
        comp.setPadding(2, 2, 2, 2);
        comp.setOpaque(true);
    }
    
    public void render(GUIContext ctx, Graphics g, SuiComponent comp, SuiSkin skin, SuiTheme theme) {        
        SuiTextArea area = (SuiTextArea)comp;
        
        SkinUtil.renderComponentBase(g, comp);
        
        Rectangle bounds = area.getAbsoluteBounds();
        
        Font oldFont = g.getFont();
        Rectangle oldClip = g.getClip();
        
        Font font = area.getFont();
        int cursorPos = area.getCaretPosition();
        Padding pad = area.getPadding();
        boolean hasFocus = area.hasFocus();
        
        //use default font
        if (font==null)
            font=g.getFont();
        
        g.setFont(font);
        g.setColor(area.getForeground());
        g.setClip(bounds);
        
        //string bounds
        float startX = bounds.getX() + pad.left;
        float startY = bounds.getY() + pad.top;
        
        int linePos = area.getCurrentLine();
        int caretPos = area.getCaretPosition();
        
        for(int i=0; i<area.getLineCount(); i++) {
            SuiTextArea.Line line = area.getLine(i);
            int offset = line.offset;
            float yoff = line.yoff;
            float lineHeight = line.height;
            String str = line.str;
            float lineY = (startY+(i*lineHeight));
            g.drawString(str, (int)startX, (int)lineY);
            
            //if this line is where the caret is at, let's draw it
            if (hasFocus && linePos == i) {
                int endIndex = caretPos-offset;
                float cpos = font.getWidth(str.substring(0, endIndex));
                g.drawString("_", (int)(startX+cpos), (int)lineY);
            }
        }
        
        g.setFont(oldFont);
        g.setClip(oldClip);
        
        if (area.isBorderRendered()) {
            g.setColor( hasFocus ? theme.getPrimaryBorder2() : theme.getPrimaryBorder1());
            g.draw(bounds);
        }
    }
    
}
