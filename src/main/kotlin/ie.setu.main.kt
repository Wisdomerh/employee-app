package ie.setu

import ie.setu.controllers.EmployeeAPI
import ie.setu.controllers.getId
import ie.setu.controllers.lastId
import ie.setu.models.Employee



import kotlin.math.round


var employees = EmployeeAPI()


fun main(args: Array<String>){

    start()
}

fun menu() : Int {
    print(""" 
         |Employee Menu
         |   1. Add Employee
         |   2. List All Employees
         |   3. Search Employees 
         |   4. Print Payslip for Employee
         |   5. Update Employee details
         |   6. Remove Employee by ID
         |  -1. Exit
         |       
         |Enter Option : """.trimMargin())
    return readLine()!!.toInt()
}

fun start() {
    var input: Int
    dummyData()
    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> list()
            3 -> search()
            4 -> paySlip()
            5 -> update()
            6 -> delete()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employees.create(Employee(firstName, surname, gender, 0, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction))
}

fun list(){
    employees.findAll()
        .forEach{ println(it) }
}


fun search() {
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println(employee)
}

fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getPayslip())
}

fun delete(){
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        employees.delete(employee)
    println("Employee has been removed from the system")

}

fun update(){

    val employee = getEmployeeById()
    if (employee != null)
        print("Update first name: " + (employee?.firstName)+ " : ")
    val firstName = readLine().toString()
    print("Update surname: " + (employee?.surname)+ " : ")
    val surname = readLine().toString()
    print("Update gender (m/f): " + (employee?.gender)+ " : " )
    val gender = readLine()!!.toCharArray()[0]
    print("Update gross salary: " + (employee?.grossSalary)+ " : ")
    val grossSalary = readLine()!!.toDouble()
    print("Update PAYE %: " + (employee?.payePercentage)+ " : ")
    val payePercentage = readLine()!!.toDouble()
    print("Update PRSI %: " + (employee?.prsiPercentage)+ " : ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Update Annual Bonus: " + employee?.annualBonus + " : ")
    val annualBonus= readLine()!!.toDouble()
    print("Update Cycle to Work Deduction: " + (employee?.cycleToWorkMonthlyDeduction)+ " : ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()
    if (employee != null) {
        employees.update(Employee(firstName, surname, gender, employee.employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction))
    }

}

internal fun getEmployeeById(): Employee? {
    print("Please Enter the Employee ID: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

internal fun dummyData() {
    employees.create(Employee("Joe", "Soap", 'm', 0, 35655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Joan", "Murphy", 'f', 1, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("Mary", "Quinn", 'f', 2, 75685.41, 40.0, 8.5, 4500.0, 0.0))
}


fun roundTwoDecimals(number: Double) = round(number * 100) / 100






