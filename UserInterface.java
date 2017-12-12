package taxCalculator;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.text.*;
@SuppressWarnings("serial")
public class UserInterface extends JPanel
	                                   implements PropertyChangeListener,
	                                    ActionListener{
	    //Values for the fields
	    static double income = 0;
	    static double capitalGains = 0;
	    static double mortgageValue = 0;
	    static double charitableGivings = 0;
	    static double medicalExpenses = 0;
	    static int age = 0;
	    static int spouseAge = 0;
	    static int children = 0;
	    static int childrenInCollege = 0;
	    static boolean americanCitizen;
	    static boolean employed;
	    static boolean blind;
	    static boolean married;
	    static boolean spouseBlind;
	    static String state = "";
	    
	    

	    //Labels to identify the fields
	    private JLabel incomeLabel;
	    private JLabel capitalGainsLabel;
	    private JLabel ageLabel;
	    private JLabel taxLabel;
	    private JLabel mortgageValueLabel;
	    private JLabel stateLabel;
	    
	    
	    //Strings for the labels
	    private static String incomeString = "Income ($) ";
	    private static String capitalGainsString = "Capital Gain ($): ";
	    private static String ageString = "Age (years) ";
	    private static String taxString = "Taxes($); ";
	    private static String mortgageValueString = "Morgage Value($): ";
	    private static String citizenString = "American Citizen";
	    private static String nonCitizenString = "Non-American Citizen";
	    private static String employedString = "Employed";
	    private static String unemployedString = "Unemployed";
	    private static String blindString = "Blind";
	    private static String notBlindString = "Not Blind";
	    private static String marriedString = "Married";
	    private static String notMarriedString = "Single";
	    private static String spouseBlindString = "Blind Spouse";
	    private static String spouseNotBlindString = "Not Blind Spouse";
	    private static String stateString = "State";
	    
	    //Fields for data entry
	    private JFormattedTextField incomeField;
	    private JFormattedTextField capitalGainsField;
	    private JFormattedTextField ageField;
	    private JFormattedTextField taxField;
	    private JFormattedTextField mortgageValueField;

	    // Charitable giving intialization
	    private JLabel charitableGivingsLabel;
	    private static String charitableGivingsString = "Charitable Givings($): ";
	    private JFormattedTextField charitableGivingsField;
	    
	    // Medical Expenses Initialization
	    private JLabel medicalExpensesLabel;
	    private static String medicalExpensesString = "Medical Expenses($): ";
	    private JFormattedTextField medicalExpensesField;
	    
	    private JLabel spouseAgeLabel;
	    private static String spouseAgeString = "Spouse Age (years): ";
	    private JFormattedTextField spouseAgeField;
	    
	    private JLabel childrenLabel;
	    private static String childrenString = "Children (humans): ";
	    private JFormattedTextField childrenField;
	    
	    private JLabel childrenInCollegeLabel;
	    private static String childrenInCollegeString = "Children in College(humans): ";
	    private JFormattedTextField childrenInCollegeField;
	    
	    //Formats to format and parse numbers
	    private NumberFormat incomeFormat;
	    private NumberFormat moneyFormat;
	    private NumberFormat taxFormat;

	    public UserInterface() {
	        super(new BorderLayout());
	        setUpFormats();
	        double tax = computetax();

	        String[] stateArray = {"", "Alabama","Alaska","Arizona","Arkansas","California",
	        		"Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii",
	        		"Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana",
	        		"Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi",
	        		"Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey",
	        		"New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma",
	        		"Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota",
	        		"Tennessee","Texas","Utah","Vermont","Virginia","Washington",
	        		"West Virginia","Wisconsin","Wyoming"};
	        @SuppressWarnings({ "rawtypes", "unchecked" })
	    	final JComboBox combo1 = new JComboBox(stateArray);
	        combo1.addActionListener(this);
	        
	        
	        //Create the labels.
	        incomeLabel = new JLabel(incomeString);
	        capitalGainsLabel = new JLabel(capitalGainsString);
	        ageLabel = new JLabel(ageString);
	        charitableGivingsLabel = new JLabel(charitableGivingsString);
	        medicalExpensesLabel = new JLabel(medicalExpensesString);
	        
	        spouseAgeLabel = new JLabel(spouseAgeString);
	        childrenLabel = new JLabel(childrenString);
	        childrenInCollegeLabel = new JLabel(childrenInCollegeString);
	        mortgageValueLabel = new JLabel(mortgageValueString);

	        stateLabel = new JLabel(stateString);
	        
	        taxLabel = new JLabel(taxString);
	        
	        // Create the Buttons 
	        
	        JRadioButton citizenButton = new JRadioButton(citizenString);
	        citizenButton.setActionCommand(citizenString);
	        JRadioButton nonCitizenButton = new JRadioButton(nonCitizenString);
	        nonCitizenButton.setActionCommand(nonCitizenString);
	        // Groups buttons
	        ButtonGroup citizen = new ButtonGroup();
	        citizen.add(nonCitizenButton);
	        citizen.add(citizenButton);
	        //Activate listener
	        citizenButton.addActionListener(this);
	        nonCitizenButton.addActionListener(this);
	        
	        
	        JRadioButton employedButton = new JRadioButton(employedString);
	        employedButton.setActionCommand(employedString);
	        JRadioButton unemployedButton = new JRadioButton(unemployedString);
	        unemployedButton.setActionCommand(unemployedString);
	        
	        ButtonGroup employed = new ButtonGroup();
	        employed.add(unemployedButton);
	        employed.add(employedButton);
	        
	        employedButton.addActionListener(this);
	        unemployedButton.addActionListener(this);
	        
	        JRadioButton blindButton = new JRadioButton(blindString);
	        blindButton.setActionCommand(blindString);
	        JRadioButton notBlindButton = new JRadioButton(notBlindString);
	        notBlindButton.setActionCommand(notBlindString);
	        
	        ButtonGroup blind = new ButtonGroup();
	        blind.add(notBlindButton);
	        blind.add(blindButton);
	        
	        blindButton.addActionListener(this);
	        notBlindButton.addActionListener(this);
	        
	        JRadioButton marriedButton = new JRadioButton(marriedString);
	        marriedButton.setActionCommand(marriedString);
	        JRadioButton notMarriedButton = new JRadioButton(notMarriedString);
	        notMarriedButton.setActionCommand(notMarriedString);
	        
	        ButtonGroup married = new ButtonGroup();
	        married.add(notMarriedButton);
	        married.add(marriedButton);
	        
	        marriedButton.addActionListener(this);
	        notMarriedButton.addActionListener(this);
	        
	        JRadioButton spouseBlindButton = new JRadioButton(spouseBlindString);
	        spouseBlindButton.setActionCommand(spouseBlindString);
	        JRadioButton spouseNotBlindButton = new JRadioButton(spouseNotBlindString);
	        spouseNotBlindButton.setActionCommand(spouseNotBlindString);
	        
	        ButtonGroup spouseBlind = new ButtonGroup();
	        spouseBlind.add(spouseNotBlindButton);
	        spouseBlind.add(spouseBlindButton);
	        
	        spouseBlindButton.addActionListener(this);
	        spouseNotBlindButton.addActionListener(this);
	        
	        //Create the text fields and set them up.
	        incomeField = new JFormattedTextField(moneyFormat);
	        incomeField.setValue(new Double(income));
	        incomeField.setColumns(10);
	        incomeField.addPropertyChangeListener("value", this);

	        charitableGivingsField = new JFormattedTextField(moneyFormat);
	        charitableGivingsField.setValue(new Double(charitableGivings));
	        charitableGivingsField.setColumns(10);
	        charitableGivingsField.addPropertyChangeListener("value", this);
	        
	        capitalGainsField = new JFormattedTextField(moneyFormat);
	        capitalGainsField.setValue(new Double(capitalGains));
	        capitalGainsField.setColumns(10);
	        capitalGainsField.addPropertyChangeListener("value", this);

	        mortgageValueField = new JFormattedTextField(moneyFormat);
	        mortgageValueField.setValue(new Double(mortgageValue));
	        mortgageValueField.setColumns(10);
	        mortgageValueField.addPropertyChangeListener("value", this);
	        
	        medicalExpensesField = new JFormattedTextField(moneyFormat);
	        medicalExpensesField.setValue(new Double(medicalExpenses));
	        medicalExpensesField.setColumns(10);
	        medicalExpensesField.addPropertyChangeListener("value", this);
	        
	        spouseAgeField = new JFormattedTextField();
	        spouseAgeField.setValue(new Integer(spouseAge));
	        spouseAgeField.setColumns(10);
	        spouseAgeField.addPropertyChangeListener("value", this);
	        
	        ageField = new JFormattedTextField();
	        ageField.setValue(new Integer(age));
	        ageField.setColumns(10);
	        ageField.addPropertyChangeListener("value", this);
	        
	        childrenField = new JFormattedTextField();
	        childrenField.setValue(new Integer(children));
	        childrenField.setColumns(10);
	        childrenField.addPropertyChangeListener("value", this);
	        
	        childrenInCollegeField = new JFormattedTextField();
	        childrenInCollegeField.setValue(new Integer(childrenInCollege));
	        childrenInCollegeField.setColumns(10);
	        childrenInCollegeField.addPropertyChangeListener("value", this);


	        taxField = new JFormattedTextField(taxFormat);
	        taxField.setValue(new Double(tax));
	        taxField.setColumns(10);
	        taxField.setEditable(false);
	        taxField.setForeground(Color.red);

	        //Tell accessibility tools about label/textfield pairs.
	        incomeLabel.setLabelFor(incomeField);
	        capitalGainsLabel.setLabelFor(capitalGainsField);
	        mortgageValueLabel.setLabelFor(mortgageValueField);
	        charitableGivingsLabel.setLabelFor(charitableGivingsField);
	        medicalExpensesLabel.setLabelFor(medicalExpensesField);
	        
	        ageLabel.setLabelFor(ageField);
	        spouseAgeLabel.setLabelFor(spouseAgeField);
	        childrenLabel.setLabelFor(childrenField);
	        childrenInCollegeLabel.setLabelFor(childrenInCollegeField);
	        
	        taxLabel.setLabelFor(taxField);

	        //Lay out the labels in a panel.
	        JPanel labelPane = new JPanel(new GridLayout(0,1));
	        labelPane.add(stateLabel);
	        labelPane.add(incomeLabel);
	        labelPane.add(capitalGainsLabel);
	        labelPane.add(charitableGivingsLabel);
	        labelPane.add(mortgageValueLabel);
	        labelPane.add(medicalExpensesLabel);
	        
	        labelPane.add(ageLabel);
	        labelPane.add(childrenLabel);
	        labelPane.add(spouseAgeLabel);
	        labelPane.add(childrenInCollegeLabel);
	        
	        labelPane.add(spouseBlindButton);
	        labelPane.add(marriedButton);
	        labelPane.add(blindButton);
	        labelPane.add(citizenButton);
	        labelPane.add(employedButton);
	        
	        labelPane.add(taxLabel);

	        //Layout the text fields in a panel.
	        JPanel fieldPane = new JPanel(new GridLayout(0,1));
	        fieldPane.add(combo1);
	        fieldPane.add(incomeField);
	        fieldPane.add(capitalGainsField);
	        fieldPane.add(charitableGivingsField);
	        fieldPane.add(mortgageValueField);
	        fieldPane.add(medicalExpensesField);

	        fieldPane.add(ageField);
	        fieldPane.add(childrenField);
	        fieldPane.add(spouseAgeField);
	        fieldPane.add(childrenInCollegeField);
	        
	        fieldPane.add(spouseNotBlindButton);
	        fieldPane.add(notMarriedButton);
	        fieldPane.add(notBlindButton);
	        fieldPane.add(nonCitizenButton);
	        fieldPane.add(unemployedButton);
	        
	        
	        
	        fieldPane.add(taxField);

	        //Put the panels in this panel, labels on left,
	        //text fields on right.
	        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	        add(labelPane, BorderLayout.CENTER);
	        add(fieldPane, BorderLayout.LINE_END);
	    }
	  
	    public void actionPerformed(ActionEvent e) {
	    	if (e.getSource() instanceof JComboBox) {
	    		JComboBox cbSource = (JComboBox)e.getSource();
	        	state = (String)cbSource.getSelectedItem();
	    	} else {
		    	Object source = e.getActionCommand();
		    	if (source == citizenString) {
		            americanCitizen = true;
		        } else if (source == nonCitizenString) { 
		            americanCitizen = false;
		        } else if (source == blindString) { 
		            blind = true;
		        } else if (source == notBlindString) { 
		            blind = false;
		        } else if (source == marriedString) { 
		            married = true;
		        } else if (source == notMarriedString) {
		        	married = false;
		        } else if (source == spouseBlindString) {
		        	spouseBlind = true;
		        } else if (source == spouseNotBlindString) {
		        	spouseBlind = false;
		        }
	    	}
	    }
	    public void propertyChange(PropertyChangeEvent e) {
	        Object source = e.getSource();
	        // changes field value //// add new elseif for new fields
	        if (source == incomeField) { // Doubles
	            income = ((Number)incomeField.getValue()).doubleValue();
	        } else if (source == capitalGainsField) {
	        	capitalGains = ((Number)capitalGainsField.getValue()).doubleValue();
	        } else if (source == medicalExpensesField) {
	            medicalExpenses = ((Number)medicalExpensesField.getValue()).doubleValue();
	        } else if (source == mortgageValueField) {
	            mortgageValue = ((Number)mortgageValueField.getValue()).doubleValue();
	        } else if (source == charitableGivingsField) {
	            charitableGivings = ((Number)charitableGivingsField.getValue()).doubleValue();
	        } else if (source == ageField) {  // Ints
	            age = ((Number)ageField.getValue()).intValue();
	        } else if (source == spouseAgeField) {
	            spouseAge = ((Number)spouseAgeField.getValue()).intValue();
	        } else if (source == childrenField) {
	            children = ((Number)childrenField.getValue()).intValue();
	        } else if (source == childrenInCollegeField) {
	            childrenInCollege = ((Number)childrenInCollegeField.getValue()).intValue();
	        } 
	        double tax = computetax();
	        taxField.setValue(new Double(tax));
	    }
	 // Create and display gui
	    
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("TaxCalc");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Add contents to the window.
	        frame.add(new UserInterface());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        frame.setSize(400, 600);
	    }

	    public static void startGUI() {
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                //Turn off metal's use of bold fonts
		        UIManager.put("swing.boldMetal", Boolean.FALSE);
	                createAndShowGUI();
	            }
	        });
	    }

	    double computetax() {
	        return TaxCalculator.taxBurdenCalculator();
	    }

	    //Create and set up number formats. These objects also
	    //parse numbers input by user.
	    private void setUpFormats() {
	        incomeFormat = NumberFormat.getNumberInstance();

	        moneyFormat = NumberFormat.getNumberInstance();
	        moneyFormat.setMaximumFractionDigits(2);
	        moneyFormat.setMinimumFractionDigits(2);
	        taxFormat = NumberFormat.getCurrencyInstance();
	    }
	}
