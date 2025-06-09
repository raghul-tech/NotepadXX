package com.notepadxx.syntaxchecker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.JTextComponent;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class BackgroundHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
    public BackgroundHighlightPainter(Color color) {
        super(color);
    }

    @Override
    public void paint(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c) {
        try {
            // Get the start and end rectangles
            Rectangle2D rectStart = c.modelToView2D(offs0);
            Rectangle2D rectEnd = c.modelToView2D(offs1);

            if (rectStart.getY() == rectEnd.getY()) {
                // Same line, paint a single rectangle
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(getColor());
                g2d.fillRect((int) rectStart.getX(), (int) rectStart.getY(),
                             (int) (rectEnd.getX() - rectStart.getX()), (int) rectStart.getHeight());
            } else {
                // Multi-line error case (if needed)
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(getColor());

                // Paint the first line
                g2d.fillRect((int) rectStart.getX(), (int) rectStart.getY(),
                             c.getWidth() - (int) rectStart.getX(), (int) rectStart.getHeight());

                // Paint the middle lines (full width)
                int startLine = ((RSyntaxTextArea) c).getLineOfOffset(offs0);
                int endLine = ((RSyntaxTextArea) c).getLineOfOffset(offs1);
                for (int line = startLine + 1; line < endLine; line++) {
                    Rectangle2D rectLine = c.modelToView2D(
                            ((RSyntaxTextArea) c).getLineStartOffset(line));
                    g2d.fillRect(0, (int) rectLine.getY(), c.getWidth(), (int) rectLine.getHeight());
                }

                // Paint the last line
                g2d.fillRect(0, (int) rectEnd.getY(), (int) rectEnd.getX(), (int) rectEnd.getHeight());
         
            }
            
        } catch (BadLocationException ex) {
           // ex.printStackTrace();
        }
    }

    }
