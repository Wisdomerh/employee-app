//Wisdom Erhimwionsobo - SETU Waterford
import java.math.BigDecimal
import java.math.RoundingMode

val firstName: String = "Joe"
val surName: String = "Soap"
val gender: Char = 'm'
val employeeID: Int = 6143
val grossSalary: Double = 67_543.21
val payePercentage: Float = 38.5f
val prsiPercentage: Float = 5.2f
val annualBonus: Double = 1450.50
val cycleToWorkSchemeMonthly: Double = 54.33

fun getFullName() = when(gender) {
    'm' -> "Mr $firstName $surName"
    'f' -> "Ms $firstName $surName"
    else -> "$firstName $surName"
}

fun main(args: Array<String>) {
    println(getFullName())
    println("Paylist Printer")
    printPaySlip()

    println("\nPayslip with Rounding")
    printPaySlipRounding()

    // Call calculation functions

    println("Monthly Salary: ${getMonthlySalary()}")
    println("Monthly PAYE: ${getMonthlyPAYE()}")
    println("Monthly PRSI: ${getMonthlyPRSI()}")
    println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
    println("Monthly Deductions: ${getTotalMonthlyDeductions()}")
}

// Functions for calculations
fun getMonthlySalary() = grossSalary/12
fun getMonthlyPAYE() = (getMonthlySalary() * (payePercentage/100))
fun getMonthlyPRSI() = (getMonthlySalary() * (prsiPercentage/100))
fun getGrossMonthlyPay() = (getMonthlySalary() + (annualBonus /12))
fun getTotalMonthlyDeductions() = (getMonthlyPRSI() + getMonthlyPAYE() + cycleToWorkSchemeMonthly)

// Function to format decimal places to 2.
fun formatted(doubleToFormat: Double): BigDecimal? {
    return BigDecimal(doubleToFormat).setScale(2, RoundingMode.HALF_EVEN)
}
fun printPaySlip(): Unit {
    println(
        """
        +____________________________________________________________________+
         Monthly Payslip:             ${getFullName().uppercase()} (${gender.uppercase()}), ID: $employeeID                  
        +____________________________________________________________________+    
              PAYMENT DETAILS (gross pay: ${getGrossMonthlyPay()})                                                                    
        +____________________________________________________________________+
                   Salary: ${grossSalary/12}
                   Bonus:  ${annualBonus/12}             
        +____________________________________________________________________+
              DEDUCTION DETAILS (total Deductions: ${getTotalMonthlyDeductions()})      
        +____________________________________________________________________+
                   PAYE: ${getMonthlyPAYE()}               
                   PRSI: ${getMonthlyPRSI()} 
                   Cycle To Work: $cycleToWorkSchemeMonthly        
        +____________________________________________________________________+
             NET PAY: ${(getGrossMonthlyPay() - ((getMonthlySalary() * (prsiPercentage / 100)) + cycleToWorkSchemeMonthly))}
        +____________________________________________________________________+"""
    )
}

fun printPaySlipRounding(): Unit {
    println("------------------------------------------------------------------------------")
    println("|                              Monthly Payslip                               |")
    println("|----------------------------------------------------------------------------|")
    println("|                                                                            |")
    println("|   Employee Name: ${getFullName().uppercase()} (${gender.uppercase()})                Employee ID: $employeeID             |")
    println("|                                                                            |")
    println("|----------------------------------------------------------------------------|")
    println("|                                                                            |")
    println("|   PAYMENT DETAILS                            DEDUCTION DETAILS             |")
    println("|                                                                            |")
    println("|----------------------------------------------------------------------------|")
    println("|   Salary: ${formatted(grossSalary/12)}                            PAYE: ${formatted(getMonthlyPAYE())}                 |")
    println("|   Bonus: ${formatted(annualBonus/12)}                              PRSI: ${formatted(getMonthlyPRSI())}                  |")
    println("|                                              Cycle To Work: ${formatted(cycleToWorkSchemeMonthly)}          |")
    println("|----------------------------------------------------------------------------|")
    println("|   Gross: ${formatted(getGrossMonthlyPay())}             Total Deductions: ${formatted(getTotalMonthlyDeductions())}                     |")
    println("|----------------------------------------------------------------------------|")
    println("|                          NET PAY:    ${formatted((getGrossMonthlyPay() - ((getMonthlySalary() * (prsiPercentage / 100)) + cycleToWorkSchemeMonthly)))}                               |")
    println("|----------------------------------------------------------------------------|")
}