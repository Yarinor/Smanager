
package Classes;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class MultiLineCellRenderer extends JTextPane implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
        
    //on focus paint cell border    
    if (hasFocus) {
      setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
      if (table.isCellEditable(row, column)) {
        setForeground(UIManager.getColor("Table.focusCellForeground"));
        setBackground(UIManager.getColor("Table.focusCellBackground"));
      }
    } else {
      setBorder(new EmptyBorder(1, 2, 1, 2));
    }

    //make text RTL and multiline cell
    this.setText(value.toString());
    this.setFont(Font.decode( "Arial-PLAIN-13"));
    StyledDocument doc = this.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT); //alignment to be Set in Constructor (ex. StyleConstants.ALIGN_RIGHT)
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    this.setDocument(doc);
    
    //return all properties to jtable
    return this;
}
}
