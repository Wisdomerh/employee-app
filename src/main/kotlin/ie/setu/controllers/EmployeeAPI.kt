package ie.setu.controllers


import ie.setu.models.Employee

var lastId = 0

internal fun getId(): Int {
    return lastId++
}


class EmployeeAPI {

    private val employees = ArrayList<Employee>()

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeID == id }
    }



    fun delete(employee: Employee){
        println(employee)
        print("Do you want to remove this Employee (y/n) : " )
        val yesOrNo = readln().toCharArray()[0]
        if (yesOrNo== 'n')
            println("You Have Cancelled the Request to Remove an Employee" )
        else if (yesOrNo== 'y') {
            print("Employee has been removed from the system")
            employees.remove(employee)
        }
    }

    fun update(employee: Employee){
        employees[employee.employeeID] = employee
    }

    fun create(employee: Employee) {
        employee.employeeID = getId()
        employees.add(employee)
    }

    fun filterBySalary() {
        print("Please Enter the First Salary you want to filter by: ")
        val firstSalary = readln().toInt()
        print("Please Enter the Second salary you want to filter by: ")
        val secondSalary = readln().toInt()
        val employeeSalary = employees.filter { it.grossSalary > firstSalary && it.grossSalary < secondSalary }
        print(employeeSalary)

    }
fun filterByGender(){
    print("Enter the gender you want to filter by (m/f): " )
    val gender = readln().toCharArray()[0]
    val employeeGender = employees.filter { it.gender == gender }
    print(employeeGender)
}
}





