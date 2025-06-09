package com.notepadxx.notepadxx;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JTabbedPane;

public class TabDragAndDropHandler {

    private static final int SCROLL_MARGIN = 50; // Distance from the edge to start scrolling

    public static void enableTabDragAndDrop(JTabbedPane tabbedPane) {
        // Store the original tab color to revert when drag ends
        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
			public void mousePressed(MouseEvent e) {
                int index = tabbedPane.indexAtLocation(e.getX(), e.getY());
                if (index != -1) {
                    tabbedPane.putClientProperty("draggedTabIndex", index);
                    // Highlight the dragged tab (optional)
                    tabbedPane.setBackgroundAt(index, Color.GRAY); // Change the tab's background color while dragging
                }
            }
        });

        tabbedPane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
			public void mouseDragged(MouseEvent e) {
                Integer draggedTabIndex = (Integer) tabbedPane.getClientProperty("draggedTabIndex");
                if (draggedTabIndex != null) {
                    int draggedTabX = e.getX();

                    // Auto-scroll when the dragged tab is near the edges
                    int tabAreaWidth = tabbedPane.getWidth();

                    // Scroll to the right if near the right edge
                    if (draggedTabX > tabAreaWidth - SCROLL_MARGIN) {
                        Rectangle tabRect = tabbedPane.getBoundsAt(draggedTabIndex);
                        tabbedPane.scrollRectToVisible(tabRect);
                    }
                    // Scroll to the left if near the left edge
                    else if (draggedTabX < SCROLL_MARGIN) {
                        Rectangle tabRect = tabbedPane.getBoundsAt(draggedTabIndex);
                        tabbedPane.scrollRectToVisible(tabRect);
                    }

                    tabbedPane.repaint();
                }
            }
        });

        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
			public void mouseReleased(MouseEvent e) {
                Integer draggedTabIndex = (Integer) tabbedPane.getClientProperty("draggedTabIndex");
                if (draggedTabIndex != null) {
                    int index = tabbedPane.indexAtLocation(e.getX(), e.getY());
                    if (index != -1 && index != draggedTabIndex) {
                        // Move the dragged tab to the new location
                        Component draggedTabComponent = tabbedPane.getComponentAt(draggedTabIndex);
                        String tabTitle = tabbedPane.getTitleAt(draggedTabIndex);

                        tabbedPane.removeTabAt(draggedTabIndex);
                        tabbedPane.insertTab(tabTitle, null, draggedTabComponent, null, index);

                        // Update the selected index to reflect the moved tab
                        tabbedPane.setSelectedIndex(index);
                    }

                    // Revert the highlighted tab to its original color
                 //   tabbedPane.setBackgroundAt(draggedTabIndex, null);
                    if (draggedTabIndex >= 0 && draggedTabIndex < tabbedPane.getTabCount()) {
                        tabbedPane.setBackgroundAt(draggedTabIndex, null);
                    }

                }
            }
        });
    }
}
