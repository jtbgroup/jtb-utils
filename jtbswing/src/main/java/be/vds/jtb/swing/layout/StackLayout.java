package be.vds.jtb.swing.layout;/* * Copyright (c) 2007, Romain Guy * All rights reserved. * * Redistribution and use in source and binary forms, with or without * modification, are permitted provided that the following conditions * are met: * *   * Redistributions of source code must retain the above copyright *     notice, this list of conditions and the following disclaimer. *   * Redistributions in binary form must reproduce the above *     copyright notice, this list of conditions and the following *     disclaimer in the documentation and/or other materials provided *     with the distribution. *   * Neither the name of the TimingFramework project nor the names of its *     contributors may be used to endorse or promote products derived *     from this software without specific prior written permission. * * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/** * * @author Romain Guy <romain.guy@mac.com> */
public class StackLayout implements LayoutManager2 {
	private List<Component> components = new LinkedList<Component>();
	private Map<Component, StackLayoutContraint> constraints = new HashMap<Component, StackLayoutContraint>();

	public void addLayoutComponent(Component comp, Object constraint) {
		synchronized (comp.getTreeLock()) {
			StackLayoutContraint c = (StackLayoutContraint) constraint;
			constraints.put(comp, c);
			if (c.getzOrder().equals(StackLayoutContraint.BOTTOM)) {
				components.add(0, comp);
			} else if (c.getzOrder().equals(StackLayoutContraint.TOP)) {
				components.add(comp);
			} else {
				components.add(comp);
			}
		}
	}

	public void addLayoutComponent(String name, Component comp) {
		StackLayoutContraint c = new StackLayoutContraint();
		c.setzOrder(StackLayoutContraint.TOP);
		addLayoutComponent(comp, c);
	}

	public void removeLayoutComponent(Component comp) {
		synchronized (comp.getTreeLock()) {
			components.remove(comp);
		}
	}

	public float getLayoutAlignmentX(Container target) {
		return 0.5f;
	}

	public float getLayoutAlignmentY(Container target) {
		return 0.5f;
	}

	public void invalidateLayout(Container target) {
	}

	public Dimension preferredLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int width = 0;
			int height = 0;
			for (Component comp : components) {
				Dimension size = comp.getPreferredSize();
				width = Math.max(size.width, width);
				height = Math.max(size.height, height);
			}
			Insets insets = parent.getInsets();
			width += insets.left + insets.right;
			height += insets.top + insets.bottom;
			return new Dimension(width, height);
		}
	}

	public Dimension minimumLayoutSize(Container parent) {
		synchronized (parent.getTreeLock()) {
			int width = 0;
			int height = 0;
			for (Component comp : components) {
				Dimension size = comp.getMinimumSize();
				width = Math.max(size.width, width);
				height = Math.max(size.height, height);
			}
			Insets insets = parent.getInsets();
			width += insets.left + insets.right;
			height += insets.top + insets.bottom;
			return new Dimension(width, height);
		}
	}

	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	public void layoutContainer(Container parent) {
		synchronized (parent.getTreeLock()) {
			int width = parent.getWidth();
			int height = parent.getHeight();
			Rectangle bounds = new Rectangle(0, 0, width, height);
			int componentsCount = components.size();
			for (int i = 0; i < componentsCount; i++) {
				Component comp = components.get(i);
				if (!constraints.get(comp).isKeepComponentBounds()) {
					comp.setBounds(bounds);
				}
				parent.setComponentZOrder(comp, componentsCount - i - 1);
			}
		}
	}
}