package abstractfactory

class EmployeeServiceRestImpl: IEmployeeService {
    private val employees = listOf("Juan Carlos", "Moises", "Sandra")
    override fun getEmployee(): List<String> {
        return this.employees
    }
}