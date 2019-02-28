package myPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static myPackage.App.MyLogger;
import myPackage.XMLtoList;

/**
 * The client GUI
 * information about all currencies
 * allows the user to convert from to
 */
public class CurrenciesGUI {
    //frame
    private JFrame frame;
    //table
    private JTable jTable;
    private JScrollPane scroller;
    //panels
    private JPanel topPanel, botPanel;
    //drop down menu
    private JComboBox<String> from, to;
    //inputs
    private JTextField amount, result;
    //lables
    private JLabel fromL, toL, resultL, date, amountL, title;
    //button
    private JButton convert;
    //
    public CurrenciesGUI(){
        //table columns
        String[] columnNames = {"Name", "Unit" , "Country","Country Code", "Rate", "Change"};
        //frame
        frame = new JFrame("Currencies Converter");
        frame.setLayout(new BorderLayout());
        //set cells to be immutable
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //update columns names
        for(String cul: columnNames){
            model.addColumn(cul);
        }
        //table
        jTable  = new JTable(model);
        scroller = new JScrollPane(jTable);
        //top panel
        topPanel = new JPanel();
        title = new JLabel("Currencies converter by: Ran, Hodaya & Tzachor");
        date = new JLabel("Default Date");
        //bottom panel
        botPanel = new JPanel();
        //from
        fromL = new JLabel("From");
        from = new JComboBox<>();
        from.setPreferredSize(new Dimension(100, 25));
        //to
        toL = new JLabel("To");
        to = new JComboBox<>();
        to.setPreferredSize(new Dimension(100, 25));
        //amount
        amountL = new JLabel("Amount");
        amount = new JTextField(10);
        //result
        resultL = new JLabel("Result");
        result = new JTextField(10);
        result.setEditable(false);
        result.setBackground(Color.WHITE);
        //button
        convert = new JButton("Convert!");
        //
        MyLogger.info(this.getClass().getName() + " Created");
    }
    //
    //recommended to have getters and setters for all components
    public JFrame getFrame() {
        return frame;   }
    public void setFrame(JFrame frame) {
        this.frame = frame; }
    public JTable getjTable() {
        return jTable;  }
    public void setjTable(JTable jTable) {
        this.jTable = jTable;   }
    public void setTitle(JLabel t){
        this.title = t; }
    public JLabel getTitle(){
        return title;   }
    public JPanel getTopPanel() {
        return topPanel;    }
    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;   }
    public JPanel getBotPanel() {
        return botPanel;    }
    public void setBotPanel(JPanel botPanel) {
        this.botPanel = botPanel;   }
    public JScrollPane getScroller() {
        return scroller;    }
    public void setScroller(JScrollPane scroller) {
        this.scroller = scroller;   }
    public JComboBox<String> getFrom() {
        return from;    }
    public void setFrom(JComboBox<String> from) {
        this.from = from;   }
    public JComboBox<String> getTo() {
        return to;  }
    public void setTo(JComboBox<String> to) {
        this.to = to;   }
    public JTextField getAmount() {
        return amount;  }
    public void setAmount(JTextField amount) {
        this.amount = amount;   }
    public JTextField getResult() {
        return result;  }
    public void setResult(JTextField result) {
        this.result = result;   }
    public JLabel getFromL() {
        return fromL;   }
    public void setFromL(JLabel fromL) {
        this.fromL = fromL; }
    public JLabel getToL() {
        return toL; }
    public void setToL(JLabel toL) {
        this.toL = toL; }
    public JLabel getResultL() {
        return resultL; }
    public void setResultL(JLabel resultL) {
        this.resultL = resultL; }
    public JLabel getDate() {
        return date;    }
    public void setDate(JLabel date) {
        this.date = date;   }
    public JButton getConvert() {
        return convert; }
    public void setConvert(JButton convert) {
        this.convert = convert; }
    public void updateDate(String d){
        this.date.setText(d);   }
    //
    //
    public void createUI(){
        //frame size
        frame.setMinimumSize(new Dimension(700, 400));
        //date
        topPanel.add(date);
        topPanel.add(title);
        //amount
        botPanel.add(amountL);
        botPanel.add(amount);
        //from
        botPanel.add(fromL);
        botPanel.add(from);
        //to
        botPanel.add(toL);
        botPanel.add(to);
        //result
        botPanel.add(resultL);
        botPanel.add(result);
        //button
        botPanel.add(convert);
        //frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(botPanel, BorderLayout.SOUTH);
        frame.add(scroller, BorderLayout.CENTER);
        frame.setVisible(true);
        //
        //adding events listeners
        to.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("To: " + getFrom().getSelectedItem());
            }
        });
        from.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("From: " + e.getItem());
            }
        });
        //
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("will close window");
                System.exit(1);
            }
        });
        //
        convert.addActionListener(new ActionListener() {
            double amountInput, result, firstConversion, secondConversion;
            String fromCode, toCode, resultSrtring;
            Currency fromCurrency, toCurrency;
            @Override
            public void actionPerformed(ActionEvent e) {
                //amount
                try{
                    amountInput = Double.parseDouble(getAmount().getText());
                }
                catch (Exception e1){
                    amountInput = 0;
                    MyLogger.error("no amount input, set to 0");
                }
                //from to
                fromCode = (String)getFrom().getSelectedItem();
                fromCurrency = XMLtoList.getCurrencyByCode(fromCode);
                toCode = (String)getTo().getSelectedItem();
                toCurrency = XMLtoList.getCurrencyByCode(toCode);
                //conversions
                firstConversion = fromCurrency.RATE() * amountInput / fromCurrency.UNIT();
                secondConversion = toCurrency.RATE() / toCurrency.UNIT();
                //result
                result = firstConversion / secondConversion;
                //
                //set the result
                resultSrtring = String.format("%.2f", result);
                getResult().setText("");
                getResult().setText(resultSrtring);
                MyLogger.info("From: " + fromCurrency);
                MyLogger.info("To: " + toCurrency);
                MyLogger.info("Amount: " + amountInput + " RESULT = " + resultSrtring);
            }
        });
    }
    //
    //
    public void updateCurrencies(Currency[] list){
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        from.removeAllItems();
        to.removeAllItems();
        model.setRowCount(0);
        from.addItem("NIS");
        to.addItem("NIS");
        //
        for(Currency aList : list) {
            model.addRow(new Object[]{aList.NAME(), aList.UNIT(), aList.COUNTRY(), aList.CURRENCYCODE(), aList.RATE(), aList.CHANGE()});
            from.addItem(aList.CURRENCYCODE());
            to.addItem(aList.CURRENCYCODE());
        }
    }
}