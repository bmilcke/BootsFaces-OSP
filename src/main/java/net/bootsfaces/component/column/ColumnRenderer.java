/**
 *  Copyright 2014-16 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

package net.bootsfaces.component.column;

import javax.faces.FacesException;
import javax.faces.component.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.render.A;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:column /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.column.Column")
public class ColumnRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:column.
	 * <code>encodeBegin</code> generates the start of the component. After the,
	 * the JSF framework calls <code>encodeChildren()</code> to generate the
	 * HTML code between the beginning and the end of the component. For
	 * instance, in the case of a panel component the content of the panel is
	 * generated by <code>encodeChildren()</code>. After that,
	 * <code>encodeEnd()</code> is called to generate the rest of the HTML code.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:column.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		Column column = (Column) component;

		if (column.isRendered()) {
			ResponseWriter rw = context.getResponseWriter();

			int colxs = columnToInt(column.getColXs());
			int colsm = columnToInt(column.getColSm()); 
			int collg = columnToInt(column.getColLg());

			int span = columnToInt(column.getSpan()); 

			int colmd = (span > 0) ? span : columnToInt(column.getColMd());
			if ((colxs > 0) || (colsm > 0) || (collg > 0)) {
				colmd = (colmd > 0) ? colmd : 0;
			} else {
				colmd = (colmd > 0) ? colmd : 12;
			}

			int offs = column.getOffset(); 
			int offsmd = (offs > 0) ? offs : column.getOffsetMd();
			int oxs = column.getOffsetXs(); 
			int osm = column.getOffsetSm(); 
			int olg = column.getOffsetLg();
			String style = column.getStyle(); 
			String sclass = column.getStyleClass();

			rw.startElement("div", column);
			if (null != column.getDir()) {
				rw.writeAttribute("dir", column.getDir(), "dir");
			}

			if (this != null) {
				rw.writeAttribute("id", column.getClientId(), "id");
				Tooltip.generateTooltip(FacesContext.getCurrentInstance(), column, rw);
			}

			StringBuilder sb = new StringBuilder();
			if (colmd > 0 || offsmd > 0) {
				if (colmd > 0) {
					sb.append("col-md-").append(colmd);
				}
				if (offsmd > 0) {
					if (colmd > 0) {
						sb.append(" ");
					}
					sb.append("col-md-offset-" + offsmd);
				}
			}

			if (colxs > 0) {
				sb.append(" col-xs-").append(colxs);
			}
			if (colxs == 0) {
				sb.append(" hidden-xs");
			}  

			if (colsm > 0) {
				sb.append(" col-sm-").append(colsm);
			}
			if (colsm == 0) {
				sb.append(" hidden-sm");
			}

			if (collg > 0) {
				sb.append(" col-lg-").append(collg);
			}
			if (collg == 0) {
				sb.append(" hidden-lg");
			}
			
			if (column.getHidden() != null) {
				if ("xs".equals(column.getHidden())) {
					sb.append(" hidden-xs");
				}
				else if ("sm".equals(column.getHidden())) {
					sb.append(" hidden-xs  hidden-sm");
				}
				else if ("md".equals(column.getHidden())) {
					sb.append(" hidden-xs hidden-sm  hidden-md");
				}
				else throw new FacesException("Error rendering the \"hidden\" attribute of b:column: unexpected value found. Legal values are xs, sm, md.");
			}
			
			if (column.getVisible() != null) {
				if ("sm".equals(column.getVisible())) {
					sb.append(" visible-sm-");
					sb.append(column.getDisplay());
					sb.append(" visible-md-");
					sb.append(column.getDisplay());
					sb.append(" visible-lg-");
					sb.append(column.getDisplay());
				}
				else if ("md".equals(column.getVisible())) {
					sb.append(" visible-md-");
					sb.append(column.getDisplay());
					sb.append(" visible-lg-");
					sb.append(column.getDisplay());
				}
				else if ("lg".equals(column.getVisible())) {
					sb.append(" visible-lg-");
					sb.append(column.getDisplay());
				}
				else throw new FacesException("Error rendering the \"visible\" attribute of b:column: unexpected value found. Legal values are sm, md and lg.");
			}
			
			if (oxs > 0) {
				sb.append(" col-xs-offset-").append(oxs);
			}
			if (osm > 0) {
				sb.append(" col-sm-offset-").append(osm);
			}
			if (olg > 0) {
				sb.append(" col-lg-offset-").append(olg);
			}

			if (sclass != null) {
				sb.append(" ").append(sclass);
			}
			rw.writeAttribute("class", sb.toString().trim(), "class");
			if (style != null) {
				rw.writeAttribute("style", style, "style");
			}

			if (null != this) {
				
			}
		}
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		Column column = (Column) component;

		if (column.isRendered()) {
			super.encodeChildren(context, component);
		}
	}
	
	public void encodeEnd(FacesContext fc, UIComponent component) throws IOException {
		Column column = (Column) component;

		if (column.isRendered()) {
	        fc.getResponseWriter().endElement("div");
	        Tooltip.activateTooltips(FacesContext.getCurrentInstance(), column);
		}
    }
	
	private int columnToInt(String column) {
		if (column==null) return -1;
		if ("full".equals(column)) return 12;
		if ("full-size".equals(column)) return 12;
		if ("fullSize".equals(column)) return 12;
		if ("full-width".equals(column)) return 12;
		if ("fullWidth".equals(column)) return 12;
		if ("half".equals(column)) return 6;
		if ("one-third".equals(column)) return 4;
		if ("oneThird".equals(column)) return 4;
		if ("two-thirds".equals(column)) return 8;
		if ("twoThirds".equals(column)) return 8;
		if ("one-fourth".equals(column)) return 3;
		if ("oneFourth".equals(column)) return 3;
		if ("three-fourths".equals(column)) return 9;
		if ("threeFourths".equals(column)) return 9;
		if (column.length()>2) {
			column=column.replace("columns", "");
			column=column.replace("column", "");
			column=column.trim();
		}
		return new Integer(column).intValue();
	}
}
