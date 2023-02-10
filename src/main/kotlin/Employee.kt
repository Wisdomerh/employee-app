package ie.setu.models

import ie.setu.roundTwoDecimals
import java.util.function.UnaryOperator

class Employee(var firstName: String, var surname: String, var gender: Char, var employeeID: Int,
               var grossSalary: Double, var payePercentage: Double, var prsiPercentage: Double,
               var annualBonus: Double, var cycleToWorkMonthlyDeduction: Double) {

    fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. ${firstName} ${surname}"
        'f', 'F' -> "Ms.  ${firstName} ${surname}"
        else -> "${firstName} ${surname}"
    }

    fun getMonthlySalary() = roundTwoDecimals(grossSalary / 12)
    fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (prsiPercentage / 100))
    fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (payePercentage / 100))
    fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (annualBonus / 12))
    fun getTotalMonthlyDeductions() =
        roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + cycleToWorkMonthlyDeduction))

    fun getNetMonthlyPay() = roundTwoDecimals(roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions()))

    fun getPayslip() =
        """
        __________________________________________________________________________
         Monthly Payslip:               ${getFullName()}            ID: ${employeeID}                 
        __________________________________________________________________________    
                                      PAYMENT DETAILS:
                                     Gross Pay: ${getGrossMonthlyPay()}                                                                   
        __________________________________________________________________________
                                      Salary: ${getMonthlySalary()}
                                       Bonus:  ${roundTwoDecimals(annualBonus / 12)}            
        __________________________________________________________________________
                                     DEDUCTION DETAILS:
                                   Total Deductions: ${getTotalMonthlyDeductions()}     
        __________________________________________________________________________
                                       PAYE: ${getMonthlyPAYE()}                
                                       PRSI: ${getMonthlyPRSI()}  
                                    Cycle To Work: ${cycleToWorkMonthlyDeduction}         
        __________________________________________________________________________
                                     NET PAY: ${getNetMonthlyPay()} 
        __________________________________________________________________________"""

    override fun toString(): String {
        return """
             Employee
             ______________________________________________________________
                |   Name: '${getFullName()}'               ID: $employeeID
                |   Gender: $gender                             
                |   Gross Salary: $grossSalary
                |   Paye Percentage: $payePercentage           
                |   Prsi Percentage: $prsiPercentage
                |   Annual Bonus: $annualBonus                              
                |   Cycle To Work Monthly Deduction: $cycleToWorkMonthlyDeduction                     
             ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾  """
    }



}