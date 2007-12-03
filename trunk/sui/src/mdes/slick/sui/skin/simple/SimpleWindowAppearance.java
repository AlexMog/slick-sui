/*
 * SimpleWindowAppearance.java
 *
 * Created on November 7, 2007, 8:58 PM
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
public class SimpleWindowAppearance extends SimpleContainerAppearance implements WindowAppearance {
        
    private static GradientFill grad = new GradientFill(0f,0f,Color.white,0f,0f,Color.white);

    private ComponentAppearance closeBtnAppearance = new CloseButtonAppearance();
    private ComponentAppearance resizerAppearance = new ResizerAppearance();
    private ComponentAppearance titleBarAppearance = new TitleBarAppearance();
    
    public void install(SuiComponent comp, SuiSkin skin, SuiTheme theme) {
        super.install(comp, skin, theme);
    }
    
    public void render(GUIContext ctx, Graphics g, SuiComponent comp, SuiSkin skin, SuiTheme theme) {
        Color old = g.getColor();
        
        SuiWindow win = (SuiWindow)comp;
        
        //borders
        Color light = theme.getSecondaryBorder1();
        Color dark = theme.getSecondaryBorder1();
        
        Rectangle rect = win.getAbsoluteBounds();
        //TODO: this is a hack, fix it
        //HACK: fix window title bar (removed) hack
        if (!win.getTitleBar().isVisible() || !win.containsChild(win.getTitleBar())) {
            float h = win.getTitleBar().getHeight();
            rect.setY(rect.getY()+h-1);
            rect.setHeight(rect.getHeight()-h+1);
        }
        
        
        float mid = rect.getWidth()/2f;
        
        grad.setStartColor(light);
        grad.setEndColor(dark);
        grad.setStart(-mid, 0);
        grad.setEnd(mid, 0);
        g.draw(rect, grad);
    }

    public ComponentAppearance getCloseButtonAppearance() {
        return closeBtnAppearance;
    }

    public ComponentAppearance getTitleBarAppearance() {
        return titleBarAppearance;
    }

    public ComponentAppearance getResizerAppearance() {
        return resizerAppearance;
    }
    
    protected class CloseButtonAppearance extends SimpleButtonAppearance {
        
        protected RoundedRectangle createRoundedBounds() {
            return new RoundedRectangle(0f,0f,0f,0f,3f,50);
        }

        public void install(SuiComponent comp, SuiSkin skin, SuiTheme theme) {
            super.install(comp, skin, theme);
            SuiButton btn = (SuiButton)comp;
            if (SkinManager.installImage(btn, "Window.CloseButton.image")) {
                btn.pack();
            }
        }
    }
    
    protected class ResizerAppearance extends SimpleLabelAppearance {
    
        private Rectangle r1 = new Rectangle(0f,0f,2f,2f);

        public void install(SuiComponent comp, SuiSkin skin, SuiTheme theme) {
            super.install(comp, skin, theme);
            SuiLabel btn = (SuiLabel)comp;

            if (SkinManager.installImage(btn, "Window.Resizer.image")) {
                btn.setPadding(2);
                btn.pack();
            }
        }

        public void render(GUIContext ctx, Graphics g, SuiComponent comp, SuiSkin skin, SuiTheme theme) {
            SuiWindow win = ((SuiWindow.Resizer)comp).getWindow();
            if (!win.isResizable())
                return;

            if (((SuiLabel)comp).getImage()==null) {
                SlickCallable.enterSafeBlock();
                Color t = Sui.getTheme().getSecondaryBorder1();

                //bind texture & color before entering gl
                t.bind();

                float x = comp.getAbsoluteX()-2 , y = comp.getAbsoluteY()-2;
                float w = comp.getWidth(), h = comp.getHeight();

                //begin drawing the triangle
                GL11.glBegin(GL11.GL_TRIANGLES);
                    GL11.glVertex3f(x+w, y, 0);
                    GL11.glVertex3f(x+w, y+h, 0);
                    GL11.glVertex3f(x, y+h, 0);
                GL11.glEnd();
                SlickCallable.leaveSafeBlock();
            } else {
                super.render(ctx, g, comp, skin, theme);
            }
        }
    }
    
    protected class TitleBarAppearance extends SimpleLabelAppearance {

        private GradientFill grad = new GradientFill(0f,0f,Color.white,0f,0f,Color.white);

        public void render(GUIContext ctx, Graphics g, SuiComponent comp, SuiSkin skin, SuiTheme theme) {
            Rectangle rect = comp.getAbsoluteBounds();

            Color old = g.getColor();
            SuiWindow.TitleBar t = (SuiWindow.TitleBar)comp;

            float x1=t.getAbsoluteX(), y1=t.getAbsoluteY();
            float width=t.getWidth(), height=t.getHeight();

            //TODO: fix rectangle + 1

            float mid = width/2.0f;

            Color start, end;

            boolean active = ((SuiWindow)t.getParent()).isActive();

            if (active) {
                start = theme.getActiveTitleBar1();
                end = theme.getActiveTitleBar2();
            } else {
                start = theme.getTitleBar1();
                end = theme.getTitleBar2();
            }

            grad.setStartColor(start);
            grad.setEndColor(end);
            grad.setStart(-mid, 0);
            grad.setEnd(mid, 0);
            g.fill(rect, grad);

            //borders
            Color light = theme.getSecondaryBorder1();
            Color dark = theme.getSecondaryBorder1();

            grad.setStartColor(light);
            grad.setEndColor(dark);
            grad.setStart(-mid, 0);
            grad.setEnd(mid, 0);
            g.draw(rect, grad);

            //g.setColor(old);
            
            RenderUtil.renderLabelBase(g, t);
        }
    }
}
