package strategy

import kotlin.system.exitProcess


object StrategyMain {
    private val authProvider: AuthentificationProvider = AuthentificationProvider()
    @JvmStatic
    fun main(args: Array<String>) {
        changeAuthetificationStrategy()
        var principal: Principal? = null
        do {
            println("Please authenticate:")
            print("User: ")
            val userName: String = readln()
            print("Pasword: ")
            val password: String = readln()
            principal = authProvider.authenticate(userName, password)
            if (principal == null) {
                println("User or password invalid.")
                print("Do you want to change the authentication method? (S/N): ")
                val op: String = readln()
                if (op.equals("S", ignoreCase = true)) {
                    changeAuthetificationStrategy()
                }else{
                    println("\nSee you")
                    break
                }
            }
        } while (principal == null)
        println("\nSuccessful authentication")
        println(principal)
    }

    private fun changeAuthetificationStrategy() {
        println("Enter the type of authentication to use.")
        println("1.-OnMemory Authentication")
        println("2.-SQL Authentication")
        println("3.-XML Authentication")
        println("Your option: ")
        val op: Int = readln().toInt()
        when (op) {
            1 -> {
                authProvider.setAuthenticationStrategy(
                    OnMemoryAuthenticationProvider()
                )
                println("OnMemory Authentication Select >")
            }

            2 -> {
                authProvider.setAuthenticationStrategy(
                    SQLAuthenticationProvier()
                )
                println("SQL Authentication Select >")
            }

            3 -> {
                authProvider.setAuthenticationStrategy(
                    XMLAuthenticationProvider()
                )
                println("XML Authentication Select >")
            }

            else -> {
                println("Invalid option")
                exitProcess(1)
            }
        }
    }
}