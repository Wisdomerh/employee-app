
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

fun main(args: Array<String>){

    var input : Int

    do {
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPRSI()}")
            3 ->println("Monthly PAYE: ${getMonthlyPAYE()}")
            4 -> println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalMonthlyDeductions()}")
            6 -> println("Monthly Net Pay: ${getMonthlySalary()}")
            7 -> println(printPaySlipRounding())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}


fun menu() : Int {
    print("""
         Employee Menu for ${getFullName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          -1. Exit
         Enter Option : """)
    return readLine()!!.toInt()
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
    println(
        """
        +____________________________________________________________________+
         Monthly Payslip:             ${getFullName().uppercase()} (${gender.uppercase()}), ID: $employeeID                  
        +____________________________________________________________________+    
              PAYMENT DETAILS (gross pay: ${getGrossMonthlyPay()})                                                                    
        +____________________________________________________________________+
                   Salary: ${formatted(grossSalary/12)}
                   Bonus:  ${formatted(annualBonus/12)}             
        +____________________________________________________________________+
              DEDUCTION DETAILS (total Deductions: ${formatted(getTotalMonthlyDeductions())})      
        +____________________________________________________________________+
                   PAYE: ${formatted(getMonthlyPAYE())}               
                   PRSI: ${formatted(getMonthlyPRSI())} 
                   Cycle To Work: $cycleToWorkSchemeMonthly        
        +____________________________________________________________________+
             NET PAY: ${formatted((getGrossMonthlyPay() - ((getMonthlySalary() * (prsiPercentage / 100)) + cycleToWorkSchemeMonthly)))}
        +____________________________________________________________________+"""
    )
}