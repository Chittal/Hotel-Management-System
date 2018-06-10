/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelproject.Panels;

import static hotelproject.Bill.tablebill;
import hotelproject.ConnectSQL;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cute
 */
public class ParcelPanel extends javax.swing.JPanel {

    Connection conn;
    //Connection conn=null;
    ArrayList alname=new ArrayList();
    /**
     * Creates new form ParcelPanel
     */
    
    private void Fillcombo(){
        conn=ConnectSQL.connectDB();
        try{
            String sql="select * from itemlist";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int code=rs.getInt("sno");
                pino.addItem(code);
            }

        }catch(Exception e){
         System.out.println(e);   
        }
    }
    
     public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =12.0;  
    double headerHeight = 8.0;                  
    double footerHeight = 8.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that print area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}

    public int getTotal(){
        int rowsCount = tableparcel.getRowCount();
        int sum=0;
        for(int i=0;i<rowsCount;i++){
            sum=sum+Integer.parseInt(tableparcel.getValueAt(i, 3).toString());
        }
        return sum;
    }
    
    /*
    Auto complete textfield
    
    private void databaseName(){
        try{
            conn=ConnectSQL.connectDB();
            Statement st=conn.createStatement();
            String sql="select * from itemlist";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                String Name=rs.getString("itemname");
                alname.add(Name);
            }
            rs.close();
            st.close();
            conn.close();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void autoComplete(String txt){
        String complete="";
        int start=txt.length();
        int last=txt.length();
        for(int i=0;i<alname.size();i++){
            if(alname.get(i).toString().startsWith(txt)){
                complete=alname.get(i).toString();
                last=complete.length();
                break;
            }
        }
        if(last>start){
            itname.setText(complete);
            itname.setCaretPosition(last);
            itname.moveCaretPosition(start);
        }
    }

    */
    public String space(int len){
        return " ";
    }
    public class BillPrintable implements Printable {
    
   
    
    
 
  public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
        
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
            

            ////////// code by alqama//////////////

      /*      FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
            //int idLength=metrics.stringWidth("000000");
            //int idLength=metrics.stringWidth("00");
            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

            //int idPosition=0;
            //int productPosition=idPosition + idLength + 2;
            //int pricePosition=productPosition + prodLength +10;
            //int qtyPosition=pricePosition + priceLength + 2;
            //int amtPosition=qtyPosition + qtyLength + 2;
            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
           int amtPosition=qtyPosition + qtyLength;
            
            */
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=20;
            int headerRectHeighta=45;
            
            ///////////////// Product names Get ///////////
            int rowsCount = tableparcel.getRowCount();
            int sno[]=new int[rowsCount];
            for(int i=0;i<rowsCount;i++){
                 sno[i]=Integer.parseInt(tableparcel.getValueAt(i, 0).toString());
            }
            String sname[]=new String[rowsCount];
            for(int i=0;i<rowsCount;i++){
                 sname[i]=tableparcel.getValueAt(i, 1).toString();
            }
            int sqty[]=new int[rowsCount];
            for(int i=0;i<rowsCount;i++){
                 sqty[i]=Integer.parseInt(tableparcel.getValueAt(i, 2).toString());
            }
            String sprice[]=new String[rowsCount];
            for(int i=0;i<rowsCount;i++){
                 sprice[i]=tableparcel.getValueAt(i, 3).toString();
            }

                ///////////// Product names Get ///////////
                
            
            ///////////////// Product price Get ///////////
                int stotal=Integer.parseInt(ptot.getText().toString());
            ///////////////// Product price Get ///////////
                
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString("----------------------------",10,y);y+=yShift;
            g2d.drawString("  Restaurant Bill Receipt        ",10,y);y+=yShift;
            g2d.drawString("----------------------------",10,y);y+=headerRectHeight;
           // g2d.drawString("             Customer Name: "+cust.getText().toString()+" ",10,y);y+=yShift;
      
            g2d.drawString("----------------------------",10,y);y+=yShift;
            g2d.drawString(" S.No. Item Name Qty Price  ",10,y);y+=yShift;
            g2d.drawString("----------------------------",10,y);y+=headerRectHeight;
            for(int i=0;i<rowsCount;i++){
                int xn=sname[i].length();
                
                g2d.drawString(" "+sno[i]+"     "+sname[i]+" "+sqty[i]+"   "+sprice[i],10,y);y+=yShift;
            }
            g2d.drawString("----------------------------",10,y);y+=yShift;
            g2d.drawString("       Total amount: "+stotal,10,y);y+=yShift;
            g2d.drawString("----------------------------",10,y);y+=yShift;
            g2d.drawString("****************************",10,y);y+=yShift;
            g2d.drawString("  THANK YOU!! VISIT AGAIN!  ",10,y);y+=yShift;
            g2d.drawString("*****************************",10,y);y+=yShift;
                   
           
             
           
            
//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift; 
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
    public ParcelPanel() {
        initComponents();
        Fillcombo();
        piname.setEditable(false);
        pit.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableparcel = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ptot = new javax.swing.JTextField();
        pamt = new javax.swing.JTextField();
        pbal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pqty = new javax.swing.JTextField();
        pprice = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pit = new javax.swing.JTextField();
        pino = new javax.swing.JComboBox();
        piname = new javax.swing.JTextField();

        tableparcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No.", "Item", "Quantity", "Cost"
            }
        ));
        jScrollPane1.setViewportView(tableparcel);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("PARCEL");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Total");

        ptot.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        pamt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pamt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pamtActionPerformed(evt);
            }
        });

        pbal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Amount Paid");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Balance");

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setText("Back");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Item Name");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Quantity");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Price");

        pqty.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pqtyActionPerformed(evt);
            }
        });

        pprice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Enter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Item No.");

        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton8.setText("Remove Item");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setText(" ITEM");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("Item");

        pit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        pino.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pino.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None" }));
        pino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinoActionPerformed(evt);
            }
        });

        piname.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        piname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(pprice))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4))
                                        .addGap(8, 8, 8))
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(pit, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pqty)
                                        .addComponent(pino, javax.swing.GroupLayout.Alignment.LEADING, 0, 232, Short.MAX_VALUE)
                                        .addComponent(piname, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(piname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(pprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton8))
                .addGap(202, 202, 202))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(253, 253, 253)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pamt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(ptot, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(12, 12, 12))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pbal, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)
                                .addGap(27, 27, 27)
                                .addComponent(jButton7)
                                .addGap(46, 46, 46)))))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ptot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(pbal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pamtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pamtActionPerformed
        // TODO add your handling code here:
         String at=pamt.getText().toString();
        if(!at.equals(" ")){
             int tot=Integer.parseInt(ptot.getText());
            int amount=Integer.parseInt(pamt.getText());
            int balance=amount-tot;
            pbal.setText(Integer.toString(balance));
        }
    }//GEN-LAST:event_pamtActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         conn=ConnectSQL.connectDB();
        try{
            Statement st=conn.createStatement();
//            st.executeUpdate("insert into sales (customer,total) values('"+cust.getText()+"',"+total.getText()+")");
  //          st.executeUpdate("update sales set time=curdate() where customer='"+cust.getText()+"'");   
            //int t=Integer.parseInt(tablebill.getValueAt(0, 4).toString());
            //int sr=Integer.parseInt(tablebill.getValueAt(0, 5).toString());
            //int rowsCount = tablebill.getRowCount();
             st.executeUpdate("insert into sales (serviceno,total,date) values(0,"+ptot.getText()+",current_timestamp())");

           // st.executeUpdate("insert into sales (tableno,serviceno,total,date) values("+tableparcel.getValueAt(0, 2).toString()+","+table.getValueAt(0, 3).toString()+","+ptot.getText()+",current_timestamp())");
           // st.executeUpdate("delete from billdet where tblno="+t+" and serno="+sr);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
       PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new ParcelPanel.BillPrintable(),getPageFormat(pj));
        try {
           pj.print();
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        tableparcel.setModel(new DefaultTableModel(null,new String[]{"S.no.","Item","Quantity","Cost"}));
        ptot.setText(null);
        pamt.setText(null);
        pbal.setText(null);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         int tot=getTotal();
        ptot.setText(Integer.toString(tot));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void pqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pqtyActionPerformed
        // TODO add your handling code here:
        String quant=pqty.getText().toString();
        if(!quant.equals(" ")){
            conn=ConnectSQL.connectDB();
            try{
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select * from itemlist where itemname='"+piname.getText()+"'");
                int x,quan,val;
                if(rs.next()){
                    x=rs.getInt(3);
                    quan=Integer.parseInt(pqty.getText());
                    val=quan*x;
                    pprice.setText(Integer.toString(val));
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_pqtyActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel) tableparcel.getModel();
        model.addRow(new Object[]{pino.getSelectedItem(),pit.getText(),pqty.getText(),pprice.getText()});
        pino.setSelectedItem(null);
        piname.setText(null);
        pit.setText(null);
        pqty.setText(null);
        pprice.setText(null);
       // tbno.setSelectedItem(null);
        //srno.setSelectedItem(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel) tableparcel.getModel();
        int selectRow=tableparcel.getSelectedRow();
        model.removeRow(selectRow);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void pinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinoActionPerformed
        // TODO add your handling code here:
        if(pino.getSelectedItem()!="None"){
            conn=ConnectSQL.connectDB();
            try{
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("select * from itemlist where sno='"+pino.getSelectedItem()+"'");
                String itemname1,ishort;
                if(rs.next()){
                    itemname1=rs.getString("itemname");
                    ishort=rs.getString("itshort");
                    piname.setText(itemname1);
                    pit.setText(ishort);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_pinoActionPerformed

    private void pinameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pinameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pamt;
    private javax.swing.JTextField pbal;
    private javax.swing.JTextField piname;
    private javax.swing.JComboBox pino;
    private javax.swing.JTextField pit;
    private javax.swing.JTextField pprice;
    private javax.swing.JTextField pqty;
    private javax.swing.JTextField ptot;
    public static javax.swing.JTable tableparcel;
    // End of variables declaration//GEN-END:variables
}
