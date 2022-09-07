package ATMinterface;

import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;

interface Icustomerlogin
{
    boolean login();
}

interface Istafflogin
{
    boolean login();
}

interface ILoan
{
    boolean loanapprove(boolean approve);
}

class customer implements Icustomerlogin
{
    String id = "", password = "";
    int balance = 1000, bill = 400, cardlimit = 3000, loanamount;
    boolean login = false;
    boolean demandloan = false;
    boolean loanapprove = false;
    boolean demandcc = false;
    boolean approvecc = false;
    boolean written = false;
    
    customer(String id, String password)
    {
        this.id = id;
        this.password = password;
    }
    
    void withdrawmoney(int witdrawnmoney)
    {
        this.balance -= witdrawnmoney;
    }
    
    void depositmoney(int depositedmoney)
    {
        this.balance += depositedmoney;
    }
    
    void transfermoney(int transferredmoney)
    {
        this.balance -= transferredmoney;
    }
    
    void paybills(int totalpayment)
    {
        this.balance -= totalpayment;
        this.bill -= totalpayment;
    }
    
    void paybills(int installment, int monthlypayment)
    {
        this.balance -= monthlypayment;
        this.bill -= monthlypayment;
    }
    
    void updatelimit(int newlimit)
    {
        this.cardlimit = newlimit;
    }

    @Override
    public boolean login() {
        return this.login;
    }
}

abstract class staff implements Istafflogin
{
    boolean login = false;
    boolean written = false;
    
    String id, password;
    void changepassword(String password)
    {
        this.password = password;
    }
}

class manager extends staff implements ILoan
{
    @Override
    public boolean loanapprove(boolean approve) {
        return approve;
    }
    
    manager(String id, String password)
    {
        this.id = id;
        this.password = password;
    }
    
    @Override
    void changepassword(String password)
    {
        this.password = password;
    }

    @Override
    public boolean login() {
        return this.login;
    }
}

class employee extends staff
{
    
    employee(String id, String password)
    {
        this.id = id;
        this.password = password;
    }
    
    @Override
    void changepassword(String password)
    {
        this.password = password;
    }

    @Override
    public boolean login() {
        return this.login;
    }
    
}


class tools
{
    int customercounter = 0, employeecounter = 0, managercounter = 0;
    int customersearch = 0, employeesearch = 0, mtransfer = -1;
    int managersearch = 0;
    boolean customercheck = false, employeecheck = false, managercheck = false;
    boolean customerreadcheck = false, employeereadcheck = false, managerreadcheck = false;
    
    String[] customerscan = new String[5];
    String[] employeescan = new String[2];
    String[] managerscan = new String[2];
    
}


public class Main {
    
    public static JButton customerregister;
    public static JButton customersignup;
    public static JButton customerlogin;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
                
        customer customers[] = new customer[40];
        staff employees[] = new employee[20];
        staff administrator[] = new manager[2];
        tools tool = new tools();
        
        JPanel panel = new JPanel();
        
        JFrame frame = new JFrame();
        frame.setSize(450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        frame.add(panel);
        panel.setLayout(null);
        
        JLabel label = new JLabel("PLEASE CHOOSE USER TYPE");
        label.setBounds(131, 0, 188, 30);
        panel.add(label);
        
        JButton customerbutton = new JButton("Customer");
        customerbutton.setBounds(90, 100, 90, 30);
        customerbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                
                JFrame customerframe = new JFrame();
                customerframe.setSize(450, 450);
                customerframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                customerframe.setLocationRelativeTo(null);
                
                JPanel customerpanel = new JPanel();
                customerframe.add(customerpanel);
                customerpanel.setLayout(null);

                JLabel nick = new JLabel("Username");
                nick.setBounds(50, 30, 100, 25);
                customerpanel.add(nick);
                
                JTextField customerid = new JTextField();
                customerid.setBounds(130, 30, 200, 25);
                customerpanel.add(customerid);
                
                JLabel pass = new JLabel("Password");
                pass.setBounds(50, 70, 100, 25);
                customerpanel.add(pass);
                
                JPasswordField customerpass = new JPasswordField();
                customerpass.setBounds(130, 70, 200, 25);
                customerpanel.add(customerpass);
 
                customerregister = new JButton("Sign Up");
                customerregister.setBounds(140, 110,80,30);
                customerpanel.add(customerregister);
                customerregister.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        JFrame customerregisterframe = new JFrame();
                        customerregisterframe.setSize(450, 450);
                        customerregisterframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        customerregisterframe.setLocationRelativeTo(null);

                        JPanel customerregisterpanel = new JPanel();
                        customerregisterframe.add(customerregisterpanel);
                        customerregisterpanel.setLayout(null);
                        
                        JLabel registererror = new JLabel("Register error!");
                        registererror.setBounds(200, 150,100,30);
                        registererror.setVisible(false);
                        customerregisterpanel.add(registererror); 
                        
                        JLabel customerregisternick = new JLabel("Username");
                        customerregisternick.setBounds(50, 30, 100, 25);
                        customerregisterpanel.add(customerregisternick);
                        
                        JTextField registerid = new JTextField();
                        registerid.setBounds(130, 30, 200, 25);
                        customerregisterpanel.add(registerid);
                
                        JLabel customerregisterpass = new JLabel("Password");
                        customerregisterpass.setBounds(50, 70, 100, 25);
                        customerregisterpanel.add(customerregisterpass);
                        
                        JPasswordField registerpass = new JPasswordField();
                        registerpass.setBounds(130, 70, 200, 25);
                        customerregisterpanel.add(registerpass);
                        
                        customersignup = new JButton("Done");
                        customersignup.setBounds(170, 110,120,30);
                        customerregisterpanel.add(customersignup);
                        customersignup.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for(int i = 0;i<tool.customercounter;i++)
                                {
                                    if(customers[i].id.equals(registerid.getText()))
                                        tool.customercheck = true;
                                }
                                
                                if(registerid.getText().isEmpty() || registerpass.getText().isEmpty())
                                   registererror.setVisible(true);
                                else if (tool.customercheck == true & tool.customercounter > 0)
                                    registererror.setVisible(true);
                                else
                                {
                                    customers[tool.customercounter] = new customer(registerid.getText(),registerpass.getText()); 
                                    
                                    try {
                                        BufferedWriter bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customercounter].id +".txt"));
                                        bwr.write("Username: "+customers[tool.customercounter].id);
                                        bwr.write("\nPassword: "+customers[tool.customercounter].password);
                                        bwr.write("\nBalance: "+customers[tool.customercounter].balance);
                                        bwr.write("\nBills: "+customers[tool.customercounter].bill);
                                        bwr.write("\nCard Limit: "+customers[tool.customercounter].cardlimit);
                                        bwr.close();
                                        
                                    } catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    try {
                                        BufferedWriter bwrlist = new BufferedWriter(new FileWriter("customers.txt", true));
                                        bwrlist.write(customers[tool.customercounter].id);
                                        bwrlist.newLine();
                                        bwrlist.close();
                                        
                                        customers[tool.customercounter].written = true;
                                    } catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    
                                    tool.customercounter++;
                                    customerregisterframe.setVisible(false);
                                }
                            }
                        });
                        
                        JButton customersignupcancel = new JButton("Cancel");
                        customersignupcancel.setBounds(325, 350, 90, 30);
                        customerregisterpanel.add(customersignupcancel);
                        customersignupcancel.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                customerregisterframe.setVisible(false);
                            }
                        });
                        
                        customerregisterframe.setVisible(true);
                    }
                });
                
                
                customerlogin = new JButton("Login");
                customerlogin.setBounds(240, 110,80,30);
                customerpanel.add(customerlogin);
                customerlogin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        JLabel loginerror = new JLabel("Login error!");
                        loginerror.setBounds(200, 150,100,30);
                        loginerror.setVisible(false);
                        customerpanel.add(loginerror);
                        
                        File check = new File("[c]"+customerid.getText()+".txt");
                        if(!(check.exists()))
                        {
                            loginerror.setVisible(true);
                        }
                        else if(check.exists())
                        {
                            for(int i = 0;i<tool.customercounter;i++)
                            {
                                if(customers[i].id.equals(customerid.getText()))
                                {
                                    tool.customersearch = i;
                                    tool.customerreadcheck = true;
                                }

                            }
                            
                            if(tool.customerreadcheck == false)
                            {
                                for(int i = 0;i<40;i++)
                                {
                                    if(customers[i] == null)
                                    {
                                        BufferedReader br;
                                        try {
                                        br = new BufferedReader(new FileReader(check));
                                        br.skip(15);
                                        tool.customerscan[0] = br.readLine();
                                        br.skip(7);
                                        tool.customerscan[1] = br.readLine();
                                        br.skip(8);
                                        tool.customerscan[2] = br.readLine();
                                        br.skip(14);
                                        tool.customerscan[3] = br.readLine();
                                        br.skip(13);
                                        tool.customerscan[4] = br.readLine();

                                        br.close();
                                        } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        customers[i] = new customer(tool.customerscan[0],tool.customerscan[1]);
                                        customers[i].balance = Integer.parseInt(tool.customerscan[2]);
                                        customers[i].bill = Integer.parseInt(tool.customerscan[3]);
                                        customers[i].cardlimit = Integer.parseInt(tool.customerscan[4]);

                                        if(!customers[i].password.equals(customerpass.getText()))
                                            loginerror.setVisible(true);
                                        else
                                            customers[i].login = true;
                                        
                                        if(customers[i].written == false)
                                        {
                                            BufferedWriter bwrmlist;
                                            try {
                                                bwrmlist = new BufferedWriter(new FileWriter("customers.txt",true));
                                                bwrmlist.write(customers[i].id);
                                                bwrmlist.newLine();
                                                bwrmlist.close();
                                                
                                                customers[i].written = true;
                                            } catch (IOException ex) {
                                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        
                                        tool.customersearch = i;
                                        tool.customercounter++;
                                        break;
                                    }
                                }
                                
                            }
                            else
                            {
                                if(!customers[tool.customersearch].password.equals(customerpass.getText()))
                                    loginerror.setVisible(true);
                                else
                                    customers[tool.customersearch].login = true;
                            }
                            
                        }
                        
                        if(customers[tool.customersearch].login() == true)
                        {
                            customerframe.setVisible(false);
                            
                            JFrame customercontrolframe = new JFrame();
                            customercontrolframe.setSize(450, 450);
                            customercontrolframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            customercontrolframe.setLocationRelativeTo(null);

                            JPanel customercontrolpanel = new JPanel();
                            customercontrolframe.add(customercontrolpanel);
                            customercontrolpanel.setLayout(null);

                            JLabel customergreetlabel = new JLabel("Welcome " + customerid.getText());
                            customergreetlabel.setHorizontalAlignment(JLabel.CENTER);
                            customercontrolframe.add(customergreetlabel, BorderLayout.NORTH);
                            
                            JButton customerchangepassword = new JButton("Change pass");
                            customerchangepassword.setBounds(30, 25,130, 30);
                            customercontrolpanel.add(customerchangepassword);
                            customerchangepassword.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame customerpasswordframe = new JFrame();
                                    customerpasswordframe.setSize(450, 450);
                                    customerpasswordframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    customerpasswordframe.setLocationRelativeTo(null);
                                    
                                    JPanel customerpasswordpanel = new JPanel();
                                    customerpasswordframe.add(customerpasswordpanel);
                                    customerpasswordpanel.setLayout(null);
                            
                                    JLabel customerpasswordlabel = new JLabel("New password ");
                                    customerpasswordlabel.setHorizontalAlignment(JLabel.CENTER);
                                    customerpasswordframe.add(customerpasswordlabel, BorderLayout.NORTH);
                                    
                                    JPasswordField customerpasswordtxt = new JPasswordField();
                                    customerpasswordtxt.setBounds(120, 30, 200, 25);
                                    customerpasswordpanel.add(customerpasswordtxt);
                                    
                                    JButton customerpassword = new JButton("Done");
                                    customerpassword.setBounds(170, 60, 100, 30);
                                    customerpasswordpanel.add(customerpassword);
                                    customerpassword.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            customers[tool.customersearch].password = customerpasswordtxt.getText();
                                            
                                            BufferedWriter bwr;
                                            try {
                                            bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customersearch].id +".txt"));
                                            bwr.write("Username: "+customers[tool.customersearch].id);
                                            bwr.write("\nPassword: "+customers[tool.customersearch].password);
                                            bwr.write("\nBalance: "+customers[tool.customersearch].balance);
                                            bwr.write("\nBills: "+customers[tool.customersearch].bill);
                                            bwr.write("\nCard Limit: "+customers[tool.customersearch].cardlimit);
                                            bwr.close();
                                            } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        
                                            customerpasswordframe.setVisible(false);
                                        }
                                    });
                                    
                                    JButton passwordquit = new JButton("Cancel");
                                    passwordquit.setBounds(325, 350, 90, 30);
                                    customerpasswordpanel.add(passwordquit);
                                    passwordquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            customerpasswordframe.setVisible(false);
                                        }
                                    });
                                    
                                    customerpasswordframe.setVisible(true);
                                }
                            });
                            
                            JLabel balancelabel = new JLabel("Your Balance: " + customers[tool.customersearch].balance);
                            balancelabel.setBounds(165, 100,130, 30);
                            customercontrolpanel.add(balancelabel);
                            balancelabel.setVisible(false);
                                    
                            JButton balancebutton = new JButton("Balance Inquiry");
                            balancebutton.setBounds(30, 100,130, 30);
                            customercontrolpanel.add(balancebutton);
                            balancebutton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    balancelabel.setText("Your Balance: " + customers[tool.customersearch].balance);
                                    balancelabel.setVisible(true);
                                }
                            });
                            
                            JButton billbutton = new JButton("Pay Bills");
                            billbutton.setBounds(30, 175,130, 30);
                            customercontrolpanel.add(billbutton);
                            billbutton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame billframe = new JFrame();
                                    billframe.setSize(450, 450);
                                    billframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    billframe.setLocationRelativeTo(null);
                                    
                                    JPanel billpanel = new JPanel();
                                    billframe.add(billpanel);
                                    billpanel.setLayout(null);
                                    
                                    JLabel billdebt = new JLabel("Bill: " + customers[tool.customersearch].bill);
                                    billdebt.setHorizontalAlignment(JLabel.CENTER);
                                    billframe.add(billdebt, BorderLayout.NORTH);
                            
                                    JLabel installmentlabel = new JLabel("Number of Installments");
                                    installmentlabel.setBounds(30, 25, 175, 30);
                                    billpanel.add(installmentlabel);
                            
                                    JTextField numofinstallments = new JTextField();
                                    numofinstallments.setBounds(180, 30, 75, 25);
                                    billpanel.add(numofinstallments);
                                    
                                    JLabel paymentlabel = new JLabel("Monthly Payment");
                                    paymentlabel.setBounds(30, 65,130, 30);
                                    billpanel.add(paymentlabel);
                                    
                                    JTextField monthlypayment = new JTextField();
                                    monthlypayment.setBounds(180, 70, 75, 25);
                                    billpanel.add(monthlypayment);
                                    
                                    JButton installmentbutton = new JButton("Pay the bill");
                                    installmentbutton.setBounds(170, 100,100, 30);
                                    billpanel.add(installmentbutton);
                                    installmentbutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            customers[tool.customersearch].paybills(Integer.parseInt(numofinstallments.getText()), Integer.parseInt(monthlypayment.getText()));
                                            
                                            BufferedWriter bwr;
                                            try {
                                            bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customersearch].id +".txt"));
                                            bwr.write("Username: "+customers[tool.customersearch].id);
                                            bwr.write("\nPassword: "+customers[tool.customersearch].password);
                                            bwr.write("\nBalance: "+customers[tool.customersearch].balance);
                                            bwr.write("\nBills: "+customers[tool.customersearch].bill);
                                            bwr.write("\nCard Limit: "+customers[tool.customersearch].cardlimit);
                                            bwr.close();
                                            } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            
                                            billframe.setVisible(false);
                                        }
                                    });
                                   
                                    JLabel advancepaymentlabel = new JLabel("Pay in advance");
                                    advancepaymentlabel.setBounds(30, 160,130, 30);
                                    billpanel.add(advancepaymentlabel);
                                    
                                    JTextField advancepaymenttxt = new JTextField();
                                    advancepaymenttxt.setBounds(180, 160, 75, 25);
                                    billpanel.add(advancepaymenttxt);
                                    
                                    JButton advancebutton = new JButton("Pay the bill");
                                    advancebutton.setBounds(170, 190, 100, 30);
                                    billpanel.add(advancebutton);
                                    advancebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            customers[tool.customersearch].paybills(Integer.parseInt(advancepaymenttxt.getText()));
                                            
                                            BufferedWriter bwr;
                                            try {
                                            bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customersearch].id +".txt"));
                                            bwr.write("Username: "+customers[tool.customersearch].id);
                                            bwr.write("\nPassword: "+customers[tool.customersearch].password);
                                            bwr.write("\nBalance: "+customers[tool.customersearch].balance);
                                            bwr.write("\nBills: "+customers[tool.customersearch].bill);
                                            bwr.write("\nCard Limit: "+customers[tool.customersearch].cardlimit);
                                            bwr.close();
                                            } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            
                                            billframe.setVisible(false);
                                        }
                                    });
                                    
                                    JButton billquit = new JButton("Cancel");
                                    billquit.setBounds(325, 350, 90, 30);
                                    billpanel.add(billquit);
                                    billquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            billframe.setVisible(false);
                                        }
                                    });
                                    
                                    billframe.setVisible(true);
                                }
                            });
                            
                            JButton wmoneybutton = new JButton("Withdraw Money");
                            wmoneybutton.setBounds(275, 25, 130, 30);
                            customercontrolpanel.add(wmoneybutton);
                            wmoneybutton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame wmoneyframe = new JFrame();
                                    wmoneyframe.setSize(450, 450);
                                    wmoneyframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    wmoneyframe.setLocationRelativeTo(null);
                                    
                                    JPanel wmoneypanel = new JPanel();
                                    wmoneyframe.add(wmoneypanel);
                                    wmoneypanel.setLayout(null);
                                    
                                    JLabel balancelabel = new JLabel("Your Balance: " + customers[tool.customersearch].balance);
                                    balancelabel.setHorizontalAlignment(JLabel.CENTER);
                                    wmoneyframe.add(balancelabel, BorderLayout.NORTH);
                                    
                                    JLabel balanceexceed = new JLabel("Insufficent Balance");
                                    balanceexceed.setBounds(140, 140, 250, 25);
                                    wmoneypanel.add(balanceexceed);
                                    balanceexceed.setVisible(false);
                                    
                                    JTextField wmoneytxt = new JTextField();
                                    wmoneytxt.setBounds(115, 30, 200, 25);
                                    wmoneypanel.add(wmoneytxt);
                                    
                                    JButton wmoneyapprovebutton = new JButton("Approve");
                                    wmoneyapprovebutton.setBounds(150, 70, 130, 25);
                                    wmoneypanel.add(wmoneyapprovebutton);
                                    wmoneyapprovebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            if(Integer.parseInt(wmoneytxt.getText()) > customers[tool.customersearch].balance)
                                                balanceexceed.setVisible(true);
                                            else
                                            {
                                                customers[tool.customersearch].withdrawmoney(Integer.parseInt(wmoneytxt.getText()));
                                            
                                                BufferedWriter bwr;
                                                try {
                                                bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customersearch].id +".txt"));
                                                bwr.write("Username: "+customers[tool.customersearch].id);
                                                bwr.write("\nPassword: "+customers[tool.customersearch].password);
                                                bwr.write("\nBalance: "+customers[tool.customersearch].balance);
                                                bwr.write("\nBills: "+customers[tool.customersearch].bill);
                                                bwr.write("\nCard Limit: "+customers[tool.customersearch].cardlimit);
                                                bwr.close();
                                                } catch (IOException ex) {
                                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                                }

                                                wmoneyframe.setVisible(false);
                                            }
                                            
                                        }
                                    });
                                    
                                    JButton wmoneyquit = new JButton("Cancel");
                                    wmoneyquit.setBounds(325, 350, 90, 30);
                                    wmoneypanel.add(wmoneyquit);
                                    wmoneyquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            wmoneyframe.setVisible(false);
                                        }
                                    });
                                    
                                    wmoneyframe.setVisible(true);
                                }
                            });
                            
                            JButton dmoneybutton = new JButton("Deposit Money");
                            dmoneybutton.setBounds(275, 100, 130, 30);
                            customercontrolpanel.add(dmoneybutton);
                            dmoneybutton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame dmoneyframe = new JFrame();
                                    dmoneyframe.setSize(450, 450);
                                    dmoneyframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    dmoneyframe.setLocationRelativeTo(null);
                                    
                                    JPanel dmoneypanel = new JPanel();
                                    dmoneyframe.add(dmoneypanel);
                                    dmoneypanel.setLayout(null);
                                    
                                    JLabel balancelabel = new JLabel("Your Balance: " + customers[tool.customersearch].balance);
                                    balancelabel.setHorizontalAlignment(JLabel.CENTER);
                                    dmoneyframe.add(balancelabel, BorderLayout.NORTH);
                            
                                    JTextField dmoneytxt = new JTextField();
                                    dmoneytxt.setBounds(115, 30, 200, 25);
                                    dmoneypanel.add(dmoneytxt);
                                    
                                    JLabel limitexceed = new JLabel("Insufficient limit");
                                    limitexceed.setBounds(140, 140, 250, 25);
                                    dmoneypanel.add(limitexceed);
                                    limitexceed.setVisible(false);
                                    
                                    JButton dmoneyapprovebutton = new JButton("Approve");
                                    dmoneyapprovebutton.setBounds(150, 70, 130, 25);
                                    dmoneypanel.add(dmoneyapprovebutton);
                                    dmoneyapprovebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            if(Integer.parseInt(dmoneytxt.getText())+customers[tool.customersearch].balance > customers[tool.customersearch].cardlimit)
                                                limitexceed.setVisible(true);
                                            else
                                            {
                                                customers[tool.customersearch].depositmoney(Integer.parseInt(dmoneytxt.getText()));
                                            
                                                BufferedWriter bwr;
                                                try {
                                                bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customersearch].id +".txt"));
                                                bwr.write("Username: "+customers[tool.customersearch].id);
                                                bwr.write("\nPassword: "+customers[tool.customersearch].password);
                                                bwr.write("\nBalance: "+customers[tool.customersearch].balance);
                                                bwr.write("\nBills: "+customers[tool.customersearch].bill);
                                                bwr.write("\nCard Limit: "+customers[tool.customersearch].cardlimit);
                                                bwr.close();
                                                } catch (IOException ex) {
                                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                                }

                                                dmoneyframe.setVisible(false);
                                            }
                                            
                                        }
                                    });
                                    
                                    JButton dmoneyquit = new JButton("Cancel");
                                    dmoneyquit.setBounds(325, 350, 90, 30);
                                    dmoneypanel.add(dmoneyquit);
                                    dmoneyquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            dmoneyframe.setVisible(false);
                                        }
                                    });
                                    
                                    dmoneyframe.setVisible(true);
                                }
                            });
                            
                            JButton transfermoney = new JButton("Transfer Money");
                            transfermoney.setBounds(275, 175, 130, 30);
                            customercontrolpanel.add(transfermoney);
                            transfermoney.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame mtransferframe = new JFrame();
                                    mtransferframe.setSize(450, 450);
                                    mtransferframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    mtransferframe.setLocationRelativeTo(null);
                                    
                                    JPanel mtransferpanel = new JPanel();
                                    mtransferframe.add(mtransferpanel);
                                    mtransferpanel.setLayout(null);
                                    
                                    JLabel balancelabel = new JLabel("Your Balance: " + customers[tool.customersearch].balance);
                                    balancelabel.setHorizontalAlignment(JLabel.CENTER);
                                    mtransferframe.add(balancelabel, BorderLayout.NORTH);
                                    
                                    JLabel idlabel = new JLabel("ID to be transferred");
                                    idlabel.setBounds(15, 25, 130, 30);
                                    mtransferpanel.add(idlabel);
                            
                                    JTextField transferid = new JTextField();
                                    transferid.setBounds(140, 30, 200, 25);
                                    mtransferpanel.add(transferid);
                                    
                                    JLabel paymentlabel = new JLabel("Amount of money");
                                    paymentlabel.setBounds(15, 65,130, 30);
                                    mtransferpanel.add(paymentlabel);
                                    
                                    JTextField mtransfertxt = new JTextField();
                                    mtransfertxt.setBounds(140, 70, 200, 25);
                                    mtransferpanel.add(mtransfertxt);
                                    
                                    JButton mtransferapprovebutton = new JButton("Approve");
                                    mtransferapprovebutton.setBounds(170, 100, 130, 25);
                                    mtransferpanel.add(mtransferapprovebutton);
                                    mtransferapprovebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            JLabel transfererror = new JLabel("ID not found");
                                            transfererror.setBounds(200, 140, 130, 25);
                                            mtransferpanel.add(transfererror);
                                            transfererror.setVisible(false);
                                            
                                            JLabel selftransfer = new JLabel("You cannot transfer yourself");
                                            selftransfer.setBounds(140, 140, 250, 25);
                                            mtransferpanel.add(selftransfer);
                                            selftransfer.setVisible(false);
                                            
                                            JLabel limitexceed = new JLabel("The amount exeeds limit of receiver");
                                            limitexceed.setBounds(140, 140, 250, 25);
                                            mtransferpanel.add(limitexceed);
                                            limitexceed.setVisible(false);
                                            
                                            JLabel balanceexceed = new JLabel("Insufficent balance");
                                            balanceexceed.setBounds(140, 140, 250, 25);
                                            mtransferpanel.add(balanceexceed);
                                            balanceexceed.setVisible(false);
                                            
                                            for(int i = 0;i<tool.customercounter;i++)
                                            {
                                                if(transferid.getText().equals(customers[tool.customersearch].id))
                                                {
                                                    balanceexceed.setVisible(false);
                                                    limitexceed.setVisible(false);
                                                    transfererror.setVisible(false);
                                                    selftransfer.setVisible(true);
                                                }
                                                else if(transferid.getText().equals(customers[i].id))
                                                {
                                                    tool.mtransfer = i;
                                                    
                                                    if((Integer.parseInt(mtransfertxt.getText()) + customers[tool.mtransfer].cardlimit) > customers[tool.mtransfer].cardlimit)
                                                    {
                                                        balanceexceed.setVisible(false);
                                                        transfererror.setVisible(false);
                                                        selftransfer.setVisible(false);
                                                        limitexceed.setVisible(true);
                                                    }
                                                    else if(Integer.parseInt(mtransfertxt.getText()) > customers[tool.mtransfer].balance)
                                                    {
                                                        transfererror.setVisible(false);
                                                        selftransfer.setVisible(false);
                                                        limitexceed.setVisible(false);
                                                        balanceexceed.setVisible(true);
                                                    }
                                                    else
                                                    {
                                                        customers[tool.customersearch].transfermoney(Integer.parseInt(mtransfertxt.getText()));
                                                        customers[tool.mtransfer].balance += Integer.parseInt(mtransfertxt.getText());

                                                        BufferedWriter bwr;
                                                        try {
                                                        bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customersearch].id +".txt"));
                                                        bwr.write("Username: "+customers[tool.customersearch].id);
                                                        bwr.write("\nPassword: "+customers[tool.customersearch].password);
                                                        bwr.write("\nBalance: "+customers[tool.customersearch].balance);
                                                        bwr.write("\nBills: "+customers[tool.customersearch].bill);
                                                        bwr.write("\nCard Limit: "+customers[tool.customersearch].cardlimit);
                                                        bwr.close();
                                                        } catch (IOException ex) {
                                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                                        }

                                                        BufferedWriter bwr2;
                                                        try {
                                                        bwr2 = new BufferedWriter(new FileWriter("[c]"+customers[tool.mtransfer].id +".txt"));
                                                        bwr2.write("Username: "+customers[tool.mtransfer].id);
                                                        bwr2.write("\nPassword: "+customers[tool.mtransfer].password);
                                                        bwr2.write("\nBalance: "+customers[tool.mtransfer].balance);
                                                        bwr2.write("\nBills: "+customers[tool.mtransfer].bill);
                                                        bwr2.write("\nCard Limit: "+customers[tool.mtransfer].cardlimit);
                                                        bwr2.close();
                                                        } catch (IOException ex) {
                                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                                        }

                                                        mtransferframe.setVisible(false);
                                                        break;
                                                        }
                                                }
                                                else if(tool.mtransfer == -1 & i == tool.customercounter - 1)
                                                {   
                                                    selftransfer.setVisible(false);
                                                    transfererror.setVisible(true);
                                                }
                                            }
                                        }
                                    });
                                    
                                    JButton mtransferquit = new JButton("Cancel");
                                    mtransferquit.setBounds(325, 350, 90, 30);
                                    mtransferpanel.add(mtransferquit);
                                    mtransferquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            mtransferframe.setVisible(false);
                                        }
                                    });
                                    
                                    mtransferframe.setVisible(true);
                                }
                            });
                            
                            JButton takeloan = new JButton("Take Out Loan");
                            takeloan.setBounds(30, 250, 130, 30);
                            customercontrolpanel.add(takeloan);
                            takeloan.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame loanframe = new JFrame();
                                    loanframe.setSize(450, 450);
                                    loanframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    loanframe.setLocationRelativeTo(null);
                                    
                                    JPanel loanpanel = new JPanel();
                                    loanframe.add(loanpanel);
                                    loanpanel.setLayout(null);
                                    
                                    JLabel demanderror = new JLabel("You cannot demand more than one loan");
                                    demanderror.setBounds(120, 140, 225, 30);
                                    loanpanel.add(demanderror);
                                    demanderror.setVisible(false);
                                    
                                    JLabel limiterror = new JLabel("Amount of loan exceeds your limit");
                                    limiterror.setBounds(120, 140, 225, 30);
                                    loanpanel.add(limiterror);
                                    limiterror.setVisible(false);
                                    
                                    JLabel amountlabel = new JLabel("Amount of loan");
                                    amountlabel.setBounds(15, 25, 130, 30);
                                    loanpanel.add(amountlabel);
                                    
                                    JTextField loantxt = new JTextField();
                                    loantxt.setBounds(115, 30, 200, 25);
                                    loanpanel.add(loantxt);
                                    
                                    JButton demandloan = new JButton("Send Request");
                                    demandloan.setBounds(150, 70, 130, 25);
                                    loanpanel.add(demandloan);
                                    demandloan.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        
                                            if((Integer.parseInt(loantxt.getText()) + customers[tool.customersearch].balance) > customers[tool.customersearch].cardlimit)
                                                limiterror.setVisible(false);
                                            else if(customers[tool.customersearch].demandloan == true)
                                                demanderror.setVisible(true);
                                            else
                                            {
                                                customers[tool.customersearch].demandloan = true;
                                                customers[tool.customersearch].loanamount = Integer.parseInt(loantxt.getText());
                                                loanframe.setVisible(false);
                                            }
                                                
                                        }
                                    });
                                    
                                    JLabel approvestatus = new JLabel("Your request has been approved!");
                                    approvestatus.setBounds(160, 140, 160, 30);
                                    loanpanel.add(approvestatus);
                                    approvestatus.setVisible(false);
                                    
                                    JButton approvebutton = new JButton("Check Request");
                                    approvebutton.setBounds(150, 100, 130, 25);
                                    loanpanel.add(approvebutton);
                                    approvebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            if(customers[tool.customersearch].loanapprove == true)
                                                approvestatus.setVisible(true);
                                            else if(customers[tool.customersearch].demandloan == false)
                                            {
                                                approvestatus.setText("No request sent");
                                                approvestatus.setVisible(true);
                                            }
                                            else
                                            {
                                                approvestatus.setText("Your request is under review");
                                                approvestatus.setVisible(true);
                                            }
                                                
                                        }
                                    });
                                    
                                    JButton loanquit = new JButton("Cancel");
                                    loanquit.setBounds(325, 350, 90, 30);
                                    loanpanel.add(loanquit);
                                    loanquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            loanframe.setVisible(false);
                                        }
                                    });
                                    
                                    loanframe.setVisible(true);
                                }
                            });
                            
                            JButton limitbutton = new JButton("Update Limit");
                            limitbutton.setBounds(30, 325, 130, 30);
                            customercontrolpanel.add(limitbutton);
                            limitbutton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame limitframe = new JFrame();
                                    limitframe.setSize(450, 450);
                                    limitframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    limitframe.setLocationRelativeTo(null);
                                    
                                    JPanel limitpanel = new JPanel();
                                    limitframe.add(limitpanel);
                                    limitpanel.setLayout(null);
                                    
                                    JLabel limitlabel = new JLabel("Your limit: " + customers[tool.customersearch].cardlimit);
                                    limitlabel.setHorizontalAlignment(JLabel.CENTER);
                                    limitframe.add(limitlabel, BorderLayout.NORTH);
                            
                                    JTextField limittxt = new JTextField();
                                    limittxt.setBounds(115, 30, 200, 25);
                                    limitpanel.add(limittxt);
                                    
                                    JButton limitapprovebutton = new JButton("Approve");
                                    limitapprovebutton.setBounds(150, 70, 130, 25);
                                    limitpanel.add(limitapprovebutton);
                                    limitapprovebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            customers[tool.customersearch].updatelimit(Integer.parseInt(limittxt.getText()));
                                            
                                            BufferedWriter bwr;
                                            try {
                                            bwr = new BufferedWriter(new FileWriter("[c]"+customers[tool.customersearch].id +".txt"));
                                            bwr.write("Username: "+customers[tool.customersearch].id);
                                            bwr.write("\nPassword: "+customers[tool.customersearch].password);
                                            bwr.write("\nBalance: "+customers[tool.customersearch].balance);
                                            bwr.write("\nBills: "+customers[tool.customersearch].bill);
                                            bwr.write("\nCard Limit: "+customers[tool.customersearch].cardlimit);
                                            bwr.close();
                                            } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            
                                            limitframe.setVisible(false);
                                        }
                                    });
                                    
                                    JButton limitquit = new JButton("Cancel");
                                    limitquit.setBounds(325, 350, 90, 30);
                                    limitpanel.add(limitquit);
                                    limitquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            limitframe.setVisible(false);
                                        }
                                    });
                                    
                                    limitframe.setVisible(true);
                                }
                            });
                            
                            JLabel cardlabel = new JLabel();
                            cardlabel.setBounds(270, 280,180, 30);
                            customercontrolpanel.add(cardlabel);
                            cardlabel.setVisible(false);
                            
                            JButton carddemand = new JButton("Demand CC");
                            carddemand.setBounds(275, 250, 130, 30);
                            customercontrolpanel.add(carddemand);
                            carddemand.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    customers[tool.customersearch].demandcc = true;
                                
                                    if(customers[tool.customersearch].approvecc == true)
                                    {
                                        cardlabel.setText("Request Approved");
                                        cardlabel.setVisible(true);
                                    }
                                    else
                                    {
                                        cardlabel.setText("Request is under review");
                                        cardlabel.setVisible(true);
                                    }
                                }
                            });
                            
                            JButton customerquit = new JButton("Log Out");
                            customerquit.setBounds(325, 350, 90, 30);
                            customercontrolpanel.add(customerquit);
                            customerquit.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    customercontrolframe.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });
                            
                            customercontrolframe.setVisible(true);
                        }
                            
                    }
                });
                
                customerframe.setVisible(true);
            }
        });
        
        JButton employeebuton = new JButton("Staff");
        employeebuton.setBounds(190, 100, 90, 30);
        employeebuton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                
                JFrame employeeframe = new JFrame();
                employeeframe.setSize(450, 450);
                employeeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                employeeframe.setLocationRelativeTo(null);
                
                JPanel employeepanel = new JPanel();
                employeeframe.add(employeepanel);
                employeepanel.setLayout(null);
                
                JLabel employeelabel = new JLabel("Please Login");
                employeelabel.setHorizontalAlignment(JLabel.CENTER);
                employeeframe.add(employeelabel, BorderLayout.NORTH);

                JLabel nick = new JLabel("Username");
                nick.setBounds(50, 30, 100, 25);
                employeepanel.add(nick);
                
                JTextField employeeid = new JTextField();
                employeeid.setBounds(130, 30, 200, 25);
                employeepanel.add(employeeid);
                
                JLabel pass = new JLabel("Password");
                pass.setBounds(50, 70, 100, 25);
                employeepanel.add(pass);
                
                JPasswordField employeepass = new JPasswordField();
                employeepass.setBounds(130, 70, 200, 25);
                employeepanel.add(employeepass);
                
                JButton eregister = new JButton("Kaydol");
                eregister.setBounds(140, 110,80,30);
                employeepanel.add(eregister);
                eregister.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        JFrame eregisterframe = new JFrame();
                        eregisterframe.setSize(450, 450);
                        eregisterframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        eregisterframe.setLocationRelativeTo(null);

                        JPanel eregisterpanel = new JPanel();
                        eregisterframe.add(eregisterpanel);
                        eregisterpanel.setLayout(null);
                        
                        JLabel registererror = new JLabel("Register error!");
                        registererror.setBounds(200, 150,100,30);
                        registererror.setVisible(false);
                        eregisterpanel.add(registererror); 
                        
                        JLabel eregisternick = new JLabel("Username");
                        eregisternick.setBounds(50, 30, 100, 25);
                        eregisterpanel.add(eregisternick);
                        
                        JTextField registerid = new JTextField();
                        registerid.setBounds(130, 30, 200, 25);
                        eregisterpanel.add(registerid);
                
                        JLabel eregisterpass = new JLabel("Password");
                        eregisterpass.setBounds(50, 70, 100, 25);
                        eregisterpanel.add(eregisterpass);
                        
                        JPasswordField registerpass = new JPasswordField();
                        registerpass.setBounds(130, 70, 200, 25);
                        eregisterpanel.add(registerpass);
                        
                        JButton eregister = new JButton("Approve");
                        eregister.setBounds(170, 110,120,30);
                        eregisterpanel.add(eregister);
                        eregister.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                
                                for(int i = 0;i<tool.employeecounter;i++)
                                {
                                    if(employees[i].id.equals(registerid.getText()))
                                        tool.employeecheck = true;
                                        
                                }
                                
                                if(registerid.getText().isEmpty() || registerpass.getText().isEmpty())
                                   registererror.setVisible(true);
                                else if (tool.employeecheck == true & tool.employeecounter > 0)
                                    registererror.setVisible(true);
                                else
                                {
                                    employees[tool.employeecounter] = new employee(registerid.getText(),registerpass.getText()); 
                                    
                                    try {
                                        BufferedWriter bwr = new BufferedWriter(new FileWriter("[p]"+employees[tool.employeecounter].id +".txt"));
                                        bwr.write("Username: "+employees[tool.employeecounter].id);
                                        bwr.write("\nPassword: "+employees[tool.employeecounter].password);
                                        bwr.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    BufferedWriter bwrplist;
                                    try {
                                        bwrplist = new BufferedWriter(new FileWriter("employees.txt", true));
                                        bwrplist.write(employees[tool.employeecounter].id);
                                        bwrplist.newLine();
                                        bwrplist.close();
                                        
                                        employees[tool.employeecounter].written = true;
                                    } catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    tool.employeecounter++;
                                    eregisterframe.setVisible(false);
                                }
                            }
                        });
                        
                        JButton eregistercancel = new JButton("Cancel");
                        eregistercancel.setBounds(325, 350, 90, 30);
                        eregisterpanel.add(eregistercancel);
                        eregistercancel.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                eregisterframe.setVisible(false);
                            }
                        });
                        
                        eregisterframe.setVisible(true);
                    }
                });
                
                
                JButton plogin = new JButton("Login");
                plogin.setBounds(240, 110,80,30);
                employeepanel.add(plogin);
                plogin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        JLabel loginerror = new JLabel("Login error!");
                        loginerror.setBounds(200, 150,100,30);
                        loginerror.setVisible(false);
                        employeepanel.add(loginerror);
                        
                        File check = new File("[p]"+employeeid.getText()+".txt");
                        if(!(check.exists()))
                        {
                            loginerror.setVisible(true);
                        }
                        else if(check.exists())
                        {
                            for(int i = 0;i<tool.employeecounter;i++)
                            {
                                if(employees[i].id.equals(employeeid.getText()))
                                {
                                    tool.employeesearch = i;
                                    tool.employeereadcheck = true;
                                }

                            }
                            
                            if(tool.employeereadcheck == false)
                            {
                                for(int i = 0;i<20;i++)
                                {
                                    if(employees[i] == null)
                                    {
                                        BufferedReader br;
                                        try {
                                        br = new BufferedReader(new FileReader(check));
                                        br.skip(15);
                                        tool.employeescan[0] = br.readLine();
                                        br.skip(7);
                                        tool.employeescan[1] = br.readLine();

                                        br.close();
                                        } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        employees[i] = new employee(tool.employeescan[0],tool.employeescan[1]);

                                        if(!employees[i].password.equals(employeepass.getText()))
                                            loginerror.setVisible(true);
                                        else
                                            employees[i].login = true;
                                        
                                        if(employees[i].written == false)
                                        {
                                            BufferedWriter bwrplist;
                                            try {
                                                bwrplist = new BufferedWriter(new FileWriter("employees.txt",true));
                                                bwrplist.write(employees[i].id);
                                                bwrplist.newLine();
                                                bwrplist.close();
                                                
                                                employees[i].written = true;
                                            } catch (IOException ex) {
                                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        
                                        tool.employeesearch = i;
                                        tool.employeecounter++;
                                        break;
                                    }
                                }
                                
                            }
                            else
                            {
                                if(!employees[tool.employeesearch].password.equals(employeepass.getText()))
                                    loginerror.setVisible(true);
                                else
                                    employees[tool.employeesearch].login = true;
                            }
                            
                        }
                        
                        if(employees[tool.employeesearch].login() == true)
                        {    
                            employeeframe.setVisible(false);
                            
                            JFrame econtrolframe = new JFrame();
                            econtrolframe.setSize(450, 450);
                            econtrolframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            econtrolframe.setLocationRelativeTo(null);

                            JPanel econtrolpanel = new JPanel();
                            econtrolframe.add(econtrolpanel);
                            econtrolpanel.setLayout(null);

                            JLabel egreetlabel = new JLabel("Welcome " + employeeid.getText());
                            egreetlabel.setHorizontalAlignment(JLabel.CENTER);
                            econtrolframe.add(egreetlabel, BorderLayout.NORTH);
                            
                            JButton echangepassword = new JButton("Change Password");
                            echangepassword.setBounds(30, 25,130, 30);
                            econtrolpanel.add(echangepassword);
                            echangepassword.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame epasswordframe = new JFrame();
                                    epasswordframe.setSize(450, 450);
                                    epasswordframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    epasswordframe.setLocationRelativeTo(null);
                                    
                                    JPanel epasswordpanel = new JPanel();
                                    epasswordframe.add(epasswordpanel);
                                    epasswordpanel.setLayout(null);
                            
                                    JLabel epasswordlabel = new JLabel("New password ");
                                    epasswordlabel.setHorizontalAlignment(JLabel.CENTER);
                                    epasswordframe.add(epasswordlabel, BorderLayout.NORTH);
                                    
                                    JPasswordField epasswordtxt = new JPasswordField();
                                    epasswordtxt.setBounds(120, 30, 200, 25);
                                    epasswordpanel.add(epasswordtxt);
                                    
                                    JButton epassword = new JButton("Change");
                                    epassword.setBounds(170, 60, 100, 30);
                                    epasswordpanel.add(epassword);
                                    epassword.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            employees[tool.employeesearch].changepassword(epasswordtxt.getText());
                                            
                                            BufferedWriter bwr;
                                            try {
                                            bwr = new BufferedWriter(new FileWriter("[p]"+employees[tool.employeesearch].id +".txt"));
                                            bwr.write("Username: "+employees[tool.employeesearch].id);
                                            bwr.write("\nPassword: "+employees[tool.employeesearch].password);
                                            bwr.close();
                                            } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        
                                            epasswordframe.setVisible(false);
                                        }
                                    });
                                    
                                    JButton passwordquit = new JButton("Cancel");
                                    passwordquit.setBounds(325, 350, 90, 30);
                                    epasswordpanel.add(passwordquit);
                                    passwordquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            epasswordframe.setVisible(false);
                                        }
                                    });
                                    
                                    epasswordframe.setVisible(true);
                                }
                            });
                            
                            JButton cardapprove = new JButton("Approve Credit Card");
                            cardapprove.setBounds(275, 25,130, 30);
                            econtrolpanel.add(cardapprove);
                            cardapprove.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    
                                    JFrame cardapproveframe = new JFrame();
                                    cardapproveframe.setSize(450, 450);
                                    cardapproveframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    cardapproveframe.setLocationRelativeTo(null);

                                    JPanel cardapprovepanel = new JPanel();
                                    cardapproveframe.add(cardapprovepanel);
                                    cardapprovepanel.setLayout(null);
                                    
                                    JLabel carddemandlabel = new JLabel("Requests:");
                                    carddemandlabel.setHorizontalAlignment(JLabel.CENTER);
                                    cardapproveframe.add(carddemandlabel, BorderLayout.NORTH);
                                    carddemandlabel.setVisible(true);
                                    
                                    for(int i = 0;i<40;i++)
                                    {
                                        if(customers[i] != null)
                                        {
                                            if(customers[i].demandcc == true)
                                            {
                                                carddemandlabel.setText(carddemandlabel.getText()+" "+customers[i].id);
                                            }
                                        }
                                        
                                    }
                                    
                                    JLabel cardlabel = new JLabel("ID to be approved");
                                    cardlabel.setBounds(35, 30, 95, 25);
                                    cardapprovepanel.add(cardlabel);
                                    
                                    JTextField cardcandidate = new JTextField();
                                    cardcandidate.setBounds(130, 30, 200, 25);
                                    cardapprovepanel.add(cardcandidate);
                                    
                                    JButton cardapprovebutton = new JButton("Approve Demand");
                                    cardapprovebutton.setBounds(175, 70, 110, 25);
                                    cardapprovepanel.add(cardapprovebutton);
                                    cardapprovebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            
                                            for(int i = 0;i<tool.customercounter;i++)
                                            {
                                                
                                                if(customers[i].demandcc == true)
                                                {
                                                    if(cardcandidate.getText().equals(customers[i].id))
                                                    {
                                                        customers[i].approvecc = true;
                                                        customers[i].demandcc = false;
                                                        cardapproveframe.setVisible(false);
                                                    }
                                                }
                                                
                                            }
                                            
                                        }
                                    });
                                    
                                    cardapproveframe.setVisible(true);
                                }
                            });
                            
                            JButton employeequit = new JButton("Log Out");
                            employeequit.setBounds(325, 350, 90, 30);
                            econtrolpanel.add(employeequit);
                            employeequit.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    econtrolframe.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });
                            
                            econtrolframe.setVisible(true);
                        }
                            
                    }
                });
                
                
                employeeframe.setVisible(true);
            }
        });
        
        JButton administratorbuton = new JButton("Manager");
        administratorbuton.setBounds(290, 100, 90, 30);
        administratorbuton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                
                JFrame administratorframe = new JFrame();
                administratorframe.setSize(450, 450);
                administratorframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                administratorframe.setLocationRelativeTo(null);
                
                JPanel administratorpanel = new JPanel();
                administratorframe.add(administratorpanel);
                administratorpanel.setLayout(null);
                
                JLabel administratorlabel = new JLabel("Please Login");
                administratorlabel.setHorizontalAlignment(JLabel.CENTER);
                administratorframe.add(administratorlabel, BorderLayout.NORTH);

                JLabel nick = new JLabel("Username");
                nick.setBounds(50, 30, 100, 25);
                administratorpanel.add(nick);
                
                JTextField administratorid = new JTextField();
                administratorid.setBounds(130, 30, 200, 25);
                administratorpanel.add(administratorid);
                
                JLabel pass = new JLabel("Password");
                pass.setBounds(50, 70, 100, 25);
                administratorpanel.add(pass);
                
                JPasswordField administratorpass = new JPasswordField();
                administratorpass.setBounds(130, 70, 200, 25);
                administratorpanel.add(administratorpass);
                
                JButton administratorregister = new JButton("Sign Up");
                administratorregister.setBounds(140, 110,80,30);
                administratorpanel.add(administratorregister);
                administratorregister.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        JFrame mngregisterframe = new JFrame();
                        mngregisterframe.setSize(450, 450);
                        mngregisterframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mngregisterframe.setLocationRelativeTo(null);

                        JPanel mngregisterpanel = new JPanel();
                        mngregisterframe.add(mngregisterpanel);
                        mngregisterpanel.setLayout(null);
                        
                        JLabel registererror = new JLabel("Login error!");
                        registererror.setBounds(200, 150,100,30);
                        registererror.setVisible(false);
                        mngregisterpanel.add(registererror); 
                        
                        JLabel mngregisternick = new JLabel("Username");
                        mngregisternick.setBounds(50, 30, 100, 25);
                        mngregisterpanel.add(mngregisternick);
                        
                        JTextField registerid = new JTextField();
                        registerid.setBounds(130, 30, 200, 25);
                        mngregisterpanel.add(registerid);
                
                        JLabel mngregisterpass = new JLabel("Password");
                        mngregisterpass.setBounds(50, 70, 100, 25);
                        mngregisterpanel.add(mngregisterpass);
                        
                        JPasswordField registerpass = new JPasswordField();
                        registerpass.setBounds(130, 70, 200, 25);
                        mngregisterpanel.add(registerpass);
                        
                        JButton mngregister = new JButton("Approve");
                        mngregister.setBounds(170, 110,120,30);
                        mngregisterpanel.add(mngregister);
                        mngregister.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                
                                for(int i = 0;i<tool.managercounter;i++)
                                {
                                    if(administrator[i].id.equals(registerid.getText()))
                                        tool.managercheck = true;
                                        
                                }
                                
                                if(registerid.getText().isEmpty() || registerpass.getText().isEmpty())
                                   registererror.setVisible(true);
                                else if (tool.managercheck == true & tool.managercounter > 0)
                                    registererror.setVisible(true);
                                else
                                {
                                    administrator[tool.managercounter] = new manager(registerid.getText(),registerpass.getText()); 
                                    
                                    try {
                                        BufferedWriter bwr = new BufferedWriter(new FileWriter("[y]"+administrator[tool.managercounter].id +".txt"));
                                        bwr.write("Username: "+administrator[tool.managercounter].id);
                                        bwr.write("\nPassword: "+administrator[tool.managercounter].password);
                                        bwr.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    try {
                                        BufferedWriter bwrylist = new BufferedWriter(new FileWriter("managerler.txt"));
                                        bwrylist.write(administrator[tool.managercounter].id);
                                        bwrylist.newLine();
                                        bwrylist.close();
                                        
                                        administrator[tool.managercounter].written = true;
                                    } catch (IOException ex) {
                                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    
                                    tool.managercounter++;
                                    mngregisterframe.setVisible(false);
                                }
                            }
                        });
                        
                        JButton mngregistercancel = new JButton("Cancel");
                        mngregistercancel.setBounds(325, 350, 90, 30);
                        mngregisterpanel.add(mngregistercancel);
                        mngregistercancel.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                mngregisterframe.setVisible(false);
                            }
                        });
                        
                        mngregisterframe.setVisible(true);
                    }
                });
                
                JButton administratorlogin = new JButton("Login");
                administratorlogin.setBounds(240, 110,80,30);
                administratorpanel.add(administratorlogin);
                administratorlogin.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        JLabel loginerror = new JLabel("Login error!");
                        loginerror.setBounds(200, 150,100,30);
                        loginerror.setVisible(false);
                        administratorpanel.add(loginerror);
                        
                        File check = new File("[y]"+administratorid.getText()+".txt");
                        if(!(check.exists()))
                        {
                            loginerror.setVisible(true);
                        }
                        else if(check.exists())
                        {
                            for(int i = 0;i<tool.managercounter;i++)
                            {
                                if(administrator[i].id.equals(administratorid.getText()))
                                {
                                    tool.managersearch = i;
                                    tool.managerreadcheck = true;
                                }

                            }
                            
                            if(tool.managerreadcheck == false)
                            {
                                for(int i = 0;i<2;i++)
                                {
                                    if(administrator[i] == null)
                                    {
                                        BufferedReader br;
                                        try {
                                        br = new BufferedReader(new FileReader(check));
                                        br.skip(15);
                                        tool.managerscan[0] = br.readLine();
                                        br.skip(7);
                                        tool.managerscan[1] = br.readLine();

                                        br.close();
                                        } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                        administrator[i] = new manager(tool.managerscan[0],tool.managerscan[1]);

                                        if(!administrator[i].password.equals(administratorpass.getText()))
                                            loginerror.setVisible(true);
                                        else
                                            administrator[i].login = true;
                                        
                                        if(administrator[i].written == false)
                                        {
                                            BufferedWriter bwrylist;
                                            try {
                                                bwrylist = new BufferedWriter(new FileWriter("managers.txt",true));
                                                bwrylist.write(administrator[i].id);
                                                bwrylist.newLine();
                                                bwrylist.close();
                                                
                                                administrator[i].written = true;
                                            } catch (IOException ex) {
                                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                        
                                        tool.managersearch = i;
                                        tool.managercounter++;
                                        break;
                                    }
                                }
                                
                            }
                            else
                            {
                                if(!administrator[tool.managersearch].password.equals(administratorpass.getText()))
                                    loginerror.setVisible(true);
                                else
                                    administrator[tool.managersearch].login = true;
                            }
                            
                        }
                        
                        
                        if(administrator[tool.managersearch].login() == true)
                        {

                            administratorframe.setVisible(false);
                            
                            JFrame mngcontrolframe = new JFrame();
                            mngcontrolframe.setSize(450, 450);
                            mngcontrolframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            mngcontrolframe.setLocationRelativeTo(null);

                            JPanel mngcontrolpanel = new JPanel();
                            mngcontrolframe.add(mngcontrolpanel);
                            mngcontrolpanel.setLayout(null);

                            JLabel mnggreetlabel = new JLabel("Welcome Mgr. " + administratorid.getText());
                            mnggreetlabel.setHorizontalAlignment(JLabel.CENTER);
                            mngcontrolframe.add(mnggreetlabel, BorderLayout.NORTH);
                            
                            JButton mngchangepassword = new JButton("Change Password");
                            mngchangepassword.setBounds(30, 25,130, 30);
                            mngcontrolpanel.add(mngchangepassword);
                            mngchangepassword.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    JFrame mngpasswordframe = new JFrame();
                                    mngpasswordframe.setSize(450, 450);
                                    mngpasswordframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    mngpasswordframe.setLocationRelativeTo(null);
                                    
                                    JPanel mngpasswordpanel = new JPanel();
                                    mngpasswordframe.add(mngpasswordpanel);
                                    mngpasswordpanel.setLayout(null);
                            
                                    JLabel mngpasswordlabel = new JLabel("New password ");
                                    mngpasswordlabel.setHorizontalAlignment(JLabel.CENTER);
                                    mngpasswordframe.add(mngpasswordlabel, BorderLayout.NORTH);
                                    
                                    JPasswordField mngpasswordtxt = new JPasswordField();
                                    mngpasswordtxt.setBounds(120, 30, 200, 25);
                                    mngpasswordpanel.add(mngpasswordtxt);
                                    
                                    JButton mngpassword = new JButton("Change");
                                    mngpassword.setBounds(170, 60, 100, 30);
                                    mngpasswordpanel.add(mngpassword);
                                    mngpassword.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            administrator[tool.employeesearch].changepassword(mngpasswordtxt.getText());
                                            
                                            BufferedWriter bwr;
                                            try {
                                            bwr = new BufferedWriter(new FileWriter("[y]"+administrator[tool.managersearch].id +".txt"));
                                            bwr.write("Username: "+administrator[tool.managersearch].id);
                                            bwr.write("\nPassword: "+administrator[tool.managersearch].password);
                                            bwr.close();
                                            } catch (IOException ex) {
                                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        
                                            mngpasswordframe.setVisible(false);
                                        }
                                    });
                                    
                                    JButton passwordquit = new JButton("Cancel");
                                    passwordquit.setBounds(325, 350, 90, 30);
                                    mngpasswordpanel.add(passwordquit);
                                    passwordquit.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            mngpasswordframe.setVisible(false);
                                        }
                                    });
                                    
                                    mngpasswordframe.setVisible(true);
                                }
                            });
                            
                            
                            JButton loanapprove = new JButton("Approve Loan");
                            loanapprove.setBounds(275, 25,130, 30);
                            mngcontrolpanel.add(loanapprove);
                            loanapprove.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    
                                    JFrame loanapproveframe = new JFrame();
                                    loanapproveframe.setSize(450, 450);
                                    loanapproveframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    loanapproveframe.setLocationRelativeTo(null);

                                    JPanel loanapprovepanel = new JPanel();
                                    loanapproveframe.add(loanapprovepanel);
                                    loanapprovepanel.setLayout(null);
                                    
                                    JLabel demandloanlabel = new JLabel("Requests:");
                                    demandloanlabel.setHorizontalAlignment(JLabel.CENTER);
                                    loanapproveframe.add(demandloanlabel, BorderLayout.NORTH);
                                    demandloanlabel.setVisible(true);
                                    
                                    for(int i = 0;i<tool.customercounter;i++)
                                    {
                                        if(customers[i].demandloan == true)
                                        {
                                            demandloanlabel.setText(demandloanlabel.getText()+" "+customers[i].id);
                                        }
                                    }
                                    
                                    JLabel loanlabel = new JLabel("ID to be approved");
                                    loanlabel.setBounds(35, 30, 95, 25);
                                    loanapprovepanel.add(loanlabel);
                                    
                                    JTextField loancandidate = new JTextField();
                                    loancandidate.setBounds(130, 30, 200, 25);
                                    loanapprovepanel.add(loancandidate);
                                    
                                    JButton loanapprovebutton = new JButton("Approve Demand");
                                    loanapprovebutton.setBounds(175, 70, 110, 25);
                                    loanapprovepanel.add(loanapprovebutton);
                                    loanapprovebutton.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            
                                            for(int i = 0;i<tool.customercounter;i++)
                                            {
                                                if(customers[i].demandloan == true)
                                                {
                                                    if(loancandidate.getText().equals(customers[i].id))
                                                    {
                                                        customers[i].loanapprove = true;
                                                        customers[i].balance += customers[i].loanamount;
                                                        customers[i].demandloan = false;
                                                        loanapproveframe.setVisible(false);
                                                    }
                                                }
                                                
                                            }
                                            
                                        }
                                    });
                                    
                                    JButton loanapprovecancel = new JButton("Cancel");
                                    loanapprovecancel.setBounds(325, 350, 90, 30);
                                    loanapproveframe.add(loanapprovecancel);
                                    loanapprovecancel.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                            loanapproveframe.setVisible(false);
                                        }
                                    });
                                    
                                    loanapproveframe.setVisible(true);
                                }
                            });
                            
                            JButton administratorquit = new JButton("Log Out");
                            administratorquit.setBounds(325, 350, 90, 30);
                            mngcontrolpanel.add(administratorquit);
                            administratorquit.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                    mngcontrolframe.setVisible(false);
                                    frame.setVisible(true);
                                }
                            });
                            
                            mngcontrolframe.setVisible(true);
                        }
                            
                    }
                });
                
                administratorframe.setVisible(true);
            }
        });
        
        panel.add(customerbutton);
        panel.add(employeebuton);
        panel.add(administratorbuton);
        
        frame.setVisible(true);
    }

}