package visitor

import org.json.JSONObject
import org.json.XML

object VisitorMain {
    @JvmStatic
    fun main(args: Array<String>) {
        var project: Project? = null
        try {
            val xmlString = VisitorMain::class.java.getResourceAsStream("Project.xml")?.bufferedReader().use { it!!.readText() }
            val jsonObject = XML.toJSONObject(xmlString)
            project = Project(jsonObject.toString())
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
        }

        // Obtener el costo total del proyecto
        val costVisitor = CostProjectVisitor()
        project?.accept(costVisitor)
        val cost: Double = costVisitor.getResult().toDouble()
        println("Total cost > $cost")

        // Obtener el precio de venta del proyecto
        val priceVisitor = PriceProjectVisitor()
        project?.accept(priceVisitor)
        val price: Double = priceVisitor.getResult().toDouble()
        println("Total price > $price")
        println("Total gain > ${price - cost}")

        // Mostrar el total a pagar por empleado
        println("\n:::::::: Pay the workers :::::::")
        val paymentVisitor = PaymentProjectVisitor()
        project?.accept(paymentVisitor)
        val result: List<EmployeePay> = paymentVisitor.getResult()
        for (pay in result) {
            println("${pay.employeeName} > ${pay.totalPay}")
        }
    }
}
