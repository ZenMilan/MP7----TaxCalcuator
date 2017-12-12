package taxCalculator;

/** This class is a dynamic tax burden calculator.
 * <p>
 * Taxes have been a hot-button issue in the news recently, and we though that this program
 * would assist individuals in a) better understanding the United States tax code as it 
 * currently stands, and b) showing them the impact of major life decisions on their tax burden.
 * How much will buying a new house change their tax burden? How much will getting married alter
 * their tax burden? How much will having a child alter their tax burden? With this tool, it is 
 * possible, and indeed easy, for users to find the answers to these questions. 
 * <p>
 * For the sake of our sanity, this program only includes credits and deductions that typical
 * Americans would conceivably use, including the mortgage interest deduction, the standard deduction,
 * the charitable giving deduction, the state and local tax deduction, the medical expenses deduction,
 * the earned income tax credit, the child tax credit, and the American Opportunity Tax Credit. Now,
 * there are hundreds of other tax deductions available to Americans in very specialized circumstances.
 * However, since most people will never use them, and we want to keep the program usable, we decided
 * not to include niche and outlandish tax clauses. Nevertheless, this program should provide
 * an accurate tax burden for a filer. 
 * <p>
 * @author Michael Kokkines and Harry Chen
 *
 */
public class TaxCalculator {	
	/**Taxpayers have the option of either accepting the standard deduction or itemizing
	 * their deductions. The standard deduction begins at $13,000 for married couples and 
	 * $6,500 for individuals. Additionally, people who are blind, people whose spouses are
	 * blind, people whose are above 65 years old, and people whose spouses are more than 
	 * 65 years old earn a larger standard deduction. For each of the above characteristics
	 * that a taxpayer meets, their standard deduction will increase by either $1,100 or $1,400.
	 * @return the size of a given filer's standard deduction.
	 */
	public static double standardDeduction() {
		if (UserInterface.married && UserInterface.americanCitizen) {
			int additives = 0;
			if (UserInterface.blind) {
				additives += 1100;
			}
			if (UserInterface.spouseBlind) {
				additives += 1100;
			}
			if (UserInterface.age >= 65) {
				additives += 1100;
			}
			if (UserInterface.spouseAge >= 65) {
				additives += 1100;
			}
			
			return 13000 + additives;
			
		} else if (UserInterface.americanCitizen) {
			int additives = 0;
			
			if (UserInterface.blind) {
				additives += 1400;
			}
			
			
			if (UserInterface.age >= 65) {
				additives += 1400;
			}
			
			return 6500 + additives;
			
		} else {
			return 0;
		}
	}	
	
	
	/** Taxpayers have the option of either accepting the standard deduction or itemizing their
	 * deductions. The four most commonly used itemized deductions in the United States
	 * are the charitable giving deduction, the state/local tax deduction, the mortage interest
	 * deduction, and the medical expenses deduction. The medical expenses deduction only applies
	 * to costs in excess of 7.5% of a taxpayer's adjusted gross income (income minus his/her other
	 * deductions. 
	 * @return the size of a filer's itemized deductions. 
	 */
	public static double itemizedDeductions() {
		double firstThree = UserInterface.charitableGivings + UserInterface.mortgageValue + StateIncomeTax.stateTaxDeduction(UserInterface.state, UserInterface.income);
		double adjustedIncome = UserInterface.income - firstThree;
		double medicalExpenseDeduction = Math.max(UserInterface.medicalExpenses - (adjustedIncome * .075), 0);
		return firstThree + medicalExpenseDeduction;
		}
	
	/** Agi, or adjusted gross income, is a filer's income minus their deductions. Agi is used
	 * to determine the value of tax credits, as well as the tax liabilities owned by an individual. 
	 * Agi cannot be less than 0. 
	 * @return  A filer's adjusted gross income. 
	 */
	public static double agi() {
		if (itemizedDeductions() > standardDeduction()) {
			return Math.max(UserInterface.income - itemizedDeductions(), 0);
		} else {
			return Math.max(UserInterface.income - standardDeduction(), 0);
		} 
	}
	
	/**Taxpayers in the United States, regardless of whether they chose to standardize or 
	 * itemize their deductions, can exempt a certain amount of money from their taxes, decreasing 
	 * their taxable income. This amount is equivalent to the number of people in the family multiplied
	 * by $4150. Above a certain wealth threshold, filer's cannot claim the personal exemption.
	 * @return the size of an individual's personal exemption. 
	 */
	public static double personalExemption() {
		if (agi() > 462000 || (agi() > 380750 && !UserInterface.married)) {
			return 0;
		} else if (UserInterface.married) {
			return (2 + UserInterface.children + UserInterface.childrenInCollege) * 4150;
		} else {
			return (1 + UserInterface.children + UserInterface.childrenInCollege) * 4150;
		}
	}
	
	
	/** Taxpayers who earn income below certain thresholds  are elgible for certain tax credits --
	 * ie. money that is given to them by the government. With tax credits, it is possible that
	 * taxpayers have a negative tax burden, in which case the government owes them money. The
	 * three most common tax credits are the earned income tax credit (eitc -- for employed individuals 
	 * who need to raise children), the child tax credit (for less wealthy families with children),
	 * and the American Opportunity Tax Credit (aotc -- for families whose children are in college).
	 * Their values are calculated below and summed up to determine a filer's total credits. 
	 **/
	public static double credits() {
		double eitc;
		double childCredit;
		double aotc;
		double agi = agi();
		
		if (!UserInterface.employed || !UserInterface.americanCitizen || UserInterface.capitalGains > 3450) {
			eitc = 0;
		} else if (UserInterface.children + UserInterface.childrenInCollege == 0){
			if (UserInterface.age > 65 || UserInterface.age < 25 || agi > 20600 ||(!UserInterface.married && agi > 15010)) {
				eitc = 0;
			} else {
				eitc = 487;
			} 
		} else if (UserInterface.children + UserInterface.childrenInCollege == 1) {
				if (agi > 45207 || (!UserInterface.married && agi > 39617)) {
					eitc = 0;
				} else {
					eitc = 3250;
				} 
		} else if (UserInterface.children + UserInterface.childrenInCollege == 2) {
			if (agi > 50597 || (!UserInterface.married && agi > 45007)) {
				eitc = 0;
				} else {
					eitc = 5372;
			}
		} else {
			if (agi > 53930 || (!UserInterface.married && agi > 48340)) {
				eitc = 0;
			} else {
				eitc = 6044;
			}
		}
		
		
		
		if (agi < 110000 && UserInterface.married) {
			childCredit = UserInterface.children * 1000;
		} else if (agi < 75000 && !UserInterface.married) {
			childCredit = UserInterface.children * 1000;
		} else {
			childCredit = 0;
		}
		
		
		
		if ((!UserInterface.married && agi > 80000) || (UserInterface.married && agi > 180000)) {
			aotc = 0;
		} else {
			aotc = 2500 * UserInterface.childrenInCollege;
		}
		
		
		return aotc + eitc + childCredit;
		
	}
	
	/** This method calculate a filer's total tax burden. It begins by calculating their taxable 
	 * income by determining their taxable income (their income, minus their deductions, minus the
	 * personal exemption. It then calculates the total income tax a filer owes based on the 
	 * secen tax bracket system in the United States. It then determines their total capital gains
	 * tax burden through the three bracket system. Lastly, this method subtracts the amount a filer
	 * is owed in credits, and returns their total tax burden. 
	 * @return A filer's total tax burden. If negative, the government owes money to the filer. 
	 */
	public static double taxBurdenCalculator() {
		double taxableIncome = Math.max(agi() - personalExemption(), 0);
		double incomeTaxBurden;
		double capitalGainsTaxBurden;
		
		if (UserInterface.married) {
			if (taxableIncome > 470000) {
				incomeTaxBurden = 131628 + (.396 * (taxableIncome - 470000)); 
			} else if (taxableIncome > 416700) {
				incomeTaxBurden = 112728 + (.35 * (taxableIncome - 416700));
			} else if (taxableIncome > 233350) {
				incomeTaxBurden = 52222.50 + (.33 * (taxableIncome - 233350)); 
			} else if (taxableIncome > 153100) {
				incomeTaxBurden = 29752.50 + (.28 * (taxableIncome - 153100)); 
			} else if (taxableIncome > 75900) {
				incomeTaxBurden = 10542.50 + (.25 * (taxableIncome - 75900));
			} else if (taxableIncome > 18650) {
				incomeTaxBurden = 1865 + (.15 * (taxableIncome - 18650));
			} else {
				incomeTaxBurden = .1 * taxableIncome;
			}
				
		} else {
			if (taxableIncome > 418400) {
				incomeTaxBurden = 121505.25 + (.396 * (taxableIncome - 418400));
			} else if (taxableIncome > 416700) {
				incomeTaxBurden = 120910.25 + (.35 * (taxableIncome - 416700));
			} else if (taxableIncome > 191650) {
				incomeTaxBurden = 46643.75 + (.33 * (taxableIncome - 191650));
			} else if (taxableIncome > 91900) {
				incomeTaxBurden = 18713.75 + (.28 * (taxableIncome - 91900));
			} else if (taxableIncome > 37950) {
				incomeTaxBurden = 5226.25 + (.25 * (taxableIncome - 37950));
			} else if (taxableIncome > 9325) {
				incomeTaxBurden = 932.5 + (.15 * (taxableIncome - 9325));
			} else {
				incomeTaxBurden = taxableIncome * .1;
			}
		}
		
		if (UserInterface.married) {
			if (UserInterface.capitalGains > 470000) {
				capitalGainsTaxBurden = 59115 + (.20 * (UserInterface.capitalGains - 470000));  
			} else if (UserInterface.capitalGains > 75900) {
				capitalGainsTaxBurden = (.20 * (UserInterface.capitalGains - 75900));
			} else {
				capitalGainsTaxBurden = 0;
			}
		}
			
			else {
				if (UserInterface.capitalGains > 418400) {
					capitalGainsTaxBurden = 57067.5 + (.20 * (UserInterface.capitalGains - 418400));
				} else if (UserInterface.capitalGains > 37950) {
					capitalGainsTaxBurden = (.15 * (UserInterface.capitalGains - 37950));
				} else {
					capitalGainsTaxBurden = 0;
				}
			}
		
		return incomeTaxBurden + capitalGainsTaxBurden - credits();
		
	}
	

	
	
	public static void main(String[] args) {
		UserInterface.startGUI();
	}

}

