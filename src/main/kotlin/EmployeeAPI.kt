package ie.setu.controllers

import ie.setu.getEmployeeById
import ie.setu.models.Employee

var lastId = 0

internal fun getId(): Int {
    return lastId++
}

private fun <E> ArrayList<E>.set(index: E) {

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
        employee.employeeID = getId()
        employees.remove(employee)
    }

    fun update(employee: Employee){
        employees.set(employee.employeeID, employee)
    }

    fun create(employee: Employee) {
        employee.employeeID = getId()
        employees.add(employee)
    }

}





